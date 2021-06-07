import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

    public static void main(String[] args) {
        Random random = new Random();
        int answer = 1 + random.nextInt(100);
        int userTry = -1;
        try (ServerSocket server = new ServerSocket(Client.PORT);) {
            System.out.println("Server started!");
            Socket client = server.accept();
            System.out.print("Connection accepted.");
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out.println("Я загадал число от 0 до 100, угадай какое(end чтобы выйти): ");
            while (userTry != answer) {
                String input = in.readLine();
                if (input.equalsIgnoreCase("end")) {
                    out.println("До свидания!");
                    System.exit(0);
                } else if (!isDigit(input)) {
                    out.println("Ошибка! Введите число");
                } else {
                    userTry = Integer.parseInt(input);
                    if (userTry < answer) {
                        out.println("Число " + input + " меньше загаданного");
                    } else if (userTry > answer) {
                        out.println("Число " + userTry + " больше загаданного");
                    }
                }
            }
            out.println("Ты выиграл! Загаданное число: " + answer +
                    "\n До свидания!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
