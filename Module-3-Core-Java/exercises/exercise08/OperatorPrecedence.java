public class OperatorPrecedence {
    public static void main(String[] args) {

        int result1 = 10 + 5 * 2;
        int result2 = (10 + 5) * 2;
        int result3 = 20 / 5 + 3 * 4;

        System.out.println("10 + 5 * 2 = " + result1);
        System.out.println("(10 + 5) * 2 = " + result2);
        System.out.println("20 / 5 + 3 * 4 = " + result3);

        System.out.println("\nOrder of Operations:");
        System.out.println("* and / are evaluated before +");
        System.out.println("Parentheses are evaluated first");
    }
}