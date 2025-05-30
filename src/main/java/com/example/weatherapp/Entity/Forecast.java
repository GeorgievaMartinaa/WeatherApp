package com.example.weatherapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    private LocalDate date;
    private int maxTemp;
    private boolean rainyDay;
    private String rainDescription;

    public Forecast(City city, LocalDate date, int maxTemp, boolean rainyDay, String rainDescription) {
        this.city = city;
        this.date = date;
        this.maxTemp = maxTemp;
        this.rainyDay = rainyDay;
        this.rainDescription = rainDescription;
    }
}
