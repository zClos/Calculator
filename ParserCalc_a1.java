package Calculator;

import static java.lang.Character.isDigit;
import static java.lang.Integer.valueOf;

/**
 * Parser to calculator from string arithmetic expression such as addition, subtraction, multiplication, division.
 * Parsing string of expression from the left to the right
 * It gives exception if expression starts with '(' and ends to ')' but they don't have relations to each other.
 * Also it breaks if digits places before open parenthesis without any arithmetic for example 2(3+1) or gives wrong
 * result if digits places right after closes parenthesis for example (3+1)2.
 * If you want to multiply or divide any arithmetic expression and then add or subtract from this you must surround it to
 * parenthesises for example ((3+1)*3)+1 gives you 13 but (3+1)*3+1 gives 16.
 * Created by Alexandr Alchimyonok on 12.03.2017.
 */

class ParserCalc_a1 {

    static int eval(String expr) {
        if (expr.startsWith("(") && expr.endsWith(")")) {
            return eval(expr.substring(1, expr.length() - 1));
        } else if (expr.startsWith("(") && !expr.endsWith(")")) {
            int pos = 0, rightParenthesis = expr.length() - 1, parenthesisesCount = 0;
            while (isDigit(expr.charAt(pos)) || isCondition(expr, pos) || expr.charAt(pos) == '(' || parenthesisesCount != 0) {
                if (expr.charAt(pos) == '(') {
                    parenthesisesCount++;
                }
                pos++;
                if (expr.charAt(pos) == ')' && parenthesisesCount != 0) {
                    parenthesisesCount--;
                    if (parenthesisesCount == 0) {
                        pos++;
                        if (isCondition(expr, pos)) {
                            char operator = expr.charAt(pos);
                            rightParenthesis = pos;
                            return arithmetic(eval(expr.substring(1, --rightParenthesis)), eval(expr.substring
                                    (rightParenthesis + 2)), operator);
                        } else {
                            return eval(expr.substring(0, rightParenthesis));
                        }
                    }
                }
            }
        } else {
            int pos = 0;
            while (pos < expr.length() - 1) {
                if (isDigit(expr.charAt(pos))) {
                    pos++;
                } else {
                    int leftOperand = valueOf(expr.substring(0, pos));
                    char operand = expr.charAt(pos);
                    int rightOperand = eval(expr.substring(++pos));
                    return arithmetic(leftOperand, rightOperand, operand);
                }
            }
        }
        return valueOf(expr);
    }

    private static boolean isCondition(String expr, int pos) {
        switch (expr.charAt(pos)) {
            case '*':
            case '+':
            case '-':
            case '/':
            case '^':
                return true;
            default:
                return false;
        }
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
