package Utilities;

import java.util.Random;

public class RandomBitsGenerator {
    public static String generateHexInputs(int n) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) { // 4 characters for 16 bits
                int randInt = rand.nextInt(16); // Generate a random integer between 0 and 15
                sb.append(Integer.toHexString(randInt).toUpperCase()); // Convert the integer to hexadecimal and append to StringBuilder
            }
            sb.append("\n"); // Add newline character after each hexadecimal input
        }
        return sb.toString();
    }
}
