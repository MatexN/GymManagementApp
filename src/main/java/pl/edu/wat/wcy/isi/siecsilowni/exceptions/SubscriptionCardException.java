package pl.edu.wat.wcy.isi.siecsilowni.exceptions;

import pl.edu.wat.wcy.isi.siecsilowni.GymApp;

public class SubscriptionCardException extends ValidationException {
    public SubscriptionCardException() {
        super(GymApp.resource.getString("exceptions.SubscriptionCardException"));
    }
}
