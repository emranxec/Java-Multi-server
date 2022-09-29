import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        try{
            DatagramSocket socket=new DatagramSocket(5000);

            while (true){
                byte[] buffer=new byte[50];
                DatagramPacket packet=new DatagramPacket(buffer,buffer.length);
                socket.receive(packet);
                System.out.println("text received is :"+ new String(buffer,0,packet.getLength()));

                //sending response back manually
                String returnString= "echo :"+ new String(buffer,0, packet.getLength());
                byte[] bytes=returnString.getBytes();
                InetAddress address=packet.getAddress();
                int port=packet.getPort();
                packet =new DatagramPacket(bytes,bytes.length,address,port);
                socket.send(packet);

            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*try(ServerSocket serverSocket=new ServerSocket(5000)){
           while (true){
               new Echoer(serverSocket.accept()).start();
           }
        }catch(IOException e){
            System.out.println("some exception at server");
        }*/
    }
}
