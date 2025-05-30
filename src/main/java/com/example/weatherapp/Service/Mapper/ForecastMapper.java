package com.example.weatherapp.Service.Mapper;

import com.example.weatherapp.Entity.City;
import com.example.weatherapp.Entity.Forecast;
import com.example.weatherapp.Repository.CityRepository;
import com.example.weatherapp.Web.DTO.ForecastDTO;
import org.springframework.stereotype.Component;

@Component
public class ForecastMapper {
    private final CityRepository cityRepository;

    public ForecastMapper(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Forecast dtoToEntity(ForecastDTO forecastDTO){
        City city = cityRepository.findByName(forecastDTO.getCity());
        return new Forecast(city,forecastDTO.getDate(),forecastDTO.getMaxTemp(),forecastDTO.isRainyDay(), forecastDTO.getRainDesc());
    }

    public ForecastDTO entityToDTO(Forecast forecast){
        return new ForecastDTO(forecast.getCity().getName(),
                forecast.getDate(),
                forecast.getMaxTemp(),
                forecast.isRainyDay(),
                forecast.getRainDescription());
    }
}
