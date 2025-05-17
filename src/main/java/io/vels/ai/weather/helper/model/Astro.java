package io.vels.ai.weather.helper.model;

public record Astro(
        String sunrise,
        String sunset,
        String moonrise,
        String moonset,
        String moon_phase,
        int moon_illumination,
        int is_moon_up,
        int is_sun_up
) {
    // Astronomical information
}
