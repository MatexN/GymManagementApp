package pl.edu.wat.wcy.isi.siecsilowni.exceptions;

import pl.edu.wat.wcy.isi.siecsilowni.GymApp;

public class WrongEmailFormatException extends ValidationException {

    public WrongEmailFormatException() {
        super(GymApp.resource.getString("exceptions.WrongEmailFormatException"));
    }
}
