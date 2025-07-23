package validate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validator {

    public static boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    public static boolean isInt(String data) {
        try {
            return Integer.parseInt(data) > 0;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPhoneNumber(String data) {
        String regex = "^[0-9]{10,11}$";
        return data.matches(regex);
    }

    public static boolean isBoolean(String data) {
        try {
            return Boolean.parseBoolean(data);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLocaldate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(data, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
