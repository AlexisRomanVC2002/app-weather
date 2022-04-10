package com.alexisvc.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.alexisvc.services.Enums.PathPeticion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class ApiWeather {

    private final String API_KEY = ResourceBundle.getBundle("apiData").getString("API_KEY");
    private final String URL_API_BASE = "http://api.weatherapi.com/v1";
    private final String LANGUAJE = "es";

    public HashMap<String, String> getCurrentWeather(String city) {

        String urlString = URL_API_BASE + PathPeticion.CLIMA_ACTUAl +
                "?key=" + API_KEY + "&q=" + FormatterParametter.formatterCity(city) + "&lang=" + LANGUAJE;
        HashMap<String, String> data = new HashMap<>();

        try {
            URL urlToRequest = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) urlToRequest.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
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
            inputReader.close();
            reader.close();

            // Mapper StringJson to JsonObject
            ObjectMapper mapperJson = new ObjectMapper();
            JsonNode json = mapperJson.readTree(jsonString.toString());

            String temperature = json.get("current").get("temp_c").toString();
            String condition = json.get("current").get("condition").get("text").toString();
            String country = json.get("location").get("country").toString();

            data.put("temperature", temperature);
            data.put("condition", condition);
            data.put("country", country);

        } catch (MalformedURLException e) {
            System.err.println("Error with url: " + urlString + " " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error to open connection: " + e.getMessage());
        }

        return data;
    }

    public HashMap<String, HashMap<String, String>> getFuturePronosticToday(String city) {
        String cityFormatter = FormatterParametter.formatterCity(city);
        HashMap<String, HashMap<String, String>> data = new HashMap<>();
        String urlString = URL_API_BASE + PathPeticion.FORECAST + "?key=" + API_KEY +
                "&q=" + cityFormatter + "&lang=" + LANGUAJE;

        try {
            URL urlToRequest = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) urlToRequest.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Error HTTP with code: " + connection.getResponseCode());
            }

            InputStreamReader inputReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputReader);

            String text = "";
            StringBuilder jsonString = new StringBuilder();

            while ((text = reader.readLine()) != null) {
                jsonString.append(text);
            }

            // Mapper jsonString to JsonNode
            JsonMapper mapper = new JsonMapper();
            JsonNode json = mapper.readTree(jsonString.toString())
                    .get("forecast")
                    .get("forecastday")
                    .get(0)
                    .get("hour");

            // Get data starting of actually hour.
            DateService dateService = new DateService();
            int sizeJsonArray = json.size();
            int elements = 0;

            for (int i = 0; i < sizeJsonArray; i++) {
                String dateString = json.get(i).get("time").asText();
                JsonNode jsonWeather = json.get(i);
                if (elements > 3) {
                    break;
                }
                if (dateService.checkDates(dateService.getDateActually(), dateString)) {
                    HashMap<String, String> info = new HashMap<>();

                    String temperature = jsonWeather.get("temp_c").toString();
                    String condition = jsonWeather.get("condition").get("text").toString();

                    info.put("temperature", temperature);
                    info.put("condition", condition);
                    data.put(dateString.split(" ")[1], info);
                    elements++;
                }
            }

        } catch (MalformedURLException e) {
            System.err.println("Error with url: " + urlString + " " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error to open connection: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }

}
