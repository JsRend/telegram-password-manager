package tgbot.jdbc.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]:.?";

    /**
     * Generates a random password with the given parameters
     * @param length password length
     * @param useNumbers include numbers
     * @param useSpecialChar include special characters
     * @param useUppercase include uppercase letters
     * @return generated password
     */

    public static String generatePassword(int length, boolean useNumbers,
                                          boolean useSpecialChar, boolean useUppercase) {
        if (length <= 0) {
            throw new IllegalArgumentException("Длина пароля должна быть больше 0!");
        }

        StringBuilder validChars = new StringBuilder(LOWERCASE);

        if (useUppercase) validChars.append(UPPERCASE);
        if (useNumbers) validChars.append(NUMBERS);
        if (useSpecialChar) validChars.append(SPECIAL_CHARS);

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validChars.length());
            password.append(validChars.charAt(randomIndex));
        }

        return password.toString();
    }

}
