package Calculator;

import static java.lang.Character.isDigit;
import static java.lang.Integer.valueOf;

/**
 * Parser to calculator from string expression.
 * It gives exception if expression starts with '(' and doesn't end to ')'.
 * Source code of question and idea had taken from GolovachCourses {@Link https://www.youtube.com/user/KharkovITCourses/}
 * exponentiation had added by Alexandr Alchimyonok on 11.03.2017.
 */

class ParserCalc_q {

    static int eval(String expr) {
        if (expr.startsWith("(") && expr.endsWith(")")) {
            return eval(expr.substring(1, expr.length() - 1));
        } else if (expr.startsWith("(") && !expr.endsWith(")")) {
            throw new IllegalArgumentException("use the correct format of formula");
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
