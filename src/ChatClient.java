import java.net.*;
import java.io.*;

/**
 * Created by amaridev on 31/10/15.
 * Package: PACKAGE_NAME for Exercise1.1_Chatbox.
 */

public class ChatClient {
    private Socket socket              = null;
    private DataInputStream  console   = null;
    private DataOutputStream streamOut = null;
    private String serverName;
    private int serverPort;

    public ChatClient(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public void init(){
        System.out.println("Establishing connection. Please wait ...");
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            start();
        }
        catch(UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        }
        catch(IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
        String line = "";
        while (!line.equals(".bye")) {
            try {
                line = console.readUTF();
                streamOut.writeUTF(line);
                streamOut.flush();
            }
            catch(IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
            }
        }
        stop();
    }

    public void start() throws IOException {
        console   = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
    }

    public void stop() {
        try {
            if (console != null)
                console.close();

            if (streamOut != null)
                streamOut.close();

            if (socket != null)
                socket.close();
        }
        catch(IOException ioe) {
            System.out.println("Error closing ...");
        }
    }

    public static void main(String args[]) {
        ChatClient client = null;
        if (args.length != 2)
            System.out.println("No parameter fount! --> hostname port");
        else {
            client = new ChatClient(args[0], Integer.parseInt(args[1]));
            client.init();
        }
    }
}