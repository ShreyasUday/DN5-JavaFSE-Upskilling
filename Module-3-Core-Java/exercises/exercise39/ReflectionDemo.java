import java.lang.reflect.Method;

public class ReflectionDemo {

    public static void main(String[] args) {

        try {

            Class<?> cls = Class.forName("GreetingService");

            Object obj = cls.getDeclaredConstructor().newInstance();

            Method[] methods = cls.getDeclaredMethods();

            System.out.println("Methods in GreetingService:");

            for (Method method : methods) {
                System.out.println("Method Name: " + method.getName());

                Class<?>[] params = method.getParameterTypes();

                System.out.println("Parameter Count: " + params.length);
            }

            Method method = cls.getDeclaredMethod("sayHello");

            method.invoke(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}