package com.alexisvc.services.Enums;

public enum PathPeticion {
    CLIMA_ACTUAl("/current.json"), FORECAST("/forecast.json");

    private String value;

    PathPeticion(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
    
}
