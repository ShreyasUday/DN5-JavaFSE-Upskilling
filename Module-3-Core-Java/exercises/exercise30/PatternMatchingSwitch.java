public class PatternMatchingSwitch {

    public static void identifyType(Object obj) {

        switch (obj) {

            case Integer i ->
                    System.out.println(i + " is an Integer");

            case String s ->
                    System.out.println("\"" + s + "\" is a String");

            case Double d ->
                    System.out.println(d + " is a Double");

            case Boolean b ->
                    System.out.println(b + " is a Boolean");

            case null ->
                    System.out.println("Object is null");

            default ->
                    System.out.println("Unknown Type");
        }
    }

    public static void main(String[] args) {

        identifyType(100);

        identifyType("Shreyas");

        identifyType(99.99);

        identifyType(true);

        identifyType(null);
    }
}