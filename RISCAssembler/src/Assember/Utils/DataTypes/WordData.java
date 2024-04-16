package Assember.Utils.DataTypes;

import Assember.Exceptions.SyntaxException;

import static Assember.Utils.BinaryOperations.binaryString;

/**
 * Class representing a word data declaration in the assembly code.
 * Extends the DataDeclaration abstract class.
 */
public class WordData extends DataDeclaration {

    private int wordValue;
    private int addressValue;
    private StringBuilder compiledInstructions;

    /**
     * Constructor for WordData class.
     * @param data The data value.
     * @param address The memory address.
     */
    public WordData(String data, String address) {
        super(data, address);
    }

    /**
     * Initializes the word data and address values.
     * @throws SyntaxException if the word data is invalid.
     */
    void initializeData() {
        try {
            wordValue = Integer.parseInt(data);
            addressValue = Integer.parseInt(address);
        } catch (NumberFormatException nfe) {
            throw new SyntaxException("Invalid word data");
        }
    }

    /**
     * Retrieves the compilation result.
     * @return The compilation result.
     */
    public String getCompilation() {
        return compiledInstructions.toString();
    }

    /**
     * Compiles the initialization of the word data.
     * @return The number of words compiled.
     */
    public int compileInitialization() {
        initializeData();
        compiledInstructions = new StringBuilder();
        compiledInstructions.append(initializeMemoryPointer(addressValue) + "\n");
        compiledInstructions.append(storeData());
        return 1;
    }

    /**
     * Stores the word data in memory.
     * @return The instructions for storing the word data.
     */
    private String storeData() {
        StringBuilder instructions = new StringBuilder();
        String binaryValue = binaryString(wordValue, 16, 1);

        int lui = Integer.parseInt(binaryValue.substring(0, 11), 2);
        int ori = Integer.parseInt(binaryValue.substring(11, 16), 2);
        instructions.append("LUI " + lui + "\n");
        instructions.append("ORI $2, $1, " + ori + "\n");
        instructions.append("SW $2, 0($3)\n");

        return instructions.toString();

    }

}
