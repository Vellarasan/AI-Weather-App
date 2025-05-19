package io.vels.ai.weather.helper.model.weather;

public record Query(String code, String unit, String radius, String country) {
}
