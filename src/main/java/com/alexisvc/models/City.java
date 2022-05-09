package com.alexisvc.models;

public class City {

    private String name;
    private String country;
    private String region;
    private String timeZone;
    private Temperature temperature;
    private String isDay; // "1" is day "0" is night.

    public City() {
    }

    public City(String name, String country, String region, String timeZone, Temperature temperature, String isDay) {
        this.name = name;
        this.country = country;
        this.region = region;
        this.timeZone = timeZone;
        this.temperature = temperature;
        this.isDay = isDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getIsDay() {
        return isDay;
    }

    public void setIsDay(String isDay) {
        this.isDay = isDay;
    }

    

}
