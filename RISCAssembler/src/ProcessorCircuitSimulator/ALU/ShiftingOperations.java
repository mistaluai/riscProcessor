package ProcessorCircuitSimulator.ALU;

/**
 * Provides shifting operations including logical left shift (SLL), arithmetic right shift (SRL),
 * arithmetic right shift with sign extension (SRA), and rotate right (ROR) on hexadecimal strings.
 */
public class ShiftingOperations {

    /**
     * Performs logical left shift (SLL) operation on a hexadecimal string.
     *
     * @param hex         the hexadecimal string to be shifted.
     * @param shiftAmount the number of bits to shift by.
     * @return the result of the logical left shift operation as a hexadecimal string.
     */
    public static String sll(String hex, int shiftAmount) {
        // Convert hexadecimal string to decimal integer
        int num = Integer.parseInt(hex, 16);

        // Perform logical left shift operation
        int result = num << shiftAmount;

        // Convert the result back to hexadecimal
        String resultHex = Integer.toHexString(result & 0xFFFF).toUpperCase();

        // Pad with zeros to ensure length of 4 characters
        while (resultHex.length() < 4) {
            resultHex = "0" + resultHex;
        }

        return resultHex;
    }

    /**
     * Performs arithmetic right shift (SRL) operation on a hexadecimal string.
     *
     * @param hex         the hexadecimal string to be shifted.
     * @param shiftAmount the number of bits to shift by.
     * @return the result of the arithmetic right shift operation as a hexadecimal string.
     */
    public static String srl(String hex, int shiftAmount) {
        // Convert hexadecimal string to decimal integer
        int num = Integer.parseInt(hex, 16);

        // Perform arithmetic right shift operation
        int result = num >> shiftAmount;

        // Convert the result back to hexadecimal
        String resultHex = Integer.toHexString(result & 0xFFFF).toUpperCase();

        // Pad with zeros to ensure length of 4 characters
        while (resultHex.length() < 4) {
            resultHex = "0" + resultHex;
        }

        return resultHex;
    }

    /**
     * Performs arithmetic right shift (SRA) operation on a hexadecimal string with sign extension.
     *
     * @param hex         the hexadecimal string to be shifted.
     * @param shiftAmount the number of bits to shift by.
     * @return the result of the arithmetic right shift operation with sign extension as a hexadecimal string.
     */
    public static String sra(String hex, int shiftAmount) {
        // Convert hexadecimal string to decimal integer
        int num = Integer.parseInt(hex, 16);

        // Perform arithmetic right shift operation
        int result = num >> shiftAmount;

        // Sign-extend the result if the sign bit was set
        if ((num & 0x8000) != 0) {
            int signExtension = 0xFFFF << (16 - shiftAmount); // Generate sign extension pattern
            result |= signExtension;
        }

        // Convert the result back to hexadecimal
        String resultHex = Integer.toHexString(result & 0xFFFF).toUpperCase();

        // Pad with zeros to ensure length of 4 characters
        while (resultHex.length() < 4) {
            resultHex = "0" + resultHex;
        }

        return resultHex;
    }

    /**
     * Performs rotate right (ROR) operation on a hexadecimal string.
     *
     * @param hex          the hexadecimal string to be rotated.
     * @param rotateAmount the number of bits to rotate by.
     * @return the result of the rotate right operation as a hexadecimal string.
     */
    public static String ror(String hex, int rotateAmount) {
        // Ensure that rotateAmount is within the range [0, 16]
        rotateAmount %= 16;
        if (rotateAmount < 0) {
            rotateAmount += 16;
        }

        // Convert hexadecimal string to decimal integer
        int num = Integer.parseInt(hex, 16);

        // Perform rotate right operation
        int result = (num >>> rotateAmount) | (num << (16 - rotateAmount)) & 0xFFFF;

        // Convert the result back to hexadecimal
        String resultHex = Integer.toHexString(result & 0xFFFF).toUpperCase();

        // Pad with zeros to ensure length of 4 characters
        while (resultHex.length() < 4) {
            resultHex = "0" + resultHex;
        }

        return resultHex;
    }

}
