package pl.edu.wat.wcy.isi.siecsilowni.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.edu.wat.wcy.isi.siecsilowni.controllers.base.BaseController;
import pl.edu.wat.wcy.isi.siecsilowni.database.model.User;
import pl.edu.wat.wcy.isi.siecsilowni.database.operations.InsertRecord;
import pl.edu.wat.wcy.isi.siecsilowni.events.RegisterSubmitEvent;

import java.net.URL;
import java.util.ResourceBundle;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterController extends BaseController {

    @FXML
    private Button registerBtn;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatedPasswordField;

    @FXML
    private GridPane root;

    private RootController rootController;

    @FXML
    private Label errorLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerBtn.addEventHandler(RegisterSubmitEvent.REGISTER_SUBMIT_EVENT, event -> {
            if (!registerBtn.isDisabled()) {
                registerBtn.fire();
            }
        });
    }

    @FXML
    private void switchToLogin(ActionEvent actionEvent) {
        lockNodeOperation((Node) actionEvent.getSource(),
                () -> {
                    rootController.loadLoginView();
                    Platform.runLater(() -> ((Node) actionEvent.getSource()).setDisable(false));
                }
        );
    }

    @FXML
    private void register(ActionEvent actionEvent) {
        lockNodeOperation((Node) actionEvent.getSource(), () -> {

            User user = new User(userNameField.getText().trim(), passwordField.getText());

            if (user.isCorrect(repeatedPasswordField.getText())) {
                new InsertRecord<User>().insert(user,
                        () -> {
                            rootController.loadPanelView(user.getUserName());
                        },
                        () -> {
                            Platform.runLater(() -> {
                                playVisibleTimeline(errorLabel.visibleProperty());
                                System.out.println("fail");
                            });
                        }
                );
            } else {
                playVisibleTimeline(errorLabel.visibleProperty());
            }

            Platform.runLater(() -> ((Node) actionEvent.getSource()).setDisable(false));
        });
    }

    public void enterPressed(ActionEvent actionEvent) {
        registerBtn.fireEvent(new RegisterSubmitEvent());
    }
}
