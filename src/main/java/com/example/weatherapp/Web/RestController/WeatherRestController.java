package com.example.weatherapp.Web.RestController;

import com.example.weatherapp.Service.ForecastService;
import com.example.weatherapp.Web.DTO.ForecastDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherRestController {
    private final ForecastService forecastService;

    public WeatherRestController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/load")
    public void loadForecastData(){
        forecastService.fetchAndSaveForecasts();
    }

    @GetMapping("/hotDays")
    public ResponseEntity<List<ForecastDTO>> getHotDays(){
        List<ForecastDTO> hotDays = forecastService.finAllHotDays();

        return ResponseEntity.ok(hotDays);
    }

    @GetMapping("/rainyDays")
    public ResponseEntity<List<ForecastDTO>> getRainyDays(){
        List<ForecastDTO> rainyDays = forecastService.finAllRainyDays();

        return ResponseEntity.ok(rainyDays);
    }
}
