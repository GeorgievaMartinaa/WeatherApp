package com.example.weatherapp.Service;

import com.example.weatherapp.Web.DTO.ForecastDTO;

import java.util.List;

public interface ForecastService {
    List<ForecastDTO> finAllHotDays();
    List<ForecastDTO> finAllRainyDays();
    void fetchAndSaveForecasts();
}
