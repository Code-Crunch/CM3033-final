/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CM3033Application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Florin Mazilu 1114040
 */
//alarm class
public class Alarm {

    //int to determine the high and low tresholds for the alarm
    int low, high;
    //boolean to determin if its active and if it's too high or too low
    boolean isActive;
    boolean ishigh;
    //String that stores what the alarm was
    private String alarm;
    //for logging when an alarm is started and stopped
    final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Calendar start = null;
    Calendar stop = null;
    //strings for logging the alarms start and stop time
    private String s, st;

//constructor
    public Alarm(int low, int high) {
        isActive = false;
        ishigh = false;
        this.low = low;
        this.high = high;
        alarm = "";
    }

//activate the alarm
    public void activate() {
        start = Calendar.getInstance();
        Date time = start.getTime();
        st = dateFormat.format(time);
        isActive = true;
    }

//deactivate alarm
    public void deactivate() {
        stop = Calendar.getInstance();
        Date time = stop.getTime();
        s = dateFormat.format(time);
        isActive = false;
    }

//check if alarm is active
    public boolean active() {
        return isActive;
    }

//check if the value is between the treshold
    public void check(int bpm) {
        //check if it's to high
        if (bpm > high) {
            activate();
            ishigh = true;
        } //check if it's to low
        else if (bpm < low) {
            ishigh = false;
            activate();
        } else {
            deactivate();
        }
        //pop up's with the alarm
        if (active()
                && ishigh) {
            JOptionPane.showMessageDialog(null, "ALERT: HEART BEAT TOO HIGH");
            alarm = "ALERT: TOO HIGH, started at : " + st + ",stopped at :" + s;
        } else if (active()
                && !ishigh) {
            JOptionPane.showMessageDialog(null, "ALERT: HEART BEAT TOO LOW");
            alarm = "ALERT: TOO LOW, started at : " + st + ",stopped at :" + s;
        }
    }

//check how long it has been since the last response
    public void timeout(Calendar start, Calendar current) {
        if (start.compareTo(current) > 30000) {
            JOptionPane.showMessageDialog(null, "ALERT: NO RESPONSE FOR 30 SECONDS");
            alarm = "ALERT: NO RESPONSE FOR 30 SECONDS";
        }
    }

//get the alarm string
    public String info() {
        return alarm;
    }

//setters for the high and low values
    public void setLow(int low) {
        this.low = low;
    }

    public void setHigh(int high) {
        this.high = high;
    }

//set the alarm text outside of the alarm
    public void setInfo(String s) {
        alarm = s;
    }

}
