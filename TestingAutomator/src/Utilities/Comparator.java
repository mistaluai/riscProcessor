package Utilities;

public class Comparator {
    public static boolean compareHexStrings(String hex1, String hex2) {
        // Convert hexadecimal strings to uppercase for case-insensitive comparison
        hex1 = hex1.toUpperCase();
        hex2 = hex2.toUpperCase();

        // Check if the lengths of the two strings are equal
        if (hex1.length() != hex2.length()) {
            return false;
        }

        // Compare each character of the two strings
        for (int i = 0; i < hex1.length(); i++) {
            if (hex1.charAt(i) != hex2.charAt(i)) {
                return false;
            }
        }

        // If all characters match, return true
        return true;
    }
}
