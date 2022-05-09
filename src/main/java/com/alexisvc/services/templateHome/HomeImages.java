package com.alexisvc.services.templateHome;

import javax.swing.ImageIcon;

import java.awt.Image;

public class HomeImages {

    public static ImageIcon resizeImage(ImageIcon background, int width, int height){
        return new ImageIcon(background.getImage().
        getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
    
}
