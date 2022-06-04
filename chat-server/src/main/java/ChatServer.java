import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(3001)) {

            Socket socket = serverSocket.accept();
            System.out.println("Пользователь подключился!");
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String rLine;
            while ((rLine = reader.readLine()) != null) {
                writer.write("Сервер получил сообщение: \n" + rLine + "\n---------------\n");
                writer.flush();
            }

        } catch(IOException e) {
            throw new RuntimeException(e);
        }

    }

}
