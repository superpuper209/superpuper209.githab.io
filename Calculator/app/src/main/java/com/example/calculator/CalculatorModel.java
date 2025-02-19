package com.example.calculator;


public class CalculatorModel {
    private int firstArg;
    private int secondArg;

    private StringBuilder inputStr = new StringBuilder();

    private int actionSelected;

    private State state;

    private enum State {
        firstArgInput,
        operationSelected,
        secondArgInput,
        resultShow
    }

    public CalculatorModel() {
        state = State.firstArgInput;
    }

    public void onNumPressed(int buttonId) {
        if (state == State.resultShow) {
            state = State.firstArgInput;
            inputStr.setLength(0);
        }
        if (state == State.operationSelected) {
            state = State.secondArgInput;
            inputStr.setLength(0);
        }
        if (inputStr.length() < 9) {
            if (buttonId == R.id.zero) {
                if (inputStr.length() != 0) {
                    inputStr.append("0");
                }
            } else if (buttonId == R.id.one) {
                inputStr.append("1");
            } else if (buttonId == R.id.two) {
                inputStr.append("2");
            } else if (buttonId == R.id.three) {
                inputStr.append("3");
            } else if (buttonId == R.id.four) {
                inputStr.append("4");
            } else if (buttonId == R.id.five) {
                inputStr.append("5");
            } else if (buttonId == R.id.six) {
                inputStr.append("6");
            } else if (buttonId == R.id.seven) {
                inputStr.append("7");
            } else if (buttonId == R.id.eight) {
                inputStr.append("8");
            } else if (buttonId == R.id.nine) {
                inputStr.append("9");
            }
        }
    }

    public void onActionPressed(int actionId) {
        if (actionId == R.id.equals && state == State.secondArgInput && inputStr.length() > 0) {
            secondArg = Integer.parseInt(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);
            if (actionSelected == R.id.plus) {
                inputStr.append(firstArg + secondArg);
            } else if (actionSelected == R.id.minus) {
                inputStr.append(firstArg - secondArg);
            } else if (actionSelected == R.id.multiply) {
                inputStr.append(firstArg * secondArg);
            } else if (actionSelected == R.id.division) {
                if (secondArg != 0) {
                    inputStr.append(firstArg / secondArg);
                } else {
                    inputStr.append("Ошибка: Деление на ноль");
                }
            }
        } else if (inputStr.length() > 0 && state == State.firstArgInput && actionId != R.id.equals) {
            firstArg = Integer.parseInt(inputStr.toString());
            state = State.operationSelected;
            actionSelected = actionId;
        }
    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        if (state == State.operationSelected) {
            return str.append(firstArg).append(' ')
                    .append(getOperationChar())
                    .toString();
        } else if (state == State.secondArgInput) {
            return str.append(firstArg).append(' ')
                    .append(getOperationChar())
                    .append(' ')
                    .append(inputStr)
                    .toString();
        } else if (state == State.resultShow) {
            return str.append(firstArg).append(' ')
                    .append(getOperationChar())
                    .append(' ')
                    .append(secondArg)
                    .append(" = ")
                    .append(inputStr.toString())
                    .toString();
        } else {
            return inputStr.toString();
        }
    }


    private char getOperationChar() {
        if (actionSelected == R.id.plus) return '+';
        else if (actionSelected == R.id.minus) return '-';
        else if (actionSelected == R.id.multiply) return '*';
        else if (actionSelected == R.id.division) return '/';
        else return ' ';
    }
    /*public void reset() {
        state = State.firstArgInput;
        inputStr.setLength(0);
        actionSelected = 0;
    }*/
}
