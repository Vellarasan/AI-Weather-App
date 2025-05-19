package io.vels.ai.weather.helper.tools;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateTimeTools {

    Logger logger = LoggerFactory.getLogger(DateTimeTools.class);

    @Tool(description = "Get the current date and time in the user's timezone")
    String getCurrentDateTime() {

        logger.info("AI Triggered Tool : getCurrentDateTime() method");

        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }
}