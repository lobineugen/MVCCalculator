package com.lobin.eugene.Controller;

import com.lobin.eugene.Model.ModelCalc;
import com.lobin.eugene.View.ViewCalc;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerCalc {
    private ModelCalc modelCalc;
    private ViewCalc viewCalc;
    private StringBuilder history = new StringBuilder();
    private boolean start;
    private int count;
    private String lastCommand;
    private String currentCommand = "=";

    private ControllerCalc(ViewCalc viewCalc, ModelCalc modelCalc) {
        start = true;
        this.modelCalc = modelCalc;
        this.viewCalc = viewCalc;
        this.viewCalc.addClearListener(new Clear());
        this.viewCalc.addResultListener(new Result());
        this.viewCalc.addCommandListener(new Command());
        this.viewCalc.add(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                action();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                action();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                action();
            }

            void action() {
                if (start) {
                    start = false;
                }
            }
        });
        this.viewCalc.addClearEListener(new ClearE());
        this.viewCalc.addRemoveLastCharListener(new RemoveLastChar());

    }

    class ClearE implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewCalc.setResult("0");
        }
    }

    class RemoveLastChar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder result = new StringBuilder();
            result.append(getResult());
            if (result.length() > 1) {
                result.delete(result.length() - 1, result.length());
                setResult(Integer.parseInt(result.toString()));
            } else {
                setResult(0);
            }
        }
    }

    class Clear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clear();
        }
    }

    class Result implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            modelCalc.setLastOperation(currentCommand);
            if (lastCommand != null && lastCommand.equals("/") && getResult() == 0) {
                errorMessage();
            } else {
                setResult(modelCalc.getResult(getResult()));
            }
            viewCalc.setHistory(" ");
            history.delete(0, history.length());
            modelCalc.setResult(0);
            count = 0;
        }
    }

    class Command implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentCommand = e.getActionCommand();
            if (!start) {
                if (count++ == 0) {
                    modelCalc.setResult(getResult());
                    setHistory(history.append(getResult()).append(" ").append(currentCommand).append(" "));
                    start = true;
                    lastCommand = currentCommand;
                } else {
                    modelCalc.setLastOperation(lastCommand);
                    setHistory(history.append(getResult()).append(" ")
                            .append(e.getActionCommand()).append(" "));
                    if (lastCommand.equals("/") && getResult() == 0) {
                        errorMessage();
                    } else {
                        setResult(modelCalc.calculate(getResult()));
                    }
                    start = true;
                    lastCommand = currentCommand;
                }
            } else {
                history.delete(history.length() - 2, history.length())
                        .append(currentCommand).append(" ");
                setHistory(history);
                lastCommand = currentCommand;
            }
        }
    }

    public static void main(String[] args) {
        ViewCalc theView = new ViewCalc();
        ModelCalc theModel = new ModelCalc();
        ControllerCalc theController = new ControllerCalc(theView, theModel);
        theView.setVisible(true);
    }

    private void clear() {
        viewCalc.setResult("0");
        modelCalc.setResult(0);
        viewCalc.setHistory(" ");
        history.delete(0, history.length());
        count = 0;
    }

    private void setHistory(StringBuilder stringBuilder) {
        viewCalc.setHistory(stringBuilder.toString());
    }

    private int getResult() {
        return viewCalc.getResult();
    }

    private void setResult(int result) {
        viewCalc.setResult(Integer.toString(result));
    }

    private void errorMessage() {
        JOptionPane.showMessageDialog(null, "Error: Cannot divide by zero", "Error Massage",
                JOptionPane.ERROR_MESSAGE);
        clear();
    }


}

