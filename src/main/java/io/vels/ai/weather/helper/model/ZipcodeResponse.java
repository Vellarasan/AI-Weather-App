package io.vels.ai.weather.helper.model;

import java.util.List;

public record ZipcodeResponse(Query query, List<String> results) {
}
