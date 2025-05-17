package io.vels.ai.weather.helper;

import io.vels.ai.weather.helper.config.WeatherConfig;
import io.vels.ai.weather.helper.config.ZipCodeBaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({ZipCodeBaseConfig.class, WeatherConfig.class})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
