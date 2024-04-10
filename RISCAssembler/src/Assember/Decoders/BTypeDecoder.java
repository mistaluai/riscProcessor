package Assember.Decoders;


import Assember.Exceptions.RangeException;
import Assember.Exceptions.SyntaxException;
import Assember.Utils.BinaryOperations;
import Assember.Utils.SymbolTable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BTypeDecoder implements Decoder {
    SymbolTable st;
    InstructionsOperations instructionsOperations;

    public BTypeDecoder(SymbolTable st)
    {
        this.st = st;
        instructionsOperations = new InstructionsOperations();
    }
    /**
     * Decodes a branch instruction to its binary representation.
     * This method takes a branch assembly instruction as input along
     * with the current address, and returns the binary representation of the instruction.
     *
     * @param instruction The branch assembly instruction to decode.
     * @param currentAddress The current memory address of the instruction.
     * @return The binary representation of the instruction.
     * @throws Exception if the instruction is unsupported, missing parameters,
     *                   the label is not found, or if an error occurs during decoding.
     */
    public String decodeInstruction(String instruction, int currentAddress) {
        // Extracts two registers from the given assembly instruction 'instruction'
        // at the current address 'currentAddress' using the instructionsOperations object.
        // The extracted registers will be used for further processing.
        int[] parameters = extractParameters(instruction, currentAddress);

        int offset = parameters[2];

        // Retrieve the opcode from instructionsOperations
        String opcode = instructionsOperations.getOpcode(instruction);
        // Encode the offset value as a 5-bit binary string
        String offsetString = BinaryOperations.binaryString(offset, 5, 1);

        // Retrieve the register numbers for rs and rt
        int rs = parameters[0];
        int rt = parameters[1];
        // Retrieve register representations from instructionsOperations
        String rtString = instructionsOperations.getRegister(rt);
        String rsString = instructionsOperations.getRegister(rs);

        // Concatenate opcode, offset value, and register representations to form the binary instruction
        return opcode + offsetString + rsString + rtString;
    }

    public int[] extractParameters(String instruction, int currentAddress) {
        int[] output = new int[3];
        int[] registers = instructionsOperations.extractRegisters(instruction, 2, currentAddress);

        output[0] = registers[0];
        output[1] = registers[1];

        // Compile a regular expression pattern to match the label in the instruction
        Pattern labelPattern = Pattern.compile("[ ][\\w]+");
        // Create a matcher for the label
        Matcher labelMatcher = labelPattern.matcher(instruction);
        String label = "";

        // Extract the label from the instruction if present
        if (labelMatcher.find())
            label = labelMatcher.group().substring(1);
        else
            throw new SyntaxException("["+currentAddress+"] Label not found in " + instruction);

        // Retrieve the address of the label from the symbol table
        int labelAddress = st.getLabel(label);
        // Calculate the offset relative to the current address
        int offset = labelAddress - currentAddress;

        //System.out.println(label + " " + labelAddress + " " + currentAddress + " " + offset);

        if (offset < -16 || offset > 15)
            throw new RangeException("["+currentAddress+"] The branch range exceeded in " + instruction);
        //System.out.println(offset);

        output[2] = offset;

        return output;

    }

}
