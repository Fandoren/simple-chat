import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

    public static void main(String[] args) {
        try {

            Socket socket = new Socket("127.0.0.1", 3001);

            WriterThread writer = new WriterThread(socket);
            writer.start();
            ReaderThread reader = new ReaderThread(socket);
            reader.start();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class WriterThread extends Thread {

    Socket serverSocket;
    public WriterThread(Socket serverSocket){
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        PrintWriter out;
        try {
            out = new PrintWriter(serverSocket.getOutputStream(), true);
            String userInput;

            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReaderThread extends Thread {

    Socket serverSocket;
    public ReaderThread(Socket serverSocket){
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()));

            String outputFromServer="";
            while((outputFromServer=in.readLine())!= null){
                System.out.println(outputFromServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
