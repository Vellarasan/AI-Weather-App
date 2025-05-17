package io.vels.ai.weather.helper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties("external.integrations.weather.apis")
public record WeatherConfig(String baseUrl,
                            String apiKey,
                            String forecastByZipUri) {

    @ConstructorBinding
    public WeatherConfig {
    }
}
