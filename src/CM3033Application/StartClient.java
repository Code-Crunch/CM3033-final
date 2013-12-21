/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CM3033Application;

import java.io.IOException;

/**
 *
 * @author Sam Cusson 1006286
 */
public class StartClient {

    // Create a datashare to pass data
    static volatile DataShare t1 = new DataShare();
    HeartBeatInput hb;

    public static void main(String args[]) throws IOException {
        // create the client Thread
        Thread client = new Thread(new Client(t1));
        // create a thread to store the GUI
        Thread clientGUI = new Thread(new ClientApp(t1));
        Thread heartbeat = new Thread(new HeartBeatInput(t1));
        // Start both threads
        clientGUI.start();
        client.start();
        heartbeat.start();
    }
}
