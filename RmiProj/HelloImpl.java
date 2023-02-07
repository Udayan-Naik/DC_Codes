
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class HelloImpl extends UnicastRemoteObject implements Hello {

    private int answer;
    private String message;

    public HelloImpl(String message) throws RemoteException {
        this.message = message;
    }
    
    @Override
    public String getMessage() throws RemoteException{
        return message;
    }

    @Override
    public void setAnswer(int num1, int num2) throws RemoteException {
        this.answer = num1 + num2;


        
    }

    public int returnAnswer() {
        return answer;
    }
}
