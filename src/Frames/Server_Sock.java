/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

/**
 *
 * @author Aidi Othmane
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server_Sock {
  String x ;

    public String getX(String y) {
        x= y;
        return x;
    }

  
    public static void main(String[] args) {
        Server_Sock x = new Server_Sock();
        try
        {
            ServerSocket ser = new ServerSocket(9999);
            Socket sock = ser.accept();
           
            BufferedReader ed = new BufferedReader(new InputStreamReader(sock.getInputStream()));
           System.out.println("ok");
           while(x.getX(ed.readLine())!=null){
            String tmp = x.x;
             System.out.println("I Recieved :"+tmp);
           
           }
            
            
        }
      catch(Exception ex){}
        
        
    }}
    
