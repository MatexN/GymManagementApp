package pl.edu.wat.wcy.isi.siecsilowni.exceptions;

import pl.edu.wat.wcy.isi.siecsilowni.GymApp;

public class NoNameException extends ValidationException {
    public NoNameException() {
        super(GymApp.resource.getString("exceptions.NoNameException"));

    }
}
