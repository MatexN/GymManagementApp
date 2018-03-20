package pl.edu.wat.wcy.isi.siecsilowni.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.edu.wat.wcy.isi.siecsilowni.controllers.base.BaseController;
import pl.edu.wat.wcy.isi.siecsilowni.xml.Author;
import pl.edu.wat.wcy.isi.siecsilowni.xml.Program;
import pl.edu.wat.wcy.isi.siecsilowni.xml.XmlLoader;

import java.net.URL;
import java.util.ResourceBundle;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreatorsController extends BaseController {

    @FXML
    private Label organization;

    @FXML
    private Label author;

    @FXML
    private Label version;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Program program = XmlLoader.load();

        Author a = program.getAuthor();

        author.setText(a.getFirstName() + " " + a.getSecondName());
        organization.setText(a.getOrganization());
        version.setText(program.getVersion());
    }
}
