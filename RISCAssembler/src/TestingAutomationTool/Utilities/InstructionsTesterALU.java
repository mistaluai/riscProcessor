package TestingAutomationTool.Utilities;

import ProcessorCircuitSimulator.ALU.ArithmeticOperations;
import ProcessorCircuitSimulator.ALU.LogicOperations;
import ProcessorCircuitSimulator.ALU.ShiftingOperations;

import javax.swing.*;

import static ProcessorCircuitSimulator.ALU.ArithmeticOperations.setLessThan;
import static ProcessorCircuitSimulator.ALU.ArithmeticOperations.setLessThanUnsigned;

public class InstructionsTesterALU {
    JTextPane input1TextPane, input2TextPane, expectedOutputTextPane;

    public InstructionsTesterALU (JTextPane input1TextPane, JTextPane input2TextPane,
                                   JTextPane expectedOutputTextPane) {
        this.input1TextPane = input1TextPane;
        this.input2TextPane = input2TextPane;
        this.expectedOutputTextPane = expectedOutputTextPane;
    }
    public void performAddTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateHexInputs(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform addition for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = ArithmeticOperations.addSignedHexStrings(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performSubtractTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateHexInputs(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform subtraction for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = ArithmeticOperations.subtractSignedHexStrings(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performBitwiseAndTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateHexInputs(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise AND for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = LogicOperations.bitwiseAnd(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performBitwiseOrTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateHexInputs(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise OR for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = LogicOperations.bitwiseOr(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performBitwiseXorTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateHexInputs(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise XOR for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = LogicOperations.bitwiseXor(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performBitwiseNorTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateHexInputs(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise NOR for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = LogicOperations.bitwiseNor(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performSllTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateRandomShiftAmount(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise SLL for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            // Parse hexadecimal string to integer
            int input2Int = Integer.parseInt(linesInput2[i], 16);

            // Perform bitwise SLL with integer input
            String result = ShiftingOperations.sll(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performSrlTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateRandomShiftAmount(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise SRL for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            // Parse hexadecimal string to integer
            int input2Int = Integer.parseInt(linesInput2[i], 16);

            // Perform bitwise SRL with integer input
            String result = ShiftingOperations.srl(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performSraTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateRandomShiftAmount(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise SRA for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            // Parse hexadecimal string to integer
            int input2Int = Integer.parseInt(linesInput2[i], 16);

            // Perform bitwise SRA with integer input
            String result = ShiftingOperations.sra(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performRorTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateRandomShiftAmount(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise ROR for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            // Parse hexadecimal string to integer
            int input2Int = Integer.parseInt(linesInput2[i], 16);

            // Perform bitwise ROR with integer input
            String result = ShiftingOperations.ror(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performSltTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateHexInputs(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform SLT for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = setLessThan(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    public void performSltuTest() {
        // Generate hexadecimal inputs
        String hexInput1 = RandomBitsGenerator.generateHexInputs(100);
        String hexInput2 = RandomBitsGenerator.generateHexInputs(100);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();

        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform SLTU for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = setLessThanUnsigned(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }





}
