package com.alexisvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.alexisvc.services.ApiWeather;
import com.alexisvc.services.DateService;
import com.alexisvc.templates.Home;

public class App {
    public static void main(String[] args) {
        Runnable runApp = new Runnable() {

            @Override
            public void run() {
                Home homeApp = new Home();
                //homeApp.setVisible(true);
            }
        };

        DateService chekH = new DateService();
        ApiWeather api = new ApiWeather();
        //System.out.println(api.getFuturePronosticToday("Puerto Vallarta"));

        Timer clock = new Timer(1000, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
               System.out.println(chekH.getDateActually()); 
            }

        });

        clock.start();
        synchronized(clock){
            try {
                clock.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };

        SwingUtilities.invokeLater(runApp);
    }
}
