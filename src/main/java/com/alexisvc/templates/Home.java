package com.alexisvc.templates;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.alexisvc.services.MoveWindow;

public class Home extends JFrame{

    private JPanel panelPrincipal;
    private final MoveWindow moveWindow = new MoveWindow(this);

    public Home(){
        setSize(1020, 600);
        setTitle("App Weather - By Alexis Valente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(this);
        setUndecorated(true);

        initComponets();
    }

    private void initComponets(){
        panels();
        labels();
    }

    private void panels(){
        panelPrincipal = new JPanel();
        getContentPane().add(panelPrincipal);
    }

    private void labels(){

    }
    
}
