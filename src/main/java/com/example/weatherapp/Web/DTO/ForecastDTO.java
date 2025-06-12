package com.example.weatherapp.Web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDTO {
    private int id;
    private String city;
    private LocalDate date;
    private int maxTemp;
    private boolean rainyDay;
    private String rainDesc;

    public ForecastDTO(String city, LocalDate date, int maxTemp, boolean rainyDay, String rainDesc) {
        this.city = city;
        this.date = date;
        this.maxTemp = maxTemp;
        this.rainyDay = rainyDay;
        this.rainDesc = rainDesc;
    }
}
