package UI.Backend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutputCheckerActionListener implements ActionListener {

    JFrame frame;
    JTextPane input1TextPane, input2TextPane, expectedOutputTextPane, actualOutputTextPane, logTextPane;

    public OutputCheckerActionListener(JFrame frame, JTextPane input1TextPane, JTextPane input2TextPane,
                                      JTextPane expectedOutputTextPane, JTextPane actualOutputTextPane, JTextPane logTextPane) {
        this.frame = frame;
        this.input1TextPane = input1TextPane;
        this.input2TextPane = input2TextPane;
        this.expectedOutputTextPane = expectedOutputTextPane;
        this.actualOutputTextPane = actualOutputTextPane;
        this.logTextPane = logTextPane;
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
        while (i < str.length() - 1 && str.charAt(i) == '0') {
            i++;
        }

        return str.substring(i);
    }
}
