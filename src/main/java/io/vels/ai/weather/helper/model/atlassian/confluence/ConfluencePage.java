package io.vels.ai.weather.helper.model.atlassian.confluence;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ConfluencePage(String spaceId,
                             String status,
                             String title,
                             String parentId,
                             ConfluencePageBody body) {
}
