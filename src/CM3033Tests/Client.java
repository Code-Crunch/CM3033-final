/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CM3033Tests;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Sam Cusson 1006286
 */
public class Client implements Runnable {
    ////////////////////////////
    //////   VARIABLES   ///////
    ////////////////////////////

    // Stores the communication Socket 
    Socket requestSocket;
    // Stores the stream to write to
    PrintWriter out;
    // Stores the stream to read from
    BufferedReader in;
    // Stores the default value for the sending String
    String sendStr = "MaxMin:null", oldSendStr = "", bpm = "null", oldBPM = "";
    // Stores the string to check if the value should be sent
    // Stores the variable to store if messages have been recieved by the server
    int messageCount = 1;
    // Stores the frame for producing messages
    Component frame = null;
    // A blank variable to confirm the maxmin value was recieved
    // A variable to store the sahred data
    volatile DataShare shared;

    ////////////////////////////
    //////  CONSTRUCTOR  ///////
    ////////////////////////////
    public Client(DataShare t2) {
        // Set the local shared data to the passed shared Data.
        shared = t2;
    }

    @Override
    public void run() {
        // While running
        while (shared.isRunning()) {
            // Catch if the connection fails
            try {
                // If a connection is required
                if (shared.isConnect()) {
                    // If not already connected

                    if (!shared.isConnected()) {
                        // Set IP to the IP passed form dialog.
                        String ip = JOptionPane.showInputDialog("Input ip address");
                        // If the user input is not null.
                        if (ip != null) {
                            // Set the new Socket
                            requestSocket = new Socket(ip, 8189);

                            if (requestSocket.isConnected() && !requestSocket.isClosed()) {
                                // Print connection details
                                System.out.println("Client>Connected to "
                                        + requestSocket.getLocalAddress().getHostName()
                                        + " on port " + requestSocket.getLocalPort());
                                // configure the out stream
                                out = new PrintWriter(requestSocket.getOutputStream(), true /* auto flush */);
                                // configure the in stream
                                in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
                                // set the shared connected to true. 
                                shared.setConnected(true);
                                // Add to the message count (Becuase confirmation of connection from the server)
                                messageCount++;
                            }
                            // If the in stream is ready

                            // Set the max min to th shared maxmin
                            //sendStr = "MaxMin:" + shared.getMaxMin();
                            // If the send has been updated 
                            if (!sendStr.equals("MaxMin:" + shared.getMaxMin())) {
                                // Send the message
                                sendMessage("MaxMin:" + shared.getMaxMin());
                                // Set the sent message record
                                sendStr = shared.getMaxMin() + "";
                            }

                        } else {
                            // set shared connect and connected to false
                            shared.setConnect(false);
                            shared.setConnected(false);
                            // Show Dialog
                            JOptionPane.showMessageDialog(frame, "Please enter a valid IP.");
                        }
                    }
                    if (!bpm.equals("BPM:" + shared.getBPM())) {
                        // Send the message
                        sendMessage("BPM:" + shared.getBPM());
                        // Set the sent message record
                        bpm = "BPM:" + shared.getBPM();
                    }
                    
                    if (in.ready()) {
                        // If shared MaxMin is not null
                        if (shared.getMaxMin() != null) {
                            // Print the returned message from the server
                            System.out.println("Server>" + in.readLine());
                            // Add to the message count
                            messageCount++;
                        } else {
                            // Set the shared max min for some reason???
                            shared.setMaxMin("");
                        }
                    }

                    if (messageCount < 0) {
                        // send a confirmation message to disconnect
                        sendMessage("BYE");
                        // Set shared connected to false
                        shared.setConnected(false);
                        // If the shared is running
                        if (shared.isRunning()) {
                            // Print the server timeout
                            System.out.println("Server Timeout!");
                            sendStr = "MaxMin:null";
                        } else {
                            // stop the system
                            System.exit(0);
                        }
                    }
                    // If shared is connected
                } else if (shared.isConnected()) {
                    // Print disconnecting
                    System.out.println("Disconnecting");
                    // Send bye to disconnect from server
                    sendMessage("BYE");
                    // Set connected to false
                    sendStr = "MaxMin:null";
                    shared.setConnected(false);
                }
            } catch (IOException ex) {
                // Print the error message
                System.out.println(ex.toString());
                // Set shared connect to false
                shared.setConnect(false);
                // Show dialog connection failed
                JOptionPane.showMessageDialog(frame, "Connection Failed");
                // Not sure why this?
                shared.setConnected(true);
            }
        }
    }

    private void sendMessage(String msg) {
        // Send the message
        out.println(msg);
        // Print the message
        System.out.println("Clientmsg>" + msg);
        messageCount--;
    }
}
