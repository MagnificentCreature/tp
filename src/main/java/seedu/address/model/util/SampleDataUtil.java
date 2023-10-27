package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Details;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Profession;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.interaction.Interaction;
import seedu.address.model.person.lead.Lead;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
                new Person.PersonBuilder(new Name("Alex Yeoh"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends")).withTelegram(new TelegramHandle("@Allyeo"))
                        .withProfession(new Profession("Software Engineer")).withIncome(new Income(80000))
                        .withDetails(new Details("Looking for automated solutions for project management"))
                        .withLead(new Lead("COLD")).addInteraction(new Interaction(
                                "setting next meeting", Interaction.Outcome.valueOf("INTERESTED"),
                                new Date(2023, 7, 12)
                        )).build(),
                new Person.PersonBuilder(new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends")).withTelegram(new TelegramHandle("@yuyubern"))
                        .withProfession(new Profession("Graphic Designer")).withIncome(new Income(60000))
                        .withDetails(new Details("Interested in design software with real-time collaboration features"))
                        .withLead(new Lead("HOT")).build(),
                new Person.PersonBuilder(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("neighbours")).withTelegram(new TelegramHandle("@yuyubern"))
                        .withProfession(new Profession("Graphic Designer")).withIncome(new Income(60000))
                        .withDetails(new Details("Interested in design software with real-time collaboration features"))
                        .withLead(new Lead("WARM")).addInteraction(new Interaction(
                                "willing to meet again", Interaction.Outcome.valueOf("NOT_INTERESTED"),
                                new Date(2022, 1, 11)
                        )).addInteraction(new Interaction(
                                "bought", Interaction.Outcome.valueOf("CLOSED"),
                                new Date(2022, 7, 14))
                        ).build(),
                new Person.PersonBuilder(new Name("David Li"), new Phone("91031282"),
                        new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("family")).withTelegram(new TelegramHandle("@pyonpyondav"))
                        .withProfession(new Profession("Financial Analyst")).withIncome(new Income(90000))
                        .withDetails(new Details("Wants investment tracking and portfolio management tools"))
                        .withLead(new Lead("COLD")).build(),
                new Person.PersonBuilder(new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("classmates")).withTelegram(new TelegramHandle("@yuyubern"))
                        .withProfession(new Profession("Registered nurse")).withIncome(new Income(70000))
                        .withDetails(new Details("Interested in medical equipment")).withLead(new Lead("WARM"))
                        .build(),
                new Person.PersonBuilder(new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("colleagues")).withTelegram(new TelegramHandle("@arsyadbala"))
                        .withProfession(new Profession("Digital Marketing Specialist")).withIncome(new Income(75000))
                        .withDetails(new Details("Looking for a comprehensive CRM platform with email marketing"))
                        .withLead(new Lead("WARM"))
                        .addInteraction(new Interaction(
                                "Still exploring", Interaction.Outcome.valueOf("FOLLOWUP_REQUIRED"),
                                new Date(2023, 8, 22)
                        )).build()
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an interaction set containing the list of strings given.
     */
    public static ArrayList<Interaction> getInteractionList(String... strings) {
        return Arrays.stream(strings)
                .map(Interaction::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
