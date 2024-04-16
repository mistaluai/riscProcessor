/**
 * ActionListener implementation for saving test results to a CSV file.
 */
package TestingAutomationTool.UI.Backend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SheetCreatorActionListener implements ActionListener {

    JFrame frame;
    JTextPane input1TextPane, input2TextPane, expectedOutputTextPane, actualOutputTextPane, operationTextPane, operationSignalsTextPane;

    /**
     * Constructs a SheetCreatorActionListener with references to various UI components.
     *
     * @param frame                    The main frame of the application.
     * @param input1TextPane           Text pane for input 1.
     * @param input2TextPane           Text pane for input 2.
     * @param expectedOutputTextPane   Text pane for expected output.
     * @param actualOutputTextPane     Text pane for actual output.
     * @param operationTextPane        Text pane for operations.
     * @param operationSignalsTextPane Text pane for operation signals.
     */
    public SheetCreatorActionListener(JFrame frame, JTextPane input1TextPane, JTextPane input2TextPane,
                                      JTextPane expectedOutputTextPane, JTextPane actualOutputTextPane,
                                      JTextPane operationTextPane, JTextPane operationSignalsTextPane) {
        this.frame = frame;
        this.input1TextPane = input1TextPane;
        this.input2TextPane = input2TextPane;
        this.expectedOutputTextPane = expectedOutputTextPane;
        this.actualOutputTextPane = actualOutputTextPane;
        this.operationTextPane = operationTextPane;
        this.operationSignalsTextPane = operationSignalsTextPane;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        saveToSheet();
    }

    /**
     * Saves the test results to a CSV file.
     */
    private void saveToSheet() {
        String input1 = input1TextPane.getText().trim();
        String input2 = input2TextPane.getText().trim();
        String expectedOutput = expectedOutputTextPane.getText().trim();
        String actualOutput = actualOutputTextPane.getText().trim();
        String operation = operationTextPane.getText().trim();
        String operationSignal = operationSignalsTextPane.getText().trim();

        String[] expectedLines = expectedOutput.split("\n");
        String[] actualLines = actualOutput.split("\n");
        String[] input1Lines = input1.split("\n");
        String[] input2Lines = input2.split("\n");
        String[] operationLines = operation.split("\n");
        String[] operationSignalLines = operationSignal.split("\n");

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save to CSV");
            fileChooser.setSelectedFile(new File("test_results.csv"));
            int userSelection = fileChooser.showSaveDialog(frame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                FileWriter writer = new FileWriter(fileToSave);

                writer.write("Input1,Input2,Operation,Operation Signal,Expected Output,Actual Output,Test Passed\n");

                for (int i = 0; i < expectedLines.length; i++) {
                    if (expectedLines[i].charAt(0) == '0')
                        expectedLines[i] = removeLeadingZeros(expectedLines[i]);
                    if (actualLines[i].charAt(0) == '0')
                        actualLines[i] = removeLeadingZeros(actualLines[i]);

                    boolean testPassed = expectedLines[i].equals(actualLines[i]);

                    writer.write("\"" + input1Lines[i].trim().replace("\n", "\",\"") + "\",");
                    writer.write("\"" + input2Lines[i].trim().replace("\n", "\",\"") + "\",");
                    writer.write("\"" + operationLines[i].trim().replace("\n", "\",\"") + "\",");
                    writer.write("\"" + operationSignalLines[i].trim().replace("\n", "\",\"") + "\",");
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

    /**
     * Removes leading zeros from the given string.
     *
     * @param str The input string.
     * @return The string with leading zeros removed.
     */
    public String removeLeadingZeros(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        int i = 0;
        while (i < str.length() - 1 && str.charAt(i) == '0') {
            i++;
        }

        return str.substring(i);
    }

}
