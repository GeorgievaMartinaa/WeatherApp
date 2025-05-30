package com.example.weatherapp.TestService;

import com.example.weatherapp.Entity.City;
import com.example.weatherapp.Entity.Forecast;
import com.example.weatherapp.Repository.ForecastRepository;
import com.example.weatherapp.Service.Impl.ForecastServiceImpl;
import com.example.weatherapp.Service.Mapper.ForecastMapper;
import com.example.weatherapp.Web.DTO.ForecastDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ForecastServiceImplTest {

    @Mock
    private ForecastRepository forecastRepository;

    @Mock
    private ForecastMapper forecastMapper;

    @InjectMocks
    private ForecastServiceImpl forecastService;


    City city = new City("Kochani",41.91639, 22.41278);

    Forecast forecast1 = new Forecast(city,LocalDate.now(),30, false , "Clear sky");
    Forecast forecast2 = new Forecast(city,LocalDate.now(),23, true , "Rain");
    Forecast forecast3 = new Forecast(city,LocalDate.now(),28, true , "Light Rain");

    ForecastDTO forecastDto1 = new ForecastDTO("Kochani",LocalDate.now(),30, false , "Clear sky");
    ForecastDTO forecastDto2 = new ForecastDTO("Kochani",LocalDate.now(),23, true , "Rain");
    ForecastDTO forecastDto3 = new ForecastDTO("Kochani",LocalDate.now(),28, true , "Light Rain");

    @Test
    void testFindAllHotDays() {

        when(forecastRepository.findAllByMaxTempGreaterThan(25)).thenReturn(List.of(forecast1,forecast3));
        when(forecastMapper.entityToDTO(forecast1)).thenReturn(forecastDto1);
        when(forecastMapper.entityToDTO(forecast3)).thenReturn(forecastDto3);

        List<ForecastDTO> result = forecastService.finAllHotDays();

        assertEquals(2, result.size());
        assertEquals(30, result.get(0).getMaxTemp());
        assertEquals(28, result.get(1).getMaxTemp());

        verify(forecastRepository).findAllByMaxTempGreaterThan(25);
        verify(forecastMapper).entityToDTO(forecast1);
        verify(forecastMapper).entityToDTO(forecast3);
    }

    @Test
    void testFindAllRainyDays() {

        when(forecastRepository.findAllByRainyDayIs(true)).thenReturn(List.of(forecast2,forecast3));
        when(forecastMapper.entityToDTO(forecast2)).thenReturn(forecastDto2);
        when(forecastMapper.entityToDTO(forecast3)).thenReturn(forecastDto3);

        List<ForecastDTO> result = forecastService.finAllRainyDays();

        assertEquals(2, result.size());
        assertTrue(result.get(0).isRainyDay());
        assertTrue(result.get(1).isRainyDay());

        verify(forecastRepository).findAllByRainyDayIs(true);
        verify(forecastMapper).entityToDTO(forecast2);
        verify(forecastMapper).entityToDTO(forecast3);
    }
}
