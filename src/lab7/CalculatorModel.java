package lab7;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class CalculatorModel extends Observable {
    private double memory = 0.0;
    private double currentResult = 0.0;
    private double firstOperand = 0.0;
    private String operator = "";
    private boolean operatorSelected = false;
    private boolean resultDisplayed = false;
    private String currentInput = "";

    public void inputNumber(String input) {
        if (resultDisplayed) {
            currentInput = input;
            resultDisplayed = false;
        } else {
            currentInput += input;
        }
        setChanged();
        notifyObservers(currentInput);
    }

    public void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            currentInput = "";
        }
        operator = op;
        operatorSelected = true;
        setChanged();
        notifyObservers(formatNumber(firstOperand));
    }

    public void calculate(String currentDisplay) {
        try {
            double operand = currentDisplay.isEmpty() ? firstOperand : Double.parseDouble(currentDisplay);
            if (operator.isEmpty() && !currentDisplay.isEmpty()) {
                currentResult = operand;
            } else {
                switch (operator) {
                    case "+":
                        currentResult = firstOperand + operand;
                        break;
                    case "-":
                        currentResult = firstOperand - operand;
                        break;
                    case "*":
                        currentResult = firstOperand * operand;
                        break;
                    case "/":
                        if (operand == 0) {
                            currentInput = "Error: Divide by 0";  // Update currentInput with the error message
                            setChanged();
                            notifyObservers(currentInput);  // Notify observers with the error message
                            return;
                        }
                        currentResult = firstOperand / operand;
                        break;
                    case "sqrt":
                        if (operand < 0) {
                            setChanged();
                            notifyObservers("Error");
                            return;
                        }
                        currentResult = Math.sqrt(operand);
                        break;
                    case "square":
                        currentResult = operand * operand;
                        break;
                    default:
                        setChanged();
                        notifyObservers("Error");
                        return;
                }
            }
            setChanged();
            notifyObservers(String.valueOf(currentResult));
            resultDisplayed = true;
            operatorSelected = false;
            firstOperand = currentResult;
        } catch (NumberFormatException e) {
            setChanged();
            notifyObservers("Error");
        }
    }

    public void deleteLast() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            setChanged();
            notifyObservers(currentInput);
        }
    }

    public void memoryAdd() {
        if (resultDisplayed) {
            memory += currentResult;
            System.out.println("M+: Memory updated to " + memory); // Debug log
            setChanged();
            notifyObservers(String.valueOf(memory));
        } else {
            System.out.println("M+: Error - No valid result"); // Debug log
            setChanged();
            notifyObservers("Error");
        }
    }

    public void memorySubtract() {
        if (resultDisplayed) {
            memory -= currentResult;
            System.out.println("M-: Memory updated to " + memory); // Debug log
            setChanged();
            notifyObservers(String.valueOf(memory));
        } else {
            System.out.println("M-: Error - No valid result"); // Debug log
            setChanged();
            notifyObservers("Error");
        }
    }

    public void memoryRecallToDisplay() {
        System.out.println("MR: Recalling memory value " + memory); // Debug log
        currentInput = String.valueOf(memory);
        setChanged();
        notifyObservers(String.valueOf(memory));
        if (operatorSelected) {
            firstOperand = memory;
        }
    }

    public void memoryClear() {
        memory = 0.0;
        System.out.println("MC: Memory cleared to 0"); // Debug log
        setChanged();
        notifyObservers("0");
    }

    public double getCurrentResult() {
        return currentResult;
    }

    public boolean isResultDisplayed() {
        return resultDisplayed;
    }

    public void clearAll() {
        currentResult = 0.0;
        memory = 0.0; // This resets memory, ensure it's intentional
        firstOperand = 0.0;
        operator = "";
        operatorSelected = false;
        resultDisplayed = false;
        currentInput = "";
        System.out.println("Clear: Memory reset to 0"); // Debug log
        setChanged();
        notifyObservers("");
    }

    public boolean isOperatorSelected() {
        return operatorSelected;
    }

    public String getCurrentInput() {
        return currentInput;
    }

    public void setCurrentInput(String input) {
        currentInput = input;
    }

    private String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.valueOf(value);
        }
    }
}