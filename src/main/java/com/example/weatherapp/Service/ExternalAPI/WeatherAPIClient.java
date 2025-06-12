package com.example.weatherapp.Service.ExternalAPI;

import com.example.weatherapp.Web.DTO.ForecastDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherAPIClient {

    @Value("${weather.api.key}")
    private String api;
    private final RestTemplate restTemplate= new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();



    public List<ForecastDTO> fetchForecast(double lat, double lon) throws JsonProcessingException {
        String url = String.format("https://api.openweathermap.org/data/2.5/forecast/daily?lat=%f&lon=%f&units=metric&cnt=16&appid=%s",
                lat, lon, api);

        String response= restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);

        List<ForecastDTO> forecastDTOS= new ArrayList<>();

        String city = root.path("city").path("name").asText();

        JsonNode days = root.path("list");

        for(JsonNode day:days){
            long dt = day.path("dt").asLong();
            LocalDate date = convertSecondToLocalDate(dt);
            int maxTemp = day.path("temp").path("max").asInt();
            JsonNode weatherGroup = day.path("weather");
            String weather = weatherGroup.get(0).path("main").asText();
            String weatherDesc = weatherGroup.get(0).path("description").asText();
            boolean rain= (weather.equals("Rain"));

            ForecastDTO forecastDTO = new ForecastDTO(city,date,maxTemp, rain,weatherDesc);

            forecastDTOS.add(forecastDTO);

        }

        return forecastDTOS;
    }

    public static LocalDate convertSecondToLocalDate(long seconds) {
        Instant instant = Instant.ofEpochSecond(seconds);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDate();
    }
}
