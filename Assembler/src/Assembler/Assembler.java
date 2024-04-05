package Assembler;

import Decoders.*;
import Utils.SymbolTable;

public class Assembler {
    private SymbolTable st;
    private InstructionsOperations instructionsOperations;

    private JTypeDecoder jtd;
    private RTypeDecoder rtd;
    private ITypeDecoder itd;
    private LoadStoreDecoder lstd;
    private BTypeDecoder btd;

    private String assemblyCode;
    private StringBuilder binaryCode;
    int currentInstruction;
    public Assembler(String code) throws Exception {
        st = new SymbolTable(code);
        instructionsOperations = new InstructionsOperations();

        rtd = new RTypeDecoder();
        itd = new ITypeDecoder();
        lstd = new LoadStoreDecoder();
        jtd = new JTypeDecoder(st);
        btd = new BTypeDecoder(st);

        assemblyCode = code;
        binaryCode = new StringBuilder();
        currentInstruction = 0;
        assemble();
    }
    /**
     * Assembles the assembly code by iterating over each instruction, decoding it,
     * and appending its corresponding binary representation to the binary code StringBuilder.
     * Skippable instructions, such as those containing labels, are ignored during assembly.
     */
    private void assemble() {
        // Split the assembly code into lines
        String[] lines = assemblyCode.split("\n");

        // Iterate over each instruction in the assembly code
        for (String instruction : lines) {
            // Skip empty lines or comments
            if (instruction.length() == 0 || instruction.charAt(0) == '#')
                continue;

            // Skip skippable instructions (those containing labels)
            if (st.isSkippable(instruction))
                continue;

            // Trim the instruction to remove leading and trailing whitespaces
            instruction = instruction.trim();

            //System.out.println(currentInstruction + " " + instruction);

            // Decode the instruction based on its type and append the binary representation
            // to the binary code StringBuilder
            switch (instructionsOperations.getInstructionType(instruction)) {
                case RType:
                    binaryCode.append(rtd.decodeInstruction(instruction, currentInstruction));
                    break;
                case IType:
                    binaryCode.append(itd.decodeInstruction(instruction, currentInstruction));
                    break;
                case JType:
                    binaryCode.append(jtd.decodeInstruction(instruction, currentInstruction));
                    break;
                case LoadStoreType:
                    binaryCode.append(lstd.decodeInstruction(instruction, currentInstruction));
                    break;
                case BType:
                    binaryCode.append(btd.decodeInstruction(instruction, currentInstruction));
                    break;
            }

            // Append a newline character after each instruction
            binaryCode.append("\n");

            // Increment the current instruction counter
            currentInstruction++;
        }
    }


    public String[][] getSymbols() {
        return st.getSymbols();
    }

    public String getBinaryCode() {
        return binaryCode.toString();
    }
    public int getCurrentInstruction()
    {
        return currentInstruction;
    }
}
