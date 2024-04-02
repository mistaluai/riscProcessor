package Utils;

public class BinaryOperations {
    public static String binaryExtend(String binary, int amount) {
        String extender = "";
        for (int c = 0; c < amount - binary.length(); c++) {
            extender += "0";
        }
        binary = extender + binary;
        return binary;
    }
}
