package Decoders;

import Exceptions.SyntaxException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionsOperations {

    /**
     * A mapping of assembly instructions to their corresponding opcodes.
     * Key: Assembly instruction
     * Value: Opcode
     */
    Map<String, String> instructionOpcode;

    /**
     * A mapping of assembly instructions to their corresponding function codes.
     * Key: Assembly instruction
     * Value: Function code
     */
    Map<String, String> instructionFunction;

    /**
     * A mapping of assembly instructions to their corresponding instruction types.
     * Key: Assembly instruction
     * Value: Instruction type
     */
    Map<String, InstructionType> instructionType;

    /**
     * A mapping of register names to their corresponding register numbers.
     * Key: Register name
     * Value: Register number
     */
    Map<Integer, String> registerString;

    /**
     * A mapping of assembly instructions to their corresponding integer opcodes.
     * Key: Assembly instruction
     * Value: Integer opcode
     */
    Map<String , Integer> integerOpcode;


    /**
     * Enum representing the types of assembly instructions.
     */
    public enum InstructionType {
        /**
         * Represents R-type instructions.
         */
        RType,

        /**
         * Represents I-type instructions.
         */
        IType,

        /**
         * Represents J-type instructions.
         */
        JType,

        /**
         * Represents Load/Store type instructions.
         */
        LoadStoreType,

        /**
         * Represents branch type instructions.
         */
        BType
    }


    /**
     * Constructs a new InstructionsOperations object.
     * Initializes the mappings for opcodes, function codes, instruction types,
     * register names, and integer opcodes.
     * This constructor initializes the object with default values for each mapping.
     */
    public InstructionsOperations() {
        // Initialize the mappings for opcodes, function codes, instruction types,
        // register names, and integer opcodes
        initializeOpcodes();
        initializeFunctions();
        initializeTypes();
        initializeRegisters();
        initializeIntegerOpcodes();
    }

    /**
     * Initializes the mapping of register names to their corresponding binary representations.
     * This method populates the registerString map with register numbers as keys and their
     * corresponding binary representations as values.
     */
    private void initializeRegisters() {
        // Create a new HashMap to store the mappings
        registerString = new HashMap<>();

        // Populate the map with register numbers as keys and their binary representations as values
        registerString.put(0, "000");
        registerString.put(1, "001");
        registerString.put(2, "010");
        registerString.put(3, "011");
        registerString.put(4, "100");
        registerString.put(5, "101");
        registerString.put(6, "110");
        registerString.put(7, "111");
    }

    /**
     * Initializes the mapping of assembly instructions to their corresponding opcodes.
     * This method populates the instructionOpcode map with assembly instructions as keys
     * and their corresponding opcodes as values.
     */
    private void initializeOpcodes() {
        // Create a new HashMap to store the mappings
        instructionOpcode = new HashMap<>();

        // Populate the map with assembly instructions and their corresponding opcodes
        // R-Type instructions
        instructionOpcode.put("AND", "00000"); //0
        instructionOpcode.put("OR", "00000"); //0
        instructionOpcode.put("XOR", "00000"); //0
        instructionOpcode.put("NOR", "00000"); //0
        instructionOpcode.put("ADD", "00001"); //1
        instructionOpcode.put("SUB", "00001"); //1
        instructionOpcode.put("SLT", "00001"); //1
        instructionOpcode.put("SLTU", "00001");//1
        instructionOpcode.put("JR", "00010"); //2

        // I-Type instructions
        instructionOpcode.put("ANDI", "00100"); //4
        instructionOpcode.put("ORI", "00101"); //5
        instructionOpcode.put("XORI", "00110"); //6
        instructionOpcode.put("ADDI", "00111"); //7
        instructionOpcode.put("SLL", "01000"); //8
        instructionOpcode.put("SRL", "01001"); //9
        instructionOpcode.put("SRA", "01010"); //10
        instructionOpcode.put("ROR", "01011"); //11
        instructionOpcode.put("LW", "01100"); //12
        instructionOpcode.put("SW", "01101"); //13
        instructionOpcode.put("BEQ", "01110"); //14
        instructionOpcode.put("BNE", "01111"); //15
        instructionOpcode.put("BLT", "10000"); //16
        instructionOpcode.put("BGE", "10001"); //17

        // J-Type instructions
        instructionOpcode.put("LUI", "10010"); //18
        instructionOpcode.put("J", "11110"); //30
        instructionOpcode.put("JAL", "11111"); //31
    }


    /**
     * Initializes the mapping of assembly instructions to their corresponding integer opcodes.
     * This method populates the integerOpcode map with assembly instructions as keys
     * and their corresponding integer opcodes as values.
     */
    private void initializeIntegerOpcodes() {
        // Create a new HashMap to store the mappings
        integerOpcode = new HashMap<>();

        // Populate the map with assembly instructions and their corresponding integer opcodes
        // R-Type instructions
        integerOpcode.put("AND", 0);
        integerOpcode.put("OR", 0);
        integerOpcode.put("XOR", 0);
        integerOpcode.put("NOR", 0);
        integerOpcode.put("ADD", 1);
        integerOpcode.put("SUB", 1);
        integerOpcode.put("SLT", 1);
        integerOpcode.put("SLTU", 1);
        integerOpcode.put("JR", 2);

        // I-Type instructions
        integerOpcode.put("ANDI", 4);
        integerOpcode.put("ORI", 5);
        integerOpcode.put("XORI", 6);
        integerOpcode.put("ADDI", 7);
        integerOpcode.put("SLL", 8);
        integerOpcode.put("SRL", 9);
        integerOpcode.put("SRA", 10);
        integerOpcode.put("ROR", 11);
        integerOpcode.put("LW", 12);
        integerOpcode.put("SW", 13);
        integerOpcode.put("BEQ", 14);
        integerOpcode.put("BNE", 15);
        integerOpcode.put("BLT", 16);
        integerOpcode.put("BGE", 17);

        // J-Type instructions
        integerOpcode.put("LUI", 18);
        integerOpcode.put("J", 30);
        integerOpcode.put("JAL", 31);
    }


    /**
     * Initializes the mapping of assembly instructions to their corresponding instruction types.
     * This method populates the instructionType map with assembly instructions as keys
     * and their corresponding instruction types as values.
     */
    private void initializeTypes() {
        // Create a new HashMap to store the mappings
        instructionType = new HashMap<>();

        // Populate the map with assembly instructions and their corresponding instruction types
        // R-Type instructions
        instructionType.put("AND", InstructionType.RType); //0
        instructionType.put("OR", InstructionType.RType); //0
        instructionType.put("XOR", InstructionType.RType); //0
        instructionType.put("NOR", InstructionType.RType); //0
        instructionType.put("ADD", InstructionType.RType); //1
        instructionType.put("SUB", InstructionType.RType); //1
        instructionType.put("SLT", InstructionType.RType); //1
        instructionType.put("SLTU", InstructionType.RType);//1
        instructionType.put("JR", InstructionType.JType); //2

        // I-Type instructions
        instructionType.put("ANDI", InstructionType.IType); //4
        instructionType.put("ORI", InstructionType.IType); //5
        instructionType.put("XORI", InstructionType.IType); //6
        instructionType.put("ADDI", InstructionType.IType); //7
        instructionType.put("SLL", InstructionType.IType); //8
        instructionType.put("SRL", InstructionType.IType); //9
        instructionType.put("SRA", InstructionType.IType); //10
        instructionType.put("ROR", InstructionType.IType); //11
        instructionType.put("LW", InstructionType.LoadStoreType); //12
        instructionType.put("SW", InstructionType.LoadStoreType); //13
        instructionType.put("BEQ", InstructionType.BType); //14
        instructionType.put("BNE", InstructionType.BType); //15
        instructionType.put("BLT", InstructionType.BType); //16
        instructionType.put("BGE", InstructionType.BType); //17

        // J-Type instructions
        instructionType.put("LUI", InstructionType.JType); //18
        instructionType.put("J", InstructionType.JType); //30
        instructionType.put("JAL", InstructionType.JType); //31
    }

    /**
     * Initializes the mapping of assembly instructions to their corresponding function codes.
     * This method populates the instructionFunction map with assembly instructions as keys
     * and their corresponding function codes as values.
     */
    private void initializeFunctions() {
        // Create a new HashMap to store the mappings
        instructionFunction = new HashMap<>();

        // Populate the map with assembly instructions and their corresponding function codes
        // Opcode 0
        instructionFunction.put("AND", "00");
        instructionFunction.put("OR", "01");
        instructionFunction.put("XOR", "10");
        instructionFunction.put("NOR", "11");

        // Opcode 1
        instructionFunction.put("ADD", "00");
        instructionFunction.put("SUB", "01");
        instructionFunction.put("SLT", "10");
        instructionFunction.put("SLTU", "11");

        // Opcode 2
        instructionFunction.put("JR", "00");
    }


    /**
     * Retrieves the instruction mnemonic from the given assembly instruction.
     * This method parses the assembly instruction to extract the mnemonic (operation) part.
     * It then verifies the validity of the instruction mnemonic and returns it.
     *
     * @param instruction The assembly instruction from which to extract the mnemonic.
     * @return The instruction mnemonic extracted from the given assembly instruction.
     * @throws SyntaxException if the instruction mnemonic cannot be found or is unknown.
     */
    public String getInstruction(String instruction) throws SyntaxException {
        // Define a pattern to match the instruction mnemonic
        Pattern instructionPattern = Pattern.compile("[\\w]+ ", Pattern.CASE_INSENSITIVE);

        // Create a matcher to find the instruction mnemonic in the given instruction
        Matcher instructionMatcher = instructionPattern.matcher(instruction);

        // If the instruction mnemonic cannot be found, throw a SyntaxException
        if (!instructionMatcher.find())
            throw new SyntaxException("Instruction not found in " + instruction);

        // Extract the instruction mnemonic from the matched group
        String instructionMnemonic = instructionMatcher.group();

        // Remove any trailing whitespace character from the instruction mnemonic
        instructionMnemonic = instructionMnemonic.substring(0, instructionMnemonic.length() - 1);

        // Convert the instruction mnemonic to uppercase for case-insensitive comparison
        instructionMnemonic = instructionMnemonic.toUpperCase();

        // If the instruction mnemonic is not recognized, throw a SyntaxException
        if (!instructionOpcode.containsKey(instructionMnemonic))
            throw new SyntaxException("Unknown Instruction " + instructionMnemonic);

        // Return the validated instruction mnemonic
        return instructionMnemonic;
    }


    /**
     * Retrieves the instruction type for the given assembly instruction.
     * This method first extracts the instruction mnemonic from the assembly instruction.
     * It then looks up the instruction type based on the mnemonic from the instructionType map.
     *
     * @param instruction The assembly instruction for which to determine the instruction type.
     * @return The instruction type of the given assembly instruction.
     * @throws SyntaxException if the instruction mnemonic cannot be found or is unknown.
     */
    public InstructionType getInstructionType(String instruction) throws SyntaxException {
        // Extract the instruction mnemonic from the given assembly instruction
        String instructionMnemonic = getInstruction(instruction);

        // Look up the instruction type based on the instruction mnemonic
        InstructionType type = instructionType.get(instructionMnemonic);

        // If the instruction type is not found for the mnemonic, throw a SyntaxException
        if (type == null)
            throw new SyntaxException("Unknown Instruction Type for mnemonic: " + instructionMnemonic);

        // Return the instruction type
        return type;
    }


    /**
     * Retrieves the opcode for the given assembly instruction.
     * This method first extracts the instruction mnemonic from the assembly instruction.
     * It then looks up the opcode based on the mnemonic from the instructionOpcode map.
     *
     * @param instruction The assembly instruction for which to retrieve the opcode.
     * @return The opcode corresponding to the given assembly instruction.
     * @throws SyntaxException if the instruction mnemonic cannot be found or is unknown.
     */
    public String getOpcode(String instruction) throws SyntaxException {
        // Extract the instruction mnemonic from the given assembly instruction
        String instructionMnemonic = getInstruction(instruction);

        // Look up the opcode based on the instruction mnemonic
        String opcode = instructionOpcode.get(instructionMnemonic);

        // If the opcode is not found for the mnemonic, throw a SyntaxException
        if (opcode == null)
            throw new SyntaxException("Unknown Opcode for mnemonic: " + instructionMnemonic);

        // Return the opcode
        return opcode;
    }


    /**
     * Retrieves the function code for the given assembly instruction.
     * This method first extracts the instruction mnemonic from the assembly instruction.
     * It then looks up the function code based on the mnemonic from the instructionFunction map.
     *
     * @param instruction The assembly instruction for which to retrieve the function code.
     * @return The function code corresponding to the given assembly instruction.
     * @throws SyntaxException if the instruction mnemonic cannot be found or is unknown.
     */
    public String getFunction(String instruction) throws SyntaxException {
        // Extract the instruction mnemonic from the given assembly instruction
        String instructionMnemonic = getInstruction(instruction);

        // Look up the function code based on the instruction mnemonic
        String functionCode = instructionFunction.get(instructionMnemonic);

        // If the function code is not found for the mnemonic, throw a SyntaxException
        if (functionCode == null)
            throw new SyntaxException("Unknown Function Code for mnemonic: " + instructionMnemonic);

        // Return the function code
        return functionCode;
    }


    /**
     * Retrieves the binary representation of the specified register.
     * This method looks up the binary representation of the register from the registerString map
     * based on the given register number.
     *
     * @param register The register number for which to retrieve the binary representation.
     * @return The binary representation of the specified register.
     */
    public String getRegister(int register) {
        // Look up the binary representation of the register based on its number
        String binaryRepresentation = registerString.get(register);

        // Return the binary representation
        return binaryRepresentation;
    }


    /**
     * Retrieves the integer opcode for the given assembly instruction.
     * This method first extracts the instruction mnemonic from the assembly instruction.
     * It then looks up the integer opcode based on the mnemonic from the integerOpcode map.
     *
     * @param instruction The assembly instruction for which to retrieve the integer opcode.
     * @return The integer opcode corresponding to the given assembly instruction.
     * @throws SyntaxException if the instruction mnemonic cannot be found or is unknown.
     */
    public int getIntegerOpcode(String instruction) throws SyntaxException {
        // Extract the instruction mnemonic from the given assembly instruction
        String instructionMnemonic = getInstruction(instruction);

        // Look up the integer opcode based on the instruction mnemonic
        Integer integerOpcodeValue = integerOpcode.get(instructionMnemonic);

        // If the integer opcode is not found for the mnemonic, throw a SyntaxException
        if (integerOpcodeValue == null)
            throw new SyntaxException("Unknown Integer Opcode for mnemonic: " + instructionMnemonic);

        // Return the integer opcode
        return integerOpcodeValue;
    }


    /**
     * Extracts registers from the given assembly instruction.
     * This method parses the assembly instruction to extract the specified number of registers.
     * It searches for register patterns in the instruction and extracts the register numbers.
     * The extracted register numbers are returned as an array.
     *
     * @param instruction The assembly instruction from which to extract the registers.
     * @param n The number of registers to extract.
     * @return An array containing the extracted register numbers.
     * @throws Exception if the specified number of registers cannot be extracted from the instruction.
     */
    public int[] extractRegisters(String instruction, int n, int currentAddress) throws Exception {
        // Define a pattern to match register patterns in the instruction
        Pattern registersPattern = Pattern.compile("[$]+\\d");

        // Create a matcher to find register patterns in the instruction
        Matcher registersMatcher = registersPattern.matcher(instruction);

        // Initialize an array to store the extracted register numbers
        int[] registers = new int[n];

        // Initialize a counter for the number of registers found
        int i = 0;

        // Iterate over register patterns in the instruction and extract register numbers
        for (; registersMatcher.find(); i++) {
            // Extract the register number from the matched group and parse it to an integer
            registers[i] = Integer.parseInt(registersMatcher.group().substring(1));
        }

        // If the number of extracted registers is less than the specified number, throw an exception
        if (i < n)
            throw new SyntaxException("["+currentAddress+"] Unsupported or missing Parameters in " + instruction);

        // Return the array of extracted register numbers
        return registers;
    }

}
