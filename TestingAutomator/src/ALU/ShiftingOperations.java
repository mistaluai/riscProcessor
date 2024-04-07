package ALU;

public class ShiftingOperations {
    // Logical left shift (SLL) operation method for hexadecimal strings
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

    // Arithmetic right shift (SRL) operation method for hexadecimal strings
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

    // Arithmetic right shift (SRA) operation method for hexadecimal strings
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
    // Rotate right (ROR) operation method for hexadecimal strings
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
