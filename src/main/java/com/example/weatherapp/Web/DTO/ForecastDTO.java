package com.example.weatherapp.Web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDTO {
    private String city;
    private LocalDate date;
    private int maxTemp;
    private boolean rainyDay;
    private String rainDesc;
}
