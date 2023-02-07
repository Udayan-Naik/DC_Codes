
import java.rmi.*;

public interface Hello extends Remote {
    public String getMessage() throws RemoteException;

    public void setAnswer(int num1, int num2) throws RemoteException;

    public int returnAnswer() throws RemoteException;
}
