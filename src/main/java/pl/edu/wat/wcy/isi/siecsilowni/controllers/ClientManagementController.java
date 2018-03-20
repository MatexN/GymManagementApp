package pl.edu.wat.wcy.isi.siecsilowni.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edu.wat.wcy.isi.siecsilowni.GymApp;
import pl.edu.wat.wcy.isi.siecsilowni.controllers.base.BaseController;
import pl.edu.wat.wcy.isi.siecsilowni.database.operations.DeleteRecord;
import pl.edu.wat.wcy.isi.siecsilowni.database.operations.InsertRecord;
import pl.edu.wat.wcy.isi.siecsilowni.database.model.Client;
import pl.edu.wat.wcy.isi.siecsilowni.database.model.Gym;
import pl.edu.wat.wcy.isi.siecsilowni.database.queries.ClientQueries;
import pl.edu.wat.wcy.isi.siecsilowni.database.operations.UpdateRecord;
import pl.edu.wat.wcy.isi.siecsilowni.dialog.ClientDialog;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientManagementController extends BaseController {

    @FXML
    private Button removeItemBtn;

    @FXML
    private Button editItemBtn;

    @FXML
    private TableView tableView;

    private TableColumn createTableColumn(String text, String property) {
        TableColumn col = new TableColumn(text);
        final int AMOUNT_OF_COLS = 5;
        col.prefWidthProperty().bind(tableView.widthProperty().divide(AMOUNT_OF_COLS));
        col.setCellValueFactory(new PropertyValueFactory<Gym, String>(property));
        return col;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        TableColumn nameCol = createTableColumn(resources.getString("ClientManagementController.FirstName"), "firstName");
        TableColumn addressCol = createTableColumn(resources.getString("ClientManagementController.SecondName"), "secondName");
        TableColumn emailCol = createTableColumn(resources.getString("ClientManagementController.PESEL"), "pesel");
        TableColumn phoneCol = createTableColumn(resources.getString("ClientManagementController.SubscriptionCardStartDate"), "subscriptionCardStartDate");
        TableColumn phoneCol1 = createTableColumn(resources.getString("ClientManagementController.AmountOfDays"), "amountOfDays");

        tableView.getColumns().addAll(nameCol, addressCol, emailCol, phoneCol, phoneCol1);

        ClientQueries.getClients(clients -> Platform.runLater(() -> {
            tableView.setItems(FXCollections.observableArrayList(clients));
            tableView.requestFocus();
            tableView.getSelectionModel().select(0);
            tableView.getFocusModel().focus(0);
        }));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        });

        editItemBtn.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        removeItemBtn.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    private void newItem(ActionEvent actionEvent) {
        new ClientDialog(new Client(), GymApp.resource.getString("formula.save"), c -> {
            tableView.getItems().add(c);

            new InsertRecord<Client>().insert(c, () -> {
                        System.out.println("sukces");
                    },
                    () -> {
                        System.out.println("fail");
                    });
        });
    }

    @FXML
    private void editItem(ActionEvent actionEvent) {
        Client client = (Client) tableView.getSelectionModel().getSelectedItem();
        final int index = tableView.getItems().indexOf(client);

        new ClientDialog(client, GymApp.resource.getString("formula.save"), c -> {
            tableView.getItems().set(index, c);
            c.setId(client.getId());

            new UpdateRecord<Client>().update(c,
                    () -> {
                        System.out.println("sukces");
                    },
                    () -> {
                        System.out.println("fail");
                    });
        });
    }

    @FXML
    private void removeItem(ActionEvent actionEvent) {
        Client client = (Client) tableView.getSelectionModel().getSelectedItem();

        final int index = tableView.getItems().indexOf(client);
        tableView.getItems().remove(index);

        new DeleteRecord<Client>().delete(client, () -> {
                    System.out.println("sukces");
                },
                () -> {
                    System.out.println("fail");
                });
    }
}
