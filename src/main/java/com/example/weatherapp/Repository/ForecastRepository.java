package com.example.weatherapp.Repository;

import com.example.weatherapp.Entity.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Integer>{
    void deleteByCity_Name(String city);
    List<Forecast> findAllByMaxTempGreaterThan(int celsiusDegrees);
    List<Forecast> findAllByRainyDayIs(boolean rain);
}
