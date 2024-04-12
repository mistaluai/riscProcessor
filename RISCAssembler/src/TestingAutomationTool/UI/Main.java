package TestingAutomationTool.UI;

import TestingAutomationTool.UI.Backend.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class Main extends JFrame {
    private JLabel instructionLabel;
    private JList<String> instructionList, componentsList;
    private JTextPane input1TextPane, input2TextPane, operationSignalsTextPane, operationTextPane, expectedOutputTextPane, actualOutputTextPane, logTextPane;
    private TestInitializer testInitializer;

    public Main() {
        // Set up the frame
        setTitle("RISC Processor Tester");
        setSize(900, 490); // Set frame size
        setLocationRelativeTo(null); // Center the frame on the screen

        // Add instruction options to the list
        String[] instructions = {"AND", "OR", "XOR", "NOR",
                "ADD", "SUB",
                "SLL", "SRL", "SRA", "ROR",
                "SLT", "SLTU",
                "ALU"
        };

        instructionList = new JList<>(instructions);
        instructionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        instructionList.setPreferredSize(new Dimension(100, 300));
        JPanel instructionListPanel = new JPanel();
        instructionListPanel.setLayout(new BoxLayout(instructionListPanel, BoxLayout.Y_AXIS));
        instructionListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instructionListPanel.add(new JLabel("Test Cases"));
        instructionListPanel.setPreferredSize(new Dimension(100, 300));
        instructionListPanel.add(new JScrollPane(instructionList));

        // Create JTextPanes for input, output, and logging
        input1TextPane = new JTextPane();
        input2TextPane = new JTextPane();
        operationSignalsTextPane = new JTextPane();
        operationTextPane = new JTextPane();
        expectedOutputTextPane = new JTextPane();
        actualOutputTextPane = new JTextPane();
        logTextPane = new JTextPane();

        // Set preferred size for logTextPane
        input1TextPane.setPreferredSize(new Dimension(100, 320));
        input2TextPane.setPreferredSize(new Dimension(100, 320));
        expectedOutputTextPane.setPreferredSize(new Dimension(100, 320));
        actualOutputTextPane.setPreferredSize(new Dimension(100, 320));
        logTextPane.setPreferredSize(new Dimension(300, 75)); // Set preferred size for logTextPane
        operationSignalsTextPane.setPreferredSize(new Dimension(80, 320));
        operationTextPane.setPreferredSize(new Dimension(80, 320));



        // Initialize testInitializer
        testInitializer = new TestInitializer(input1TextPane, input2TextPane, expectedOutputTextPane, operationTextPane, operationSignalsTextPane);

        // ActionListener for instructionComboBox
        instructionList.addListSelectionListener(new ListSelectionListener(){
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Get the index of the selected item
                    int selectedOption = instructionList.getSelectedIndex();
                    testInitializer.initializeTest(selectedOption); // Initialize test based on selected instruction
                }
            }
        });



        // Set up the layout
        setLayout(new BorderLayout());

        // Panel for input and output components
        JPanel ioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
        ioPanel.setPreferredSize(new Dimension(800, 450));
        //ioPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel inputsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 3));
        inputsPanel.setPreferredSize(new Dimension(415, 350));
        inputsPanel.add(createLabeledPanel("Input 1", input1TextPane));
        inputsPanel.add(createLabeledPanel("Input 2", input2TextPane));
        inputsPanel.add(createLabeledPanel("Operation Signals", operationSignalsTextPane));
        inputsPanel.add(createLabeledPanel("Operations", operationTextPane));
        ioPanel.add(inputsPanel);
        JPanel outputsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 3));
        outputsPanel.setPreferredSize(new Dimension(300, 350));
        outputsPanel.add(createLabeledPanel("Expected Output", expectedOutputTextPane));
        outputsPanel.add(createLabeledPanel("Actual Output", actualOutputTextPane));
        ioPanel.add(outputsPanel);
        add(ioPanel, BorderLayout.CENTER);

        add(instructionListPanel, BorderLayout.EAST);
        // Panel for logging and ALU operations
        JPanel bottomPanel = new JPanel(new BorderLayout());
        //bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(createLabeledPanel("Test Results", logTextPane), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        initializeMenuBar();
    }

    // Method to create a labeled panel with a JTextPane
    private JPanel createLabeledPanel(String labelText, JTextPane textPane) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(textPane), BorderLayout.CENTER);
        return panel;
    }
    private void initializeMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveImagesMenuItem = new JMenuItem("Save Images");
        JMenuItem loadOutputImagesMenuItem = new JMenuItem("Load Output Images");

        saveImagesMenuItem.addActionListener(new SaveActionListener(this, input1TextPane, input2TextPane,
                operationSignalsTextPane, expectedOutputTextPane, actualOutputTextPane, instructionList));

        loadOutputImagesMenuItem.addActionListener(new LoadImageActionListener(this, input1TextPane, input2TextPane,
                expectedOutputTextPane, actualOutputTextPane, logTextPane));


        fileMenu.add(saveImagesMenuItem);
        fileMenu.add(loadOutputImagesMenuItem);
        menuBar.add(fileMenu);

        // Testing menu
        JMenu testingMenu = new JMenu("Testing");
        JMenuItem checkOutputMenuItem = new JMenuItem("Check Output");
        JMenuItem saveTestCasesMenuItem = new JMenuItem("Save Test Cases");

        checkOutputMenuItem.addActionListener(new OutputCheckerActionListener(this, input1TextPane, input2TextPane,
                expectedOutputTextPane, actualOutputTextPane, logTextPane));

        saveTestCasesMenuItem .addActionListener(new SheetCreatorActionListener(this, input1TextPane, input2TextPane,
                expectedOutputTextPane, actualOutputTextPane, operationTextPane, operationSignalsTextPane));

        testingMenu.add(checkOutputMenuItem);
        testingMenu.add(saveTestCasesMenuItem);
        menuBar.add(testingMenu);

        setJMenuBar(menuBar);
    }
    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main ui = new Main();
            ui.setVisible(true);
        });
    }
}
