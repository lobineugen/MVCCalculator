package com.lobin.eugene.View;


import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ViewCalc extends JFrame {
    private JLabel history = new JLabel(" ");
    private JTextField result;
    private JButton bAddition = new JButton("+");
    private JButton bSubtraction = new JButton("-");
    private JButton bMultiplication = new JButton("*");
    private JButton bDivision = new JButton("/");
    private JButton bExponent = new JButton("^");
    private JButton bResult = new JButton("=");
    private JButton bClear = new JButton("C");
    private JButton bClearE = new JButton("CE");
    private JButton bRemoveLastChar = new JButton("<");
    private static final int FONTSIZE = 10;
    private static final int WIDTHBUTTON =65;
    private static final int HEIGHTBUTTON = 40;
    private static final int HEIGHTFRAME = 250;
    private static final int WIDTHFRAME = 238;
    private static final int HEIGHTRESULT = 30;
    private static final int WIDTHRESULT = 20;
    private Font font = new Font("TimesRoman", Font.PLAIN, FONTSIZE);

    public ViewCalc() {
        super("Calculator");
        // маска для ввода Интеравала only int
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        result = new JFormattedTextField(formatter);
        result.setText("0");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bAddition.setPreferredSize(new Dimension(WIDTHBUTTON, HEIGHTBUTTON));
        result.setPreferredSize(new Dimension(WIDTHRESULT, HEIGHTRESULT));
        this.setSize(new Dimension(WIDTHFRAME, HEIGHTFRAME));
        history.setFont(font);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                        .addComponent(history)
                        .addComponent(result)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                        .addComponent(bRemoveLastChar)
                                        .addComponent(bAddition)
                                        .addComponent(bSubtraction))
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                        .addComponent(bClearE)
                                        .addComponent(bMultiplication)
                                        .addComponent(bDivision))
                                .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                        .addComponent(bClear)
                                        .addComponent(bExponent)
                                        .addComponent(bResult))))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, bAddition,
                bSubtraction, bMultiplication, bDivision, bExponent,
                bResult, bClear, bClearE, bRemoveLastChar);

        layout.linkSize(SwingConstants.VERTICAL, bAddition,
                bSubtraction, bMultiplication, bDivision, bExponent,
                bResult, bClear, bClearE, bRemoveLastChar);

        layout.linkSize(SwingConstants.VERTICAL, result);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(history)
                .addComponent(result)
                .addGroup(layout.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                        .addComponent(bRemoveLastChar)
                        .addComponent(bClear)
                        .addComponent(bClearE))
                .addGroup(layout.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                        .addComponent(bAddition)
                        .addComponent(bMultiplication)
                        .addComponent(bExponent))
                .addGroup(layout.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                        .addComponent(bSubtraction)
                        .addComponent(bDivision)
                        .addComponent(bResult))
        );
    }

    public void addCommandListener(ActionListener listener) {
        bAddition.addActionListener(listener);
        bDivision.addActionListener(listener);
        bMultiplication.addActionListener(listener);
        bExponent.addActionListener(listener);
        bSubtraction.addActionListener(listener);
    }

    public void addClearEListener(ActionListener listener) {
        bClearE.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        bClear.addActionListener(listener);
    }

    public void addRemoveLastCharListener(ActionListener listener) {
        bRemoveLastChar.addActionListener(listener);
    }

    public void addResultListener(ActionListener listener) {
        bResult.addActionListener(listener);
    }

    public void add(DocumentListener listener) {
        result.getDocument().addDocumentListener(listener);
    }

    public int getResult() {
        return Integer.parseInt(result.getText());
    }

    public void setResult(String result) {
        this.result.setText(result);
    }

    public void setHistory(String history) {
        this.history.setText(history);
    }
}
