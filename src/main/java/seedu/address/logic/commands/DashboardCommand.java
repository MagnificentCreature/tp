package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.interaction.Interaction;

/**
 * Shows the dashboard containing statistics of the address book.
 */
public class DashboardCommand extends Command {

    public static final String COMMAND_WORD = "dashboard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the dashboard. ";

    public static final String MESSAGE_SUCCESS = "DASHBOARD";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        ObservableList<Person> personList = addressBook.getPersonList();

        int interactionCount = getInteractionCount(personList);

        int interestedInteractionsCount = getSpecifiedOutcomeCount(personList, Interaction.Outcome.INTERESTED);

        int notInterestedInteractionsCount = getSpecifiedOutcomeCount(personList, Interaction.Outcome.NOT_INTERESTED);

        double interestedPercentage = (double) interestedInteractionsCount / interactionCount * 100;
        double notInterestedPercentage = (double) notInterestedInteractionsCount / interactionCount * 100;

        // Might want to refactor this for flexibility. Left as is for now.
        String message = "Total number of interactions: "
                + interactionCount + "\n"
                + "Percentage of interested interactions: "
                + String.format("%.2f", interestedPercentage) + "%\n"
                + "Percentage of not interested interactions: "
                + String.format("%.2f", notInterestedPercentage) + "%\n";

        return new CommandResult(MESSAGE_SUCCESS + "\n" + message);
    }

    private int getSpecifiedOutcomeCount(ObservableList<Person> personList, Interaction.Outcome outcome) {
        return personList.stream()
                .map(person ->
                        person.getFilteredInteraction(i -> i.isOutcome(outcome)).size())
                .reduce(0, Integer::sum);
    }

    private int getInteractionCount(ObservableList<Person> personList) {
        return personList.stream()
                .mapToInt(person -> person.getInteractions().size())
                .sum();
    }
}
