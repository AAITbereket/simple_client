/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple_client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import static simple_client.Simple_client.clientSocket;

/**
 *
 * @author bereket
 */
public class dictionary_client {
    
    public String msg = null;
    
    Socket clientSocket = null;
    DataInputStream is = null;
    PrintStream os = null;
    DataInputStream inputLine = null;
    
    
    public void connect(){
        try {
                clientSocket = new Socket("localhost", 5566);
                os = new PrintStream(clientSocket.getOutputStream());
                is = new DataInputStream(clientSocket.getInputStream());
                inputLine = new DataInputStream(new BufferedInputStream(System.in));
            } catch (UnknownHostException e) {
                 System.err.println("Don't know about host");
            } catch (IOException e) {
                 System.err.println("Couldn't get I/O for the connection to host");
            }
    }
    
    
    public void clientSession(){
        
        if (clientSocket != null && os != null && is != null) {
            try {

              /*
               * Keep on reading from/to the socket till we receive the "Ok" from the
               * server, once we received that then we break.
               */
              System.out.println("The client started. Type any text. To quit it type 'Ok'.");
              String responseLine;
              os.println(inputLine.readLine());
              while ((responseLine = is.readLine()) != null) {
                System.out.println(responseLine);
                if (responseLine.indexOf("Ok") != -1) {
                  break;
                }
                
                if( ! msg.isEmpty()){
                    os.println(msg);
                }
                
              }

              /*
               * Close the output stream, close the input stream, close the socket.
               */
              os.close();
              is.close();
              clientSocket.close();
            } catch (UnknownHostException e) {
              System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
              System.err.println("IOException:  " + e);
            }
          }
        
    }
    
    public void sendMsg(String msg){
        System.out.println("hello there");
        this.msg = msg;
    }
    
}
