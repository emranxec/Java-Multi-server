import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try(Socket socket=new Socket("localhost",5000)){
            socket.setSoTimeout(5000);
            BufferedReader input= new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter echo=new PrintWriter(socket.getOutputStream(),true);

            Scanner scanner=new Scanner(System.in);
            String echoString;
            do{
                System.out.println("String to be echoed :");
                echoString=scanner.nextLine();
                echo.println(echoString);
                if(!echoString.equals("exit")){
                    System.out.println(input.readLine());
                }

            }while (!echoString.equals("exit"));

        }catch (SocketTimeoutException e){
            System.out.println("some exception during timeout");
        }catch (IOException e){
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
