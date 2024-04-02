package Utils;

public class BinaryOperations {
    public static String binaryString(int decimal, int amount, int signed) {
        String binary = Integer.toBinaryString(decimal);
        char msb = binary.charAt(0);
        String extender = "";
        for (int c = 0; c < amount - binary.length(); c++) {
            extender += (signed == 0) ? "0" : msb;
        }
        binary = extender + binary;
        return binary;
    }
}
