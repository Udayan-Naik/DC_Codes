
import java.rmi.Naming;

public class HelloServer {

    public static void main(String[] args) {

        try{
            Hello message = new HelloImpl("Hello World");
            Naming.rebind("Hello", message);
            System.out.println("Hello server is ready");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
