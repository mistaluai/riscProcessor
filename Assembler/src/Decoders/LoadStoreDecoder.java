package Decoders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.BinaryOperations.binaryString;

public class LoadStoreDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    public LoadStoreDecoder() {
        instructionsOperations = new InstructionsOperations();
    }

    public String decodeInstruction(String instruction, int currentAddress) throws Exception {
        Pattern registersPattern = Pattern.compile("[$]+\\d");
        Matcher registersMatcher = registersPattern.matcher(instruction);

        int[] registers = new int[2];
        int i = 0;

        for (;registersMatcher.find(); i++) {
            registers[i] = Integer.parseInt(registersMatcher.group().substring(1));
        }

        if (i < 2)
            throw new Exception("Unsupported or missing Parameters in " + instruction);

        Pattern offsetPattern = Pattern.compile("\\d*[(]");
        Matcher offsetMatcher  = offsetPattern.matcher(instruction);

        int offset = 0;
        if (offsetMatcher.find()) {
            String offsetDecimal = offsetMatcher.group();
            offset = Integer.parseInt(offsetDecimal.substring(0, offsetDecimal.length() - 1));
        } else throw new Exception("Offset value not found in " + instruction);

        if (offset > 15 || offset < -16) throw new Exception("offset out of range in " + instruction);

        String opcodeString = instructionsOperations.getOpcode(instruction);

        int rd = registers[0];
        String rdString = instructionsOperations.getRegister(rd);

        int rs = registers[1];
        String rsString = instructionsOperations.getRegister(rs);

        String offsetString = binaryString(offset, 5, 1);

        return opcodeString
                + offsetString
                + rsString
                + rdString;

    }


}
