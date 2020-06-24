import javax.swing.JFrame;
import java.util.*;
import javax.swing.JSlider;

import com.fazecast.jSerialComm.*;

public class Main {
// https://www.youtube.com/watch?v=8B6j_yr9H8g
//	https://www.youtube.com/watch?v=lFZ26gD7OIE
// https://idyl.io/arduino/how-to/interface-gps-module-arduino gps module
	public static void main(String[] args) {

		// GUI

		JFrame window = new JFrame();
		JSlider slider = new JSlider();
		slider.setMaximum(1023);
		window.add(slider);
		window.pack();
		window.setVisible(true);

		// getCommPorts

		SerialPort ports[] = SerialPort.getCommPorts();

		System.out.println("Select Port: ");
		int i = 1;
		for (SerialPort port : ports) {
			// getCommPorts from pc
			System.out.println(i++ + ". " + port.getSystemPortName());
		}

		Scanner sc = new Scanner(System.in);
		int chosenPort = sc.nextInt();

		SerialPort port = ports[chosenPort - 1];
		if (port.openPort()) {
			System.out.println("Successfully Opened Port");

		} else {
			System.out.println("Unable to open Port");
			return;

		}

		port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

		
		Scanner data = new Scanner(port.getInputStream());
		while(data.hasNextLine()) { //keep waiting for input
		//	System.out.println(data.nextLine()); //it will change data according to the change of arduino input
		int number=0;
		try{number = Integer.parseInt(data.nextLine());}catch(Exception e) {} //putting that in gui
		slider.setValue(number);
		
		//get info from camera, accelerometer, gps sensor, gas sensor
		}
		
	}

}
