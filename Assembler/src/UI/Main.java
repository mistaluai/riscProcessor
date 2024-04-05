package UI;

import Assembler.Assembler;
import Utils.FileHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame {
    private JTextArea assemblyCodeTextArea;
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

    public Main() {
        setTitle("RISC Assembler.Assembler");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        Font globalFont = new Font("Lucida Grande", Font.BOLD, 13);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        initializeTextAreas();
        initializeMenuBar();

        add(mainPanel);
        setJMenuBar(menuBar);


        setVisible(true);
    }



private void initializeTextAreas() {

    assemblyCodeTextArea = new JTextArea();
    assemblyCodeTextArea.setRows(10);
    assemblyCodeTextArea.setColumns(50);


    assemblyScrollPane = new JScrollPane(assemblyCodeTextArea);

    binaryCodeTextArea = new JTextArea();
    binaryCodeTextArea.setRows(10);
    binaryCodeTextArea.setColumns(16);
    binaryCodeTextArea.setEditable(false);
    binaryScrollPane = new JScrollPane(binaryCodeTextArea);

    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Label", "Address"}, 0);
    symbolTable = new JTable(tableModel);
    tableScrollPane = new JScrollPane(symbolTable);

    errorDetailsTextArea = new JTextArea();
    errorDetailsTextArea.setRows(5);
    errorDetailsTextArea.setColumns(50);
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
    gbc.weighty = 0.1; // Make error details take 25% of the height
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
            FileHandler.openFromFile(null, assemblyCodeTextArea);
        }
    });
    fileMenu.add(openMenuItem);

    // Create the Save menu item
    JMenuItem saveMenuItem = new JMenuItem("Save Assembly");
    saveMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileHandler.saveToFile(null, assemblyCodeTextArea);
        }
    });
    fileMenu.add(saveMenuItem);
    
    // Create the Save menu item
    JMenuItem saveBinaryMenuItem = new JMenuItem("Save Binary");
    saveMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileHandler.saveToFile(null, binaryCodeTextArea);
        }
    });
    fileMenu.add(saveBinaryMenuItem);

    JMenu buildMenu = new JMenu("Build");
    menuBar.add(buildMenu);

    JMenuItem assemble = new JMenuItem("Assemble Code");
    assemble.addActionListener(new AssemblingOperation());
    buildMenu.add(assemble);

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


    }
}

    public static void main(String[] args) {
        new Main();
    }
}
