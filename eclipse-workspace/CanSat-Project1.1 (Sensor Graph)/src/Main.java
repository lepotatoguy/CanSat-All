
// https://www.youtube.com/watch?v=cw31L_OwX3A
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
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

public class Main {

	static SerialPort chosenPort;
	static int x = 0; // seconds which will be in the graph "A"

	// capturing the infos

	static String id = "ID";
	static String name = "Name";
	static String age = "Age";
	static String filePath = "output.csv";

	public static void main(String[] args) throws InterruptedException {

		// saving the initial infos
		saveRecord(id, name, age, filePath);

		// timestamp
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = now = LocalDateTime.now();
		/*
		 * for(int i=0;i<=4;i++) { TimeUnit.SECONDS.sleep(1); --i; now =
		 * LocalDateTime.now(); }
		 */
		String time = dtf.format(now);

		// create and configure the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame window = new JFrame();
		window.setTitle("CanSat GUI");
		window.setSize(screenSize.width, screenSize.height); // resolution
		window.setResizable(true);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create a drop-down box and connect button, then place them at the top of the
		// window
		JComboBox<String> portList = new JComboBox<String>();
		JButton connectButton = new JButton("Connect");
		JPanel topPanel = new JPanel(); // new BorderLayout(35,35)
		JLabel l1 = new JLabel("Welcome to CanSat GUI. Choose a Port: ");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		// l1.setSize(300,300);
		topPanel.add(l1, BorderLayout.PAGE_START);
		topPanel.add(portList);
		topPanel.add(connectButton);
		window.add(topPanel, BorderLayout.PAGE_START);

		// bottom panel
	/*	JPanel bottomPanel = new JPanel();
		JLabel trademark = new JLabel("Made with Love by Joyanta");
		JLabel time1 = new JLabel(time);
		bottomPanel.add(trademark);
		bottomPanel.add(time1);
		trademark.setFont(new Font("Times New Roman", Font.BOLD, 18));
		window.add(bottomPanel, BorderLayout.PAGE_END); */

		// populate all the ports in the drop-down box
		SerialPort[] portNames = SerialPort.getCommPorts();
		for (int i = 0; i < portNames.length; i++) {
			portList.addItem(portNames[i].getSystemPortName());

		}
		
		//we need to show 5 sensors. but we can arrange 4 in this way. this is a challenge 

		// create the line graph "A"

		XYSeries series = new XYSeries("A Sensor Readings");
		XYSeriesCollection data = new XYSeriesCollection(series);
		// data.addSeries(series);
		JFreeChart chart = ChartFactory.createXYLineChart("A Sensor Readings", "X Axis Label", "Y Axis Label", data);
		ChartPanel chartPanel1 = new ChartPanel(chart);
		// chartPanel1.setDomainZoomable(true);
		chartPanel1.setMouseZoomable(true);
		chartPanel1.setPreferredSize(new java.awt.Dimension(300, 300));
		// chart.setSize();
		window.add(chartPanel1, BorderLayout.EAST);

		// create the line graph "B"

		XYSeries series_b = new XYSeries("B Sensor Readings");
		XYSeriesCollection data_b = new XYSeriesCollection(series_b);
		// data.addSeries(series);
		JFreeChart chart_b = ChartFactory.createXYLineChart("B Sensor Readings", "X Axis Label", "Y Axis Label",
				data_b);
		ChartPanel chartPanel2 = new ChartPanel(chart_b);
		// chartPanel1.setDomainZoomable(true);
		chartPanel2.setMouseZoomable(true);
		chartPanel2.setPreferredSize(new java.awt.Dimension(300, 300));
		// chart.setSize();
		window.add(chartPanel2, BorderLayout.WEST);

		// create the line graph "C"

		XYSeries series_c = new XYSeries("C Sensor Readings");
		XYSeriesCollection data_c = new XYSeriesCollection(series_c);
		// data.addSeries(series);
		JFreeChart chart_c = ChartFactory.createXYLineChart("C Sensor Readings", "X Axis Label", "Y Axis Label",
				data_c);
		ChartPanel chartPanel3 = new ChartPanel(chart_c);
		// chartPanel1.setDomainZoomable(true);
		chartPanel2.setMouseZoomable(true);
		chartPanel2.setPreferredSize(new java.awt.Dimension(300, 300));
		// chart.setSize();
		window.add(chartPanel3, BorderLayout.CENTER);
		
		// create the line "D" //GPS Sensor Output (Struggling)

		XYSeries series_d = new XYSeries("D Sensor Readings");
		XYSeriesCollection data_d = new XYSeriesCollection(series_d);
		// data.addSeries(series);
		JFreeChart chart_d = ChartFactory.createXYLineChart("D Sensor Readings", "X Axis Label", "Y Axis Label",
				data_d);
		ChartPanel chartPanel4 = new ChartPanel(chart_d);
		// chartPanel1.setDomainZoomable(true);
		//chartPanel2.setMouseZoomable(true);
		chartPanel4.setPreferredSize(new java.awt.Dimension(300, 300));
		// chart.setSize();
		window.add(chartPanel4, BorderLayout.SOUTH);
		
		// bottom panel
		/*	JPanel bottomPanel = new JPanel();
			JLabel trademark = new JLabel("Made with Love by Joyanta");
			JLabel time1 = new JLabel(time);
			bottomPanel.add(trademark);
			bottomPanel.add(time1);
			trademark.setFont(new Font("Times New Roman", Font.BOLD, 18));
			window.add(bottomPanel, BorderLayout.SOUTH); */

		// configure the connect button and use another thread to listen for data
		connectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (connectButton.getText().contentEquals("Connect")) {
					// attempt to connect to the serial port
					chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					if (chosenPort.openPort()) {
						connectButton.setText("Disconnect");
						portList.setEnabled(false);
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
									series.add(x++, number);
									//here, we will be needing to customize for each sensor's output
									window.repaint();
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, "Error in Incoming Output from Arduino");
								}
							}
							sc.close(); // close after disconnect
							JOptionPane.showMessageDialog(null, "Record Saved");
						}

					};
					thread.start();
				} else {
					// disconnect from the serial port
					chosenPort.closePort();
					portList.setEnabled(true);
					connectButton.setText("Connect");
					series.clear();
					series_b.clear();
					series_c.clear();
					x = 0;
				}

			}

		});

		// show the window
		window.setVisible(true);

	}

	public static void saveRecord(String id, String name, String age, String filePath) {
		try {

			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.println(id + ", " + name + ", " + age);
			pw.flush(); // make sure all data is written in the file
			pw.close();

		} catch (Exception E) {
			JOptionPane.showMessageDialog(null, "Record Not Saved");
		}
	}

}
