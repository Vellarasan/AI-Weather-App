package io.vels.ai.weather.helper.model;

public record WeatherResponse(Location location, Current current, Forecast forecast) {
    // Root response object containing all weather data
}

record Condition(
        String text,
        String icon,
        int code
) {
    // Weather condition information
}

