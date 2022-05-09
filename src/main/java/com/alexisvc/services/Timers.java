package com.alexisvc.services;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.Timer;
import java.util.TimerTask;

import com.alexisvc.models.City;
import com.alexisvc.models.Temperature;
import com.alexisvc.services.templateHome.HomeImages;
import com.alexisvc.templates.Home;

public class Timers {

    private Home window;

    // Services
    private final ApiWeather apiWeather;
    private DateService dateService = new DateService();

    // Models
    private City city;

    // Decorators
    private final Color COLOR_BLACK = new Color(0, 0, 0, 180);
    private final Color COLOR_BLUE = new Color(4, 7, 50, 180);

    private static String IMAGE_ACTUAL = "";
    private final String PATH_IMAGES = "src/main/java/com/alexisvc/resources/img/";
    private String nameImage;

    public Timers(Home window) {
        this.window = window;
        this.apiWeather = new ApiWeather();
    }

    // Timers
    public void initTimerClock() {
        DateFormat formatHour = DateFormat.getTimeInstance(DateFormat.MEDIUM);

        TimerTask eventClock = new TimerTask() {

            @Override
            public void run() {
                if (city != null) {
                    formatHour.setTimeZone(TimeZone.getTimeZone(city.getTimeZone()));
                }

                window.getLabelHour().setText(formatHour.format(new Date()));
            }

        };

        Timer timerClock = new Timer();
        timerClock.schedule(eventClock, 0, 1000);
    }

    private void initTimerUpdateFuturePronostics() {

        TimerTask updateDataPanels = new TimerTask() {

            @Override
            public void run() {
                if (dateService.changeHour()) {
                    fetchFutureWeather();
                }
            }

        };
        Timer timerFuturePronostics = new Timer();
        timerFuturePronostics.schedule(updateDataPanels, 0, 1000);

    }

    private void initTimerUpdatePronostic() {

        TimerTask eventPronostic = new TimerTask() {

            @Override
            public void run() {
                fetchCurrentWeather();
            }
        };

        Timer timerCurrentWeather = new Timer();
        timerCurrentWeather.schedule(eventPronostic, 0, 60000);

    }

    // Methods for timers.
    private void fetchFutureWeather() {
        ArrayList<Temperature> temperatures = apiWeather.getFuturePronosticToday(city.getName());

        List<JLabel> labelsFutureTemperatures = window.getArrayLabelsFutureTemperatures();
        List<JLabel> labelsHours = window.getArrayLabelsHours();
        List<JLabel> labelsConditions = window.getArrayLabelsConditions();

        for (int i = 0; i < temperatures.size(); i++) {
            labelsFutureTemperatures.get(i).setText(temperatures.get(i).getTemperatureCelcius() + " °C");
            labelsHours.get(i).setText(temperatures.get(i).getHour());
            labelsConditions.get(i).setText(temperatures.get(i).getCondition());
        }

        // Set name of city and country of futures panels
        window.getArrayLabelCity().forEach(label -> label.setText(city.getName()));
        window.getArrayLabelCountry().forEach(label -> label.setText(city.getCountry() + ", " + city.getRegion()));

    }

    private void fetchCurrentWeather() {

        String code = city.getTemperature().getCodeCondition();
        String day = city.getIsDay();

        window.getLabelMainTemperature().setText(city.getTemperature().getTemperatureCelcius() + " °C");
        window.getLabelCity().setText(city.getName());
        window.getLabelCountry().setText(city.getCountry() + ", " + city.getRegion());
        window.getLabelStateWeather().setText(city.getTemperature().getCondition());

        nameImage = "defecto.png";

        // Set background image according to actually weather.
        if (city.getTemperature().getCodeCondition().equals("1000"))
            nameImage = day.equals("1") ? "soleado.png" : "despejado.png";
        else if (code.equals("1003") || code.equals("1006"))
            nameImage = "nublado.png";
        else if (code.equals("1009"))
            nameImage = "nublado.png";
        else if (code.equals("1087") || code.equals("1189"))
            nameImage = "lluvia.png";
        else if (code.equals("1219"))
            nameImage = "nieve.png";

        if (!IMAGE_ACTUAL.equals(nameImage)) {
            setDisignHomeTemplate();
            IMAGE_ACTUAL = nameImage;
        }
    }

    public void fetchCity(String place) {
        TimerTask fetchInfo = new TimerTask() {

            @Override
            public void run() {
                City cityTemp = apiWeather.getCurrentWeather(place);

                if (cityTemp != null) {

                    // We save requests.
                    if (city != null && !city.getName().equals(cityTemp.getName())) {
                        city = cityTemp;
                        fetchCurrentWeather();
                        fetchFutureWeather();

                    } else if(city == null) {
                        city = cityTemp;
                        initTimerUpdatePronostic();
                        initTimerUpdateFuturePronostics();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la ciudad: " + place);
                }
            }
        };

        Timer timerInit = new Timer();
        timerInit.schedule(fetchInfo, 0);
    }

    // Set disign with new information in the app.
    private void setDisignHomeTemplate() {

        setDisginButtons();
        setBackgroundMainPanel();
        setDisignPanels();

        window.setDecoratorsContent();
        window.updatePanels();
        window.getMainPanel().updateUI();
    }

    private void setDisignPanels() {

        window.setColorToPanels(nameImage.equals("despejado.png") ? COLOR_BLUE : COLOR_BLACK);
    }

    private void setBackgroundMainPanel() {

        window.setImageBackground(
                HomeImages.resizeImage(new ImageIcon(PATH_IMAGES + nameImage), window.getWidth(), window.getHeight()));
    }

    private void setDisginButtons() {

        String imageMinimizerButton = "minimizer_black.png";
        String imageExitButton = "exit_black.png";

        if (nameImage.equals("despejado.png") || nameImage.equals("lluvia.png")) {

            imageMinimizerButton = "minimizer_grey.png";
            imageExitButton = "exit_grey.png";
        }

        window.setImageButtonMinimizer(new ImageIcon(PATH_IMAGES + imageMinimizerButton));
        window.setImageButtonExit(new ImageIcon(PATH_IMAGES + imageExitButton));

    }

}
