/**
 * The Assembler class is responsible for assembling assembly code into binary code.
 * It decodes each instruction, appends its corresponding binary representation, and handles
 * skip instructions containing labels. It also provides functionalities to retrieve instructions,
 * symbols, binary code, debug code, and the current instruction.
 */
package Assember.Assembler;

import Assember.Decoders.*;
import Assember.Utils.DataInitializer;
import Assember.Utils.SymbolTable;
import ProcessorCircuitSimulator.Processor.Processor;

import static Assember.Utils.BinaryOperations.binaryToHex;

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
    private StringBuilder instructionCode;

    StringBuilder instructions;
    StringBuilder compiledInstructions;

    private Processor processor;

    int currentInstruction;

    /**
     * Constructs an Assembler object with the given assembly code.
     *
     * @param code The assembly code to be assembled.
     */
    public Assembler(String code) {
        st = new SymbolTable(code);
        instructionsOperations = new InstructionsOperations();

        rtd = new RTypeDecoder();
        itd = new ITypeDecoder();
        lstd = new LoadStoreDecoder();
        jtd = new JTypeDecoder(st);
        btd = new BTypeDecoder(st);

        processor = new Processor(st);

        assemblyCode = code;
        binaryCode = new StringBuilder();
        instructionCode = new StringBuilder();
        currentInstruction = 0;
    }

    /**
     * Retrieves the assembly code along with initialized data declarations.
     *
     * @param textSection      The text section of the assembly code.
     * @param dataInitializer  The data initializer object.
     * @return The assembled assembly code.
     */
    private String getAssemblyCode(String textSection, DataInitializer dataInitializer) {
        StringBuilder code = new StringBuilder();
        code.append(dataInitializer.getDeclarationsCompiled());
        code.append(textSection);
        return code.toString();
    }

    /**
     * Assembles the assembly code into binary code.
     * Iterates over each instruction, decodes it, and appends its corresponding binary representation.
     * Skippable instructions containing labels are ignored during assembly.
     */
    public void assemble() {
        String code = getAssemblyCode(assemblyCode, st.getDataInitializer());
        // Split the assembly code into lines
        String[] lines = code.split("\n");
        instructions = new StringBuilder();
        compiledInstructions = new StringBuilder();
        // Iterate over each instruction in the assembly code
        for (String instruction : lines) {
            // Trim the instruction to remove leading and trailing whitespaces
            instruction = instruction.trim();

            // Skip empty lines or comments
            if (instruction.length() == 0 || instruction.charAt(0) == '#')
                continue;

            instruction = removeComments(instruction);

            // Skip skippable instructions (those containing labels)
            if (st.isSkippable(instruction))
                continue;

            // Decode the instruction based on its type and append the binary representation
            // to the binary code StringBuilder
            String machineCode = "";
            switch (instructionsOperations.getInstructionType(instruction)) {
                case RType:
                    machineCode = rtd.decodeInstruction(instruction, currentInstruction);
                    break;
                case IType:
                    machineCode = itd.decodeInstruction(instruction, currentInstruction);
                    break;
                case JType:
                    machineCode = jtd.decodeInstruction(instruction, currentInstruction);
                    break;
                case LoadStoreType:
                    machineCode = lstd.decodeInstruction(instruction, currentInstruction);
                    break;
                case BType:
                    machineCode = btd.decodeInstruction(instruction, currentInstruction);
                    break;
            }

            // Append a newline character after each instruction
            binaryCode.append(machineCode + "\n");

            instructionCode.append(instruction + " : " + machineCode + " | " + binaryToHex(machineCode) + "\n");

            if (!instructionsOperations.getInstruction(instruction).equals("DRAW")) {
                processor.addInstruction(instruction, currentInstruction);
                instructions.append(instruction + "\n");
                compiledInstructions.append(binaryToHex(machineCode) + "\n");
            }
            // Increment the current instruction counter
            currentInstruction++;
        }
    }

    /**
     * Retrieves the assembled instructions along with their binary representations.
     *
     * @return A 2D array containing instruction index, binary code, and assembly instruction.
     */
    public String[][] getInstructions() {
        String[] instructionLines = instructions.toString().split("\n");
        String[] compiledInstructionLines = compiledInstructions.toString().split("\n");
        String[][] output = new String[instructionLines.length][3];
        for (int i = 0; i < instructionLines.length; i++) {
            output[i][0] = String.valueOf(i);
            output[i][1] = compiledInstructionLines[i];
            output[i][2] = instructionLines[i];
        }
        return output;
    }

    /**
     * Removes comments from the assembly code.
     *
     * @param assemblyCode The assembly code with comments.
     * @return The assembly code without comments.
     */
    private String removeComments(String assemblyCode) {
        StringBuilder result = new StringBuilder();
        boolean inComment = false;

        for (int i = 0; i < assemblyCode.length(); i++) {
            char currentChar = assemblyCode.charAt(i);
            char nextChar = i < assemblyCode.length() - 1 ? assemblyCode.charAt(i + 1) : '\0';

            if (currentChar == '#' && (i == 0 || assemblyCode.charAt(i - 1) != '\\')) {
                inComment = true;
            }

            if (!inComment) {
                result.append(currentChar);
            }

            if (currentChar == '\n' || (currentChar == '\r' && nextChar == '\n')) {
                inComment = false;
            }
        }
        String output = result.toString();
        output = output.trim();
        return output;
    }

    /**
     * Retrieves the processor associated with the assembler.
     *
     * @return The processor object.
     */
    public Processor getProcessor() {
        return processor;
    }

    /**
     * Retrieves symbols declared in the assembly code.
     *
     * @return A 2D array containing symbols and their corresponding addresses.
     */
    public String[][] getSymbols() {
        return st.getSymbols();
    }

    /**
     * Retrieves the binary code generated from assembly code.
     *
     * @return The binary code.
     */
    public String getBinaryCode() {
        return binaryCode.toString();
    }

    /**
     * Retrieves the debug code containing assembly instructions along with their binary representations.
     *
     * @return The debug code.
     */
    public String getDebugCode() {
        return instructionCode.toString();
    }

    /**
     * Retrieves the current instruction index.
     *
     * @return The current instruction index.
     */
    public int getCurrentInstruction() {
        return currentInstruction;
    }
}
