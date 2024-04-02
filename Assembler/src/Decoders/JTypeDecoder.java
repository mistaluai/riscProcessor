package Decoders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.BinaryOperations.binaryString;

public class JTypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    public JTypeDecoder() {
        instructionsOperations = new InstructionsOperations();
    }
    public String decodeInstruction(String instruction) throws Exception {
        
        String instructionName = instructionsOperations.getInstruction(instruction);

        if (instructionName.equals("LUI")) {

            String opcode = instructionsOperations.getOpcode(instructionName);

            Pattern immediatePattern = Pattern.compile("[ ][\\d]*");
            Matcher immediateMatcher = immediatePattern.matcher(instruction);
            int immediate = 0;
            if (immediateMatcher.find())
                immediate = Integer.parseInt(immediateMatcher.group().substring(1));
            else
                throw new Exception("immediate value not given in " + instruction);

            String immediateString = binaryString(immediate, 11, 0);

            return opcode
                    + immediateString;

        }

        if (instructionName.equals("J")) {

        }

        if (instructionName.equals("JAL")) {

        }

        if (instructionName.equals("JR")) {

        }

        return "";
    }



}
