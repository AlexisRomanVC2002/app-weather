package com.alexisvc.services.templateHome;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Image;

public class HomeImages {

    public static ImageIcon resizeImage(ImageIcon background, int width, int height){
        return new ImageIcon(background.getImage().
        getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public static void setImageButtons(JButton button, String image){
        button.setIcon(new ImageIcon("src/main/java/com/alexisvc/resources/img/" + image));
    }
    
}
