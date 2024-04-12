package TestingAutomationTool.Utilities;

import ProcessorCircuitSimulator.ALU.ArithmeticOperations;
import ProcessorCircuitSimulator.ALU.LogicOperations;
import ProcessorCircuitSimulator.ALU.ShiftingOperations;
import ProcessorCircuitSimulator.StaticData.SignalALU;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static ProcessorCircuitSimulator.ALU.ArithmeticOperations.*;
import static ProcessorCircuitSimulator.ALU.LogicOperations.*;
import static ProcessorCircuitSimulator.ALU.ShiftingOperations.*;
import static TestingAutomationTool.Utilities.RandomBitsGenerator.generateHexInputs;
import static TestingAutomationTool.Utilities.RandomBitsGenerator.generateRandomShiftAmount;

public class InstructionsTesterALU {
    JTextPane input1TextPane, input2TextPane, expectedOutputTextPane, operationTextPane, operationSignalsTextPane;
    SignalALU sALU;

    public InstructionsTesterALU(JTextPane input1TextPane, JTextPane input2TextPane,
                                 JTextPane expectedOutputTextPane, JTextPane operationTextPane,
                                 JTextPane operationSignalsTextPane) {
        this.input1TextPane = input1TextPane;
        this.input2TextPane = input2TextPane;
        this.expectedOutputTextPane = expectedOutputTextPane;
        this.operationTextPane = operationTextPane;
        this.operationSignalsTextPane = operationSignalsTextPane;
        sALU = new SignalALU();
    }

    public void performAddTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = generateHexInputs(127);

        String operation = "ADD";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform addition for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = ArithmeticOperations.addSignedHexStrings(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performSubtractTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = generateHexInputs(127);

        String operation = "SUB";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform subtraction for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = ArithmeticOperations.subtractSignedHexStrings(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performBitwiseAndTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = generateHexInputs(127);

        String operation = "AND";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise AND for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = bitwiseAnd(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performBitwiseOrTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = generateHexInputs(127);

        String operation = "OR";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise OR for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = bitwiseOr(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performBitwiseXorTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = generateHexInputs(127);

        String operation = "XOR";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise XOR for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = LogicOperations.bitwiseXor(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performBitwiseNorTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = generateHexInputs(127);

        String operation = "NOR";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise NOR for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = LogicOperations.bitwiseNor(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performSllTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = RandomBitsGenerator.generateRandomShiftAmount(127);

        String operation = "SLL";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise SLL for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            // Parse hexadecimal string to integer
            int input2Int = Integer.parseInt(linesInput2[i], 16);

            // Perform bitwise SLL with integer input
            String result = sll(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performSrlTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = RandomBitsGenerator.generateRandomShiftAmount(127);

        String operation = "SRL";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise SRL for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            // Parse hexadecimal string to integer
            int input2Int = Integer.parseInt(linesInput2[i], 16);

            // Perform bitwise SRL with integer input
            String result = srl(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performSraTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = RandomBitsGenerator.generateRandomShiftAmount(127);

        String operation = "SRA";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform bitwise SRA for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            // Parse hexadecimal string to integer
            int input2Int = Integer.parseInt(linesInput2[i], 16);

            // Perform bitwise SRA with integer input
            String result = sra(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performRorTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = RandomBitsGenerator.generateRandomShiftAmount(127);

        String operation = "ROR";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
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
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performSltTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = generateHexInputs(127);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        String operation = "SLT";
        String signal = sALU.getSignal(operation);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform SLT for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = setLessThan(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }

    public void performSltuTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(127);
        String hexInput2 = generateHexInputs(127);

        String operation = "SLTU";
        String signal = sALU.getSignal(operation);

        // Set text of input text panes
        input1TextPane.setText(hexInput1);
        input2TextPane.setText(hexInput2);

        // Initialize StringBuilder to store expected outputs
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();
        // Split input strings into lines and iterate over them
        String[] linesInput1 = hexInput1.split("\n");
        String[] linesInput2 = hexInput2.split("\n");

        // Perform SLTU for each pair of lines and store the result
        for (int i = 0; i < linesInput1.length; i++) {
            String result = setLessThanUnsigned(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append((i == linesInput1.length - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == linesInput1.length - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == linesInput1.length - 1) ? "" : "\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
    }


    public void testALU() {
        int numTests = 127;
        Random random = new Random();

        // List of available operations
        List<String> operationsList = new ArrayList<>();
        Collections.addAll(operationsList, "ADD", "SUB",
                "AND", "OR", "XOR", "NOR",
                "SLL", "SRL", "SRA", "ROR",
                "SLT", "SLTU");

        // Initialize StringBuilder to store expected outputs
        StringBuilder input1 = new StringBuilder();
        StringBuilder input2 = new StringBuilder();
        StringBuilder expectedOutput = new StringBuilder();
        StringBuilder signals = new StringBuilder();
        StringBuilder operations = new StringBuilder();

        for (int i = 0; i < numTests; i++) {
            // Shuffle the list of operations
            Collections.shuffle(operationsList);

            // Select the first operation from the shuffled list
            String operation = operationsList.get(0);

            // Get signal for the operation
            String signal = sALU.getSignal(operation);

            String in1 = generateHexInputs(1);
            String in2 = generateHexInputs(1);
            String sa = generateRandomShiftAmount(1);

            String expectedOutputIteration = "";
            switch (operation) {
                case "ADD":
                    expectedOutputIteration = addSignedHexStrings(in1, in2);
                    input2.append(in2);
                    break;
                case "SUB":
                    expectedOutputIteration = subtractSignedHexStrings(in1, in2);
                    input2.append(in2);
                    break;
                case "AND":
                    expectedOutputIteration = bitwiseAnd(in1, in2);
                    input2.append(in2);
                    break;
                case "OR":
                    expectedOutputIteration = bitwiseOr(in1, in2);
                    input2.append(in2);
                    break;
                case "XOR":
                    expectedOutputIteration = bitwiseXor(in1, in2);
                    input2.append(in2);
                    break;
                case "NOR":
                    expectedOutputIteration = bitwiseNor(in1, in2);
                    input2.append(in2);
                    break;
                case "SLL":
                    expectedOutputIteration = sll(in1, Integer.parseInt(sa, 16));
                    input2.append(sa);
                    break;
                case "SRL":
                    expectedOutputIteration = srl(in1, Integer.parseInt(sa, 16));
                    input2.append(sa);
                    break;
                case "SRA":
                    expectedOutputIteration = sra(in1, Integer.parseInt(sa, 16));
                    input2.append(sa);
                    break;
                case "ROR":
                    expectedOutputIteration = ror(in1, Integer.parseInt(sa, 16));
                    input2.append(sa);
                    break;
                case "SLT":
                    expectedOutputIteration = setLessThan(in1, in2);
                    input2.append(in2);
                    break;
                case "SLTU":
                    expectedOutputIteration = setLessThanUnsigned(in1, in2);
                    input2.append(in2);
                    break;
            }

            input1.append(in1);
            input1.append((i == numTests - 1) ? "" : "\n");

            input2.append((i == numTests - 1) ? "" : "\n");

            expectedOutput.append(expectedOutputIteration);
            expectedOutput.append((i == numTests - 1) ? "" : "\n");

            signals.append(signal);
            signals.append((i == numTests - 1) ? "" : "\n");

            operations.append(operation);
            operations.append((i == numTests - 1) ? "" : "\n");

        }
        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
        operationTextPane.setText(operations.toString());
        operationSignalsTextPane.setText(signals.toString());
        input1TextPane.setText(input1.toString());
        input2TextPane.setText(input2.toString());
    }

}
