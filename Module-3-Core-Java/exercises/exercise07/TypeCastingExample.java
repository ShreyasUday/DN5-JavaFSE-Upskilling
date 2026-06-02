public class TypeCastingExample {
    public static void main(String[] args) {

        double doubleValue = 25.75;
        int intFromDouble = (int) doubleValue;

        int intValue = 100;
        double doubleFromInt = (double) intValue;

        System.out.println("Original double value: " + doubleValue);
        System.out.println("Double cast to int: " + intFromDouble);

        System.out.println("Original int value: " + intValue);
        System.out.println("Int cast to double: " + doubleFromInt);
    }
}