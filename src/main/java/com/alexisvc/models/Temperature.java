package com.alexisvc.models;

public class Temperature {

    private double temperatureCelcius;
    private String codeCondition;
    private String condition;
    private String hour;

    public Temperature() {
    }

    public Temperature(double temperatureCelcius, String condition, String codeCondition) {
        this.temperatureCelcius = temperatureCelcius;
        this.condition = condition;
        this.codeCondition = codeCondition;
    }

    public double getTemperatureCelcius() {
        return temperatureCelcius;
    }

    public void setTemperatureCelcius(double temperatureCelcius) {
        this.temperatureCelcius = temperatureCelcius;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCodeCondition() {
        return codeCondition;
    }

    public void setCodeCondition(String codeCondition) {
        this.codeCondition = codeCondition;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

}
