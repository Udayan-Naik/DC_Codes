import java.rmi.Naming;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        int num1, num2;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        num1 = sc.nextInt();
        System.out.print("Enter second number:");
        num2 = sc.nextInt();
        try {
            
            Hello hello = (Hello) Naming.lookup("//localhost/Hello");
            System.out.println(hello.getMessage());
            hello.setAnswer(num1, num2);
            System.out.println(hello.returnAnswer());
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();

    }

}
