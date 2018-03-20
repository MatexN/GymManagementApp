package pl.edu.wat.wcy.isi.siecsilowni.events;

import javafx.event.Event;
import javafx.event.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterSubmitEvent extends Event {
    public static final EventType<RegisterSubmitEvent> REGISTER_SUBMIT_EVENT =
            new EventType<>(Event.ANY, "REGISTER_SUBMIT_EVENT");

    public RegisterSubmitEvent() {
        super(REGISTER_SUBMIT_EVENT);
    }
}