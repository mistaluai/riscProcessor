package Assember.Utils;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.io.*;

/**
 * A utility class for handling file operations.
 */
public class FileHandler {
    private final JFrame frame;

    /**
     * Constructs a new FileHandler with the specified JFrame.
     *
     * @param frame the JFrame associated with the file operations.
     */
    public FileHandler(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Saves the contents of a JTextPane to a file.
     *
     * @param textPane the JTextPane containing the text to be saved.
     */
    public void saveToFile(JTextPane textPane) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(frame);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (FileWriter fileWriter = new FileWriter(file);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                StyledDocument doc = textPane.getStyledDocument();
                String text = doc.getText(0, doc.getLength());
                bufferedWriter.write(text);
            } catch (IOException | javax.swing.text.BadLocationException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Saves the contents of a JTextArea to a file.
     *
     * @param codeTextArea the JTextArea containing the text to be saved.
     */
    public void saveToFile(JTextArea codeTextArea) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(codeTextArea.getText());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Saves the provided text to a file.
     *
     * @param text the text to be saved.
     */
    public void saveToFile(String text) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Opens a file and loads its contents into a JTextPane.
     *
     * @param codeTextArea the JTextPane to which the file contents will be loaded.
     */
    public void openFromFile(JTextPane codeTextArea) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                codeTextArea.setText(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
