/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CM3033Tests;

/**
 *
 * @author Sam Cusson 1006286
 */
public class DataShare {

    ////////////////////////////
    //////   VARIABLES   ///////
    ////////////////////////////
    // Store wether to connect or not
    public static volatile boolean connect = false;
    // Store if connected or not
    public static volatile boolean connected = false;
    // Store if running or not
    public static volatile boolean running = true;
    // Store the max/min limits
    public static volatile String maxMin = "";
    // BPM

    ////////////////////////////
    //////  CONSTRUCTOR  ///////
    ////////////////////////////
    public DataShare() {
    }

    //////////////////////////////
    ////// GETTERS/SETTERS ///////
    //////////////////////////////
    public boolean isConnect() {
        return connect;
    }

    public void setConnect(boolean connect) {
        DataShare.connect = connect;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        DataShare.connected = connected;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        DataShare.running = running;
    }

    public String getMaxMin() {
        return maxMin;
    }

    public void setMaxMin(String maxMin) {
        DataShare.maxMin = maxMin;
    }

}
