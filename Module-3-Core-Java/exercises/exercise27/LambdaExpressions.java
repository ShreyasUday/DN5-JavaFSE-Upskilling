import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExpressions {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("Shreyas");
        names.add("Nitin");
        names.add("Ajay");
        names.add("Anshuk");

        Collections.sort(names,
                (a, b) -> a.compareTo(b));

        System.out.println("Sorted List:");

        for (String name : names) {
            System.out.println(name);
        }
    }
}