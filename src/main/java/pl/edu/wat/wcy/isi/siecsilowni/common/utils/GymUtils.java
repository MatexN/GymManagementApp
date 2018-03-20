package pl.edu.wat.wcy.isi.siecsilowni.common.utils;

import pl.edu.wat.wcy.isi.siecsilowni.database.model.Gym;
import pl.edu.wat.wcy.isi.siecsilowni.exceptions.NoAddressException;
import pl.edu.wat.wcy.isi.siecsilowni.exceptions.NoNameException;
import pl.edu.wat.wcy.isi.siecsilowni.exceptions.ValidationException;
import pl.edu.wat.wcy.isi.siecsilowni.exceptions.WrongEmailFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GymUtils {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static void validate(Gym gym) throws ValidationException {
        if (Utils.isNullOrEmpty(gym.getName()))
            throw new NoNameException();

        if (Utils.isNullOrEmpty(gym.getAddress()))
            throw new NoAddressException();

        if (Utils.isNullOrEmpty(gym.getEmail()) || !validateEmail(gym.getEmail())) {
            throw new WrongEmailFormatException();
        }
    }
}
