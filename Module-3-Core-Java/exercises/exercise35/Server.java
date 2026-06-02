import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server started...");
            System.out.println("Waiting for client connection...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connected.");

            InputStream inputStream = socket.getInputStream();

            byte[] buffer = new byte[1024];

            int bytesRead = inputStream.read(buffer);

            String message = new String(buffer, 0, bytesRead);

            System.out.println("Message from client: " + message);

            OutputStream outputStream = socket.getOutputStream();

            String reply = "Hello from Server!";

            outputStream.write(reply.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}