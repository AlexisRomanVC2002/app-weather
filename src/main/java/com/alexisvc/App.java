package com.alexisvc;

import javax.swing.SwingUtilities;
import com.alexisvc.templates.Home;

public class App {
    public static void main(String[] args) {
        Runnable runApp = new Runnable() {

            @Override
            public void run() {
                Home homeApp = new Home();
                homeApp.setVisible(true);
            }
        };

        SwingUtilities.invokeLater(runApp);
    }
}
