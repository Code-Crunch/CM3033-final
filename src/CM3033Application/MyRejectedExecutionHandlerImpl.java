/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CM3033Application;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author Marina Shchukina 1014481
 */
public class MyRejectedExecutionHandlerImpl implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        System.out.println(runnable.toString() + " : I've been rejected ! ");
    }
}
  
