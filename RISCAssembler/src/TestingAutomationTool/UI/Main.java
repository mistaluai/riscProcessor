package TestingAutomationTool.UI;

import TestingAutomationTool.UI.Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JLabel instructionLabel;
    private JComboBox<String> instructionComboBox;
    private JTextPane input1TextPane, input2TextPane, expectedOutputTextPane, actualOutputTextPane, logTextPane;
    private TestInitializer testInitializer;

    public Main() {
        // Set up the frame
        setTitle("RISC Processor Tester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 550); // Set frame size
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

        // Create JTextPanes for input, output, and logging
        input1TextPane = new JTextPane();
        input2TextPane = new JTextPane();
        expectedOutputTextPane = new JTextPane();
        actualOutputTextPane = new JTextPane();
        logTextPane = new JTextPane();

        // Set preferred size for logTextPane
        logTextPane.setPreferredSize(new Dimension(300, 75)); // Set preferred size for logTextPane

        // Create JScrollPane for logTextPane
        JScrollPane logScrollPane = new JScrollPane(logTextPane);

        // Add instruction options to the combobox
        for (String instruction : instructions)
            instructionComboBox.addItem(instruction);

        // Initialize testInitializer
        testInitializer = new TestInitializer(input1TextPane, input2TextPane, expectedOutputTextPane);

        // ActionListener for instructionComboBox
        instructionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedOption = instructionComboBox.getSelectedIndex();
                testInitializer.initializeTest(selectedOption); // Initialize test based on selected instruction
            }
        });

        // Set up buttons
        JButton saveImageButton = new JButton("Save Images");
        JButton loadOutputImageButton = new JButton("Load Output Image");
        JButton checkOutputButton = new JButton("Check Output");
        JButton saveToSheetButton = new JButton("Save Test Cases");

        // Add action listeners to buttons
        saveImageButton.addActionListener(new SaveActionListener(this, input1TextPane, input2TextPane,
                expectedOutputTextPane, actualOutputTextPane, instructionComboBox));

        loadOutputImageButton.addActionListener(new LoadImageActionListener(this, input1TextPane, input2TextPane,
                expectedOutputTextPane, actualOutputTextPane, logTextPane));

        checkOutputButton.addActionListener(new OutputCheckerActionListener(this, input1TextPane, input2TextPane,
                expectedOutputTextPane, actualOutputTextPane, logTextPane));

        saveToSheetButton.addActionListener(new SheetCreatorActionListener(this, input1TextPane, input2TextPane,
                expectedOutputTextPane, actualOutputTextPane));

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

    // Method to create a labeled panel with a JTextPane
    private JPanel createLabeledPanel(String labelText, JTextPane textPane) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(textPane), BorderLayout.CENTER);
        return panel;
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main ui = new Main();
            ui.setVisible(true);
        });
    }
}
