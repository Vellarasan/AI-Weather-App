package io.vels.ai.weather.helper.tools;


import io.vels.ai.weather.helper.client.WeatherClient;
import io.vels.ai.weather.helper.model.Hour;
import io.vels.ai.weather.helper.model.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherTools {

    Logger log = LoggerFactory.getLogger(WeatherTools.class);

    private WeatherClient weatherClient;

    public WeatherTools(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }


    @Tool(description = "Get the weather temperature in celsius for the provided zipcode and number of days count")
    List<List<Double>> getForeCastByZipCode(int zipcode, int daysCount) {
        WeatherResponse weatherResponse = weatherClient.getForecastByZipForDays(zipcode, daysCount);

        log.info("Zipcode ::: {}", weatherResponse.forecast());
        return weatherResponse
                .forecast()
                .forecastday()
                .stream()
                .map(eachForeCast ->
                        eachForeCast.hour()
                                .stream()
                                .map(Hour::temp_c).toList())
                .toList();
    }
}