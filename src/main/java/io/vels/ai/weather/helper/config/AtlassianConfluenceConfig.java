package io.vels.ai.weather.helper.config;

import io.vels.ai.weather.helper.util.HttpUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties("external.integrations.atlassian.confluence.apis")
public record AtlassianConfluenceConfig(String confluenceBaseUrl,
                                        String pagesContextUri,
                                        String spacesContextUri,
                                        String confluenceUsername,
                                        String confluenceApiToken) {

    @ConstructorBinding
    public AtlassianConfluenceConfig {
        // Added for records
    }

    public String encodedAuthValue() {
        return "Basic " + HttpUtils.encodeBasicAuth(confluenceUsername, confluenceApiToken);
    }
}

