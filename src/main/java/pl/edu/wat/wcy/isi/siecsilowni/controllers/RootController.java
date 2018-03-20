package pl.edu.wat.wcy.isi.siecsilowni.controllers;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.edu.wat.wcy.isi.siecsilowni.controllers.base.BaseController;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Data
@EqualsAndHashCode(callSuper = true)
public class RootController extends BaseController {

    @FXML
    private StackPane root;

    private Stage stage;

    private LoginController loginController;

    public void setStage(Stage stage) {
        this.stage = stage;

        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private FXMLLoader loadView(String path) {
        return loadView(root, path);
    }

    public void loadLoginView() {
        loginController = loadView("fxml/login.fxml").getController();
        loginController.setRootController(this);
        loginController.i18n(stage.titleProperty(), "app.name");
    }

    public void loadRegisterView() {
        RegisterController controller = loadView("fxml/register.fxml").getController();
        controller.setRootController(this);
    }

    public void loadPanelView(String userName) {
        GymAppController controller = loadView("fxml/panel.fxml").getController();
        controller.setRootController(this);
        controller.setUserName(userName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}