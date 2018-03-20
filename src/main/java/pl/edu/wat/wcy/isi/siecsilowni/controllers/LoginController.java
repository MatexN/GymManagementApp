package pl.edu.wat.wcy.isi.siecsilowni.controllers;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.edu.wat.wcy.isi.siecsilowni.GymApp;
import pl.edu.wat.wcy.isi.siecsilowni.components.ChoiceBoxLabel;
import pl.edu.wat.wcy.isi.siecsilowni.controllers.base.BaseController;
import pl.edu.wat.wcy.isi.siecsilowni.database.queries.UserQueries;
import pl.edu.wat.wcy.isi.siecsilowni.events.LoginSubmitEvent;
import pl.edu.wat.wcy.isi.siecsilowni.rest.TimeAndDate;
import pl.edu.wat.wcy.isi.siecsilowni.rest.TimeUser;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginController extends BaseController {

    @FXML
    private Hyperlink register;

    @FXML
    private GridPane root;

    @FXML
    private Label timeLabel;

    @FXML
    private Label time;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameField;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private Text welcome;

    @FXML
    private Label userName;

    @FXML
    private Label password;

    @FXML
    private Button signIn;

    @FXML
    private Label errorLabel;

    private RootController rootController;

    private void updateLang(String language) {
        Locale.setDefault(new Locale(language));
        GymApp.resource = ResourceBundle.getBundle("messages",Locale.getDefault());
        GymApp.lang.setValue(language);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        GymApp.lang.addListener((observable, oldValue, newValue) -> GymApp.logger.info("zmiana jezyka" + newValue));

        updateLang(GymApp.lang.get());

        i18n(welcome.textProperty(), (String) welcome.getUserData());
        i18n(register.textProperty(), (String) register.getUserData());

        i18n(userName.textProperty(), (String) userName.getUserData());
        i18n(password.textProperty(), (String) password.getUserData());
        i18n(signIn.textProperty(), (String) signIn.getUserData());
        i18n(errorLabel.textProperty(), (String) errorLabel.getUserData());
        i18n(timeLabel.textProperty(), (String) timeLabel.getUserData());

        for (Object o : choiceBox.getItems()) {
            ChoiceBoxLabel label = (ChoiceBoxLabel) o;
            if (label.getUserData().equals(GymApp.lang.get())) {
                choiceBox.setValue(label);
            }
        }

        choiceBox.setOnAction(event -> {
            ChoiceBoxLabel label = (ChoiceBoxLabel) choiceBox.getSelectionModel().getSelectedItem();
            updateLang(label.getUserData().toString());
        });

        Task<TimeAndDate> task = new TimeUser().getTime();
        task.setOnSucceeded(event -> time.setText(task.getValue().toString()));
        Executors.newCachedThreadPool().submit(task);

        signIn.addEventHandler(LoginSubmitEvent.LOGIN_SUBMIT_EVENT, event -> {
            if (!signIn.isDisabled())
                signIn.fire();
        });
    }

    public void i18n(StringProperty property, String key) {
        property.bind(Bindings.createStringBinding(() -> GymApp.resource.getString(key), GymApp.lang));
    }

    @FXML
    private void login(ActionEvent actionEvent) {
        lockNodeOperation((Node) actionEvent.getSource(), () -> UserQueries
                .getUser(userNameField.getText(), user -> {
                    if (user == null || !user.getPassword().equals(passwordField.getText())) {
                        playVisibleTimeline(errorLabel.visibleProperty());
                    } else {
                        rootController.loadPanelView(user.getUserName());
                    }
                    Platform.runLater(() -> ((Node) actionEvent.getSource()).setDisable(false));
                })
        );
    }

    @FXML
    private void switchToRegister(ActionEvent actionEvent) {
        lockNodeOperation((Node) actionEvent.getSource(), () -> {
                    rootController.loadRegisterView();
                    Platform.runLater(() -> ((Node) actionEvent.getSource()).setDisable(false));
                }
        );
    }

    public void enterPressed(ActionEvent actionEvent) {
        signIn.fireEvent(new LoginSubmitEvent());
    }
}
