package com.alexisvc.templates;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.alexisvc.services.MoveWindow;
import com.alexisvc.services.Timers;
import com.alexisvc.services.templateHome.HomeImages;
import com.alexisvc.services.templateHome.HomeWindowEvent;

public class Home extends JFrame {

    // Components
    private JPanel mainPanel, actualTemperaturePanel, futurePanel1, futurePanel2, futurePanel3, futurePanel4,
            basicInfoPanel, panelSearh, contentPanels;
    private JLabel labelTextActually, labelTestState, stateWeather, labelTextHour, hour, city, country, cityPanel1,
            cityPanel2, cityPanel3, cityPanel4, countryPanel1, countryPanel2, countryPanel3, countryPanel4,
            mainTemperature, futureTemperature1, futureTemperature2, futureTemperature3, futureTemperature4,
            futureStateWeather1, futureStateWeather2, futureStateWeather3, futureStateWeather4,
            hourPanel1, hourPanel2, hourPanel3, hourPanel4, barDrag, background;
    private JButton buttonMiniizer, buttonExit, buttonSearch;
    private JTextField fieldCity;

    // Decorators
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private Color colorToPanels = new Color(0, 0, 0, 180);
    private final Color COLOR_PANEL_SEARCH = new Color(111, 122, 132);
    private final Color COLOR_TEXT = new Color(255, 255, 255);
    private final Color COLOR_CONDITION_TEXT = new Color(111, 122, 132);
    private ImageIcon iconButtonExit, iconButtonMinimizer;
    private ImageIcon iconBackground;

    private final MoveWindow moveWindow;
    private final HomeWindowEvent homeEvents;
    private final Timers timersWindow;

    // Arrays of Components
    private List<JLabel> labelsCity;
    private List<JLabel> labelsCountry;
    private List<JLabel> labelsFutureTemperatures;
    private List<JLabel> labelsHours;
    private List<JLabel> labelsConditions;

    public Home() {

        setUI();

        setSize(1020, 600);
        setTitle("App Weather - By Alexis Valente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(this);
        setUndecorated(true);

        timersWindow = new Timers(this);
        moveWindow = new MoveWindow(this);
        homeEvents = new HomeWindowEvent(this, timersWindow);

        initComponets();

        labelsCity = Arrays.asList(cityPanel1, cityPanel2, cityPanel3, cityPanel4);
        labelsCountry = Arrays.asList(countryPanel1, countryPanel2, countryPanel3, countryPanel4);
        labelsFutureTemperatures = Arrays.asList(futureTemperature1, futureTemperature2, futureTemperature3, futureTemperature4);
        labelsHours = Arrays.asList(hourPanel1, hourPanel2, hourPanel3, hourPanel4);
        labelsConditions = Arrays.asList(futureStateWeather1, futureStateWeather2, futureStateWeather3, futureStateWeather4);

        homeEvents.setEvents();
    }

    private void setUI(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getLabelHour() {
        return hour;
    }

    public JLabel getLabelMainTemperature(){
        return mainTemperature;
    }

    public JLabel getLabelCity(){
        return city;
    }

    public List<JLabel> getArrayLabelCity(){
        return labelsCity;
    }

    public List<JLabel> getArrayLabelCountry(){
        return labelsCountry;
    }

    public List<JLabel> getArrayLabelsFutureTemperatures(){
        return labelsFutureTemperatures;
    }

    public List<JLabel> getArrayLabelsHours(){
        return labelsHours;
    }

    public List<JLabel> getArrayLabelsConditions(){
        return labelsConditions;
    }

    public JLabel getLabelCountry(){
        return country;
    }

    public JLabel getLabelStateWeather(){
        return stateWeather;
    }

    public void setColorToPanels(Color color) {
        colorToPanels = color;
    }

    public Timers getTimers() {
        return timersWindow;
    }

    public void setImageButtonExit(ImageIcon image) {
        iconButtonExit = image;
    }

    public void setImageButtonMinimizer(ImageIcon image) {
        iconButtonMinimizer = image;
    }

    public void setImageBackground(ImageIcon image) {
        iconBackground = image;
    }

    private JLabel initLabelCity(JLabel city, JPanel panel) {

        city = new JLabel("-------------");
        city.setSize(panel.getWidth(), 20);
        city.setHorizontalAlignment(SwingConstants.CENTER);
        city.setLocation(0, 20);
        city.setForeground(COLOR_TEXT);
        city.setFont(new Font("Helvetica", Font.BOLD, 14));

        return city;
    }

    private void initLabelHour(JLabel hourPanel, JPanel panel) {

        hourPanel.setSize(panel.getWidth(), 20);
        hourPanel.setHorizontalAlignment(SwingConstants.CENTER);
        hourPanel.setLocation(0, 5);
        hourPanel.setForeground(COLOR_TEXT);
        hourPanel.setFont(new Font("Helvetica", Font.BOLD, 14));

    }

    private void initLabelFutureWeather(JLabel weather, JPanel panel) {

        weather.setSize(panel.getWidth(), 40);
        weather.setHorizontalAlignment(SwingConstants.CENTER);
        weather.setLocation(0, ((panel.getHeight() - weather.getHeight()) / 2) - 10);
        weather.setForeground(COLOR_TEXT);
        weather.setFont(new Font("Helvetica", Font.BOLD, 40));
    }

    private void initLabelFutureCondition(JLabel condition, JLabel weather, JPanel panel) {
        condition.setSize(panel.getWidth(), 20);
        condition.setLocation(0, weather.getY() + weather.getHeight() + 20);
        condition.setHorizontalAlignment(SwingConstants.CENTER);
        condition.setForeground(COLOR_CONDITION_TEXT);
        condition.setFont(new Font("Helvetica", Font.BOLD, 14));
    }

    private void initLabelCountry(JLabel country, JLabel condition, JPanel panel) {

        country.setSize(panel.getWidth(), 20);
        country.setLocation(0, condition.getY() + condition.getHeight() + 10);
        country.setHorizontalAlignment(SwingConstants.CENTER);
        country.setForeground(COLOR_TEXT);
        country.setFont(new Font("Helvetica", Font.BOLD, 14));
    }

    private void initComponets() {
        panels();
        textFields();
        buttons();
        labels();

        setDecoratorsContent();
        addComponentsUI();
    }

    private void addComponentsUI(){

        addComponentToMainPanel();
        addComponentToActualTemperaturePanel();
        addComponentToFuturePanel1();
        addComponentToFuturePanel2();
        addComponentToFuturePanel3();
        addComponentToFuturePanel4();
        addComponentToContentPanels();
        addComponentToBasicInfoPanel();
        addComponentToSearchPanel();
    }

    private JPanel roundPanel(JPanel panel) {

        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(20, 20); // Border corners arcs {width,height}, change this to whatever
                                                        // you want
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draws the rounded panel with borders.
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint background
            }
        };
    }

    private void panels() {

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        getContentPane().add(mainPanel);

        panelSearh = roundPanel(panelSearh);
        panelSearh.setLayout(null);
        panelSearh.setSize(620, 50);
        panelSearh.setLocation( (this.getWidth() - panelSearh.getWidth())/2, 20 );
        panelSearh.setOpaque(false);

        basicInfoPanel = roundPanel(basicInfoPanel);
        basicInfoPanel.setLayout(null);
        basicInfoPanel.setSize(350, 200);
        basicInfoPanel.setOpaque(false);
        basicInfoPanel.setLocation(20, ((this.getHeight() - basicInfoPanel.getHeight()) / 2) - 80);

        actualTemperaturePanel = roundPanel(actualTemperaturePanel);
        actualTemperaturePanel.setLayout(null);
        actualTemperaturePanel.setSize(230, 280);
        actualTemperaturePanel.setOpaque(false);
        actualTemperaturePanel.setLocation((this.getWidth() - actualTemperaturePanel.getWidth()) / 2,
                (this.getHeight() - actualTemperaturePanel.getHeight() - 160) / 2);

        contentPanels = new JPanel();
        contentPanels.setLayout(null);
        contentPanels.setSize(780, 200);
        contentPanels.setBackground(null);
        contentPanels.setBorder(null);
        contentPanels.setOpaque(false);
        contentPanels.setLocation((this.getWidth() - contentPanels.getWidth()) / 2,
                actualTemperaturePanel.getY() + actualTemperaturePanel.getHeight() + 20);

        futurePanel1 = roundPanel(futurePanel1);
        futurePanel1.setLayout(null);
        futurePanel1.setSize(170, 190);
        futurePanel1.setLocation(20, 10);
        futurePanel1.setOpaque(false);

        futurePanel2 = roundPanel(futurePanel1);
        futurePanel2.setLayout(null);
        futurePanel2.setSize(170, 190);
        futurePanel2.setLocation(futurePanel1.getX() + futurePanel1.getWidth() + 20, 10);
        futurePanel2.setOpaque(false);

        futurePanel3 = roundPanel(futurePanel1);
        futurePanel3.setLayout(null);
        futurePanel3.setSize(170, 190);
        futurePanel3.setLocation(futurePanel2.getX() + futurePanel2.getWidth() + 20, 10);
        futurePanel3.setOpaque(false);

        futurePanel4 = roundPanel(futurePanel1);
        futurePanel4.setLayout(null);
        futurePanel4.setSize(170, 190);
        futurePanel4.setLocation(futurePanel3.getX() + futurePanel3.getWidth() + 20, 10);
        futurePanel4.setOpaque(false);

    }

    private void labels() {

        barDrag = new JLabel();
        barDrag.addMouseListener(moveWindow);
        barDrag.addMouseMotionListener(moveWindow);
        barDrag.setLocation(0, 0);
        barDrag.setSize(buttonMiniizer.getX() - 10, 20);
        barDrag.setCursor(new Cursor(Cursor.MOVE_CURSOR));

        city = new JLabel("--------");
        city.setSize(actualTemperaturePanel.getWidth(), 20);
        city.setHorizontalAlignment(SwingConstants.CENTER);
        city.setLocation(0, 10);
        city.setForeground(COLOR_TEXT);
        city.setFont(new Font("Helvetica", Font.BOLD, 16));

        labelTextActually = new JLabel("Actually");
        labelTextActually.setSize(actualTemperaturePanel.getWidth(), 20);
        labelTextActually.setHorizontalAlignment(SwingConstants.CENTER);
        labelTextActually.setLocation(0, (city.getHeight() + city.getY() + 10));
        labelTextActually.setForeground(COLOR_TEXT);
        labelTextActually.setFont(new Font("Helvetica", Font.BOLD, 14));

        mainTemperature = new JLabel("------");
        mainTemperature.setSize(actualTemperaturePanel.getWidth(), 60);
        mainTemperature.setHorizontalAlignment(SwingConstants.CENTER);
        mainTemperature.setLocation(0, (actualTemperaturePanel.getHeight() - mainTemperature.getHeight()) / 2);
        mainTemperature.setForeground(COLOR_TEXT);
        mainTemperature.setFont(new Font("Helvetica", Font.BOLD, 60));

        country = new JLabel("----------");
        country.setSize(actualTemperaturePanel.getWidth(), 100);
        country.setHorizontalAlignment(SwingConstants.CENTER);
        country.setLocation(0, mainTemperature.getY() + mainTemperature.getHeight() + 20);
        country.setForeground(COLOR_TEXT);
        country.setFont(new Font("Helvetica", Font.BOLD, 16));

        background = new JLabel();
        background.setLocation(0, 0);
        background.setOpaque(true);
        background.setSize(this.getWidth(), this.getHeight());
        iconBackground = HomeImages.resizeImage(new ImageIcon("src/main/java/com/alexisvc/resources/img/defecto.png"), background.getWidth(), background.getHeight());

        // Tag for panel of basic info. (hour and condition)

        labelTestState = new JLabel("Condition");
        labelTestState.setSize(basicInfoPanel.getWidth(), 20);
        labelTestState.setFont(new Font("Helvetica", Font.BOLD, 20));
        labelTestState.setForeground(COLOR_TEXT);
        labelTestState.setLocation(0, 20);
        labelTestState.setHorizontalAlignment(SwingConstants.CENTER);

        stateWeather = new JLabel("--------");
        stateWeather.setSize(basicInfoPanel.getWidth(), 18);
        stateWeather.setFont(new Font("Helvetica", Font.BOLD, 16));
        stateWeather.setForeground(COLOR_CONDITION_TEXT);
        stateWeather.setLocation(0, 50);
        stateWeather.setHorizontalAlignment(SwingConstants.CENTER);

        labelTextHour = new JLabel("Current Time");
        labelTextHour.setSize(basicInfoPanel.getWidth(), 20);
        labelTextHour.setFont(new Font("Helvetica", Font.BOLD, 20));
        labelTextHour.setForeground(COLOR_TEXT);
        labelTextHour.setLocation(0, 100);
        labelTextHour.setHorizontalAlignment(SwingConstants.CENTER);

        hour = new JLabel();
        hour.setSize(basicInfoPanel.getWidth(), 16);
        hour.setFont(new Font("Helvetica", Font.BOLD, 16));
        hour.setForeground(COLOR_CONDITION_TEXT);
        hour.setLocation(0, 130);
        hour.setHorizontalAlignment(SwingConstants.CENTER);

        // Tag for Panels of weather future.
        cityPanel1 = initLabelCity(cityPanel1, futurePanel1);
        cityPanel2 = initLabelCity(cityPanel2, futurePanel2);
        cityPanel3 = initLabelCity(cityPanel3, futurePanel3);
        cityPanel4 = initLabelCity(cityPanel4, futurePanel4);

        hourPanel1 = new JLabel("----------");
        hourPanel2 = new JLabel("----------");
        hourPanel3 = new JLabel("----------");
        hourPanel4 = new JLabel("----------");

        initLabelHour(hourPanel1, futurePanel1);
        initLabelHour(hourPanel2, futurePanel2);
        initLabelHour(hourPanel3, futurePanel3);
        initLabelHour(hourPanel4, futurePanel4);

        futureTemperature1 = new JLabel("------");
        futureTemperature2 = new JLabel("------");
        futureTemperature3 = new JLabel("------");
        futureTemperature4 = new JLabel("------");

        initLabelFutureWeather(futureTemperature1, futurePanel1);
        initLabelFutureWeather(futureTemperature2, futurePanel2);
        initLabelFutureWeather(futureTemperature3, futurePanel3);
        initLabelFutureWeather(futureTemperature4, futurePanel4);

        futureStateWeather1 = new JLabel("---------");
        futureStateWeather2 = new JLabel("---------");
        futureStateWeather3 = new JLabel("---------");
        futureStateWeather4 = new JLabel("---------");

        initLabelFutureCondition(futureStateWeather1, futureTemperature1, futurePanel1);
        initLabelFutureCondition(futureStateWeather2, futureTemperature2, futurePanel2);
        initLabelFutureCondition(futureStateWeather3, futureTemperature3, futurePanel3);
        initLabelFutureCondition(futureStateWeather4, futureTemperature4, futurePanel4);

        countryPanel1 = new JLabel("---------------");
        countryPanel2 = new JLabel("---------------");
        countryPanel3 = new JLabel("---------------");
        countryPanel4 = new JLabel("---------------");

        initLabelCountry(countryPanel1, futureStateWeather1, futurePanel1);
        initLabelCountry(countryPanel2, futureStateWeather2, futurePanel2);
        initLabelCountry(countryPanel3, futureStateWeather3, futurePanel3);
        initLabelCountry(countryPanel4, futureStateWeather4, futurePanel4);
    }

    private void buttons() {

        buttonExit = new JButton();
        buttonExit.setFocusable(false);
        buttonExit.setBackground(null);
        buttonExit.setBorder(null);
        buttonExit.setContentAreaFilled(false);
        buttonExit.setSize(40, 40);
        buttonExit.setLocation(this.getWidth() - buttonExit.getWidth() - 10, 10);
        buttonExit.setCursor(cursor);
        iconButtonExit = new ImageIcon("src/main/java/com/alexisvc/resources/img/exit_black.png");
        buttonExit.addActionListener(new ActionListener() {

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
        iconButtonMinimizer = new ImageIcon("src/main/java/com/alexisvc/resources/img/minimizer_black.png");
        buttonMiniizer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                setState(JFrame.ICONIFIED);
            }
        });

        buttonSearch = new JButton();
        buttonSearch.setFocusable(false);
        buttonSearch.setBackground(Color.WHITE);
        buttonSearch.setBorder(null);
        buttonSearch.setSize(60, 50);
        buttonSearch.setContentAreaFilled(false);
        buttonSearch.setLocation(fieldCity.getX() + fieldCity.getWidth(), 0);
        buttonSearch.setCursor(cursor);
        buttonSearch.setIcon(new ImageIcon("src/main/java/com/alexisvc/resources/img/image_search.png"));
        buttonSearch.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                timersWindow.fetchCity(fieldCity.getText());
            }

        });
    }

    private void textFields(){
        fieldCity = new JTextField();
        fieldCity.setSize(560, 50);
        fieldCity.setHorizontalAlignment(SwingConstants.CENTER);
        fieldCity.setBorder(null);
        fieldCity.setBackground(null);
        fieldCity.setOpaque(false);
        fieldCity.setForeground(COLOR_TEXT);
        fieldCity.setLocation(0,0);
        fieldCity.setFont(new Font("Helvetica", Font.BOLD, 16));
        fieldCity.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    timersWindow.fetchCity(fieldCity.getText());
                } 
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
    }

    public void setDecoratorsContent() {
        // Labels
        background.setIcon(iconBackground);

        // Buttons
        buttonExit.setIcon(iconButtonExit);
        buttonMiniizer.setIcon(iconButtonMinimizer);

        // Panels
        actualTemperaturePanel.setBackground(colorToPanels);
        panelSearh.setBackground(COLOR_PANEL_SEARCH);
        basicInfoPanel.setBackground(colorToPanels);
        futurePanel1.setBackground(colorToPanels);
        futurePanel2.setBackground(colorToPanels);
        futurePanel3.setBackground(colorToPanels);
        futurePanel4.setBackground(colorToPanels);
    }

    public void updatePanels() {
        actualTemperaturePanel.updateUI();
    }

    private void addComponentToMainPanel() {
        // Others Panels
        mainPanel.add(actualTemperaturePanel);
        mainPanel.add(contentPanels);
        mainPanel.add(basicInfoPanel);
        mainPanel.add(panelSearh);

        // Buttons
        mainPanel.add(buttonMiniizer);
        mainPanel.add(buttonExit);

        // JLabels
        mainPanel.add(barDrag);
        mainPanel.add(background);
    }

    private void addComponentToActualTemperaturePanel() {
        // JLabels
        actualTemperaturePanel.add(city);
        actualTemperaturePanel.add(labelTextActually);
        actualTemperaturePanel.add(mainTemperature);
        actualTemperaturePanel.add(country);
    }

    private void addComponentToContentPanels() {
        contentPanels.add(futurePanel1);
        contentPanels.add(futurePanel2);
        contentPanels.add(futurePanel3);
        contentPanels.add(futurePanel4);
    }

    private void addComponentToFuturePanel1() {
        futurePanel1.add(hourPanel1);
        futurePanel1.add(cityPanel1);
        futurePanel1.add(futureTemperature1);
        futurePanel1.add(futureStateWeather1);
        futurePanel1.add(countryPanel1);
    }

    private void addComponentToFuturePanel2() {
        futurePanel2.add(hourPanel2);
        futurePanel2.add(cityPanel2);
        futurePanel2.add(futureTemperature2);
        futurePanel2.add(futureStateWeather2);
        futurePanel2.add(countryPanel2);
    }

    private void addComponentToFuturePanel3() {
        futurePanel3.add(hourPanel3);
        futurePanel3.add(cityPanel3);
        futurePanel3.add(futureTemperature3);
        futurePanel3.add(futureStateWeather3);
        futurePanel3.add(countryPanel3);
    }

    private void addComponentToFuturePanel4() {
        futurePanel4.add(hourPanel4);
        futurePanel4.add(cityPanel4);
        futurePanel4.add(futureTemperature4);
        futurePanel4.add(futureStateWeather4);
        futurePanel4.add(countryPanel4);
    }

    private void addComponentToBasicInfoPanel(){
        basicInfoPanel.add(labelTestState);
        basicInfoPanel.add(stateWeather);
        basicInfoPanel.add(labelTextHour);
        basicInfoPanel.add(hour);
    }

    private void addComponentToSearchPanel(){
        panelSearh.add(fieldCity);
        panelSearh.add(buttonSearch);
    }
}
