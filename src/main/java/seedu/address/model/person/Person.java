package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.lead.Lead;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    // Optional fields
    private Lead lead;
    private final TelegramHandle telegram;
    private final Profession profession;
    private final Income income;
    private final Details details;
    private final Set<Interaction> interactions = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);

        this.lead = null;
        this.telegram = null;
        this.profession = null;
        this.income = null;
        this.details = null;
    }

    /**
     * Same constructor but with optional fields.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
        TelegramHandle telegram, Profession profession, Income income, Details details, Lead lead) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);

        this.telegram = telegram;
        this.profession = profession;
        this.income = income;
        this.details = details;
        this.lead = lead;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Lead getLead() {
        return lead;
    }

    public TelegramHandle getTelegram() {
        return telegram;
    }

    public Profession getProfession() {
        return profession;
    }

    public Income getIncome() {
        return income;
    }

    public Details getDetails() {
        return details;
    }

    public Set<Interaction> getInteractions() {
        return interactions;
    }

    /**
     * Adds an interaction to the person.
     * @param interactions the set of interaction to be added
     * @return the updated set of interactions
     */
    public Set<Interaction> addInteractions(Set<Interaction> interactions) {
        this.interactions.addAll(interactions);
        return this.interactions;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Changes the lead potential of the client.
     */
    public void setLead(Lead newLead) {
        this.lead = newLead;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
