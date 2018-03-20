package pl.edu.wat.wcy.isi.siecsilowni.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pl.edu.wat.wcy.isi.siecsilowni.GymApp;
import pl.edu.wat.wcy.isi.siecsilowni.components.NumberTextField;
import pl.edu.wat.wcy.isi.siecsilowni.controllers.base.BaseController;
import pl.edu.wat.wcy.isi.siecsilowni.database.queries.ClientQueries;
import pl.edu.wat.wcy.isi.siecsilowni.events.SearchSubmitEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchClientController extends BaseController {

    @FXML
    private Button searchBtn;

    @FXML
    private Label resultLabel;

    @FXML
    private NumberTextField peselField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchBtn.addEventHandler(SearchSubmitEvent.SEARCH_SUBMIT_EVENT, event -> {
            if (!searchBtn.isDisabled()) {
                searchBtn.fire();
            }
        });
    }

    public void searchClientByPesel(ActionEvent actionEvent) {
        ClientQueries.findClientByPesel(peselField.getText(), client -> {

            resultLabel.setText(client == null ?
                    GymApp.resource.getString("searchresult.noclient")
                    : client.hasValidSubscriptionCard());
        }, () -> {
            resultLabel.setText(GymApp.resource.getString("searchresult.error"));
        });

    }

    public void enterPressed(ActionEvent actionEvent) {
        searchBtn.fireEvent(new SearchSubmitEvent());
    }
}
