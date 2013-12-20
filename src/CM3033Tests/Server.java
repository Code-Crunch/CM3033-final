/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CM3033Tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Sam Cusson 1006286
 */
public class Server implements Runnable {

    // store the incoming connection
    private Socket incoming;
    private int number;
    private ServerApp serverApp;

    // Create the server using the passed incoming code
    public Server(Socket incoming, int no, ServerApp serverApp) {
        // Set this's incoming to the passed on
        this.incoming = incoming;
        this.number = no;
        this.serverApp = serverApp; //@ MS
       
    }
    
    @Override
    public void run() {
        
        try {
             //serverApp.setVisible(true); //@ MS
            // Configure the listener/sender
            BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            PrintWriter out = new PrintWriter(incoming.getOutputStream(), true /* auto flush */);
            
            // Once connected, let the user know so by printing the details
            out.println(number + " You are connected to "
                    + incoming.getLocalAddress().getHostName()
                    + " on port " + incoming.getLocalPort()
            );
            // Print the connected machine
            System.out.println(incoming.getLocalAddress().getHostName() + " Connected " + number);
            // A boolean to store a while variable
            boolean finished = false;
            int bpm = 0;
            while (!finished) {
                bpm++;
                serverApp.updateBPM(number, bpm);
                serverApp.updateConnected(number);
                
                // if the listener is ready
                if (in.ready()) {
                    // create a String to store the sent data
                    String str = in.readLine().trim();
                    // If the string is not null
                    if (str != null) {
                        // Send the receive value back to the client
                        out.println("Received: " + str);
                        // Print the recieve value
                        System.out.println("Receieved: " + str);
                        // If the string is bye
                        //processInput(str);
                        if (str.trim().equals("BYE")) {
                            // Print that machine is disconnecting
                            System.out.println(incoming.getLocalAddress().getHostName() + " Disconnected");
                            // Set finised to true to exit the while
                            finished = true;
                        }
                    }
                }
            }
            // Disconnect the client
            serverApp.disconnect(number);
            incoming.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
