package ProcessorCircuitSimulator.ALU;

/**
 * Provides arithmetic operations for signed and unsigned hexadecimal strings with overflow handling.
 */
public class ArithmeticOperations {

    /**
     * Adds two signed hexadecimal strings with overflow handling.
     *
     * @param hex1 the first hexadecimal string to be added.
     * @param hex2 the second hexadecimal string to be added.
     * @return the result of the addition as a hexadecimal string.
     */
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

    /**
     * Subtracts one signed hexadecimal string from another with overflow handling.
     *
     * @param hex1 the hexadecimal string to be subtracted from.
     * @param hex2 the hexadecimal string to subtract.
     * @return the result of the subtraction as a hexadecimal string.
     */
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

    /**
     * Determines whether the first signed hexadecimal number is less than the second.
     *
     * @param hexString1 the first hexadecimal number.
     * @param hexString2 the second hexadecimal number.
     * @return "0001" if hexString1 is less than hexString2, otherwise "0000".
     */
    public static String setLessThan(String hexString1, String hexString2) {
        int num1 = hexToSignedInt(hexString1);
        int num2 = hexToSignedInt(hexString2);
        return (num1 < num2) ? "0001" : "0000";
    }

    /**
     * Converts a signed hexadecimal string to an integer value.
     *
     * @param hexString the signed hexadecimal string to convert.
     * @return the integer value corresponding to the hexadecimal string.
     */
    public static int hexToSignedInt(String hexString) {
        // Convert hexadecimal string to integer
        int intValue = Integer.parseInt(hexString, 16);

        // If the most significant bit (bit 15) is set, it's a negative number
        if ((intValue & 0x8000) != 0) {
            // Perform sign extension to extend the sign bit to the full 32-bit integer
            intValue |= 0xFFFF0000;
        }

        return intValue;
    }

    /**
     * Determines whether the first unsigned hexadecimal number is less than the second.
     *
     * @param hexString1 the first unsigned hexadecimal number.
     * @param hexString2 the second unsigned hexadecimal number.
     * @return "0001" if hexString1 is less than hexString2, otherwise "0000".
     */
    public static String setLessThanUnsigned(String hexString1, String hexString2) {
        int num1 = hexToUnsignedInt(hexString1);
        int num2 = hexToUnsignedInt(hexString2);
        return (num1 < num2) ? "0001" : "0000";
    }

    /**
     * Converts an unsigned hexadecimal string to an integer value.
     *
     * @param hexString the unsigned hexadecimal string to convert.
     * @return the integer value corresponding to the hexadecimal string.
     */
    public static int hexToUnsignedInt(String hexString) {
        // Convert hexadecimal string to long
        long unsignedLongValue = Long.parseLong(hexString, 16);

        // Truncate to 16 bits to fit into an int
        int unsignedIntValue = (int) (unsignedLongValue & 0xFFFF);

        return unsignedIntValue;
    }
}
