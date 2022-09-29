import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            InetAddress address=InetAddress.getLocalHost(); //getByName()
            DatagramSocket sock= new DatagramSocket();
            Scanner scanner=new Scanner(System.in);
            String echoString;

            do{
                System.out.println("Enter String to be echoed");
                echoString=scanner.nextLine();

                byte[] buffer= echoString.getBytes();

                DatagramPacket packet=new DatagramPacket(buffer, buffer.length,address,5000);
                sock.send(packet);

                //dont use in real world
                byte[] bytes=new byte[50];
                packet =new DatagramPacket(bytes,bytes.length);
                sock.receive(packet);
                System.out.println("text recieved from server :" + new String(bytes,0,packet.getLength()));



            }while (!echoString.equals("exit"));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
