package pl.edu.wat.wcy.isi.siecsilowni;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import pl.edu.wat.wcy.isi.siecsilowni.controllers.RootController;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class GymApp extends Application {

    public final static Logger logger = Logger.getLogger(GymApp.class);

    public static StringProperty lang = new SimpleStringProperty();
    public static ResourceBundle resource = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("properties/config.properties"));

        lang.set("pl");

        Locale.setDefault(new Locale(lang.get()));

        resource = ResourceBundle.getBundle(properties.getProperty("resourcebundle.name"), Locale.getDefault());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(properties.getProperty("mainfile.name")), resource);

        Parent parent = fxmlLoader.load();

        RootController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);

        controller.loadLoginView();

        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("images/logo.png")));

        primaryStage.setScene(new Scene(parent,
                Integer.parseInt(properties.getProperty("program.width")),
                Integer.parseInt(properties.getProperty("program.height"))));
        primaryStage.show();
    }
}
