package io.vels.ai.weather.helper.model.weather;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Hour(
        long time_epoch,
        String time,
        double temp_c,
        double temp_f,
        int is_day,
        Condition condition,
        double wind_mph,
        double wind_kph,
        int wind_degree,
        String wind_dir,
        double pressure_mb,
        double pressure_in,
        double precip_mm,
        double precip_in,
        double snow_cm,
        int humidity,
        int cloud,
        double feelslike_c,
        double feelslike_f,
        double windchill_c,
        double windchill_f,
        double heatindex_c,
        double heatindex_f,
        double dewpoint_c,
        double dewpoint_f,
        int will_it_rain,
        int chance_of_rain,
        int will_it_snow,
        int chance_of_snow,
        double vis_km,
        double vis_miles,
        double gust_mph,
        double gust_kph,
        double uv
) {
    // Hourly forecast data

    // Helper method to parse time string
    public LocalDateTime getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(time, formatter);
    }
}
