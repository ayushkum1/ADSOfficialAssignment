package utils;

import exceptions.InvalidDataException;

public class ValidationUtils {

    public static long validatePhoneNumber(long phoneNumber) throws InvalidDataException {
        if (String.valueOf(phoneNumber).matches("^[1-9][0-9]{9}")) return phoneNumber;
        else throw new InvalidDataException("'" + phoneNumber + "' is not a phone number!");
    }
}
