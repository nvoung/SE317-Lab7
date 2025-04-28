package lab7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        view.addCalculatorListener(new ButtonListener());
        model.addObserver(view);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("[0-9]") || command.equals(".")) {
                if (model.isResultDisplayed()) {
                    model.setCurrentInput("");
                }
                model.inputNumber(command);
            } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                if (!model.getCurrentInput().isEmpty()) {
                    model.calculate(model.getCurrentInput());
                }
                model.setOperator(command);
            } else if (command.equals("√")) {
                model.setOperator("sqrt");
                if (model.getCurrentInput().isEmpty()) {
                    model.calculate(view.getDisplayText());
                } else {
                    model.calculate(model.getCurrentInput());
                }
                model.setCurrentInput("");
            } else if (command.equals("x²")) {
                model.setOperator("square");
                if (model.getCurrentInput().isEmpty()) {
                    model.calculate(view.getDisplayText());
                } else {
                    model.calculate(model.getCurrentInput());
                }
                model.setCurrentInput("");
            } else if (command.equals("=")) {
                if (!model.getCurrentInput().isEmpty() || model.isOperatorSelected()) {
                    model.calculate(model.getCurrentInput());
                }
                model.setCurrentInput("");
            } else if (command.equals("C")) {
                model.clearAll();
            } else if (command.equals("Del")) {
                model.deleteLast();
            } else if (command.equals("M+")) {
                System.out.println("Controller: M+ pressed, resultDisplayed=" + model.isResultDisplayed()); // Debug log
                model.memoryAdd();
            } else if (command.equals("M-")) {
                System.out.println("Controller: M- pressed, resultDisplayed=" + model.isResultDisplayed()); // Debug log
                model.memorySubtract();
            } else if (command.equals("MR")) {
                model.memoryRecallToDisplay();
            } else if (command.equals("MC")) {
                model.memoryClear();
            }
        }
    }
}