package TestingAutomationTool.UI.Backend;

import TestingAutomationTool.Utilities.InstructionsTesterALU;

import javax.swing.*;

public class TestInitializer {
    InstructionsTesterALU instructionsTesterALU;

    public TestInitializer(JTextPane input1TextPane, JTextPane input2TextPane,
                           JTextPane expectedOutputTextPane, JTextPane operationTextPane,
                           JTextPane operationSignalsTextPane) {
        instructionsTesterALU = new InstructionsTesterALU(input1TextPane, input2TextPane,
                expectedOutputTextPane,
                operationTextPane, operationSignalsTextPane);
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
            case 4: //ADD
                instructionsTesterALU.performAddTest();
                break;
            case 5: //Subtract
                instructionsTesterALU.performSubtractTest();
                break;
            case 6://SLL
                instructionsTesterALU.performSllTest();
                break;
            case 7: //SRL
                instructionsTesterALU.performSrlTest();
                break;
            case 8: //SRA
                instructionsTesterALU.performSraTest();
                break;
            case 9: //ROR
                instructionsTesterALU.performRorTest();
                break;
            case 10: //SLT
                instructionsTesterALU.performSltTest();
                break;
            case 11://SLTU
                instructionsTesterALU.performSltuTest();
                break;
            case 12: //ALU
                instructionsTesterALU.testALU();
        }
    }
}
