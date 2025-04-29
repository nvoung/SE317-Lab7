package lab7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class CalculatorView extends JFrame implements Observer {
    private final JTextField display = new JTextField();
    private final JButton[] digitButtons = new JButton[10];
    private final JButton addButton = new JButton("+");
    private final JButton subButton = new JButton("-");
    private final JButton mulButton = new JButton("*");
    private final JButton divButton = new JButton("/");
    private final JButton sqrtButton = new JButton("√");
    private final JButton squareButton = new JButton("x²");
    private final JButton decimalButton = new JButton(".");
    private final JButton equalButton = new JButton("=");
    private final JButton clearButton = new JButton("C");
    private final JButton deleteButton = new JButton("Del");
    private final JButton memAddButton = new JButton("M+");
    private final JButton memSubButton = new JButton("M-");
    private final JButton memRecallButton = new JButton("MR");
    private final JButton memClearButton = new JButton("MC");

    public CalculatorView() {
        setTitle("MVC Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);

        JPanel panel = new JPanel(new GridLayout(6, 4, 5, 5));
        for (int i = 0; i <= 9; i++) {
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].setName("digit" + i);
        }

        addButton.setName("add");
        subButton.setName("subtract");
        mulButton.setName("multiply");
        divButton.setName("divide");
        sqrtButton.setName("sqrt");
        squareButton.setName("square");
        decimalButton.setName("decimal");
        equalButton.setName("equals");
        clearButton.setName("clear");
        deleteButton.setName("delete");
        memAddButton.setName("memAdd");
        memSubButton.setName("memSubtract");
        memRecallButton.setName("memRecall");
        memClearButton.setName("memClear");

        // Add buttons to panel
        panel.add(digitButtons[7]);
        panel.add(digitButtons[8]);
        panel.add(digitButtons[9]);
        panel.add(addButton);
        panel.add(digitButtons[4]);
        panel.add(digitButtons[5]);
        panel.add(digitButtons[6]);
        panel.add(subButton);
        panel.add(digitButtons[1]);
        panel.add(digitButtons[2]);
        panel.add(digitButtons[3]);
        panel.add(mulButton);
        panel.add(decimalButton);
        panel.add(digitButtons[0]);
        panel.add(equalButton);
        panel.add(divButton);

        panel.add(sqrtButton);
        panel.add(squareButton);
        panel.add(deleteButton);
        panel.add(clearButton);

        panel.add(memAddButton);
        panel.add(memSubButton);
        panel.add(memRecallButton);
        panel.add(memClearButton);

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public void addCalculatorListener(ActionListener listener) {
        for (JButton button : digitButtons) {
            button.addActionListener(listener);
        }
        addButton.addActionListener(listener);
        subButton.addActionListener(listener);
        mulButton.addActionListener(listener);
        divButton.addActionListener(listener);
        sqrtButton.addActionListener(listener);
        squareButton.addActionListener(listener);
        decimalButton.addActionListener(listener);
        equalButton.addActionListener(listener);
        clearButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        memAddButton.addActionListener(listener);
        memSubButton.addActionListener(listener);
        memRecallButton.addActionListener(listener);
        memClearButton.addActionListener(listener);
    }

    public void setDisplayText(String text) {
        if (text != null && text.endsWith(".0")) {
            text = text.substring(0, text.length() - 2);
        }
        display.setText(text);
    }

    public String getDisplayText() {
        return display.getText();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            // Update the display with whatever the model is notifying
            setDisplayText((String) arg);
        }
    }

}
