package io.vels.ai.weather.helper.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Location(
        String name,
        String region,
        String country,
        double lat,
        double lon,
        String tz_id,
        long localtime_epoch,
        String localtime
) {
    // Helper method to parse localtime string
    public LocalDateTime getLocalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(localtime, formatter);
    }
}
