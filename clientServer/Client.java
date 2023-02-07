import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{

    
    public static void main(String[] args) {
        
        try {
            
            // Socket s = new Socket("localhost", 5000);
            // DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            // DataInputStream dis = new DataInputStream(s.getInputStream());
            // String clientMessage = "";
            // String serverMessage = "";
            // // Scanner sc = new Scanner(System.in);
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // while(!clientMessage.equals("close") && !serverMessage.equals("close")) {

            //     System.out.print("Enter Client Message: ");
            //     // clientMessage = sc.nextLine();
            //     clientMessage = br.readLine();
            //     dos.writeUTF(clientMessage);
            //     dos.flush();
            //     serverMessage = dis.readUTF();
            //     System.out.println("SERVER: " + serverMessage);
            // }

            // // sc.close();
            // s.close();
            Socket s = new Socket("localhost", 5000);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String clientMessage = "";
            String serverMessage = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(!clientMessage.equals("close")) {
                System.out.print("Client Message: ");
                clientMessage = br.readLine();
                dos.writeUTF(clientMessage);
                dos.flush();

                serverMessage = dis.readUTF();
                System.out.println("Server Message: " + serverMessage);
            }

            dos.close();
            dis.close();
            br.close();
            s.close();


            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}