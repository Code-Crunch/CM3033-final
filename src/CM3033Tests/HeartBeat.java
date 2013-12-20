/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CM3033Tests;

import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Florin Mazilu 1114040
 */
public class HeartBeat {

    //create a random for the BPM
    private Random r;
    //set a max value that the BPM can be 
    private int max, min;
    //create a input for the heartbeat
    private HeartBeatInput hbi;
    private Alarm a;
    private int BPM;
    //boolean that decides if the bpm should be user inputted or automatic
    private boolean automatic = false;
    //gen date time for logging when a BPM is automaticly generated
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private Calendar start = null;

    //constructor
    public HeartBeat(int min, int max, Alarm a) {
        r = new Random();
        this.max = max;
        this.min = min;
        hbi = new HeartBeatInput();
        this.a = a;
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
        getRandom();
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
    public void setMaxMin(int min, int max) {
        this.min = min;
        this.max = max;
    }

    //generate a random integer between 0 and the max value
    public void getRandom() {
        //generate a temp value for the random;
        if (!a.active()) {
            int temp = 0;
            if (automatic) {
                temp = r.nextInt(max - min) + min;
                BPM = temp;
            } else {
                if (!hbi.isVisible()) {
                    hbi.setVisible(true);
                }
                temp = hbi.getBpm();
                BPM = temp;
            }
        }
    }

    int getTolerance() {
        return hbi.getTolerance();
    }

    int getDelay() {
        return hbi.getDelay();
    }
}
