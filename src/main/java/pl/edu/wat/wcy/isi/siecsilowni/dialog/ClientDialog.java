package pl.edu.wat.wcy.isi.siecsilowni.dialog;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import pl.edu.wat.wcy.isi.siecsilowni.GymApp;
import pl.edu.wat.wcy.isi.siecsilowni.common.Handler;
import pl.edu.wat.wcy.isi.siecsilowni.common.utils.ClientUtils;
import pl.edu.wat.wcy.isi.siecsilowni.components.NumberTextField;
import pl.edu.wat.wcy.isi.siecsilowni.database.model.Client;
import pl.edu.wat.wcy.isi.siecsilowni.events.ClientValidationErrorEvent;
import pl.edu.wat.wcy.isi.siecsilowni.exceptions.ValidationException;

public class ClientDialog extends CustomDialog<Client> {

    private TextField firstName;
    private TextField secondName;
    private NumberTextField pesel;
    private DatePicker subscriptionCardStartDate;
    private NumberTextField amountOfDays;

    private ClientDialog(Client client, String title) {
        super(title);

        setData(client);

        pesel.setMaxLength(11);


        firstName.setPrefWidth(400);
        subscriptionCardStartDate.setPrefWidth(400);

        grid.add(new Label(GymApp.resource.getString("ClientManagementController.FirstName")), 0, 0);
        grid.add(firstName, 1, 0);

        grid.add(new Label(GymApp.resource.getString("ClientManagementController.SecondName")), 0, 1);
        grid.add(secondName, 1, 1);

        grid.add(new Label(GymApp.resource.getString("ClientManagementController.PESEL")), 0, 2);
        grid.add(pesel, 1, 2);

        grid.add(new Label(GymApp.resource.getString("ClientManagementController.SubscriptionCardStartDate")), 0, 3);
        grid.add(subscriptionCardStartDate, 1, 3);

        grid.add(new Label(GymApp.resource.getString("ClientManagementController.AmountOfDays")), 0, 4);
        grid.add(amountOfDays, 1, 4);


        Platform.runLater(firstName::requestFocus);

        firstName.addEventHandler(ClientValidationErrorEvent.CLIENT_VALIDATION_ERROR_EVENT, e -> {
            getErrorDialog(e.getMessage());
        });

        final Button btOk = (Button) getDialogPane().lookupButton(buttonType);

        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    Client c = new Client(null, firstName.getText(), secondName.getText(), pesel.getText(), subscriptionCardStartDate.getValue(), amountOfDays.getText());

                    try {
                        ClientUtils.validate(c);
                    } catch (ValidationException e) {
                        event.consume();

                        firstName.fireEvent(new ClientValidationErrorEvent(e.getMessage()));
                    }
                }
        );

        setResultConverter(dialogButton -> {

            if (dialogButton == buttonType) {
                return new Client(null, firstName.getText(), secondName.getText(), pesel.getText(), subscriptionCardStartDate.getValue(), amountOfDays.getText());
            }

            return null;
        });

        dialogResult = showAndWait();
    }

    public ClientDialog(Client client, String title, Handler<Client> handler) {
        this(client, title);
        dialogResult.ifPresent(handler::handle);
    }

    private void setData(Client client) {
        if (client == null) {
            firstName = new TextField();
            secondName = new TextField();
            pesel = new NumberTextField();
            subscriptionCardStartDate = new DatePicker();
            amountOfDays = new NumberTextField();
        } else {
            firstName = new TextField(client.getFirstName());
            secondName = new TextField(client.getSecondName());
            pesel = new NumberTextField(client.getPesel());
            subscriptionCardStartDate = new DatePicker(client.getSubscriptionCardStartDate());
            amountOfDays = new NumberTextField(client.getAmountOfDays());
        }
    }
}
