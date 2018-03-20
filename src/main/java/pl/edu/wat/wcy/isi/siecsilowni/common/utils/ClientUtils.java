package pl.edu.wat.wcy.isi.siecsilowni.common.utils;

import pl.edu.wat.wcy.isi.siecsilowni.database.model.Client;
import pl.edu.wat.wcy.isi.siecsilowni.exceptions.SubscriptionCardException;
import pl.edu.wat.wcy.isi.siecsilowni.exceptions.TooShortPeselException;
import pl.edu.wat.wcy.isi.siecsilowni.exceptions.ValidationException;

public class ClientUtils {

    private static boolean validatePesel(String pesel) {
        return pesel != null && pesel.length() == 11;
    }



    public static void validate(Client client) throws ValidationException {
        if (!validatePesel(client.getPesel()))
            throw new TooShortPeselException();

        if (client.getSubscriptionCardStartDate() == null || Utils.isNullOrEmpty(client.getAmountOfDays()))
            throw new SubscriptionCardException();
    }

}
