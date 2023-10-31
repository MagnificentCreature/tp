package seedu.address.model.reminder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents a Reminder
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder implements Comparable<LocalDate> {
    private final Person person;
    private final LocalDate followUpDate;

    /**
     * Represents a Reminder in the list of reminders
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Reminder(Person person) {
        this.person = person;
        this.followUpDate = person.getFollowUpDate();
    }

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public long getDueTime() {
        return ChronoUnit.MILLIS.between(LocalDateTime.now(), followUpDate.atStartOfDay());
    }

    /**
     * Returns true if both reminders have the same identity and data fields.
     * This defines a stronger notion of equality between two reminders.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Reminder otherReminder = (Reminder) other;
        return person.isSamePerson(otherReminder.person)
                && followUpDate.equals(otherReminder.followUpDate);
    }

    @Override
    public int compareTo(LocalDate date) {
        return this.followUpDate.isAfter(followUpDate) ? 1 : -1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person", person)
                .add("followUpDate", followUpDate)
                .toString();
    }
}
