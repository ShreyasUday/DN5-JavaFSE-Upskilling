import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5000);

            System.out.println("Connected to server.");

            OutputStream outputStream = socket.getOutputStream();

            String message = "Hello from Client!";

            outputStream.write(message.getBytes());

            InputStream inputStream = socket.getInputStream();

            byte[] buffer = new byte[1024];

            int bytesRead = inputStream.read(buffer);

            String reply = new String(buffer, 0, bytesRead);

            System.out.println("Reply from server: " + reply);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}