package sample;
//https://www.youtube.com/watch?v=qn2tbftFjno
//https://www.youtube.com/watch?v=zvgWgpGZVKc
//https://www.youtube.com/watch?v=STIHzuVmIG4&list=PLoodc-fmtJNYbs-gYCdd5MYS4CKVbGHv2
//https://www.youtube.com/watch?v=gkJU29Bxq_g
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

//import gnu.io.CommPortIdentifier;
//import gnu.io.*;
//import gnu.io.SerialPortEvent;
//import gnu.io.SerialPortEventListener;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CanSat GUI");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        //primaryStage.
    }


    public static void main(String[] args) {
        launch(args);
    }
}
