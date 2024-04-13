package Assember.Utils.DataTypes;

import Assember.Exceptions.SyntaxException;

public class SpaceData extends DataDeclaration {
    int numberOfSpaces;

    public SpaceData(String data) {
        super(data);
    }


    void initializeData() {
        try {
            numberOfSpaces = Integer.parseInt(data);
        } catch (NumberFormatException nfe) {throw new SyntaxException("Invalid number of words for space");}
    }

    public String getCompilation() {
        return compiledInstructions.toString();
    }

    public int compileInitialization(int memoryPointer) {
        initializeData();
        compiledInstructions = new StringBuilder();
        compiledInstructions.append(initializeMemoryPointer(memoryPointer) + "\n");
        compiledInstructions.append(storeData());
        return numberOfSpaces;
    }

    private String storeData() {
        StringBuilder instructions = new StringBuilder();


        for (int count = 0; count < numberOfSpaces; count++) {
            instructions.append("SW $0, 0($1)\n");
            instructions.append("ADDI $1, $1, 1\n");
        }

        return instructions.toString();

    }

    public static void main(String[] args) {
        SpaceData sd = new SpaceData("13");
        sd.compileInitialization(13);
        System.out.println(sd.getCompilation());
    }

}
