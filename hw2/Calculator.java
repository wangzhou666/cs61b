import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    private EquationList listHead = null;

    public int add(int x, int y) {
        // YOUR CODE HERE
        int carry = 0, result = 0;
        for(int i = 0; i < 32; i++)
        {
            int n1 = (x & (1<<(i)))>>(i); //find the nth bit of p
            int n2 = (y & (1<<(i)))>>(i); //find the nth bit of q

            int s = (n1 ^ n2) ^ carry; //sum of bits
            carry = (carry==0) ? (n1&n2): (n1 | n2); //calculate the carry for next step
            result = result | (s<<(i)); //calculate resultant bit
        }
        return result;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        // YOUR CODE HERE
        int carry = 0, result = 0;
        for (int i = 0; i < 32; i++) {
            if ((x & (1 << (i))) != 0) {
                carry = y << (i);
            } else {
                carry = 0;
            }
            result = add(result, carry);
        }
        return result;

}

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        if (listHead == null) {
            listHead = new EquationList(equation, result, null);
        } else {
            EquationList p = listHead;
            while (p.next != null) {
                p = p.next;
            }
            p.next = new EquationList(equation, result, null);
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
        EquationList p = listHead;
        while (p != null) {
            System.out.println(p.equation + " = " + p.result);
            p = p.next;
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // YOUR CODE HERE
        EquationList p = listHead;
        for (int i = 0; i < n; i++) {
            p = p.next;
        }
        System.out.println(p.equation + " = " + p.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList p = listHead;
        while (p.next.next != null) {
            p = p.next;
        }
        p.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        listHead = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int result = 0;
        EquationList p = listHead;
        while (p != null) {            
            result = add(result, p.result);
            p = p.next;
        }
        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int result = 1;
        EquationList p = listHead;
        while (p != null) {            
            result = multiply(result, p.result);
            p = p.next;
        }
        return result;
    }
}