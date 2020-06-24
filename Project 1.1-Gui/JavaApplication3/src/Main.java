
import java.awt.Dimension;
import java.awt.Toolkit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joyanta
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        GUI gui = new GUI();
        gui.setTitle("CanSat GUI");
	gui.setSize(screenSize.width, screenSize.height); // resolution
	gui.setResizable(true);
        gui.setVisible(true);
    }
    
}
