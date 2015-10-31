import java.io.*;
import java.net.*;

/**
 * Created by amaridev on 28/10/15.
 * Package: PACKAGE_NAME for Exercise1.1_Chatbox.
 */

public class ChatServer {
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream streamIn = null;
    private int iPort;

    public ChatServer(int i) {
        this.iPort = i;
    }

    public void init(){
        try {
            System.out.println("Binding to port " + iPort + ", please wait  ...");
            server = new ServerSocket(iPort);
            System.out.println("Server started: " + server);
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted: " + socket);
            open();
            boolean done = false;
            while (!done) {
                try {
                    String line = streamIn.readUTF();
                    System.out.println(line);
                    done = line.equals(".bye");
                } catch (IOException ioe) {
                    done = true;
                }
            }
            close();
        }
        catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

    private void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    private void close() throws IOException {
        if (socket != null)
            socket.close();
        if (streamIn != null)
            streamIn.close();
    }

    public static void main(String[] args) {
        if (args.length != 1)
            System.out.println("No parameter found! --> port");
        else {
            ChatServer server = new ChatServer(Integer.parseInt(args[0]));
            server.init();
        }
    }
}