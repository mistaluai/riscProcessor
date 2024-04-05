package Decoders;

import Utils.BinaryOperations;
import Utils.SymbolTable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.BinaryOperations.binaryString;

public class JTypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;
    SymbolTable st;

    public JTypeDecoder(SymbolTable st) {
        instructionsOperations = new InstructionsOperations();
        this.st = st;
    }
    public String decodeInstruction(String instruction, int currentAddress) throws Exception {

        String instructionName = instructionsOperations.getInstruction(instruction);

        if (instructionName.equals("LUI")) {

            String opcode = instructionsOperations.getOpcode(instruction);

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

        if (instructionName.equals("J") || instructionName.equals("JAL")) {

            String opcode = instructionsOperations.getOpcode(instruction);

            String label = instruction.substring(2);
            System.out.println(instruction);
            System.out.println(label);
            int labelAddress = st.getLabel(label);
            int offset = labelAddress - currentAddress;

            String offsetString = BinaryOperations.binaryString(offset, 11, 1);

            return opcode
                    + offsetString;
        }


        if (instructionName.equals("JR")) {
            String opcode = instructionsOperations.getOpcode(instruction);

            Pattern registersPattern = Pattern.compile("[$]+\\d");
            Matcher registersMatcher = registersPattern.matcher(instruction);

            int rs = 0;
            if (registersMatcher.find())
                rs = Integer.parseInt(registersMatcher.group().substring(1));
            else throw new Exception("target register not found in " + instruction);

            String rsString = BinaryOperations.binaryString(rs, 3, 0);

            return opcode
                    + "00"
                    + "000"
                    + rsString
                    + "000";

        }

        return "";
    }



}
