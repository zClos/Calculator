package Calculator;

/**
 * Parser to calculator from string arithmetic expression such as addition, subtraction, multiplication, division.
 * Parsing string of expression from the right to the left.
 * If find arithmetic expression such starts with digit and ends to ')' uses method eval(expr) from class Parser_q.
 * This allows to parse an expression like (* 5-2 (13/6)) + 1.
 * It gives exception if expression starts with '(' and ends to ')' but they don't have relations to each other.
 * Created by Alex on 12.03.2017.
 */

import static java.lang.Character.isDigit;
import static java.lang.Integer.valueOf;

class ParserCalc_a2 {

    static int eval(String expr) {
        if (expr.startsWith("(") && expr.endsWith(")")) {
            return eval(expr.substring(1, expr.length() - 1));
        } else if (!expr.startsWith("(") && expr.endsWith(")")) {
            return ParserCalc_q.eval(expr);
        } else {
            int pos = expr.length() - 1;
            while (pos >= 0) {
                if (isDigit(expr.charAt(pos))) {
                    pos--;
                } else {
                    int rightOperand = valueOf(expr.substring(++pos));
                    char operand = expr.charAt(--pos);
                    int leftOperand = eval(expr.substring(0, pos));
                    return arithmetic(leftOperand, rightOperand, operand);
                }
            }
        }
        return valueOf(expr);

    }

    private static int arithmetic(int leftOperand, int rightOperand, char operand) {
        switch (operand) {
            case '*': return leftOperand * rightOperand;
            case '+': return leftOperand + rightOperand;
            case '-': return leftOperand - rightOperand;
            case '/': return leftOperand / rightOperand;
            case '^': return exponentiation(leftOperand, rightOperand);
            default: throw new ArithmeticException("Bad operand : " + operand);
        }
    }

    private static int exponentiation(int leftOperand, int exponent) {
        if (exponent == 0) {
            return 1;
        } else {
            return leftOperand * exponentiation(leftOperand, --exponent);
        }
    }

}
