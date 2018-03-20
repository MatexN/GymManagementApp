package pl.edu.wat.wcy.isi.siecsilowni.dialog;


import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.edu.wat.wcy.isi.siecsilowni.GymApp;

import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class CustomDialog<T> extends Dialog<T> {

    protected ButtonType buttonType;

    protected GridPane grid;

    protected Optional<T> dialogResult;

    protected void getErrorDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(GymApp.resource.getString("errormessage.tittle"));
        alert.setHeaderText(GymApp.resource.getString("errormessage.text"));
        alert.setContentText(text);

        alert.showAndWait();

    }

    public CustomDialog(String title) {
        setTitle(title);

        buttonType = new ButtonType(GymApp.resource.getString("button.ok"), ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(GymApp.resource.getString("button.cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(buttonType, cancel);

        grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        getDialogPane().setContent(grid);
    }
}
