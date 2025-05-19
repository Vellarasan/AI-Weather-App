package io.vels.ai.weather.helper.tools;


import io.vels.ai.weather.helper.client.ZipCodeClient;
import io.vels.ai.weather.helper.model.zipcode.ZipcodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class ZipcodeTools {

    Logger logger = LoggerFactory.getLogger(ZipcodeTools.class);

    private ZipCodeClient zipCodeClient;

    public ZipcodeTools(ZipCodeClient zipCodeClient) {
        this.zipCodeClient = zipCodeClient;
    }


    @Tool(description = "Get the zip code of the provided city name")
    String getZipCodeByCity(String cityName) {
        logger.info("AI Triggered Tool : getZipCodeByCity() method");

        ZipcodeResponse zipCode = zipCodeClient.getAllByCity(cityName);

        return zipCode.results().get(0);
    }
}