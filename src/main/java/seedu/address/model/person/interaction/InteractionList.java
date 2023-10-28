package seedu.address.model.person.interaction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents interactions as a list of {@code Interaction}.
 */
public class InteractionList {
    private final List<Interaction> interactions = new ArrayList<>();

    /**
     * Construct an empty {@code InteractionList}.
     */
    public InteractionList() {}

    /**
     * Construct an {@code InteractionList} with the given {@code Interaction}.
     *
     * @param interactions The list of interactions to feed into the {@code InteractionList}.
     */
    public InteractionList(List<Interaction> interactions) {
        this.interactions.addAll(interactions);
    }

    /**
     * Adds the given {@code Interaction} to the list.
     *
     * @param interaction The {@code Interaction} to add.
     */
    public InteractionList addInteraction(Interaction interaction) {
        this.interactions.add(interaction);
        return this;
    }

    /**
     * Adds the given InteractionList {@code InteractionList} to the list.
     * @param interactions The {@code InteractionList} to add.
     * @return
     */
    public InteractionList addInteractions(InteractionList interactions) {
        this.interactions.addAll(interactions.getInteractions());
        return this;
    }

    /**
     * Removes the {@code Interaction} at the given index.
     *
     * @param index The index of the {@code Interaction} to remove.
     */
    public void removeInteraction(int index) {
        this.interactions.remove(index);
    }

    /**
     * Returns the {@code Interaction} at the given index.
     *
     * @param index The index of the {@code Interaction} to return.
     */
    public Interaction getInteraction(int index) {
        return this.interactions.get(index);
    }

    /**
     * Returns the List of {@code Interaction}.
     */
    public List<Interaction> getInteractions() {
        return this.interactions;
    }

    /**
     * Returns a filtered list of {@code Interaction} that matches the given predicate.
     */
    public List<Interaction> getFilteredInteractions(Predicate<Interaction> predicate) {
        return this.interactions.stream()
            .filter(predicate)
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
