package io.vels.ai.weather.helper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties("external.integrations.zipcode.apis")
public record ZipCodeBaseConfig(String baseUrl,
                                String apiKey,
                                String byCityUri,
                                String cityQuery,
                                String countryQuery) {

    @ConstructorBinding
    public ZipCodeBaseConfig {
    }
}
