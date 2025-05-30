package com.example.weatherapp.config;

import com.example.weatherapp.Entity.City;
import com.example.weatherapp.Repository.CityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInit {
    private final CityRepository cityRepository;

    public DataInit(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PostConstruct
    public void saveCities(){
        City kochani = new City("Kočani",41.91639, 22.41278);
        City skopje = new City("Skopje",41.99646, 21.43141);
        City ohrid = new City("Ohrid",41.11722, 20.80194);

        if(cityRepository.findAll().isEmpty()){
            cityRepository.saveAll(Arrays.asList(kochani,skopje,ohrid));
        }
    }
}
