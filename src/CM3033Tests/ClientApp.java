package CM3033Tests;

import java.awt.Component;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Sam Cusson 1006286
 */
public class ClientApp extends javax.swing.JFrame implements Runnable {

    ////////////////////////////
    //////   VARIABLES   ///////
    ////////////////////////////
    // variable to store the maxLimit and minLimit
    private int highValue, lowValue;
    // variables to store the oldMaxLimit and oldMinLimit
    private int oldHighValue = highValue, oldLowValue = lowValue;
    // A date format template
    final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    // A calander to store the time of now and the time the application was started
    Calendar now = null, start = Calendar.getInstance();
    Alarm a;
    int heartbeatValue;
    int delay;
    long tStart = System.currentTimeMillis();
    //public volatile boolean running;
    //public volatile boolean connect;
    //public volatile boolean connected;
    //public volatile String maxMin = "";
    DataShare dataShare;
    ////////////////////////////
    //////  CONSTRUCTOR  ///////
    ////////////////////////////

    public ClientApp(DataShare ds2) throws IOException {
        // Initialise the components
        initComponents();

        // set the data share to that passed to this class
        dataShare = ds2;

        textSpace.setEditable(false);

        // Reset the max and min value dropdowns
        maxValue.removeAllItems();
        minValue.removeAllItems();

        // Add the options to the max/min
        maxValue.addItem(40);
        maxValue.addItem(60);
        maxValue.addItem(80);
        maxValue.addItem(100);
        maxValue.addItem(120);
        maxValue.addItem(140);
        maxValue.addItem(160);
        maxValue.addItem(180);
        maxValue.addItem(200);
        minValue.addItem(20);
        minValue.addItem(40);
        minValue.addItem(60);
        minValue.addItem(80);
        minValue.addItem(100);
        minValue.addItem(120);
        minValue.addItem(140);
        minValue.addItem(160);
        minValue.addItem(180);
        a = new Alarm(Integer.parseInt(maxValue.getSelectedItem().toString()), Integer.parseInt(minValue.getSelectedItem().toString()));
        dataShare.setMax(Integer.parseInt(maxValue.getSelectedItem().toString()));
        dataShare.setMin(Integer.parseInt(minValue.getSelectedItem().toString()));
        delay = 10000;
        dataShare.setAlarm(a);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        maxLabel = new javax.swing.JLabel();
        minLabel = new javax.swing.JLabel();
        currentTime = new javax.swing.JLabel();
        elapsedTime = new javax.swing.JLabel();
        maxValue = new javax.swing.JComboBox();
        minValue = new javax.swing.JComboBox();
        bpmLabel = new javax.swing.JLabel();
        opModeLabel = new javax.swing.JLabel();
        connectionButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textSpace = new javax.swing.JTextArea();
        bpmValue = new javax.swing.JLabel();
        opModeValue = new javax.swing.JLabel();
        currentTimeValue = new javax.swing.JLabel();
        elapsedTimeValue = new javax.swing.JLabel();
        sendBPM = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuExit = new javax.swing.JMenu();
        resetMenu = new javax.swing.JMenuItem();
        conectionsLeft = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        maxLabel.setText("Max:");

        minLabel.setText("Min");

        currentTime.setText("Current Time:");

        elapsedTime.setText("Elapsed Time:");

        maxValue.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        maxValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxValueActionPerformed(evt);
            }
        });

        minValue.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        minValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minValueActionPerformed(evt);
            }
        });

        bpmLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        bpmLabel.setText("BPM:");

        opModeLabel.setText("Operating Mode:");

        connectionButton.setText("Connect");
        connectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionButtonActionPerformed(evt);
            }
        });

        textSpace.setColumns(20);
        textSpace.setRows(5);
        jScrollPane1.setViewportView(textSpace);

        bpmValue.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        bpmValue.setText("200");

        opModeValue.setText("Local");

        currentTimeValue.setText("00:00:00");

        elapsedTimeValue.setText("00:00:00");

        sendBPM.setText("Send ");
        sendBPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBPMActionPerformed(evt);
            }
        });

        jLabel1.setText("Send new Heart Beat value");

        menuExit.setText("File");

        resetMenu.setText("Reset");
        resetMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMenuActionPerformed(evt);
            }
        });
        menuExit.add(resetMenu);

        conectionsLeft.setText("Conections Left");
        conectionsLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectionsLeftActionPerformed(evt);
            }
        });
        menuExit.add(conectionsLeft);
        menuExit.add(jSeparator1);

        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        menuExit.add(exit);

        jMenuBar1.add(menuExit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(opModeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(opModeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(minLabel)
                                .addGap(13, 13, 13)
                                .addComponent(minValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(connectionButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentTime, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(elapsedTime, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentTimeValue)
                                    .addComponent(elapsedTimeValue, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bpmLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bpmValue)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(maxLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addComponent(sendBPM)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(currentTime)
                            .addComponent(currentTimeValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(elapsedTime)
                            .addComponent(elapsedTimeValue)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(opModeLabel)
                            .addComponent(connectionButton)
                            .addComponent(opModeValue))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maxLabel)
                            .addComponent(maxValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minLabel)
                            .addComponent(minValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bpmLabel)
                            .addComponent(bpmValue))))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendBPM)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // Set the shared running to false
        dataShare.setRunning(false);
    }//GEN-LAST:event_exitActionPerformed

    private void conectionsLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectionsLeftActionPerformed

    }//GEN-LAST:event_conectionsLeftActionPerformed

    private void resetMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetMenuActionPerformed
        // reset the text Space
        textSpace.setText(null);
    }//GEN-LAST:event_resetMenuActionPerformed

    private void connectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionButtonActionPerformed
        // If the system is not connected
        if (!dataShare.isConnect()) {
            // set the data share to atempt to connect
            dataShare.setConnect(!dataShare.isConnect());
            // Set the connection
            setConnection(!dataShare.isConnect());
            // Show the values that have been set in the text area.
            alterText("Min set to: " + lowValue + "\tMax set to: " + highValue);
        } else {
            // set shared data to disconncet
            dataShare.setConnect(!dataShare.isConnect());
            // Set the connection to that value
            setConnection(!dataShare.isConnect());
        }
        a.setHigh(Integer.parseInt(maxValue.getSelectedItem().toString()));
        a.setLow(Integer.parseInt(minValue.getSelectedItem().toString()));
    }//GEN-LAST:event_connectionButtonActionPerformed

    private void minValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minValueActionPerformed
        // Test the dropdown vairables with min
        testDropDowns("min");
    }//GEN-LAST:event_minValueActionPerformed

    private void maxValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxValueActionPerformed
        // Test the dropdown variables with the max
        testDropDowns("max");
    }//GEN-LAST:event_maxValueActionPerformed

    private void sendBPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBPMActionPerformed
        a.setHigh(Integer.parseInt(maxValue.getSelectedItem().toString()));
        a.setLow(Integer.parseInt(minValue.getSelectedItem().toString()));
    }//GEN-LAST:event_sendBPMActionPerformed

// A method to update the time
    public void updateTime() throws InterruptedException {
        // set the now calander
        now = Calendar.getInstance();
        // get the time from the now calander
        Date time = now.getTime();
        // Set the time label to the selected time
        currentTimeValue.setText(dateFormat.format(time));
        // Set the elapsed variable to the current time minus the start time.
        elapsedTimeValue.setText(dateFormat.format((time.getTime() - start.getTimeInMillis() - 3600000)));
    }

    // The method to test dropdowns
    private void testDropDowns(String v) {
        // if both max values and max strings have been initiated
        if (maxValue.getSelectedItem() != null && maxValue.getSelectedItem().toString() != null) {
            // if both max values and max strings have been initiated
            if (minValue.getSelectedItem() != null && minValue.getSelectedItem().toString() != null) {
                // Set highvalue to the value from the relative drop down
                highValue = Integer.parseInt(maxValue.getSelectedItem().toString());
                // Set highvalue to the value from the relative drop down
                lowValue = Integer.parseInt(minValue.getSelectedItem().toString());
                // If the high value is lower or equal to the min
                if (highValue <= lowValue) {
                    switch (v) {
                        case "max":
                            // If max, set the max to the old value
                            maxValue.setSelectedItem(oldHighValue);
                            // break
                            break;
                        case "min":
                            // if min, set the min to the old value
                            minValue.setSelectedItem(oldLowValue);
                            // break
                            break;
                    }
                    // Create a frame to display the error message
                    Component frame = null;
                    // Show the error for the dropdown
                    JOptionPane.showMessageDialog(frame, "The \"Max Value\" must be more than the \"Min Value\"!");
                } else {
                    // Else valid change
                    switch (v) {
                        case "max":
                            // set the oldMax to the new max
                            oldHighValue = highValue;
                        case "min":
                            // set the oldMin to the new min
                            oldLowValue = lowValue;
                    }
                    // Set the data share maxmin to the relevant value
                    dataShare.setMaxMin(highValue + "," + lowValue);
                }
            }
        }
    }

    // A method to the configure if the client is connected or not. 
    public void setConnection(boolean connected) {
        if (!connected) {
            //connect = true;
            // If connected, disable the dropdowns
            maxValue.setEnabled(false);
            minValue.setEnabled(false);
            // Set the mode lable to remote
            opModeValue.setText("Remote");
            // change the connect button to disconnect
            connectionButton.setText("Disconnect");
        } else {
            // Set the mode label to local
            opModeValue.setText("Local");
            // set the disconnect button to connect
            connectionButton.setText("Connect");
            // enable both the dropdowns
            maxValue.setEnabled(true);
            minValue.setEnabled(true);
        }
    }

    // A method to alter the text in the scrollable text box
    public void alterText(String text) {
        // Display the time the message was posted to the text area as well as the text passed
        now = Calendar.getInstance();
        textSpace.append(dateFormat.format(now.getTime()) + " | " + text + "\n");
    }

    public void updateBpm() throws InterruptedException {
        if (!bpmValue.getText().equals(dataShare.getBPM() + "")) {
            bpmValue.setText("" + dataShare.getBPM());
        }
    }

    public int getHb() {
        return heartbeatValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bpmLabel;
    private javax.swing.JLabel bpmValue;
    private javax.swing.JMenuItem conectionsLeft;
    private javax.swing.JButton connectionButton;
    private javax.swing.JLabel currentTime;
    private javax.swing.JLabel currentTimeValue;
    private javax.swing.JLabel elapsedTime;
    private javax.swing.JLabel elapsedTimeValue;
    private javax.swing.JMenuItem exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel maxLabel;
    private javax.swing.JComboBox maxValue;
    private javax.swing.JMenu menuExit;
    private javax.swing.JLabel minLabel;
    private javax.swing.JComboBox minValue;
    private javax.swing.JLabel opModeLabel;
    private javax.swing.JLabel opModeValue;
    private javax.swing.JMenuItem resetMenu;
    private javax.swing.JButton sendBPM;
    private javax.swing.JTextArea textSpace;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        // Show the GUI
        setVisible(true);
        // Update the time in near real time
        while (dataShare.isRunning()) {
            try {
                updateTime();
                updateBpm();
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
