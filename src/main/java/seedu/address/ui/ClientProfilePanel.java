package seedu.address.ui;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.person.Income;
import seedu.address.model.person.Interaction;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profession;
import seedu.address.model.person.TelegramHandle;

/**
 * A UI component that displays the profile of a {@code Client}.
 */
public class ClientProfilePanel extends UiPart<Region> {
    private static final String FXML = "ClientProfilePanel.fxml";

    public final Person client;

    @FXML
    private Label name;
    @FXML
    private Text phone;
    @FXML
    private Text telegram;
    @FXML
    private Text address;
    @FXML
    private Text email;
    @FXML
    private Text profession;
    @FXML
    private Text income;
    @FXML
    private FlowPane tags;
    @FXML
    private ListView<Interaction> interactionsList;

    /**
     * Creates a {@code ClientProfilePanel} with the given {@code Person}.
     */
    public ClientProfilePanel(Person client) {
        super(FXML);
        this.client = client;
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        address.setText(client.getAddress().value);
        email.setText(client.getEmail().value);
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        // optional fields
        TelegramHandle t = client.getTelegram();
        if (t != null) {
            telegram.setText(t.value);
        }

        Profession p = client.getProfession();
        if (p != null) {
            profession.setText(p.value);
        }

        Income i = client.getIncome();
        if (i != null) {
            income.setText("$" + i.value.toString());
        }

        // interactions
        ObservableList<Interaction> interactions = FXCollections.observableArrayList(client.getInteractions());
        interactionsList.setItems(interactions);
        interactionsList.setCellFactory(listView -> new InteractionListViewCell());
    }

    static class InteractionListViewCell extends ListCell<Interaction> {
        @Override
        protected void updateItem(Interaction interaction, boolean empty) {
            super.updateItem(interaction, empty);

            if (empty || interaction == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InteractionEntry(interaction).getRoot());
            }
        }
    }
}
