package pl.edu.wat.wcy.isi.siecsilowni.exceptions;

import pl.edu.wat.wcy.isi.siecsilowni.GymApp;

public class TooShortPeselException extends ValidationException {
    public TooShortPeselException() {
        super(GymApp.resource.getString("exceptions.TooShortPeselException"));
    }
}
