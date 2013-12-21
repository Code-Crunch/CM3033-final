package CM3033Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Sam Cusson 1006286
 * @author Marina Shchukina 1014481
 * This code was based on CM3033 Lab 2 Exercise 3 - ClientRequest.java
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

            while (!finished) {
                
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
                        } //if BYE
                        
                        /* START CODE Marina Shchukina,  1014481 */
                        
                        //passing the BMP value to the server GUI
                        if (str.trim().startsWith("BPM")) {
                           
                            System.out.println("My BPM: " + str + "\n");
                            
                            int bpm = Integer.parseInt(str.trim().substring(4));
                            serverApp.updateBPM(number, bpm);
                        } //if BPM is passed through
                        
                         //passing the max and min values to the server GUI
                        if (str.trim().startsWith("MaxMin")) {
                           
                            String tempStr = str.trim().substring(7); 
                            String[] split = tempStr.split(",");
                            
                            int max, min;
                            
                            min = Integer.parseInt(split[0]);
                            max = Integer.parseInt(split[1]);
                            
                            serverApp.updateMaxMin(number, max, min);
                            
                        } //if MinMax is passed through
                        
                        /* END CODE Marina Shchukina, 1014481 */
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
