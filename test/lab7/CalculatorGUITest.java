package lab7;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import javax.swing.JButton;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorGUITest {
    private FrameFixture window;

    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = GuiActionRunner.execute(() -> new CalculatorView());
        new CalculatorController(model, view);
        window = new FrameFixture(view);
        window.show(); // Important!
    }

    @AfterEach
    public void tearDown() {
        if (window != null) {
            window.cleanUp();
        }
    }

    // Functional tests
    @Test
    public void testAddition() {
        window.button("digit1").click();
        window.button("digit2").click();
        window.button("add").click();
        window.button("digit3").click();
        window.button("digit4").click();
        window.button("equals").click();
        window.textBox().requireText("46");
    }

    @Test
    public void testDivisionByZero() {
        window.button("digit2").click();
        window.button("digit2").click();
        window.button("divide").click();
        window.button("digit0").click();
        window.button("equals").click();
        window.textBox().requireText("Error: Divide by 0");
    }

    @Test
    public void testSquareRoot() {
        window.button("digit9").click();
        window.button("sqrt").click();
        window.textBox().requireText("3");
    }

    @Test
    public void testMemoryAddRecall() {
        window.button("digit2").click();
        window.button("multiply").click();
        window.button("digit2").click();
        window.button("equals").click();
        window.button("memAdd").click();
        window.button("memClear").click();
        window.button("memRecall").click();
        window.textBox().requireText("0");
    }

    @Test
    public void testSubtraction() {
        window.button("digit5").click();
        window.button("digit0").click();
        window.button("subtract").click();
        window.button("digit2").click();
        window.button("digit5").click();
        window.button("equals").click();
        window.textBox().requireText("25");
    }

    @Test
    public void testSquareOperation() {
        window.button("digit6").click();
        window.button("square").click();
        window.textBox().requireText("36");
    }

    // "Only operands / results on screen" tests
    @Test
    public void testOperatorNotDisplayed() {
        window.button("digit1").click();
        window.button("digit2").click();
        window.button("add").click();
        window.textBox().requireText("12");        // never shows "+"
    }

    @Test
    public void testDecimalInput() {
        window.button("digit3").click();
        window.button("decimal").click();
        window.button("digit1").click();
        window.textBox().requireText("3.1");      // decimal appears properly
    }

    @Test
    public void testClearLeavesBlank() {
        window.button("digit9").click();
        window.button("clear").click();
        window.textBox().requireText("");         // blank, no stray operator
    }

    // Button-state tests
    @Test
    public void testAddButtonChangesState() {
        JButton addButton = window.button("add").target();
        assertTrue(addButton.isEnabled(), "Add button should be enabled before clicking.");
        window.button("add").click();
        assertTrue(addButton.isEnabled(), "Add button should stay enabled after clicking.");
    }

    @Test
    public void testMultiplyButtonChangesState() {
        JButton mulButton = window.button("multiply").target();
        assertTrue(mulButton.isEnabled(), "Multiply button should be enabled before clicking.");
        window.button("multiply").click();
        assertTrue(mulButton.isEnabled(), "Multiply button should stay enabled after clicking.");
    }

    @Test
    public void testSquareRootButtonChangesState() {
        JButton sqrtButton = window.button("sqrt").target();
        assertTrue(sqrtButton.isEnabled(), "Square root button should be enabled before clicking.");
        window.button("sqrt").click();
        assertTrue(sqrtButton.isEnabled(), "Square root button should stay enabled after clicking.");
    }
}
