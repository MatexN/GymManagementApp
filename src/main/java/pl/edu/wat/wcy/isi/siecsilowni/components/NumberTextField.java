package pl.edu.wat.wcy.isi.siecsilowni.components;

import javafx.scene.control.TextField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NumberTextField extends TextField {

    private Integer maxLength = Integer.MAX_VALUE;

    public NumberTextField() {
        this("");
    }

    public NumberTextField(String text) {
        super(text);
        addListener();
    }

    private void addListener() {
        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                setText(newValue.replaceAll("[^\\d]", ""));
            }

            if (getText().length() > maxLength) {
                String s = getText().substring(0, maxLength);
                setText(s);
            }
        });
    }
}
