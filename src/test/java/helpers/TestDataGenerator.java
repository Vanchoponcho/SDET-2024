package helpers;

import java.util.Random;

public class TestDataGenerator {
    public static String generateNumber(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    public static String convertNumberToStringByAlphabet(String stringNumber) {
        StringBuilder firstName = new StringBuilder();
        for (int i = 0; i < stringNumber.length(); i += 2) {
            String digitPair = stringNumber.substring(i, i + 2);
            int number = Integer.parseInt(digitPair);

            char letter = (char) ((number % 26) + 97);

            while (letter > 'z') {
                letter = (char) (letter - 26);
            }

            firstName.append(letter);
        }
        return firstName.toString();
    }
}
