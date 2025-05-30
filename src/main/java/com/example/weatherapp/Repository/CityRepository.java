package com.example.weatherapp.Repository;

import com.example.weatherapp.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    City findByName(String name);
}
