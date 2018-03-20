package pl.edu.wat.wcy.isi.siecsilowni.dialog;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.edu.wat.wcy.isi.siecsilowni.GymApp;
import pl.edu.wat.wcy.isi.siecsilowni.common.Handler;
import pl.edu.wat.wcy.isi.siecsilowni.common.utils.ClientUtils;
import pl.edu.wat.wcy.isi.siecsilowni.common.utils.GymUtils;
import pl.edu.wat.wcy.isi.siecsilowni.database.model.Gym;
import pl.edu.wat.wcy.isi.siecsilowni.events.ClientValidationErrorEvent;
import pl.edu.wat.wcy.isi.siecsilowni.events.GymValidationErrorEvent;
import pl.edu.wat.wcy.isi.siecsilowni.exceptions.ValidationException;

import java.util.ResourceBundle;

public class GymDialog extends CustomDialog<Gym> {

    private TextField name;
    private TextField address;
    private TextField email;
    private TextField phone;

    private GymDialog(Gym gym, String title) {
        super(title);

        setData(gym);

        name.setPrefWidth(400);

        grid.add(new Label(GymApp.resource.getString("GymManagementController.Name")), 0, 0);
        grid.add(name, 1, 0);

        grid.add(new Label(GymApp.resource.getString("GymManagementController.Address")), 0, 1);
        grid.add(address, 1, 1);

        grid.add(new Label(GymApp.resource.getString("GymManagementController.Email")), 0, 2);
        grid.add(email, 1, 2);

        grid.add(new Label(GymApp.resource.getString("GymManagementController.Phone")), 0, 3);
        grid.add(phone, 1, 3);


        Platform.runLater(name::requestFocus);

        name.addEventHandler(GymValidationErrorEvent.GYM_VALIDATION_ERROR_EVENT, e -> {
            getErrorDialog(e.getMessage());
        });

        final Button btOk = (Button) getDialogPane().lookupButton(buttonType);
        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    Gym g = new Gym(null, name.getText(), address.getText(), email.getText(), phone.getText());

                    try {
                        GymUtils.validate(g);
                    } catch (ValidationException e) {
                        event.consume();

                        name.fireEvent(new GymValidationErrorEvent(e.getMessage()));
                    }
                }
        );

        setResultConverter(dialogButton -> {
            if (dialogButton == buttonType) {
                return new Gym(null, name.getText(), address.getText(), email.getText(), phone.getText());
            }
            return null;
        });

        dialogResult = showAndWait();
    }

    public GymDialog(Gym gym, String title, Handler<Gym> handler) {
        this(gym, title);
        dialogResult.ifPresent(handler::handle);
    }

    private void setData(Gym gym) {
        if (gym == null) {
            name = new TextField();
            address = new TextField();
            email = new TextField();
            phone = new TextField();
        } else {
            name = new TextField(gym.getName());
            address = new TextField(gym.getAddress());
            email = new TextField(gym.getEmail());
            phone = new TextField(gym.getPhone());
        }
    }
}
