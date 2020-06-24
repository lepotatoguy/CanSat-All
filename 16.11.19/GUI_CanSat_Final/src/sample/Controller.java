package sample;
import javax.swing.*;


//import java.awt.*;

import com.fazecast.jSerialComm.SerialPort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

//import gnu.io.CommPortIdentifier;
//import gnu.io.*;
//import gnu.io.SerialPortEvent;
//import gnu.io.SerialPortEventListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable{
    static SerialPort chosenPort = null;

   // @FXML
   // static JComboBox<String> comPortBox;
    @FXML
    public JComboBox<String> comPortBox;

    static List<String> comPortList;

    static String temp="Temperature";
    static int x=0;
    static String temp_time;
    static String filePath = "D:\\output.csv";


   // public static void comPortFind() {

 //   }

        public void connectButtonClicked () {

            //     TurnOnSensorAActionPerformed();
            //     TurnOnSensorBActionPerformed();
            //    TurnOnSensorCActionPerformed();
            // attempt to connect to the serial port
            chosenPort = SerialPort.getCommPort(comPortBox.getSelectedItem().toString());
            chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
            //  if (chosenPort.openPort()) {
            //       Connect.setText("Disconnect");
            //       comPort.setEnabled(false);
            //   }

            // create new thread that listens for incoming text and populate the graph
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Scanner sc = new Scanner(chosenPort.getInputStream());
                    while (sc.hasNextLine()) {
                        try {
                            String line = sc.nextLine(); // getting line of text from the serial port (the most
                            // important part)
                            int number = Integer.parseInt(line);
                            temp = line; // updating data
                            temp_time = Integer.toString(x); // updating time as x is time right now
                            saveRecord(temp, temp_time, filePath); // saving the updated data
                            //  series.add(x++, number);
                            //here, we will be needing to customize for each sensor's output
                            // JFrame.repaint();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error in Incoming Output from Arduino");
                        }
                    }
                    sc.close(); // close after disconnect
                    //JOptionPane.showMessageDialog(null, "Record Saved", "Successful!", JOptionPane.PLAIN_MESSAGE);
                   // OptionPaneExample op = new OptionPaneExample();
                  //  op.OptionPaneExample();
                }

            };
            thread.start();


        }

    public static void saveRecord(String temp, String temp_time, String filePath) {

        try {

            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(temp + ", " + temp_time);
            pw.flush(); // make sure all data is written in the file
            pw.close();
            //JOptionPane.showMessageDialog(null, "Record Saved", "Successful!", JOptionPane.PLAIN_MESSAGE);

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record Not Saved");
        }

        /* try {
                           //PrintWriter pw = new PrintWriter(new File("D:\\output.csv"));
                           //pw.println(id + ", " + name + ", " + age);
                           //pw.flush();
			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
                    try (PrintWriter pw = new PrintWriter(bw)) {
                       pw.println(id + ", " + name + ", " + age);
                       pw.flush(); // make sure all data is written in the file
                   }

		} catch (IOException E) {
			JOptionPane.showMessageDialog(null, "Record Not Saved");
		} */
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SerialPort[] portNames = SerialPort.getCommPorts();
        for (int i = 0; i < portNames.length; i++) {
            // comPort.addItem(portNames[i].getSystemPortName());
            comPortList.add(portNames[i].getSystemPortName());
            comPortBox.addItem(portNames[i].getSystemPortName());
        }
    }

    public void comPortFind(ActionEvent actionEvent) {
        SerialPort[] portNames = SerialPort.getCommPorts();
        for (int i = 0; i < portNames.length; i++) {
            // comPort.addItem(portNames[i].getSystemPortName());
            comPortList.add(portNames[i].getSystemPortName());
            comPortBox.addItem(portNames[i].getSystemPortName());
        }
    }
}

