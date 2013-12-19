package CM3033Tests;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Sam Cusson 1006286
 */
public class StartServer {

    public static void main(String[] args) throws IOException {
        // Set the thread size
        int tpSize = 4;
        // Start the thread pool
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                tpSize,
                tpSize,
                50000L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        ServerApp serverApp = new ServerApp();
       // ServerApp sv = new ServerApp();
        //serverApp.setVisible(true);
        try {
            // Try start the server on port 8189
            ServerSocket socketServer = new ServerSocket(8189);
            // Print that it is waiting for clients
            System.out.println("Server>Waiting For Clients...");
            // listen for a connection request on SocketServer
            // incoming is the connection socket
            for (;;) {
                // Accecpt the requested connection if there are less than 4 active threads
                 
                    Socket incoming = socketServer.accept();
                    // Start a server instance using the incoming conncetion
                    pool.execute(new Server(incoming,pool.getPoolSize(), serverApp));
                 
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        // Close the pool if an error occurs
        pool.shutdown();
    }
}
