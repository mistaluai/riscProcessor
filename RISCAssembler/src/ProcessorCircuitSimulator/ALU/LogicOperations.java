package ProcessorCircuitSimulator.ALU;

/**
 * Provides logic operations for bitwise AND, OR, XOR, and NOR on hexadecimal strings.
 */
public class LogicOperations {

    /**
     * Performs bitwise AND operation on two hexadecimal strings.
     *
     * @param hex1 the first hexadecimal string.
     * @param hex2 the second hexadecimal string.
     * @return the result of the bitwise AND operation as a hexadecimal string.
     */
    public static String bitwiseAnd(String hex1, String hex2) {
        // Convert hexadecimal strings to decimal integers
        int num1 = Integer.parseInt(hex1, 16);
        int num2 = Integer.parseInt(hex2, 16);

        // Perform bitwise AND operation
        int result = num1 & num2;

        // Convert the result back to hexadecimal
        String resultHex = Integer.toHexString(result).toUpperCase();

        // Pad with zeros to ensure length of 4 characters
        while (resultHex.length() < 4) {
            resultHex = "0" + resultHex;
        }

        return resultHex;
    }

    /**
     * Performs bitwise OR operation on two hexadecimal strings.
     *
     * @param hex1 the first hexadecimal string.
     * @param hex2 the second hexadecimal string.
     * @return the result of the bitwise OR operation as a hexadecimal string.
     */
    public static String bitwiseOr(String hex1, String hex2) {
        // Convert hexadecimal strings to decimal integers
        int num1 = Integer.parseInt(hex1, 16);
        int num2 = Integer.parseInt(hex2, 16);

        // Perform bitwise OR operation
        int result = num1 | num2;

        // Convert the result back to hexadecimal
        String resultHex = Integer.toHexString(result).toUpperCase();

        // Pad with zeros to ensure length of 4 characters
        while (resultHex.length() < 4) {
            resultHex = "0" + resultHex;
        }

        return resultHex;
    }

    /**
     * Performs bitwise XOR operation on two hexadecimal strings.
     *
     * @param hex1 the first hexadecimal string.
     * @param hex2 the second hexadecimal string.
     * @return the result of the bitwise XOR operation as a hexadecimal string.
     */
    public static String bitwiseXor(String hex1, String hex2) {
        // Convert hexadecimal strings to decimal integers
        int num1 = Integer.parseInt(hex1, 16);
        int num2 = Integer.parseInt(hex2, 16);

        // Perform bitwise XOR operation
        int result = num1 ^ num2;

        // Convert the result back to hexadecimal
        String resultHex = Integer.toHexString(result).toUpperCase();

        // Pad with zeros to ensure length of 4 characters
        while (resultHex.length() < 4) {
            resultHex = "0" + resultHex;
        }

        return resultHex;
    }

    /**
     * Performs bitwise NOR operation on two hexadecimal strings.
     *
     * @param hex1 the first hexadecimal string.
     * @param hex2 the second hexadecimal string.
     * @return the result of the bitwise NOR operation as a hexadecimal string.
     */
    public static String bitwiseNor(String hex1, String hex2) {
        // Convert hexadecimal strings to decimal integers
        int num1 = Integer.parseInt(hex1, 16);
        int num2 = Integer.parseInt(hex2, 16);

        // Perform bitwise NOR operation
        int result = ~(num1 | num2);

        // Convert the result back to hexadecimal
        String resultHex = Integer.toHexString(result & 0xFFFF).toUpperCase();

        // Pad with zeros to ensure length of 4 characters
        while (resultHex.length() < 4) {
            resultHex = "0" + resultHex;
        }

        return resultHex;
    }
}
