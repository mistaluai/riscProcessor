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



    public static String setLessThan(String hexString1, String hexString2) {
        int a = Integer.parseInt(hexString1, 16); // Convert hex string to integer
        int b = Integer.parseInt(hexString2, 16); // Convert hex string to integer

        boolean set_less_than;
        boolean overflow;

        // Check for overflow
        if ((a < 0 && b > 0 && a - b > 0) || (a > 0 && b < 0 && a - b < 0)) {
            overflow = true;
        } else {
            overflow = false;
        }

        // Check for less than
        if (a < b && !overflow) {
            set_less_than = true;
        } else {
            set_less_than = false;
        }

        // Return result
        return set_less_than ? "0001" : "0000";
    }

    public static String setLessThanUnsigned(String hexString1, String hexString2) {
        // Convert hex strings to integers
        int a = Integer.parseInt(hexString1, 16);
        int b = Integer.parseInt(hexString2, 16);

        // Check for less than
        boolean setLessThan = a < b;

        // Return result based on setLessThan
        return setLessThan ? "0001" : "0000";
    }

}
