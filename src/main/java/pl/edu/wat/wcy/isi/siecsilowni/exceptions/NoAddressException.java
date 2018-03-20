package pl.edu.wat.wcy.isi.siecsilowni.exceptions;

import pl.edu.wat.wcy.isi.siecsilowni.GymApp;

public class NoAddressException extends ValidationException {
    public NoAddressException() {
        super(GymApp.resource.getString("exceptions.NoAddressException"));
    }
}
