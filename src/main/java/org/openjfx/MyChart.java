package org.openjfx;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import java.util.Random;

import javafx.scene.control.*;

public class MyChart extends VBox {
    XYChart.Series  <Double, Double> series = new XYChart.Series<>();
    double T = 5.0;
    LineChart lc = new LineChart(
            new NumberAxis("Time Constant", 0.0, T, 1),
            new NumberAxis("Voltage (Vs)", 0.0, 1.0, 0.1)
    );
    public LineChart buildSampleLineChart() {

        series.getData().add(new XYChart.Data<>(0.0,0.0));
        series.getData().add(new XYChart.Data<>(0.7,0.5));
        series.getData().add(new XYChart.Data<>(2.0,0.865));
        series.getData().add(new XYChart.Data<>(1.0,0.632));
        series.getData().add(new XYChart.Data<>(3.0,0.95));
        series.getData().add(new XYChart.Data<>(4.0,0.985));
        series.getData().add(new XYChart.Data<>(5.0,0.993));


        lc.getData().add(series);
        return lc;
    }
    public MyChart () {
        getChildren().add(buildSampleLineChart());
        Button myButton = new Button("Add 10000 random value to the chart");

        Thread myThread = new Thread(new Runnable(){
            @Override
            public void run(){
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        Random rand = new Random();
                        double randomT = 5 * rand.nextDouble();
                        double randomV = rand.nextDouble();
                        series.getData().add(new XYChart.Data<>(randomT, randomV));
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                    Platform.runLater(updater);

                }
            }
        });

        myThread.setDaemon(true);
        myThread.start();
        getChildren().add(myButton);
    }
}
