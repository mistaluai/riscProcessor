package TestingAutomationTool.UI.Backend;

import TestingAutomationTool.Utilities.InstructionsTesterALU;

import javax.swing.*;

public class TestInitializer {
    InstructionsTesterALU instructionsTesterALU;

    public TestInitializer(JTextPane input1TextPane, JTextPane input2TextPane,
                           JTextPane expectedOutputTextPane) {
        instructionsTesterALU = new InstructionsTesterALU(input1TextPane, input2TextPane, expectedOutputTextPane);
    }
    public void initializeTest(int selectedOption) {
        if (selectedOption >= 0 && selectedOption <= 15) {
            testSingleInstruction(selectedOption);
            return;
        }
    }

    private void testSingleInstruction(int selectedOption) {
        switch (selectedOption) {
            case 0: //AND
                instructionsTesterALU.performBitwiseAndTest();
                break;
            case 1: //OR
                instructionsTesterALU.performBitwiseOrTest();
                break;
            case 2: //XOR
                instructionsTesterALU.performBitwiseXorTest();
                break;
            case 3: //NOR
                instructionsTesterALU.performBitwiseNorTest();
                break;
            case 4: //ANDI
                instructionsTesterALU.performBitwiseAndTest();
                break;
            case 5: //ORI
                instructionsTesterALU.performBitwiseOrTest();
                break;
            case 6: //XORI
                instructionsTesterALU.performBitwiseXorTest();
                break;
            case 7: //ADD
                instructionsTesterALU.performAddTest();
                break;
            case 8: //Subtract
                instructionsTesterALU.performSubtractTest();
                break;
            case 9: //ADDI
                instructionsTesterALU.performAddTest();
                break;
            case 10://SLL
                instructionsTesterALU.performSllTest();
                break;
            case 11: //SRL
                instructionsTesterALU.performSrlTest();
                break;
            case 12: //SRA
                instructionsTesterALU.performSraTest();
                break;
            case 13: //ROR
                instructionsTesterALU.performRorTest();
                break;
            case 14: //SLT
                instructionsTesterALU.performSltTest();
                break;
            case 15://SLTU
                instructionsTesterALU.performSltuTest();
                break;
        }
    }
}
