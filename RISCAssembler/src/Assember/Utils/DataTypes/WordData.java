package Assember.Utils.DataTypes;

import Assember.Exceptions.SyntaxException;

public class WordData extends DataDeclaration {

    private int wordValue;
    private StringBuilder compiledInstructions;
    public WordData(String data) {
        super(data);
    }

    void initializeData() {
        try {
            wordValue = Integer.parseInt(data);
        } catch (NumberFormatException nfe) {throw new SyntaxException("Invalid word data");}
    }

    public String getCompilation() {
        return compiledInstructions.toString();
    }

    public int compileInitialization(int memoryPointer) {
        initializeData();
        compiledInstructions = new StringBuilder();
        compiledInstructions.append(initializeMemoryPointer(memoryPointer) + "\n");
        compiledInstructions.append(storeData());
        return 1;
    }
    private String storeData() {
        StringBuilder instructions = new StringBuilder();

        instructions.append("ADD $2, $0, $0\n"); //clear the pointer
        int numberOfAdditions = wordValue/15;
        double remainder = wordValue%15;

        for (int count = 0; count < numberOfAdditions; count++)
            instructions.append("ADDI $2, $2, 15\n");

        if (remainder != 0)
            instructions.append("ADDI $2, $2, " + (int)remainder + "\n");

        instructions.append("SW $2, 0($1)\n");

        return instructions.toString();

    }

    public static void main(String[] args) {
        WordData wd = new WordData("43");
        wd.compileInitialization(12);
        System.out.println(wd.getCompilation());
    }
}
