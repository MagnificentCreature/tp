package seedu.address.model.reminder.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateReminderException extends RuntimeException {
    public DuplicateReminderException() {
        super("Operation would result in duplicate reminder");
    }
}

