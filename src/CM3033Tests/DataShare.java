/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CM3033Tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Sam Cusson 1006286
 */
public class DataShare {

    ////////////////////////////
    //////   VARIABLES   ///////
    ////////////////////////////
    //integer for min and max 
    int min, max, delay;
    // Store wether to connect or not
    public static volatile boolean connect = false;
    // Store if connected or not
    public static volatile boolean connected = false;
    // Store if running or not
    public static volatile boolean running = true;
    // Store the max/min limits
    public static volatile String maxMin = "";
    // BPM
    private int BPM;
    private Alarm a;
    private Calendar start;
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    ////////////////////////////
    //////  CONSTRUCTOR  ///////
    ////////////////////////////
    public DataShare() {
        min = 0;
        max = 0;
        delay = 1;
        a = new Alarm(0, 0);
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
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

    public void setAlarm(Alarm a) {
        this.a = a;
    }

    public void setBPM(int BPM) {
        this.BPM = BPM;
    }

    public int getBPM() {
        return BPM;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String genTime(int temp) throws InterruptedException {
        start = Calendar.getInstance();
        Date time = start.getTime();
        String str = "BPM generated : " + temp + ", generated at :" + dateFormat.format(time.getTime());
        return str;
    }
}
