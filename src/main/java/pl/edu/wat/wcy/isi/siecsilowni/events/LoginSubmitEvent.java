package pl.edu.wat.wcy.isi.siecsilowni.events;

import javafx.event.Event;
import javafx.event.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginSubmitEvent extends Event {
    public static final EventType<LoginSubmitEvent> LOGIN_SUBMIT_EVENT =
            new EventType<>(Event.ANY, "LOGIN_SUBMIT_EVENT");

    public LoginSubmitEvent() {
        super(LOGIN_SUBMIT_EVENT);
    }
}