
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;



//import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.fazecast.jSerialComm.SerialPort;
import static java.awt.SystemColor.window;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import gnu.io.CommPortIdentifier;
import gnu.io.*;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author Joyanta
 */
public class GUI extends javax.swing.JFrame implements SerialPortEventListener {

    JFrame f;
    static SerialPort chosenPort = null;
    static int x = 0; // seconds which will be in the graph "A"
    
    LineChart<?,?> lc;
    NumberAxis y;
    
        SerialPort serialPort_0;
        InputStream serialInput;
        OutputStream serialOutput;
        
        static final int TIME_OUT = 2000;
    

    static String id = "ID";
    static String name = "Name";
    static String age = "Age";
    // static String filePath = "D:\\output.csv";
    static String filePath = "D:\\output.txt";
    // static JComboBox<String> comPort;

    public static void comPortFind() {
        JComboBox<String> comPort = new JComboBox<>();
        SerialPort[] portNames = SerialPort.getCommPorts();
        for (SerialPort portName : portNames) {
            comPort.addItem(portName.getSystemPortName());
        }
    }

    /**
     * Creates new form GUI
     */
    public GUI() {

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Welcome = new javax.swing.JLabel();
        Connect = new javax.swing.JButton();
        comPort = new JComboBox<String>();
        Trademark = new javax.swing.JLabel();
        SensorA = new javax.swing.JPanel();
        SensorB = new javax.swing.JPanel();
        SensorC = new javax.swing.JPanel();
        SensorD = new javax.swing.JPanel();
        SensorE = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        Welcome.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Welcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Welcome.setText("Welcome to CanSat GUI. Choose a Port:");

        Connect.setText("Connect");
        Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectActionPerformed(evt);
            }
        });

        SerialPort[] portNames = SerialPort.getCommPorts();
        for (int i = 0; i < portNames.length; i++) {
            comPort.addItem(portNames[i].getSystemPortName());

        }
        comPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comPortActionPerformed(evt);
            }
        });

        Trademark.setText("Made with Love by Asteroid Invaders ");

        SensorA.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SensorA.setLayout(new java.awt.BorderLayout());

        SensorB.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SensorB.setLayout(new java.awt.BorderLayout());

        SensorC.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SensorC.setLayout(new java.awt.BorderLayout());

        SensorD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout SensorDLayout = new javax.swing.GroupLayout(SensorD);
        SensorD.setLayout(SensorDLayout);
        SensorDLayout.setHorizontalGroup(
            SensorDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        SensorDLayout.setVerticalGroup(
            SensorDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        SensorE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout SensorELayout = new javax.swing.GroupLayout(SensorE);
        SensorE.setLayout(SensorELayout);
        SensorELayout.setHorizontalGroup(
            SensorELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        SensorELayout.setVerticalGroup(
            SensorELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Welcome)
                        .addGap(18, 18, 18)
                        .addComponent(comPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(Connect))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SensorD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SensorA, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SensorE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SensorB, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SensorC, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Trademark))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Connect)
                    .addComponent(comPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Welcome))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(SensorA, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SensorB, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SensorE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SensorD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(SensorC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Trademark)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comPortActionPerformed
        comPortFind();

    }//GEN-LAST:event_comPortActionPerformed

    private void ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectActionPerformed
        if (Connect.getText().contentEquals("Connect")) {

            TurnOnSensorAActionPerformed();
            TurnOnSensorBActionPerformed();
            TurnOnSensorCActionPerformed();
            // attempt to connect to the serial port
            chosenPort = SerialPort.getCommPort(comPort.getSelectedItem().toString());
            chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
            if (chosenPort.openPort()) {
                Connect.setText("Disconnect");
                comPort.setEnabled(false);
            }

            // create new thread that listens for incoming text and populate the graph
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Scanner sc = new Scanner(chosenPort.getInputStream());
                    while (sc.hasNextLine()) {
                        try {
                            String line = sc.nextLine(); // geting line of text from the serial port (the most
                            // important part)
                            int number = Integer.parseInt(line);
                            name = line; // updating data
                            id = Integer.toString(x); // updating time as x is time right now
                            saveRecord(id, name, age, filePath); // saving the updated data
                            //  series.add(x++, number);
                            //here, we will be needing to customize for each sensor's output
                            // JFrame.repaint();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error in Incoming Output from Arduino");
                        }
                    }
                    sc.close(); // close after disconnect
                    //JOptionPane.showMessageDialog(null, "Record Saved", "Successful!", JOptionPane.PLAIN_MESSAGE);
                       OptionPaneExample op= new OptionPaneExample();
                       op.OptionPaneExample();
                }

            };
            thread.start();
        } else {
            // disconnect from the serial port
            chosenPort.closePort();
            comPort.setEnabled(true);
            Connect.setText("Connect");
            //series.clear();
            //series_b.clear();
            //series_c.clear();
            x = 0;
        }
    }//GEN-LAST:event_ConnectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        comPortFind();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        }); */
    }

    public static void saveRecord(String id, String name, String age, String filePath) {

        try {

            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(id + ", " + name + ", " + age);
            pw.flush(); // make sure all data is written in the file
            pw.close();
            JOptionPane.showMessageDialog(null, "Record Saved", "Successful!", JOptionPane.PLAIN_MESSAGE);

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Connect;
    public javax.swing.JPanel SensorA;
    public javax.swing.JPanel SensorB;
    public javax.swing.JPanel SensorC;
    public javax.swing.JPanel SensorD;
    public javax.swing.JPanel SensorE;
    public javax.swing.JLabel Trademark;
    public javax.swing.JLabel Welcome;
    public javax.swing.JComboBox<String> comPort;
    // End of variables declaration//GEN-END:variables

    private void TurnOnSensorAActionPerformed() {
        XYSeries series = new XYSeries("Temperature Sensor Readings");
        XYSeriesCollection data = new XYSeriesCollection(series);
        // data.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Temperature Sensor Readings", "Time", "Temperature", data);
        ChartPanel chartPanel1 = new ChartPanel(chart);
        // chartPanel1.setDomainZoomable(true);
        //chartPanel1.setMouseZoomable(true);
        chartPanel1.setPreferredSize(new java.awt.Dimension(458, 234));
        // chart.setSize();
        SensorA.add(chartPanel1, BorderLayout.CENTER); //To change body of generated methods, choose Tools | Templates.
    }

    private void TurnOnSensorBActionPerformed() {
        XYSeries series_b = new XYSeries("Pressure Sensor Readings");
        XYSeriesCollection data_b = new XYSeriesCollection(series_b);
        // data.addSeries(series);
        JFreeChart chart_b = ChartFactory.createXYLineChart("Pressure Sensor Readings", "Time", "Pascal", data_b);
        ChartPanel chartPanel2 = new ChartPanel(chart_b);
        // chartPanel1.setDomainZoomable(true);
        //chartPanel1.setMouseZoomable(true);
        chartPanel2.setPreferredSize(new java.awt.Dimension(629, 434));
        // chart.setSize();
        SensorB.add(chartPanel2, BorderLayout.CENTER);
    }

    private void TurnOnSensorCActionPerformed() {
        XYChart.Series series_c_1 = new XYChart.Series();
        XYSeries series_c_2 = new XYSeries("Accelerometer Y");
        XYSeries series_c_3 = new XYSeries("Accelerometer Z");
        
        //XYSeriesCollection data_c_1 = new XYSeriesCollection(series_c_1);
        //XYSeriesCollection data_c_2 = new XYSeriesCollection(series_c_2);
        //XYSeriesCollection data_c_3 = new XYSeriesCollection(series_c_3);
        // data.addSeries(series);
        series_c_1.getData().add(new XYChart.Data(1, 23));
        //JFreeChart chart_c = ChartFactory.createXYLineChart("Accelerometer", "Sample Number", "G");
        //ChartPanel chartPanel3 = new ChartPanel(chart_c);
        // chartPanel1.setDomainZoomable(true);
        //chartPanel1.setMouseZoomable(true);
        lc.getData(),addAll(series_c_1);
        chartPanel3.setPreferredSize(new java.awt.Dimension(317, 587));
        // chart.setSize();
        SensorC.add(chartPanel3, BorderLayout.CENTER);
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    public static class OptionPaneExample {

        JFrame f;

        public void OptionPaneExample() {
            f = new JFrame();
            JOptionPane.showMessageDialog(f, "Successfully Updated.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    //camera
    
  /*  private final List<Integer> baudRateList = Arrays.asList(
      2000000, //may be unreliable
      1000000,
      115200,
      57600,
      38400,
      19200,
      9600);

  public interface SerialDataReceived {
    void serialDataReceived(byte receivedByte);
  }

  private SerialDataReceived serialReceivedCallback;


  public void SerialReader(SerialDataReceived callback) {
    serialReceivedCallback = callback;
  }




  public void startListening(String portName, Integer baudRate) {
    CommPortIdentifier portIdentifier = getPortIdentifiers().get(portName);
    if (portIdentifier == null) {
      System.err.println("'" + portName + "' not found"); //error printout in a box
    } else {
      openPort(portIdentifier, baudRate);
    }
  }



  private synchronized void openPort(
      CommPortIdentifier portIdentifier,
      Integer baudRate
  ) {
    try {
      stopListening();

      serialPort_0 = (SerialPort) portIdentifier.open(
          this.getClass().getName(),
          TIME_OUT);

      serialPort.setSerialPortParams(
          baudRate,
          SerialPort.DATABITS_8,
          SerialPort.STOPBITS_1,
          SerialPort.PARITY_NONE);

      serialInput = serialPort.getInputStream();
      serialOutput = serialPort.getOutputStream();

      serialPort.addEventListener(this);
      serialPort.notifyOnDataAvailable(true);
    } catch (Exception e) {
      throw new SerialReaderException("Connect failed " + e.getMessage());
    }
  }



  public synchronized void serialEvent(SerialPortEvent oEvent) {
    if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
      try {
        int b;
        while((b = serialInput.read()) > -1) {
          serialReceivedCallback.serialDataReceived((byte)(b));
        }
      } catch (Exception e) {
        System.err.println(e.toString());
      }
    } else {
      System.out.println("Received event " + oEvent.getEventType());
    }
  }



  public synchronized void stopListening() {
    if (serialPort != null) {
      serialPort.removeEventListener();
      serialPort.close();
      serialPort = null;
    }
  }


  public List<String> getAvailablePorts() {
    List<String> ports = new ArrayList<>(getPortIdentifiers().keySet());
    Collections.reverse(ports);
    return ports;
  }

  public List<Integer> getAvailableBaudRates() {
    return baudRateList;
  }

  public Integer getDefaultBaudRate() {
    return baudRateList.get(1);
  }

  private Map<String, CommPortIdentifier> getPortIdentifiers() {
    Map<String, CommPortIdentifier> portIdentifierMap = new LinkedHashMap<>();
    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
    while(portEnum.hasMoreElements()) {
      CommPortIdentifier portIdentifier = (CommPortIdentifier) portEnum.nextElement();
      portIdentifierMap.put(portIdentifier.getName(), portIdentifier);
    }
    return portIdentifierMap;
  }
    
   public class SerialReaderException extends RuntimeException {



  public SerialReaderException(String errorMsg) {
    super(errorMsg);
  }


} 
*/
   public interface SerialPortListener {
	
	public void connectionOpened(int sampleRate, String packetType, String portName, int baudRate);
	
	public void connectionClosed();

} 
    
    
    
}
