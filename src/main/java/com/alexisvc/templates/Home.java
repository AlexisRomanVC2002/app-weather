package com.alexisvc.templates;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.alexisvc.services.MoveWindow;
import com.alexisvc.services.Timers;
import com.alexisvc.services.templateHome.HomeImages;
import com.alexisvc.services.templateHome.HomeWindowEvent;

public class Home extends JFrame{

    private JPanel mainPanel, actualTemperaturePanel, futurePanel1, futurePanel2, futurePanel3, futurePanel4, basicInfoPanel;
    private JLabel stateWeather, hour, city, country, futureHour1, futureHour2, futureHour3, futureHour4, 
    mainTemperature, futureTemperature1, futureTemperature2, futureTemperature3, futureTemperature4,
    futureStateWeather1, futureStateWeather2, futureStateWeather3, futureStateWeather4, barDrag, background;
    private JButton buttonMiniizer, buttonExit;
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    private final MoveWindow moveWindow = new MoveWindow(this);
    private final HomeWindowEvent homeEvents;
    private final Timers timersWindow;

    public Home(){

        setSize(1020, 600);
        setTitle("App Weather - By Alexis Valente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(this);
        setUndecorated(true);

        initComponets();

        timersWindow = new Timers(this);
        timersWindow.fetchCurrentWeather();
        homeEvents = new HomeWindowEvent(this);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    public JLabel getLabelHour(){
        return hour;
    }

    public JLabel getLabelBackground(){
        return background;
    }

    public JButton[] getButtons(){
        return new JButton[]{buttonMiniizer, buttonExit};
    }

    public Timers getTimers(){
        return timersWindow;
    }

    private void initComponets(){
        panels();
        buttons();
        labels();
    }

    private void panels(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        getContentPane().add(mainPanel);
    }

    private void labels(){

        barDrag = new JLabel();
        barDrag.addMouseListener(moveWindow);
        barDrag.addMouseMotionListener(moveWindow);
        barDrag.setLocation(0, 0);
        barDrag.setSize(buttonMiniizer.getX() - 10, 20);

        background = new JLabel();
        background.setLocation(0, 0);
        background.setOpaque(true);
        background.setSize(this.getWidth(), this.getHeight());
        background.setIcon(HomeImages.resizeImage(new ImageIcon("src/main/java/com/alexisvc/resources/img/defecto.png"), this.getWidth(), this.getHeight()));

        mainPanel.add(background);
        mainPanel.add(barDrag);
    }

    private void buttons(){

        buttonExit = new JButton();
        buttonExit.setFocusable(false);
        buttonExit.setBackground(null);
        buttonExit.setBorder(null);
        buttonExit.setContentAreaFilled(false);
        buttonExit.setSize(40, 40);
        buttonExit.setLocation(this.getWidth() - buttonExit.getWidth() - 10, 10);
        buttonExit.setCursor(cursor);
        buttonExit.setIcon(new ImageIcon("src/main/java/com/alexisvc/resources/img/exit_black.png"));
        buttonExit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
            
        });

        buttonMiniizer = new JButton();
        buttonMiniizer.setFocusable(false);
        buttonMiniizer.setBackground(null);
        buttonMiniizer.setBorder(null);
        buttonMiniizer.setSize(40, 40);
        buttonMiniizer.setContentAreaFilled(false);
        buttonMiniizer.setCursor(cursor);
        buttonMiniizer.setLocation(buttonExit.getX() - buttonMiniizer.getWidth() - 10, 10);
        buttonMiniizer.setIcon(new ImageIcon("src/main/java/com/alexisvc/resources/img/minimizer_black.png"));
        buttonMiniizer.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                setState(JFrame.ICONIFIED);
            }

        });

        mainPanel.add(buttonMiniizer);
        mainPanel.add(buttonExit);

    }
    
}
