package ProcessorCircuitSimulator.ALU;

public class ArithmeticOperations {

    // Addition method for signed hexadecimal strings with overflow handling
    public static String addSignedHexStrings(String hex1, String hex2) {
        // Convert hexadecimal strings to decimal integers
        int num1 = Integer.parseInt(hex1, 16);
        int num2 = Integer.parseInt(hex2, 16);

        // Perform addition
        int result = num1 + num2;

        // Check for overflow around the 16-bit limit
        if ((result & 0xFFFF0000) != 0) {
            // Overflow occurred
            result = (result >= 0) ? result - 0x10000 : result + 0x10000;
        }

        // Convert the result back to hexadecimal
        String sumHex = Integer.toHexString(result).toUpperCase();

        // Pad with zeros to ensure length of 4 characters
        while (sumHex.length() < 4) {
            sumHex = "0" + sumHex;
        }

        return sumHex;
    }

    // Subtraction method for signed hexadecimal strings with overflow handling
    public static String subtractSignedHexStrings(String hex1, String hex2) {
        // Convert hexadecimal strings to decimal integers
        int num1 = Integer.parseInt(hex1, 16);
        int num2 = Integer.parseInt(hex2, 16);

        // Perform subtraction
        int result = num1 - num2;

        // Check for overflow around the 16-bit limit
        if ((result & 0xFFFF0000) != 0) {
            // Overflow occurred
            result = (result >= 0) ? result - 0x10000 : result + 0x10000;
        }

        // Convert the result back to hexadecimal
        String diffHex = Integer.toHexString(result).toUpperCase();

        // Pad with zeros to ensure length of 4 characters
        while (diffHex.length() < 4) {
            diffHex = "0" + diffHex;
        }

        return diffHex;
    }
}
