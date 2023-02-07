import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) {
        ServerSocket ss;
        try {

            ss = new ServerSocket(5000);
            ss.setReuseAddress(true);
            // Socket soc = ss.accept();
            int clientId = 0;
            while(true){

                Socket newSocket = ss.accept();

                System.out.println("New client connected: " + newSocket.getInetAddress());
                ClientManager newClient = new ClientManager(newSocket, ++clientId);
                
                new Thread(newClient).start();
            }
            // DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
            // DataInputStream dis = new DataInputStream(soc.getInputStream());
            // String clientMessage = "";
            // String serverMessage = "";
            // // Scanner sc = new Scanner(System.in);
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // while(!clientMessage.equals("close") && !serverMessage.equals("close")) {

            //     clientMessage = dis.readUTF();
            //     System.out.println("Client Message: " + clientMessage);
            //     System.out.print("Enter server message: ");
            //     serverMessage = br.readLine();
            //     dos.writeUTF(serverMessage);
            //     dos.flush();

            // }
            // // dos.close();
            // dis.close();
            // soc.close();
            // ss.close();
        

        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    
    
}
