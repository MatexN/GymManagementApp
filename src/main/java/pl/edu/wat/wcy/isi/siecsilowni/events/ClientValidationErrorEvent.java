package pl.edu.wat.wcy.isi.siecsilowni.events;

import javafx.event.Event;
import javafx.event.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientValidationErrorEvent extends Event {

    public static final EventType<ClientValidationErrorEvent> CLIENT_VALIDATION_ERROR_EVENT =
            new EventType<>(Event.ANY, "CLIENT_VALIDATION_ERROR_EVENT");

    private String message;

    public ClientValidationErrorEvent(String message) {
        super(CLIENT_VALIDATION_ERROR_EVENT);

        this.message = message;
    }

}
