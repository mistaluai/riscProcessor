package Decoders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RTypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    public RTypeDecoder() {
        instructionsOperations = new InstructionsOperations();
    }
    public String decodeInstruction(String instruction) throws Exception {
        Pattern parametersPattern = Pattern.compile("[$]+\\d");
        Matcher parametersMatcher = parametersPattern.matcher(instruction);

        int[] parameters = new int[3];
        int i = 0;
        for (;parametersMatcher.find(); i++) {
            parameters[i] = Integer.parseInt(parametersMatcher.group().substring(1));
        }

        if (i < 3)
            throw new Exception("Unsupported or missing Parameters in " + instruction);


        String opcodeString = instructionsOperations.getOpcode(instruction);


        String funcString = instructionsOperations.getFunction(instruction);

        int rd = parameters[0];
        String rdString = instructionsOperations.getRegister(rd);

        int rs = parameters[1];
        String rsString = instructionsOperations.getRegister(rs);

        int rt = parameters[2];
        String rtString = instructionsOperations.getRegister(rt);

        return opcodeString
                + funcString
                + rdString
                + rsString
                + rtString;
    }

}
