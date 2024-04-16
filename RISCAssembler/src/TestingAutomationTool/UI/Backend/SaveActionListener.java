/**
 * ActionListener implementation for saving input, output, and signal files.
 */
package TestingAutomationTool.UI.Backend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class SaveActionListener implements ActionListener {
    JFrame frame;
    JTextPane input1TextPane, input2TextPane, operationSignalsTextPane, expectedOutputTextPane, actualOutputTextPane;
    JList<String> instructionComboBox;

    /**
     * Constructs a SaveActionListener with references to various UI components.
     *
     * @param frame                  The main frame of the application.
     * @param input1TextPane         Text pane for input 1.
     * @param input2TextPane         Text pane for input 2.
     * @param operationSignalsTextPane Text pane for operation signals.
     * @param expectedOutputTextPane Text pane for expected output.
     * @param actualOutputTextPane   Text pane for actual output.
     * @param instructionComboBox    Combo box for selecting instructions.
     */
    public SaveActionListener(JFrame frame, JTextPane input1TextPane, JTextPane input2TextPane, JTextPane operationSignalsTextPane,
                              JTextPane expectedOutputTextPane, JTextPane actualOutputTextPane,
                              JList<String> instructionComboBox) {
        this.frame = frame;
        this.input1TextPane = input1TextPane;
        this.input2TextPane = input2TextPane;
        this.expectedOutputTextPane = expectedOutputTextPane;
        this.actualOutputTextPane = actualOutputTextPane;
        this.instructionComboBox = instructionComboBox;
        this.operationSignalsTextPane = operationSignalsTextPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        saveFiles();
    }

    /**
     * Saves the input, output, and signal files.
     */
    private void saveFiles() {
        String instruction = instructionComboBox.getSelectedValue();
        String input1Text = input1TextPane.getText().trim();
        String input2Text = input2TextPane.getText().trim();
        String signals = operationSignalsTextPane.getText().trim();

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
            saveToFile(new File(folder, "signals.hex"), signals);

            JOptionPane.showMessageDialog(frame, "Files saved successfully.");
        }
    }

    /**
     * Saves the given text to the specified file.
     *
     * @param file The file to which the text will be saved.
     * @param text The text to be saved.
     */
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

    /**
     * Generates a random two-digit number.
     *
     * @return A random two-digit number as a string.
     */
    private String getRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(90) + 10; // Generate a random number between 10 and 99
        return String.valueOf(randomNumber);
    }

}
