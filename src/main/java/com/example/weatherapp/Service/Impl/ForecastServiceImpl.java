package com.example.weatherapp.Service.Impl;

import com.example.weatherapp.Entity.City;
import com.example.weatherapp.Entity.Forecast;
import com.example.weatherapp.Repository.CityRepository;
import com.example.weatherapp.Repository.ForecastRepository;
import com.example.weatherapp.Service.ExternalAPI.WeatherAPIClient;
import com.example.weatherapp.Service.ForecastService;
import com.example.weatherapp.Service.Mapper.ForecastMapper;
import com.example.weatherapp.Web.DTO.ForecastDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;
    private final WeatherAPIClient weatherAPIClient;
    private final ForecastMapper forecastMapper;

    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository, WeatherAPIClient weatherAPIClient, ForecastMapper forecastMapper) {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        this.weatherAPIClient = weatherAPIClient;
        this.forecastMapper = forecastMapper;
    }

    @Override
    public List<ForecastDTO> finAllHotDays() {
        List<Forecast> allForecast= forecastRepository.findAllByMaxTempGreaterThan(25);
        List<ForecastDTO> hotDays = allForecast.stream().map(forecastMapper::entityToDTO).collect(Collectors.toList());

        return hotDays;
    }

    @Override
    public List<ForecastDTO> finAllRainyDays() {
        List<Forecast> allForecast= forecastRepository.findAllByRainyDayIs(true);
        List<ForecastDTO> rainDays = allForecast.stream().map(forecastMapper::entityToDTO).collect(Collectors.toList());

        return rainDays;

    }

    @Override
    @Transactional
    public void fetchAndSaveForecasts() {
        forecastRepository.deleteAll();

        List<City> cities = cityRepository.findAll();
        for (City city:cities) {
            List<ForecastDTO> response;
            try {
                response = weatherAPIClient.fetchForecast(city.getLat(), city.getLon());
               List<Forecast> forecastList = response.stream().map(forecastMapper::dtoToEntity).collect(Collectors.toList());
               forecastRepository.saveAll(forecastList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
    }
}
