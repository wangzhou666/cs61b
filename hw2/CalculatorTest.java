import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    /* Do not change this to be private. For silly testing reasons it is public. */
    public Calculator tester;

    /**
     * setUp() performs setup work for your Calculator.  In short, we 
     * initialize the appropriate Calculator for you to work with.
     * By default, we have set up the Staff Calculator for you to test your 
     * tests.  To use your unit tests for your own implementation, comment 
     * out the StaffCalculator() line and uncomment the Calculator() line.
     **/
    @Before
    public void setUp() {
        tester = new StaffCalculator(); // Comment me out to test your Calculator
        // tester = new Calculator();   // Un-comment me to test your Calculator
    }

    // TASK 1: WRITE JUNIT TESTS
    // YOUR CODE HERE
    @Test
    public void testAdd(){
        int expected_1 = 3 + 5;
        int expected_2 = 2 + 5;
        int expected_3 = -1 + 9;
        int result_1 = tester.add(3, 5);
        int result_2 = tester.add(2, 5);
        int result_3 = tester.add(-1, 9);
        assertEquals(expected_1, result_1);
        assertEquals(expected_2, result_2);
        assertEquals(expected_3, result_3);
    }

    /* Run the unit tests in this file. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(CalculatorTest.class);
    }       
}