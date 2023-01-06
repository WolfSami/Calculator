/**
 * Performs mathematical calculations for the calculator.
 */
public class Calculate {
    /**
     * Returns the double value of the String exp, or throws a SyntaxError if the
     * expression is mathematically invalid.
     * @param exp The expression to evaluate.
     */
    public static double calculateStr(String exp) throws SyntaxError {
        if (lastOperandIndex(exp) <= 0) {
            try {
                return Double.parseDouble(exp); }
            catch (NumberFormatException error) {
                throw new SyntaxError();
            }
        }
        if (!(isANumber(exp.charAt(exp.length() - 1)))) //if not a number
            throw new SyntaxError();
        else {
            int asi = lastASIndex(exp);
            if (asi > 0) {
                char lastAS = lastAS(exp);
                switch (lastAS) {
                    case '+':
                        return calculateStr(exp.substring(0, asi))
                                + calculateStr(exp.substring(asi + 1));
                    case '—':
                        return calculateStr(exp.substring(0, asi))
                                - calculateStr(exp.substring(asi + 1));
                }
            }
            else {
                int mdi = lastOperandIndex(exp);
                switch (lastOperand(exp)) {
                    case '*':
                        return calculateStr(exp.substring(0,mdi)) * calculateStr(exp.substring(mdi + 1));
                    case '/':
                        return calculateStr(exp.substring(0,mdi)) / calculateStr(exp.substring(mdi + 1));
                }
            }
        }
        throw new SyntaxError();
    }

    /**
     * Returns true if c is a number.
     */
    private static boolean isANumber(char c) {
        return (c >= 48 && c <= 57);
    }

    /**
     * Returns the index of the last instance of +,-,*, or / in exp.
     */
    private static int lastOperandIndex(String exp) {
        return Math.max(Math.max(exp.lastIndexOf('+'),exp.lastIndexOf('—')),
                Math.max(exp.lastIndexOf('*'),exp.lastIndexOf('/')));
    }

    /**
     * Returns the last instance of +,-,*,or / in exp.
     * Requires: at least one such operand exists in exp.
     */
    private static char lastOperand(String exp) {
        return exp.charAt(lastOperandIndex(exp));
    }

    /**
     * Returns the index of the last instance of + or - in exp.
     */
    private static int lastASIndex(String exp) {
        return Math.max(exp.lastIndexOf('+'),exp.lastIndexOf('—'));
    }

    /**
     * Returns the last instance of + or - in exp.
     * Requires: at least one such operand exists in exp.
     */
    private static char lastAS(String exp) {
        return exp.charAt(lastASIndex(exp));
    }

}
