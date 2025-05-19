package io.vels.ai.weather.helper.model.zipcode;

import io.vels.ai.weather.helper.model.weather.Query;

import java.util.List;

public record ZipcodeResponse(Query query, List<String> results) {
}
