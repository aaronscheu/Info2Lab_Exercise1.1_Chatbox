import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by amaridev on 28/10/15.
 * Package: PACKAGE_NAME for Exercise1.1_Chatbox.
 */
public class Client {

    public static void main(String[] args){
        Socket socket;

        try {
            socket = new Socket("localhost", 8000);

            DataInputStream input = new DataInputStream(System.in);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            String line = "";
            while (!line.equals("bye")){
                line = input.readUTF();
                output.writeUTF(line);
                output.flush();
            }




        } catch (IOException e){
            System.out.println(e);
        }

    }


}
