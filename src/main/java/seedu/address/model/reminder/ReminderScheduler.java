package seedu.address.model.reminder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;

/**
 * Represents a thread that manages the reminders.
 */
public class ReminderScheduler extends Thread {
    private static final long FREQUENCY = 1; // in minutes

    private final Model model;
    private final UniqueReminderList reminderList = UniqueReminderList.getInstance();
    private final Object mutex;

    private Logger logger = LogsCenter.getLogger(ReminderScheduler.class);

    /**
     * Creates a ReminderManager object.
     *
     * @param taskQueue
     * @param mutex
     */
    public ReminderScheduler(Model model, Object mutex) {
        this.model = model;
        this.mutex = mutex;
    }

    /**
     * Starts the ReminderManager thread.
     */
    public void start() {
        super.start();

        //Add all reminders to the reminderList
        //Note: This could be done in JsonAdaptedInteractions when loading the data from the file
        //But it is done here instead to adhere to Seperation of Concerns principle

        logger.info(model.getAddressBook().getPersonList().get(1).getInteractions().toString());
        model.getAddressBook().getPersonList().forEach(person -> {
            person.getInteractions().forEach(interaction -> {
                reminderList.add(person, interaction);
            });
        });

        // Start the scheduler to wake up the ReminderManager occasionally
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            synchronized (mutex) {
                mutex.notifyAll();
            }
        }, 0, FREQUENCY, TimeUnit.MINUTES); // Wakes up every 10 minutes
    }

    @Override
    public void run() {
        while (true) {
            synchronized (mutex) {
                while (reminderList.isEmpty()) {
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                long timeUntilNextReminder = reminderList.getEarliestReminderTime();
                // timeUntilNextReminder should always be positive, unless the reminderList is empty,
                // and if it is empty, the while loop above should have caught it
                assert timeUntilNextReminder >= 0;

                logger.info("Time until next reminder update: " + timeUntilNextReminder);

                if (timeUntilNextReminder > FREQUENCY * 60 * 1000) {
                    try {
                        // Wait until it's time for the next reminder
                        mutex.wait(timeUntilNextReminder);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    reminderList.removeOutdatedReminders();
                    // TODO: Update the reminderList to the GUI
                }
            }
        }
    }
}
