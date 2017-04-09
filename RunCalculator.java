package Calculator;

/**
 * arithmetic expression for ParserCalc
 * Created by Alexandr Alchimyonok on 11.03.2017.
 */

public class RunCalculator {

    public static void main(String[] args) {
/** These work correctly with any ParserCalc*/
        System.out.println(">>> 123 = " + ParserCalc_q.eval("123"));
        System.out.println(">>> 2*3 = " + ParserCalc_q.eval("2*3"));
        System.out.println(">>> 2*(1+3) = " + ParserCalc_q.eval("2*(1+3)"));
        System.out.println(">>> 1+(5-2*(13/6)) = " + ParserCalc_q.eval("1+(5-2*(13/6))"));
        System.out.println(">>> 2^10 = " + ParserCalc_q.eval("2^10"));

/** These work correctly with any ParserCalc except ParserCalc_q */
        System.out.println(">>> (1+3)*2 = " + ParserCalc_a1.eval("(1+3)*2"));
        System.out.println(">>> ((1+3)*3)+1 = " + ParserCalc_a1.eval("((1+3)*3)+1"));
        System.out.println(">>> (5-2*(13/6))+1 = " + ParserCalc_a1.eval("(5-2*(13/6))+1"));

/** This one give correct result with ParserCalc_a2 but wrong with ParserCalc_a1 */
        System.out.println(">>> (1+3)*3+1 = " + ParserCalc_a2.eval("(1+3)*3+1"));

/** These correctly work with ParserCalc_a1 but give exception with ParserCalc_a2 */
        System.out.println(">>> (5-2*(13/6))*((1+3)*2)+1 = " + ParserCalc_a1.eval("(5-2*(13/6))*((1+3)*2)+1"));
        System.out.println(">>> (5-2*(13/6))*(((1+3)*2)/2)+1 = " + ParserCalc_a1.eval("(5-2*(13/6))*(((1+3)*2)/2)+1"));

/** This string doesn't work with any ParserCalc */
        try {
            System.out.println(">>> (5-2*(13/6))*((1+3)*2) = " + ParserCalc_q.eval("(5-2*(13/6))*((1+3)*2)"));
        }catch (Exception err){
            System.out.print(">>> (5-2*(13/6))*((1+3)*2) = ");
            System.err.println(err);
        }
    }


}
