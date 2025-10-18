package org.personal.springai.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;

@Component
public class TimeTools {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTools.class);

    @Tool(name = "getCurrentLocalTime", description = "Returns the current local time in the user's timezone")
    String getCurrentLocalTime() {
        LOGGER.info("Returning current local time in user's timezone");
        return LocalTime.now().toString();
    }

    @Tool(name = "getCurrentTime", description = "Returns the current time for a specified timezone")
    public String getCurrentTime(@ToolParam(description = "Value representing the time zone") String timeZone) {
        LOGGER.info("Returning current time for the timezone: {}", timeZone);
        return LocalTime.now(ZoneId.of(timeZone)).toString();
    }
}
