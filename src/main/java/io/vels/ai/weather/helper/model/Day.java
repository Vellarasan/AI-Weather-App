package io.vels.ai.weather.helper.model;

public record Day(
        double maxtemp_c,
        double maxtemp_f,
        double mintemp_c,
        double mintemp_f,
        double avgtemp_c,
        double avgtemp_f,
        double maxwind_mph,
        double maxwind_kph,
        double totalprecip_mm,
        double totalprecip_in,
        double totalsnow_cm,
        double avgvis_km,
        double avgvis_miles,
        int avghumidity,
        int daily_will_it_rain,
        int daily_chance_of_rain,
        int daily_will_it_snow,
        int daily_chance_of_snow,
        Condition condition,
        double uv
) {
    // Daily weather summary
}
