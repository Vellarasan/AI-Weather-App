package io.vels.ai.weather.helper.client;

import io.vels.ai.weather.helper.config.ZipCodeBaseConfig;
import io.vels.ai.weather.helper.model.ZipcodeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ZipCodeClient {

    private final ZipCodeBaseConfig zipCodeBaseConfig;
    private RestClient restClient;

    public ZipCodeClient(ZipCodeBaseConfig zipCodeBaseConfig) {
        this.zipCodeBaseConfig = zipCodeBaseConfig;
        this.restClient = RestClient.builder()
                .baseUrl(zipCodeBaseConfig.baseUrl())
                .build();
    }

    public ZipcodeResponse getAllByCity(String cityName) {
        ResponseEntity<ZipcodeResponse> response = this.restClient.get()
                .uri(zipCodeBaseConfig
                        .byCityUri(), zipCodeBaseConfig.apiKey(), cityName, "US")
                .retrieve()
                .toEntity(ZipcodeResponse.class);
        return response.getBody();
    }
}
