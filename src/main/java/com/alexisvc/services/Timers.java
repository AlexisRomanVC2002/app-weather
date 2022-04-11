package com.alexisvc.services;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.util.Timer;
import java.util.TimerTask;

import com.alexisvc.services.templateHome.HomeImages;
import com.alexisvc.templates.Home;

public class Timers {

    private final ApiWeather apiWeather;
    private Home window;
    private JButton[] buttons;

    public Timers(Home window) {
        this.window = window;
        buttons = window.getButtons();
        this.apiWeather = new ApiWeather();
    }

    public void initTimerClock() {
        DateFormat formatHour = DateFormat.getTimeInstance(DateFormat.MEDIUM);

        TimerTask eventClock = new TimerTask() {

            @Override
            public void run() {
                window.getLabelHour().setText(formatHour.format(new Date()));
            }

        };

        Timer timerClock = new Timer();
        timerClock.schedule(eventClock, 1000);
    }

    public void initTimerUpdateFuturePronostics() {

    }

    public void initTimerUpdatePronostic() {

        TimerTask eventPronostic = new TimerTask() {

            @Override
            public void run() {
                fetchCurrentWeather();
            }

        };

        Timer timerPronostic = new Timer();
        timerPronostic.schedule(eventPronostic, 60000);

    }

    public void fetchCurrentWeather() {
        HashMap<String, String> info = apiWeather.getCurrentWeather("Puerto Vallarta");

        String temperature = info.get("temperature");
        String condition = info.get("condition");
        String country = info.get("country");
        String day = info.get("day");
        String code = info.get("code");

        String image = "defecto.png";

        // Set background image according to actually weather.
        if (code.equals("1000"))
            image = day.equals("1") ? "soleado.png" : "despejado.png";
        else if (code.equals("1003") || code.equals("1006"))
            image = "nublado.png";
        else if (code.equals("1009"))
            image = "nublado.png";
        else if (code.equals("1087") || code.equals("1189"))
            image = "lluvia.png";
        else if (code.equals("1219"))
            image = "nieve.png";

        setBackgroundImage(image);
    }

    private void setBackgroundImage(String image) {
        if (image.equals("despejado.png") || image.equals("lluvia.png")) {
            HomeImages.setImageButtons(buttons[0], "minimizer_grey.png");
            HomeImages.setImageButtons(buttons[1], "exit_grey.png");
        } else {
            HomeImages.setImageButtons(buttons[0], "minimizer_black.png");
            HomeImages.setImageButtons(buttons[1], "exit_black.png");
        }

        String path = "src/main/java/com/alexisvc/resources/img/" + image;
        window.getLabelBackground().setIcon(HomeImages.resizeImage(new ImageIcon(path), window.getWidth(),
                window.getHeight()));
        window.getMainPanel().updateUI();
    }

}
