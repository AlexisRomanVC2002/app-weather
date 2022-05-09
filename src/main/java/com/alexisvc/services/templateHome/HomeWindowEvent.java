package com.alexisvc.services.templateHome;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.alexisvc.services.Timers;
import com.alexisvc.templates.Home;

public class HomeWindowEvent {

    private Home window;
    private final Timers timersWindow;;

    public HomeWindowEvent(Home window, Timers timers){
        this.window = window;
        this.timersWindow = timers;
    }

    public void setEvents(){
        window.addWindowListener(new WindowListener(){

            @Override
            public void windowActivated(WindowEvent arg0) {
            }

            @Override
            public void windowClosed(WindowEvent arg0) {
            }

            @Override
            public void windowClosing(WindowEvent arg0) { 
            }

            @Override
            public void windowDeactivated(WindowEvent arg0) {
            }

            @Override
            public void windowDeiconified(WindowEvent arg0) { 
            }

            @Override
            public void windowIconified(WindowEvent arg0) {  
            }

            @Override
            public void windowOpened(WindowEvent arg0) {
                timersWindow.initTimerClock();
            }
        });
    }
    
}
