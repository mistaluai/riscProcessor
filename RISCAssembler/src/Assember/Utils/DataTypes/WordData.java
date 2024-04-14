package Assember.Utils.DataTypes;

import Assember.Exceptions.SyntaxException;

import static Assember.Utils.BinaryOperations.binaryString;

public class WordData extends DataDeclaration {

    private int wordValue;
    private int addressValue;
    private StringBuilder compiledInstructions;

    public WordData(String data, String address) {
        super(data, address);
    }

    void initializeData() {
        try {
            wordValue = Integer.parseInt(data);
            addressValue = Integer.parseInt(address);
        } catch (NumberFormatException nfe) {throw new SyntaxException("Invalid word data");}
    }

    public String getCompilation() {
        return compiledInstructions.toString();
    }

    public int compileInitialization() {
        initializeData();
        compiledInstructions = new StringBuilder();
        compiledInstructions.append(initializeMemoryPointer(addressValue) + "\n");
        compiledInstructions.append(storeData());
        return 1;
    }
    private String storeData() {
        StringBuilder instructions = new StringBuilder();
        String binaryValue = binaryString(wordValue, 16, 1);

        int lui = Integer.parseInt(binaryValue.substring(0, 11), 2);
        int ori = Integer.parseInt(binaryValue.substring(11, 16), 2);
        instructions.append("LUI " + lui + "\n");
        instructions.append("ORI $2, $1, " + ori + "\n");
        instructions.append("SW $2, 0($3)\n");

        /*
        instructions.append("ADD $2, $0, $0\n"); //clear the pointer
        int numberOfAdditions = wordValue/15;
        double remainder = wordValue%15;

        for (int count = 0; count < numberOfAdditions; count++)
            instructions.append("ADDI $2, $2, 15\n");

        if (remainder != 0)
            instructions.append("ADDI $2, $2, " + (int)remainder + "\n");

        instructions.append("SW $2, 0($1)\n");
         */

        return instructions.toString();

    }

}
