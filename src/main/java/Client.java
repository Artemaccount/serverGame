import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final int PORT = 8080;
    public static final String HOST = "netology.homework";

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            while (clientSocket.isConnected()) {
                System.out.println(in.readLine());
                String input = scanner.nextLine();
                    out.println(input);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
