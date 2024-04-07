package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

import static ALU.ArithmeticOperations.addSignedHexStrings;
import static ALU.ArithmeticOperations.subtractSignedHexStrings;
import static ALU.LogicOperations.*;
import static ALU.ShiftingOperations.*;
import static Utilities.RandomBitsGenerator.generateHexInputs;
import static Utilities.RandomBitsGenerator.generateRandomShiftAmount;

public class Main extends JFrame {
    private JLabel instructionLabel;
    private JComboBox<String> instructionComboBox;
    private JTextPane input1TextPane, input2TextPane, expectedOutputTextPane, actualOutputTextPane, logTextPane;

    public Main() {
        // Set up the frame
        setTitle("RISC Processor Tester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 550); // Adjust size as needed
        setLocationRelativeTo(null); // Center the frame on the screen

        // Initialize components
        instructionLabel = new JLabel("Instruction");
        instructionComboBox = new JComboBox<>();
        // Add instruction options to the combobox
        String[] instructions = {"AND", "OR", "XOR", "NOR",
                "ANDI", "ORI", "XORI",
                "ADD", "SUB", "ADDI",
                "SLL", "SRL", "SRA", "ROR",
        };

        for (String instruction : instructions)
            instructionComboBox.addItem(instruction);

        instructionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedOption = instructionComboBox.getSelectedIndex();
                switch (selectedOption) {
                    case 0: //AND
                        performBitwiseAndTest();
                        break;
                    case 1: //OR
                        performBitwiseOrTest();
                        break;
                    case 2: //XOR
                        performBitwiseXorTest();
                        break;
                    case 3: //NOR
                        performBitwiseNorTest();
                        break;
                    case 4: //ANDI
                        performBitwiseAndTest();
                        break;
                    case 5: //ORI
                        performBitwiseOrTest();
                        break;
                    case 6: //XORI
                        performBitwiseXorTest();
                        break;
                    case 7: //ADD
                        performAddTest();
                        break;
                    case 8: //Subtract
                        performSubtractTest();
                        break;
                    case 9: //ADDI
                        performAddTest();
                        break;
                    case 10://SLL
                        performSllTest();
                        break;
                    case 11: //SRL
                        performSrlTest();
                        break;
                    case 12: //SRA
                        performSraTest();
                        break;
                    case 13: //ROR
                        performRorTest();
                        break;
                }
            }
        });

        // Set up buttons
        JButton saveImageButton = new JButton("Save Images");
        JButton loadOutputImageButton = new JButton("Load Output Image");
        JButton checkOutputButton = new JButton("Check Output");
        JButton saveToSheetButton = new JButton("Save Test Cases");

        // Add action listeners to buttons
        saveImageButton.addActionListener(new SaveActionListener(this));

        loadOutputImageButton.addActionListener(new LoadImageActionListener(this));

        checkOutputButton.addActionListener(new OutputCheckerActionListener(this));

        saveToSheetButton.addActionListener(new SheetCreatorActionListener(this));


        // Set up the layout
        setLayout(new BorderLayout());

        // Panel for instruction components
        JPanel instructionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        instructionPanel.add(instructionLabel);
        instructionPanel.add(instructionComboBox);
        instructionPanel.add(saveImageButton);
        instructionPanel.add(loadOutputImageButton);
        instructionPanel.add(checkOutputButton);
        instructionPanel.add(saveToSheetButton);
        add(instructionPanel, BorderLayout.NORTH);

        // Create JTextPanes
        input1TextPane = new JTextPane();
        input2TextPane = new JTextPane();
        expectedOutputTextPane = new JTextPane();
        actualOutputTextPane = new JTextPane();
        logTextPane = new JTextPane();

        // Set preferred size for logTextPane
        logTextPane.setPreferredSize(new Dimension(300, 75)); // Adjust the dimensions as needed

        // Create JScrollPanes for JTextPanes
        JScrollPane logScrollPane = new JScrollPane(logTextPane);

        // Panel for input and output components
        JPanel ioPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        ioPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ioPanel.add(new JScrollPane(createLabeledPanel("Input 1", input1TextPane)));
        ioPanel.add(new JScrollPane(createLabeledPanel("Input 2", input2TextPane)));
        ioPanel.add(new JScrollPane(createLabeledPanel("Expected Output", expectedOutputTextPane)));
        ioPanel.add(new JScrollPane(createLabeledPanel("Actual Output", actualOutputTextPane)));
        add(ioPanel, BorderLayout.CENTER);

        // Panel for logging
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logPanel.add(logScrollPane, BorderLayout.CENTER);
        add(logPanel, BorderLayout.SOUTH);
    }

    private JPanel createLabeledPanel(String labelText, JTextPane textPane) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(textPane), BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main ui = new Main();
            ui.setVisible(true);
        });
    }

    private void performAddTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(100);
        String hexInput2 = generateHexInputs(100);

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
            String result = addSignedHexStrings(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    private void performSubtractTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(100);
        String hexInput2 = generateHexInputs(100);

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
            String result = subtractSignedHexStrings(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    private void performBitwiseAndTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(100);
        String hexInput2 = generateHexInputs(100);

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
            String result = bitwiseAnd(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    private void performBitwiseOrTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(100);
        String hexInput2 = generateHexInputs(100);

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
            String result = bitwiseOr(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    private void performBitwiseXorTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(100);
        String hexInput2 = generateHexInputs(100);

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
            String result = bitwiseXor(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    private void performBitwiseNorTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(100);
        String hexInput2 = generateHexInputs(100);

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
            String result = bitwiseNor(linesInput1[i], linesInput2[i]);
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    private void performSllTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(100);
        String hexInput2 = generateRandomShiftAmount(100);

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
            String result = sll(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    private void performSrlTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(100);
        String hexInput2 = generateRandomShiftAmount(100);

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
            String result = srl(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    private void performSraTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(100);
        String hexInput2 = generateRandomShiftAmount(100);

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
            String result = sra(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    private void performRorTest() {
        // Generate hexadecimal inputs
        String hexInput1 = generateHexInputs(100);
        String hexInput2 = generateRandomShiftAmount(100);

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
            String result = ror(linesInput1[i], input2Int);

            // Append the result to the StringBuilder
            expectedOutput.append(result).append("\n");
        }

        // Set text of expected output text pane
        expectedOutputTextPane.setText(expectedOutput.toString());
    }

    private class SaveActionListener implements ActionListener {
        JFrame frame;

        public SaveActionListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Code to save images
            saveFiles();
        }

        private void saveFiles() {
            String instruction = (String) instructionComboBox.getSelectedItem();
            String input1Text = input1TextPane.getText().trim();
            String input2Text = input2TextPane.getText().trim();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showSaveDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();
                String folderName = instruction + getRandomNumber();
                File folder = new File(selectedDirectory, folderName);

                // Create directory if it doesn't exist
                if (!folder.exists()) {
                    folder.mkdirs(); // Create the directory and any necessary parent directories
                }

                saveToFile(new File(folder, "input1.hex"), input1Text);
                saveToFile(new File(folder, "input2.hex"), input2Text);

                JOptionPane.showMessageDialog(frame, "Files saved successfully.");
            }
        }

        private void saveToFile(File file, String text) {
            try {
                PrintWriter writer = new PrintWriter(file);
                writer.println("v2.0 raw");

                String[] lines = text.split("\n");
                int lastIndex = lines.length - 1;

                for (int i = 0; i < lastIndex; i++) {
                    writer.println(lines[i]);
                }

                // Skip empty lines at the end
                for (int i = lastIndex; i >= 0; i--) {
                    if (!lines[i].isEmpty()) {
                        break;
                    }
                    lastIndex--;
                }

                if (!lines[lastIndex].isEmpty()) {
                    writer.println(lines[lastIndex]);
                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String getRandomNumber() {
            Random random = new Random();
            int randomNumber = random.nextInt(90) + 10; // Generate a random number between 10 and 99
            return String.valueOf(randomNumber);
        }

    }

    private class LoadImageActionListener implements ActionListener {
        JFrame frame;

        public LoadImageActionListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            loadOutputImage();
        }

        private void loadOutputImage() {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    boolean skipFirstLine = true;
                    while ((line = reader.readLine()) != null) {
                        if (skipFirstLine) {
                            skipFirstLine = false;
                            if (line.trim().equals("v2.0 raw")) {
                                continue; // Skip the first line if it's "v2.0 raw"
                            }
                        }
                        content.append(line).append("\n");
                    }
                    String[] contents = content.toString().split(" ");
                    actualOutputTextPane.setText("");
                    for (String lineContent : contents) {
                        actualOutputTextPane.setText(actualOutputTextPane.getText() + lineContent.toUpperCase());
                        actualOutputTextPane.setText(actualOutputTextPane.getText() + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error loading file.");
                }
            }
        }
    }

    private class OutputCheckerActionListener implements ActionListener {

        JFrame frame;

        public OutputCheckerActionListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            checkOutput();
        }

        private void checkOutput() {
            String expectedOutput = expectedOutputTextPane.getText().trim();
            String actualOutput = actualOutputTextPane.getText().trim();

            String[] expectedLines = expectedOutput.split("\n");
            String[] actualLines = actualOutput.split("\n");
            String[] input1Lines = input1TextPane.getText().trim().split("\n");
            String[] input2Lines = input2TextPane.getText().trim().split("\n");

            boolean testPassed = true;
            StringBuilder logMessage = new StringBuilder();

            if (expectedLines.length != actualLines.length) {
                logMessage.append("Test Failed: Number of lines in expected and actual output do not match.\n");
                testPassed = false;
            } else {
                for (int i = 0; i < expectedLines.length; i++) {

                    if (expectedLines[i].charAt(0) == '0')
                        expectedLines[i] = removeLeadingZeros(expectedLines[i]);

                    if (!expectedLines[i].equals(actualLines[i])) {
                        logMessage.append("Test Failed at line ").append(i + 1).append("\n");
                        logMessage.append("Input1: ").append(input1Lines[i]).append("\n");
                        logMessage.append("Input2: ").append(input2Lines[i]).append("\n");
                        logMessage.append("Expected Output: ").append(expectedLines[i]).append("\n");
                        logMessage.append("Actual Output: ").append(actualLines[i]).append("\n");
                        testPassed = false;
                    }
                }
            }

            if (testPassed) {
                logMessage.append("Test Passed: Expected and actual output match.");
            }

            logTextPane.setText(logMessage.toString());
        }

        public String removeLeadingZeros(String str) {
            if (str == null || str.isEmpty()) {
                return str;
            }

            int i = 0;
            while (i < str.length() && str.charAt(i) == '0') {
                i++;
            }

            return str.substring(i);
        }
    }

    private class SheetCreatorActionListener implements ActionListener {

        JFrame frame;

        public SheetCreatorActionListener(JFrame frame) {
            this.frame = frame;
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {
            saveToSheet();
        }

        private void saveToSheet() {
            String input1 = input1TextPane.getText().trim();
            String input2 = input2TextPane.getText().trim();
            String expectedOutput = expectedOutputTextPane.getText().trim();
            String actualOutput = actualOutputTextPane.getText().trim();

            String[] expectedLines = expectedOutput.split("\n");
            String[] actualLines = actualOutput.split("\n");
            String[] input1Lines = input1.split("\n");
            String[] input2Lines = input2.split("\n");

            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save to CSV");
                fileChooser.setSelectedFile(new File("test_results.csv"));
                int userSelection = fileChooser.showSaveDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    FileWriter writer = new FileWriter(fileToSave);

                    writer.write("Input1,Input2,Expected Output,Actual Output,Test Passed\n");

                    for (int i = 0; i < expectedLines.length; i++) {
                        if (expectedLines[i].charAt(0) == '0')
                            expectedLines[i] = removeLeadingZeros(expectedLines[i]);
                        boolean testPassed = expectedLines[i].equals(actualLines[i]);

                        writer.write("\"" + input1Lines[i].trim().replace("\n", "\",\"") + "\",");
                        writer.write("\"" + input2Lines[i].trim().replace("\n", "\",\"") + "\",");
                        writer.write("\"" + expectedLines[i] + "\",");
                        writer.write("\"" + actualLines[i] + "\",");
                        writer.write("\"" + (testPassed ? "Passed" : "Failed") + "\"\n");
                    }

                    writer.close();
                    JOptionPane.showMessageDialog(frame, "Test results saved to CSV.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error saving to CSV.");
            }

        }

        public String removeLeadingZeros(String str) {
            if (str == null || str.isEmpty()) {
                return str;
            }

            int i = 0;
            while (i < str.length() && str.charAt(i) == '0') {
                i++;
            }

            return str.substring(i);
        }

    }
}
