package Assember.Utils.DataTypes;

import static Assember.Utils.BinaryOperations.binaryString;

/**
 * Abstract class representing a data declaration in the assembly code.
 * This class provides methods for initializing data, compiling initialization, and getting the compilation result.
 */
public abstract class DataDeclaration {
    /** The data value. */
    public String data;
    /** The memory address. */
    public String address;
    /** The compiled instructions. */
    public StringBuilder compiledInstructions;

    /**
     * Constructor for DataDeclaration class.
     * @param data The data value.
     * @param address The memory address.
     */
    public DataDeclaration(String data, String address) {
        this.data = data;
        this.address = address;
    }

    /**
     * Abstract method to initialize data.
     */
    abstract void initializeData();

    /**
     * Abstract method to compile initialization.
     * @return The compiled initialization.
     */
    public abstract int compileInitialization();

    /**
     * Abstract method to get the compilation result.
     * @return The compilation result.
     */
    public abstract String getCompilation();

    /**
     * Initializes the memory pointer.
     * @param memoryPointer The memory pointer.
     * @return The initialized memory pointer instructions.
     */
    String initializeMemoryPointer(int memoryPointer) {
        StringBuilder instructions = new StringBuilder();

        String binaryValue = binaryString(memoryPointer, 16, 0);
        int lui = Integer.parseInt(binaryValue.substring(0, 11), 2);
        int ori = Integer.parseInt(binaryValue.substring(11, 16), 2);
        instructions.append("LUI " + lui + "\n");
        instructions.append("ORI $3, $1, " + ori + "\n");

        return instructions.toString();
    }
}
