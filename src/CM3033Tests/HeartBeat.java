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
 * @author Florin Mazilu 1114040
 */
public class HeartBeat implements Runnable {

    //set a max value that the BPM can be 
    //create a input for the heartbeat
    private HeartBeatInput hbi;
    private Alarm a;
    private int BPM;
    //boolean that decides if the bpm should be user inputted or automatic
    private boolean automatic = false;
    //gen date time for logging when a BPM is automaticly generated
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private Calendar start = null;
    long tStart = System.currentTimeMillis();

    //constructor
    public HeartBeat(Alarm a) {

        hbi = new HeartBeatInput();
        this.a = a;
        hbi.setVisible(true);
    }

    //set automatic true
    public void isAutomatic() {
        automatic = true;
    }

    //returns whether the heartbeat is automatic or not 
    public boolean auto() {
        return automatic;
    }

    public int getCurrentBPM() {
        return BPM;
    }

    //generates a time for the BPM
    public String genTime(int rand) throws InterruptedException {
        start = Calendar.getInstance();
        Date time = start.getTime();
        String str = "BPM generated : " + rand + ", generated at :" + dateFormat.format(time.getTime());
        return str;
    }

    //set the automatic to false
    public void isManual() {
        automatic = false;
    }

    //set the max and min value for generating a random input
    //generate a random integer between 0 and the max value
    public void getRandom() {
        //generate a temp value for the random;
        if (!a.active()) {
            int temp = 0;
            if (!hbi.isVisible()) {
                hbi.setVisible(true);
                temp = hbi.getBpm();
                BPM = temp;
            }
        }
    }

    public HeartBeatInput getHBI() {
        return hbi;
    }

    @Override
    public void run() {
        while (true) {
            long tEnd = System.currentTimeMillis();
            long tDelta = tEnd - tStart;
            double elapsedSeconds = tDelta / 1000.0;
            if (elapsedSeconds > hbi.getDelay()) {
                tStart = System.currentTimeMillis();
                getRandom();
            }
        }
    }
}
