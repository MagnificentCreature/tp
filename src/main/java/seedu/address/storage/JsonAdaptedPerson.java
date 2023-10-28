package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.interaction.InteractionList;
import seedu.address.model.person.lead.Lead;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String PERSON_MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String lead;
    // private final JsonAdaptedInteractionList interactions;
    private final List<JsonAdaptedInteraction> interactions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("lead") String lead,
            @JsonProperty("interactions") List<JsonAdaptedInteraction> interactions) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.lead = lead;
        if (interactions != null) {
            this.interactions.addAll(interactions);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        if (source.getLead() == null) {
            lead = "";
        } else {
            lead = source.getLead().toString().toLowerCase();
        }
        interactions.addAll(source.getInteractions().stream()
            .map(JsonAdaptedInteraction::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        final InteractionList modelInteractions = new InteractionList(new ArrayList<>());
        for (JsonAdaptedInteraction interaction : interactions) {
            modelInteractions.addInteraction(interaction.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT,
                                            Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT,
                                            Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT,
                                            Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT,
                                            Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        Person.PersonBuilder personBuilder =
                new Person.PersonBuilder(modelName, modelPhone, modelEmail, modelAddress, modelTags);

        if (lead != null && !lead.isEmpty()) {
            final Lead modelLead = new Lead(lead);
            personBuilder = personBuilder.withLead(modelLead);
        }
        personBuilder.withInteractions(modelInteractions);
        return personBuilder.build();
    }
}
