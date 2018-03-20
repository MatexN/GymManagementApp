package pl.edu.wat.wcy.isi.siecsilowni.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.edu.wat.wcy.isi.siecsilowni.controllers.base.BaseController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Data
@EqualsAndHashCode(callSuper = true)
public class GymAppController extends BaseController {

    @FXML
    private AnchorPane root;

    @FXML
    private Button editItemBtn;

    @FXML
    private Button removeItemBtn;

    @FXML
    private MenuButton userStatus;

    @FXML
    private TableView tableView;

    private RootController rootController;

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void loadCreatorsView() {
        loadView(root,"fxml/creators.fxml").getController();
    }

    public void loadGymManagementView() {
        loadView(root,"fxml/gymManagement.fxml").getController();
    }

    public void loadClientManagementView() {
        loadView(root,"fxml/clientManagement.fxml").getController();
    }

    public void loadSearchClientView() {
        loadView(root,"fxml/searchClient.fxml").getController();
    }

    @FXML
    private void signOut(ActionEvent actionEvent) {
        rootController.loadLoginView();
    }

    public void setUserName(String userName) {
        userStatus.setText(userName);
    }


    public void manageGyms(ActionEvent actionEvent) {
        loadGymManagementView();
    }

    public void searchClient(ActionEvent actionEvent) {
        loadSearchClientView();
    }

    public void manageClients(ActionEvent actionEvent) {
        loadClientManagementView();
    }

    public void showCreators(ActionEvent actionEvent) {
        loadCreatorsView();
    }

    public void changeStyle(ActionEvent actionEvent) {
        MenuItem item = (MenuItem) actionEvent.getSource();

        rootController.getRoot().getStylesheets().clear();
        rootController.getRoot().getStylesheets().add(item.getUserData().toString());

        System.out.println(item.getUserData().toString());
    }
}
