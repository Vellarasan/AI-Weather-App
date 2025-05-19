package io.vels.ai.weather.helper.client;

import io.vels.ai.weather.helper.config.WeatherConfig;
import io.vels.ai.weather.helper.model.weather.WeatherResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WeatherClient {

    private final WeatherConfig weatherConfig;

    private RestClient restClient;

    public WeatherClient(WeatherConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
        this.restClient = RestClient.builder()
                .baseUrl(weatherConfig.baseUrl())
                .build();
    }

    public WeatherResponse getForecastByZipForDays(int zipCode, int daysCount) {

        ResponseEntity<WeatherResponse> response = this.restClient.get()
                .uri(weatherConfig.forecastByZipUri(), weatherConfig.apiKey(), zipCode, daysCount)
                .retrieve()
                .toEntity(WeatherResponse.class);
        return response.getBody();
    }
}
