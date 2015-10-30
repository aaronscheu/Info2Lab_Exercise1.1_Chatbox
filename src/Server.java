import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by amaridev on 28/10/15.
 * Package: PACKAGE_NAME for Exercise1.1_Chatbox.
 */
public class Server {
    private int iPort;
    ServerSocket serverSocket = null;
    Socket socket = null;
    InputStream inputStream = null;
    PrintWriter outputStream = null;
    String line = "abc";


    public Server(int iPort){
        this.iPort = iPort;
    }

    public void start(){
        try{
            serverSocket = new ServerSocket(iPort);
        }
        catch (IOException e){
            System.out.println(e);
            System.exit(1);
        }

        try{
            System.out.println("test");
            socket = serverSocket.accept();

            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readUTF()) != null) {
                System.out.println(inputLine);
                if(inputLine == "bye") break;
            }

            in.close();
            socket.close();
            serverSocket.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        Server server = new Server(8000);
        server.start();

    }

}
