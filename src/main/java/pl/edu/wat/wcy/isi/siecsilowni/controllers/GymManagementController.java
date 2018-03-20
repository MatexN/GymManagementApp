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
import pl.edu.wat.wcy.isi.siecsilowni.database.model.Gym;
import pl.edu.wat.wcy.isi.siecsilowni.database.queries.GymQueries;
import pl.edu.wat.wcy.isi.siecsilowni.database.operations.UpdateRecord;
import pl.edu.wat.wcy.isi.siecsilowni.dialog.GymDialog;

import java.net.URL;
import java.util.ResourceBundle;

public class GymManagementController extends BaseController {

    @FXML
    private Button removeItemBtn;

    @FXML
    private Button editItemBtn;

    @FXML
    private TableView tableView;

    private TableColumn createTableColumn(String text, String property) {
        TableColumn col = new TableColumn(text);
        final int AMOUNT_OF_COLS = 4;
        col.prefWidthProperty().bind(tableView.widthProperty().divide(AMOUNT_OF_COLS));
        col.setCellValueFactory(new PropertyValueFactory<Gym, String>(property));
        return col;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn nameCol = createTableColumn(resources.getString("GymManagementController.Name"), "name");
        TableColumn addressCol = createTableColumn(resources.getString("GymManagementController.Address"), "address");
        TableColumn emailCol = createTableColumn(resources.getString("GymManagementController.Email"), "email");
        TableColumn phoneCol = createTableColumn(resources.getString("GymManagementController.Phone"), "phone");

        tableView.getColumns().addAll(nameCol, addressCol, emailCol, phoneCol);

        GymQueries.getGyms(gyms -> Platform.runLater(() -> {
            tableView.setItems(FXCollections.observableArrayList(gyms));
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
        new GymDialog(new Gym(), GymApp.resource.getString("formula.save"), g -> {

            tableView.getItems().add(g);

            new InsertRecord<Gym>().insert(g, () -> {
                        System.out.println("sukces");
                    },
                    () -> {
                        System.out.println("fail");
                    });
        });
    }

    @FXML
    private void editItem(ActionEvent actionEvent) {
        Gym gym = (Gym) tableView.getSelectionModel().getSelectedItem();
        final int index = tableView.getItems().indexOf(gym);


        new GymDialog(gym, GymApp.resource.getString("formula.save"), g -> {
            tableView.getItems().set(index, g);
            g.setId(gym.getId());

            new UpdateRecord<Gym>().update(g,
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
        Gym gym = (Gym) tableView.getSelectionModel().getSelectedItem();

        final int index = tableView.getItems().indexOf(gym);
        tableView.getItems().remove(index);

        new DeleteRecord<Gym>().delete(gym, () -> {
                    System.out.println("sukces");
                },
                () -> {
                    System.out.println("fail");
                });
    }
}
