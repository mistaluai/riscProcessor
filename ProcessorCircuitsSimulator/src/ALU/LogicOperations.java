package ALU;

public class LogicOperations {
    // Bitwise AND operation method for hexadecimal strings
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

    // Bitwise OR operation method for hexadecimal strings
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

    // Bitwise XOR operation method for hexadecimal strings
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
    // Bitwise NOR operation method for hexadecimal strings
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
