package com.alexisvc.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.alexisvc.models.City;
import com.alexisvc.models.Temperature;
import com.alexisvc.services.Enums.PathPeticion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiWeather {

    private final String API_KEY = ResourceBundle.getBundle("apiData").getString("API_KEY");
    private final String URL_API_BASE = "http://api.weatherapi.com/v1";
    private final String LANGUAJE = "es";

    public JsonNode fetchData(String nameCity, PathPeticion path) {

        // Url to petition.
        String urlString = URL_API_BASE + path + "?key=" + API_KEY + "&q=" + FormatterParametter.formatterCity(nameCity)
                + "&lang=" + LANGUAJE;

        try {
            URL urlToRequest = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) urlToRequest.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 400) {
                return null;
            } else if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Error HTTP con codigo: " + connection.getResponseCode()
                        + " en url: " + urlString);
            }

            InputStreamReader inputReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputReader);

            String text = "";
            StringBuilder jsonString = new StringBuilder();
            while ((text = reader.readLine()) != null) {
                jsonString.append(text);
            }

            // Mapper StringJson to JsonObject
            ObjectMapper mapperJson = new ObjectMapper();
            JsonNode json = mapperJson.readTree(jsonString.toString());

            // Close resources.
            inputReader.close();
            reader.close();

            return json;
        } catch (MalformedURLException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return null;
    }

    public City getCurrentWeather(String nameCity) {

        JsonNode json = fetchData(nameCity, PathPeticion.CLIMA_ACTUAl);
        City city = null;

        if (json != null) {

            city = new City();

            String temperature = json.get("current").get("temp_c").toString();
            String condition = json.get("current").get("condition").get("text").asText();
            String code = json.get("current").get("condition").get("code").toString();
            String day = json.get("current").get("is_day").toString();
            String country = json.get("location").get("country").asText();
            String region = json.get("location").get("region").asText();
            String timeZone = json.get("location").get("tz_id").asText();

            city.setName(nameCity);
            city.setTemperature(new Temperature(Double.valueOf(temperature), condition, code));
            city.setCountry(country);
            city.setRegion(region);
            city.setIsDay(day);
            city.setTimeZone(timeZone);

        }

        return city;
    }

    public ArrayList<Temperature> getFuturePronosticToday(String nameCity) {

        ArrayList<Temperature> temperatures = null;
        JsonNode json = fetchData(nameCity, PathPeticion.FORECAST)
                .get("forecast")
                .get("forecastday")
                .get(0)
                .get("hour");

        if (json != null) {
            // Get data starting of actually hour.
            DateService dateService = new DateService();
            int sizeJsonArray = json.size();
            int elements = 0;

            temperatures = new ArrayList<>();

            for (int i = 0; i < sizeJsonArray; i++) {
                String dateString = json.get(i).get("time").asText();
                JsonNode jsonWeather = json.get(i);
                if (elements > 3) {
                    break;
                }else if (dateService.checkDates(dateService.getDateActually(), dateString)) {

                    String temperature = jsonWeather.get("temp_c").asText();
                    String condition = jsonWeather.get("condition").get("text").asText();

                    Temperature temp = new Temperature();
                    temp.setTemperatureCelcius(Double.valueOf(temperature));
                    temp.setCondition(condition);
                    temp.setHour(dateString.split(" ")[1]);

                    temperatures.add(temp);
                    elements++;
                }

            }
        }

        return temperatures;
    }

}
