package pl.edu.wat.wcy.isi.siecsilowni.events;

import javafx.event.Event;
import javafx.event.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GymValidationErrorEvent extends Event {

    public static final EventType<GymValidationErrorEvent> GYM_VALIDATION_ERROR_EVENT =
            new EventType<>(Event.ANY, "GYM_VALIDATION_ERROR_EVENT");

    private String message;

    public GymValidationErrorEvent(String message) {
        super(GYM_VALIDATION_ERROR_EVENT);

        this.message = message;
    }
}
