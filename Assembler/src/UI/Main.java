package UI;

import Assembler.Assembler;
import Utils.BinaryOperations;
import Utils.FileHandler;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame {
    private JTextPane assemblyCodeTextArea;
    private JTextArea binaryCodeTextArea;
    private JTable symbolTable;
    private JTextArea errorDetailsTextArea;
    private  JButton assembleButton;
    private JButton hexButton;
    private JScrollPane assemblyScrollPane;
    private JScrollPane binaryScrollPane;
    private JScrollPane tableScrollPane;
    private JScrollPane errorScrollPane;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private FileHandler fh;

    public Main() {

        fh = new FileHandler(this);

        setTitle("RISC Assembler");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        initializeTextAreas();
        initializeMenuBar();

        add(mainPanel);
        setJMenuBar(menuBar);


        setVisible(true);
    }



    private void initializeTextAreas() {

        // Create JTextPane instead of JTextArea for assembly code
        assemblyCodeTextArea = new JTextPane();
        assemblyCodeTextArea.setDocument(new DefaultStyledDocument());
        assemblyCodeTextArea.setMargin(new Insets(5, 5, 5, 5));
        assemblyCodeTextArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                SyntaxHighlighter.highlightSyntax(assemblyCodeTextArea);
            }

            public void removeUpdate(DocumentEvent e) {
                SyntaxHighlighter.highlightSyntax(assemblyCodeTextArea);
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });

        // Use JScrollPane for scrolling
        assemblyScrollPane = new JScrollPane(assemblyCodeTextArea);

        binaryCodeTextArea = new JTextArea();
        binaryCodeTextArea.setRows(10);
        binaryCodeTextArea.setColumns(16);
        binaryCodeTextArea.setEditable(false);
        binaryScrollPane = new JScrollPane(binaryCodeTextArea);

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Label", "Address"}, 0);
        symbolTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(symbolTable);

        errorDetailsTextArea = new JTextArea();
        errorDetailsTextArea.setRows(5);
        errorDetailsTextArea.setColumns(50);
        errorDetailsTextArea.setEditable(false);
        errorScrollPane = new JScrollPane(errorDetailsTextArea);

        assembleButton = new JButton("Assemble");
        hexButton = new JButton("Hex");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5; // Make assembly code take half of the width
        gbc.weighty = 0.75; // Make assembly code take 75% of the height
        mainPanel.add(assemblyScrollPane, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.25; // Make binary code take quarter of the width
        mainPanel.add(binaryScrollPane, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        mainPanel.add(tableScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1; // Make error details stretch across all columns
        gbc.weighty = 0.1; // Make error details take 10% of the height
        mainPanel.add(errorScrollPane, gbc);
    }


private void initializeMenuBar() {
    // Create the menu bar
    menuBar = new JMenuBar();


    // Create the File menu
    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);

    // Create the Open menu item
    JMenuItem openMenuItem = new JMenuItem("Open Assembly");
    openMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            fh.openFromFile(assemblyCodeTextArea);
        }
    });
    fileMenu.add(openMenuItem);

    // Create the Save menu item
    JMenuItem saveMenuItem = new JMenuItem("Save Assembly");
    saveMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            fh.saveToFile(assemblyCodeTextArea);
        }
    });
    fileMenu.add(saveMenuItem);

    // Create the Save menu item
    JMenuItem saveBinaryMenuItem = new JMenuItem("Save Binary");
    saveBinaryMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            fh.saveToFile(binaryCodeTextArea);
        }
    });
    fileMenu.add(saveBinaryMenuItem);

    // Create the Save menu item
    JMenuItem saveDebugCode = new JMenuItem("Save Debug Code");
    saveDebugCode.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            fh.saveToFile(errorDetailsTextArea);
        }
    });
    fileMenu.add(saveDebugCode);

    // Create the Save menu item
    JMenuItem dumpTestCode = new JMenuItem("Dump Test Code");
    dumpTestCode.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder dump = new StringBuilder();
            dump.append("Input Code:\n------------------------\n" + assemblyCodeTextArea.getText());
            dump.append("\n ------------------------\n" + "Output binary:\n ------------------------\n" + binaryCodeTextArea.getText());
            dump.append("\n ------------------------\n" + "Debug Log:\n ------------------------\n" + errorDetailsTextArea.getText());
            fh.saveToFile(dump.toString());
        }
    });
    fileMenu.add(dumpTestCode);

    // Create the Save menu item
    JMenuItem closeMenuItem = new JMenuItem("Close");
    closeMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showOptionDialog(null, "There might be unsaved work, save before close?",
                    "Warning", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, null, null);

            if (option == JOptionPane.YES_OPTION) {
                // User clicked Yes
                fh.saveToFile(assemblyCodeTextArea);
                System.exit(0);
            } else if (option == JOptionPane.NO_OPTION) {
                // User clicked No
                System.exit(0);
            } else if (option == JOptionPane.CANCEL_OPTION) {
                // User clicked Cancel
            } else if (option == JOptionPane.CLOSED_OPTION) {
                // User closed the dialog without clicking any button
            }
        }
    });
    fileMenu.add(closeMenuItem);

    JMenu buildMenu = new JMenu("Build");
    menuBar.add(buildMenu);

    JMenuItem assemble = new JMenuItem("Assemble Code");
    assemble.addActionListener(new AssemblingOperation());
    buildMenu.add(assemble);

    JMenuItem convertHEX = new JMenuItem("Convert to HEX");
    convertHEX.addActionListener(new HexConversion());
    buildMenu.add(convertHEX);

    JMenuItem clearErrors = new JMenuItem("Clear Errors Console");
    clearErrors.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            errorDetailsTextArea.setText("");
        }
    });
    buildMenu.add(clearErrors);

}
private class HexConversion implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        String machineCodes = binaryCodeTextArea.getText();
        String[] codes = machineCodes.split("\n");
        StringBuilder hexCodes = new StringBuilder("");
        try {
            hexCodes.append("v2.0 raw\n");
            for (String code : codes) {
                code = BinaryOperations.binaryToHex(code);
                hexCodes.append(code);
                hexCodes.append("\n");
            }
        } catch (Exception ex) {
            errorDetailsTextArea.setText(errorDetailsTextArea.getText() + "\n" + "ERROR: The code has already been converted to HEX");
        }
        binaryCodeTextArea.setText(hexCodes.toString());
    }


}
private class AssemblingOperation implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        Assembler as = new Assembler(assemblyCodeTextArea.getText());
        symbolTable.setModel(new DefaultTableModel(as.getSymbols(),
                new String[]{"Label","Address"}));
        try {
            as.assemble();
            binaryCodeTextArea.setText(as.getBinaryCode());
            errorDetailsTextArea.setText(errorDetailsTextArea.getText()
            + "\n" + as.getDebugCode());
        } catch (RuntimeException ex) {
            errorDetailsTextArea.setText(errorDetailsTextArea.getText() + "\n" + ex.getMessage());
            ex.printStackTrace();
        }



    }
}

    public static void main(String[] args) {
        new Main();
    }
}
