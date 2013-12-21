package CM3033Application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Sam Cusson 1006286
 * This code was based on CM3033 Lab 2 Exercise 3 - Main Java File
 */

public class StartServer {
    

    public static void main(String[] args) throws IOException {
        // Set the thread size
        int tpSize = 4;
        int count = 1; //@MS
        
        RejectedExecutionHandler executionHandler = new MyRejectedExecutionHandlerImpl();
        // Start the thread pool
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                tpSize,
                tpSize,
                50000L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                executionHandler
                );
        
        ServerApp serverApp = new ServerApp(); //@MS
        Thread serverGUI = new Thread(serverApp); //@MS
        
        try {
            // Try start the server on port 8189
            ServerSocket socketServer = new ServerSocket(8189);
            // Print that it is waiting for clients
            System.out.println("Server>Waiting For Clients...");
            // listen for a connection request on SocketServer
            // incoming is the connection socket
            serverGUI.start(); //@MS
            for (;;) {
                // Acccept the requested connection if there are less than 4 active threads
                 
                    Socket incoming = socketServer.accept();
                    // Start a server instance using the incoming conncetion
                    pool.execute(new Server(incoming, count, serverApp));
                    count++;
                    
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        // Close the pool if an error occurs
        pool.shutdown();
    }
}
