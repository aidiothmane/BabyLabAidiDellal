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
import java.net.Socket;


public class Client_Sock {
  
 BufferedReader in;
    public static void main(String[] args) {
       
        try
        {
            Socket sock = new Socket("127.0.0.1",9999);
            
            PrintStream pr = new PrintStream(sock.getOutputStream());
            String temp = "" ;
            while(!temp.equals("fin")){
            System.out.print("Enter Something : ");
          
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader ed = new BufferedReader(rd);
            
             temp = ed.readLine();
            
            pr.println(temp);
        
            }
            
        }
        catch(Exception ex)
        {
            
        }
        
    
    }
}