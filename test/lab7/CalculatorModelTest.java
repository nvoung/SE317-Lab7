package lab7;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorModelTest {
    private CalculatorModel model;

    @BeforeEach
    public void setUp() {
        model = new CalculatorModel();
    }

    @Test
    public void testAddition() {
        model.inputNumber("10");
        model.setOperator("+");
        model.calculate("5");
        assertEquals(15.0, model.getCurrentResult(), "Addition failed");
    }

    @Test
    public void testSubtraction() {
        model.inputNumber("10");
        model.setOperator("-");
        model.calculate("5");
        assertEquals(5.0, model.getCurrentResult(), "Subtraction failed");
    }

    @Test
    public void testMultiplication() {
        model.inputNumber("3");
        model.setOperator("*");
        model.calculate("5");
        assertEquals(15.0, model.getCurrentResult(), "Multiplication failed");
    }

    @Test
    public void testDivision() {
        model.inputNumber("10");
        model.setOperator("/");
        model.calculate("2");
        assertEquals(5.0, model.getCurrentResult(), "Division failed");
    }

    @Test
    public void testDivisionByZero() {
        model.inputNumber("10");
        model.setOperator("/");
        model.calculate("0");
        // Fixing the assertion to expect "Error: Divide by 0" as per your model's logic
        assertEquals("Error: Divide by 0", model.getCurrentInput(), "Division by zero should return an error");
    }


    @Test
    public void testSquareRoot() {
        model.inputNumber("9");
        model.setOperator("sqrt");
        model.calculate("9");
        assertEquals(3.0, model.getCurrentResult(), "Square root operation failed");
    }

    @Test
    public void testSquare() {
        model.inputNumber("5");
        model.setOperator("square");
        model.calculate("5");
        assertEquals(25.0, model.getCurrentResult(), "Square operation failed");
    }

    @Test
    public void testMemoryAdd() {
        model.inputNumber("10");
        model.setOperator("+");
        model.calculate("5");
        model.memoryAdd();
        assertEquals(15.0, model.getCurrentResult(), "Memory add operation failed");
    }

    @Test
    public void testMemorySubtract() {
        model.inputNumber("10");
        model.setOperator("+");
        model.calculate("5");
        model.memoryAdd(); // First add to memory
        model.memorySubtract(); // Now subtract from memory
        assertEquals(15.0, model.getCurrentResult(), "Memory subtract operation failed");
    }

    @Test
    public void testMemoryRecall() {
        model.inputNumber("10");
        model.setOperator("+");
        model.calculate("5");
        model.memoryAdd(); // Add to memory
        model.memoryRecallToDisplay();
        assertEquals(15.0, model.getCurrentResult(), "Memory recall operation failed");
    }

    @Test
    public void testMemoryClear() {
        model.inputNumber("10");
        model.setOperator("+");
        model.calculate("5");
        model.memoryAdd(); // Add to memory
        model.memoryClear(); // Clear memory
        model.clearAll();
        model.memoryRecallToDisplay();
        assertEquals(0.0, model.getCurrentResult(), "Memory clear operation failed");
    }

    @Test
    public void testClearAll() {
        model.inputNumber("10");
        model.setOperator("+");
        model.calculate("5");
        model.clearAll();
        assertEquals(0.0, model.getCurrentResult(), "Clear all operation failed");
    }


    @Test
    public void testResultDisplayedFlag() {
        model.inputNumber("10");
        model.setOperator("+");
        model.calculate("5");
        assertTrue(model.isResultDisplayed(), "Result displayed flag is incorrect");
    }
}
