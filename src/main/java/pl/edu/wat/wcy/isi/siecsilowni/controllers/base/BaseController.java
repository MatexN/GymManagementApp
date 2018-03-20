package pl.edu.wat.wcy.isi.siecsilowni.controllers.base;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import pl.edu.wat.wcy.isi.siecsilowni.common.Execute;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public abstract class BaseController implements Initializable {

    protected final void playVisibleTimeline(BooleanProperty property) {
        Properties properties = new Properties();
        new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(property, true)),
                new KeyFrame(new Duration(1500), new KeyValue(property, false))
        ).play();
    }

    protected final void lockNodeOperation(Node node, Execute execute) {
        node.setDisable(true);
        execute.execute();
    }

    protected final FXMLLoader loadView(Pane pane, String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(path),
                ResourceBundle.getBundle("messages", Locale.getDefault()));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pane.getChildren().clear();
        pane.getChildren().add(parent);

        return fxmlLoader;
    }
}
