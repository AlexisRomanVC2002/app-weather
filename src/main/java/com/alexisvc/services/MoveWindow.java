package com.alexisvc.services;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.awt.event.MouseListener;
import java.awt.Window;

public class MoveWindow implements MouseListener, MouseMotionListener {

    private Window window;
    private int x_press;
    private int y_press;

    public MoveWindow(Window window){
        this.window = window;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int x_moved = x - x_press;
        int y_moved = y - y_press;

        window.setLocation(window.getX() + x_moved, window.getY() + y_moved);
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.x_press = e.getX();
        this.y_press = e.getY();
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
