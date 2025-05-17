package io.vels.ai.weather.helper.model;

import java.util.List;

public record ForecastDay(
        String date,
        long date_epoch,
        Day day,
        Astro astro,
        List<Hour> hour
) {
    // Weather data for a specific day
}
