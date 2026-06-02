import java.util.List;
import java.util.stream.Collectors;

record Person(String name, int age) {}

public class RecordsExample {

    public static void main(String[] args) {

        Person p1 = new Person("Shreyas", 21);
        Person p2 = new Person("Nitin", 17);
        Person p3 = new Person("Ajay", 25);

        System.out.println("Individual Records:");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        List<Person> people = List.of(p1, p2, p3);

        List<Person> adults = people.stream().filter(person -> person.age() >= 18).collect(Collectors.toList());

        System.out.println("\nPeople age 18 or above:");
        adults.forEach(System.out::println);
    }
}