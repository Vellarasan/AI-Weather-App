package io.vels.ai.weather.helper.model.atlassian.confluence;

import java.time.Instant;
import java.util.List;

public record SpaceApiResponse(
        List<SpaceResult> results,
        Links _links
) {
    public record SpaceResult(
            String spaceOwnerId,
            Instant createdAt,
            String authorId,
            String homepageId,
            String icon,
            String description,
            String status,
            String name,
            String key,
            String id,
            String type,
            SpaceLinks _links,
            String currentActiveAlias
    ) {
    }

    public record SpaceLinks(
            String webui
    ) {
    }

    public record Links(
            String base
    ) {
    }
}
