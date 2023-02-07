import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientManager implements Runnable {

    Socket s;
    int clientId;
    
    public ClientManager(Socket s, int clientId) {

        this.s = s;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage = "";
            String serverMessage = "";

            while(!clientMessage.equals("close")) {
                clientMessage = dis.readUTF();
                // System.out.println("Client " + s.getInetAddress() + " " + );
                System.out.printf("Client %d: %s\n", clientId, clientMessage);

                System.out.print("Message to Client " + clientId + ": ");
                serverMessage = br.readLine();
                dos.writeUTF(serverMessage);
                dos.flush();
            }

            dis.close();
            dos.close();
            s.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
