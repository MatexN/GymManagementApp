package pl.edu.wat.wcy.isi.siecsilowni.events;

import javafx.event.Event;
import javafx.event.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchSubmitEvent extends Event {
    public static final EventType<SearchSubmitEvent> SEARCH_SUBMIT_EVENT =
            new EventType<>(Event.ANY, "SEARCH_SUBMIT_EVENT");

    public SearchSubmitEvent() {
        super(SEARCH_SUBMIT_EVENT);
    }
}