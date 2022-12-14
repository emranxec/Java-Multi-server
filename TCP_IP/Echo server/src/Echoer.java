import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread{

    private Socket socket;

    public Echoer(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader input=new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter output=new PrintWriter(socket.getOutputStream(),true);

            while(true){
                String echoString=input.readLine();
                if(echoString.equals("exit")){
                    break;
                }
               /* try {
                    Thread.sleep(15000);
                }catch(InterruptedException e){
                    System.out.println("Sleep exception");
                }*/
                System.out.println("Client said :" +echoString);
               output.println(echoString);
            }
    }catch (IOException e){
            System.out.println("Some exception at server");
        }finally {
            try {
                socket.close();
            }catch (IOException e){

            }
        }
    }
}
