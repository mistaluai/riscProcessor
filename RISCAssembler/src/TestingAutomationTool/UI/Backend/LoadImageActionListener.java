/**
 * ActionListener implementation for loading an image file and displaying its content.
 */
package TestingAutomationTool.UI.Backend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadImageActionListener implements ActionListener {
    JFrame frame;
    JTextPane input1TextPane, input2TextPane, expectedOutputTextPane, actualOutputTextPane, logTextPane;

    /**
     * Constructs a LoadImageActionListener with references to various UI components.
     *
     * @param frame                The main frame of the application.
     * @param input1TextPane       Text pane for input 1.
     * @param input2TextPane       Text pane for input 2.
     * @param expectedOutputTextPane Text pane for expected output.
     * @param actualOutputTextPane   Text pane for actual output.
     * @param logTextPane           Text pane for logging.
     */
    public LoadImageActionListener(JFrame frame, JTextPane input1TextPane, JTextPane input2TextPane,
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
        loadOutputImage();
    }

    /**
     * Loads the image file selected by the user and displays its content in the actual output text pane.
     */
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
                actualOutputTextPane.setText(interpretText(actualOutputTextPane.getText()));
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error loading file.");
            }
        }
    }

    /**
     * Interprets the text content of the actual output text pane.
     *
     * @param text The text content to interpret.
     * @return The interpreted text.
     */
    public static String interpretText(String text) {
        StringBuilder interpretedText = new StringBuilder();
        String[] lines = text.split("\n");
        for (String line : lines) {
            String[] parts = line.split("\\*");
            if (parts.length == 2) {
                int count = Integer.parseInt(parts[0]);
                String value = parts[1];
                for (int i = 0; i < count; i++) {
                    interpretedText.append(value).append("\n");
                }
            } else {
                interpretedText.append(line).append("\n");
            }
        }
        return interpretedText.toString();
    }
}
