package Decoders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RTypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    public RTypeDecoder() {
        instructionsOperations = new InstructionsOperations();
    }
    public String decodeInstruction(String instruction, int currentAddress) throws Exception {
        Pattern registersPattern = Pattern.compile("[$]+\\d");
        Matcher registersMatcher = registersPattern.matcher(instruction);

        int[] registers = new int[3];
        int i = 0;
        for (;registersMatcher.find(); i++) {
            registers[i] = Integer.parseInt(registersMatcher.group().substring(1));
        }

        if (i < 3)
            throw new Exception("Unsupported or missing Parameters in " + instruction);


        String opcodeString = instructionsOperations.getOpcode(instruction);


        String funcString = instructionsOperations.getFunction(instruction);

        int rd = registers[0];
        String rdString = instructionsOperations.getRegister(rd);

        int rs = registers[1];
        String rsString = instructionsOperations.getRegister(rs);

        int rt = registers[2];
        String rtString = instructionsOperations.getRegister(rt);

        return opcodeString
                + funcString
                + rdString
                + rsString
                + rtString;
    }

}
