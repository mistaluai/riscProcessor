package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JLabel instructionLabel;
    private JComboBox<String> instructionComboBox;
    private JTextArea input1TextArea, input2TextArea, expectedOutputTextArea, actualOutputTextArea, logTextArea;

    public Main() {
        // Set up the frame
        setTitle("RISC Processor Tester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 550); // Adjust size as needed
        setLocationRelativeTo(null); // Center the frame on the screen

        // Initialize components
        instructionLabel = new JLabel("Instruction");
        instructionComboBox = new JComboBox<>();
        // Add instruction options to the combobox
        String[] instructions = {"AND", "OR", "XOR", "NOR",
                "ANDI", "ORI", "XORI", "NORI",
                "ADD", "SUB", "SLT", "SLTU",
                "SLL", "SRL", "SRA", "ROR",
        };

        for (String instruction : instructions)
            instructionComboBox.addItem(instruction);

        input1TextArea = new JTextArea();
        input2TextArea = new JTextArea();
        expectedOutputTextArea = new JTextArea();
        actualOutputTextArea = new JTextArea();
        logTextArea = new JTextArea(4, 20); // Adjust the rows and columns
        logTextArea.setLineWrap(true); // Enable line wrapping

        // Set equal width for text areas
        Dimension textAreaSize = new Dimension(150, 50);
        input1TextArea.setPreferredSize(textAreaSize);
        input2TextArea.setPreferredSize(textAreaSize);
        expectedOutputTextArea.setPreferredSize(textAreaSize);
        actualOutputTextArea.setPreferredSize(textAreaSize);

        // Set up buttons
        JButton saveImageButton = new JButton("Save Images");
        JButton loadOutputImageButton = new JButton("Load Output Image");
        JButton checkOutputButton = new JButton("Check Output");

        // Add action listeners to buttons
        saveImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to save images
            }
        });

        loadOutputImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to load output image
            }
        });

        checkOutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to check output
            }
        });

        // Set up the layout
        setLayout(new BorderLayout());

        // Panel for instruction components
        JPanel instructionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        instructionPanel.add(instructionLabel);
        instructionPanel.add(instructionComboBox);
        instructionPanel.add(saveImageButton);
        instructionPanel.add(loadOutputImageButton);
        instructionPanel.add(checkOutputButton);
        add(instructionPanel, BorderLayout.NORTH);

        // Panel for input and output components
        JPanel ioPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        ioPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ioPanel.add(createLabeledPanel("Input 1", input1TextArea));
        ioPanel.add(createLabeledPanel("Input 2", input2TextArea));
        ioPanel.add(createLabeledPanel("Expected Output", expectedOutputTextArea));
        ioPanel.add(createLabeledPanel("Actual Output", actualOutputTextArea));
        add(ioPanel, BorderLayout.CENTER);

        // Panel for logging
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logPanel.add(new JScrollPane(logTextArea), BorderLayout.CENTER);
        add(logPanel, BorderLayout.SOUTH);
    }

    private JPanel createLabeledPanel(String labelText, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main ui = new Main();
            ui.setVisible(true);
        });
    }
}
