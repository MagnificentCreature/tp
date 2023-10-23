package seedu.address.model.person;

/**
 * Represents an interaction with the client in the addressbook.
 */
public class Interaction {

    /**
     * Represents the outcome of the interaction.
     */
    public static enum Outcome {
        INTERESTED,
        NOT_INTERESTED,
        FOLLOWUP_REQUIRED,
        UNKNOWN;

        public static final String MESSAGE_CONSTRAINTS = "Outcome should be one of the following: "
                + "INTERESTED, NOT_INTERESTED, FOLLOWUP_REQUIRED, UNKNOWN "
                + "and it should not be blank";

        /**
        * Returns true if a given string is a valid outcome.
        */
        public static boolean isValidOutcome(String test) {
            System.out.println(test);
            return test.matches("INTERESTED|NOT_INTERESTED|FOLLOWUP_REQUIRED|UNKNOWN");
        }
    }

    public final String interactionNote;
    public final Outcome outcome;

    /**
    * Constructs a {@code Interaction} with a default LeadStatus of UNKNOWN.
    *
    * @param interactionNote A valid Interaction note.
    * @param outcome A valid outcome.
    */
    public Interaction(String interactionNote) {
        this.interactionNote = interactionNote;
        this.outcome = Outcome.UNKNOWN;
    }

    /**
    * Constructs a {@code Interaction}.
    *
    * @param interactionNote A valid Interaction note.
    * @param outcome A valid LeadStatus.
    */
    public Interaction(String interactionNote, Outcome outcome) {
        this.interactionNote = interactionNote;
        this.outcome = outcome;
    }

    public String getInteraction() {
        return this.interactionNote;
    }

    public String getOutcome() {
        return this.outcome.toString();
    }

    @Override
    public String toString() {
        return interactionNote + "\nThe outcome of this interaction is: " + outcome;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interaction)) {
            return false;
        }

        Interaction otherInteraction = (Interaction) other;
        return otherInteraction.interactionNote.equals(this.interactionNote)
                && otherInteraction.outcome.equals(this.outcome);
    }
}
