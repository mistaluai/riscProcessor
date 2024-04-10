package Assember.Utils;

public class BinaryOperations {
    /**
     * Converts a decimal integer to a binary string representation with the specified number of bits.
     * If the binary representation is shorter than the specified amount of bits, it extends or truncates
     * the binary string accordingly. If signed is set to 1, it extends the binary string with the sign bit,
     * considering the value as a signed integer.
     *
     * @param decimal the decimal integer to convert to binary.
     * @param amount the number of bits in the resulting binary string.
     * @param signed specifies whether the binary string should be signed or unsigned (0 for unsigned, 1 for signed).
     * @return the binary string representation of the decimal integer with the specified number of bits.
     */
    public static String binaryString(int decimal, int amount, int signed) {
        // Convert the decimal integer to a binary string
        String binary = Integer.toBinaryString(decimal);

        // If the binary string is shorter than the specified amount of bits, extend or truncate it accordingly
        if (binary.length() < amount) {
            // If signed is set to 1, extend the binary string with the sign bit
            char signBit = (decimal < 0) ? '1' : '0';
            String extender = "";
            for (int c = 0; c < amount - binary.length(); c++) {
                extender += (signed == 0) ? "0" : signBit;
            }
            binary = extender + binary;
        } else if (binary.length() > amount) {
            binary = binary.substring(binary.length() - amount, binary.length());
        }

        // Return the binary string representation
        return binary;
    }

    public static String hexString(int value, int signExtension) {
        // Apply sign extension if signExtension is 1 (signed), otherwise don't apply sign extension
        if (signExtension == 1) {
            // Signed extension (keep only 16 least significant bits)
            value &= 0xFFFF;
        } else {
            // Unsigned extension (keep all 32 bits)
            value &= 0xFFFFFFFF;
        }

        // Convert the integer to hexadecimal string with a maximum of 4 digits
        String hexString = Integer.toHexString(value);
        if (hexString.length() > 4) {
            // If the hexadecimal string is longer than 4 digits, truncate it
            hexString = hexString.substring(hexString.length() - 4);
        } else {
            // If the hexadecimal string is shorter than 4 digits, pad it with zeros
            hexString = String.format("%04x", value);
        }

        return hexString;
    }

    public static String binaryToHex(String binaryString) {
        // Ensure the binary string length is a multiple of 4
        int length = binaryString.length();
        if (length % 4 != 0) {
            throw new IllegalArgumentException("Binary string length must be a multiple of 4.");
        }

        // Initialize a StringBuilder to store the hexadecimal result
        StringBuilder hexBuilder = new StringBuilder();

        // Convert each group of 4 bits to hexadecimal
        for (int i = 0; i < length; i += 4) {
            // Get a substring of 4 bits
            String substring = binaryString.substring(i, i + 4);

            // Convert the substring to hexadecimal
            int decimal = Integer.parseInt(substring, 2);
            String hexDigit = Integer.toHexString(decimal);

            // Append the hexadecimal digit to the result
            hexBuilder.append(hexDigit);
        }

        // Return the hexadecimal representation
        return hexBuilder.toString().toUpperCase();
    }


}
