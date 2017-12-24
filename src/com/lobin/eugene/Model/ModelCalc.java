package com.lobin.eugene.Model;


public class ModelCalc {
    private int result;
    private String lastOperation = "=";

    public void setResult(int result) {
        this.result = result;
    }

    public int calculate(int x) {
        switch (lastOperation) {
            case "+":
                result += x;
                break;
            case "-":
                result -= x;
                break;
            case "/":
                result /= x;
                break;
            case "*":
                result *= x;
                break;
            case "^":
                result = (int) Math.pow(result, x);
                break;
            case "=":
                result = x;
                break;
        }
        return result;

    }

    public void setLastOperation(String lastOperation) {
        this.lastOperation = lastOperation;
    }

    public int getResult(int x) {
        return calculate(x);
    }

}
