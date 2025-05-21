package io.vels.ai.weather.helper.tools.atlassian;

import io.vels.ai.weather.helper.client.ConfluenceClient;
import io.vels.ai.weather.helper.model.atlassian.confluence.ConfluencePage;
import io.vels.ai.weather.helper.model.atlassian.confluence.ConfluencePageBody;
import io.vels.ai.weather.helper.model.atlassian.confluence.SpaceApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfluenceTools {

    private static final Logger logger = LoggerFactory.getLogger(ConfluenceTools.class);

    private final ConfluenceClient confluenceClient;

    public ConfluenceTools(ConfluenceClient confluenceClient) {
        this.confluenceClient = confluenceClient;
    }

    /**
     * Creates a simple page with dummy content
     *
     * @param spaceKey    The space key where the page will be created
     * @param title       The title of the page
     * @param pageContent The page content in HTML format
     * @return The created page response
     */
    @Tool(description = """
            Creates a confluence page with provided spaceKey,
            title of the page and page content in HTML format.
            If no spaceKey available get the space details first and use its 'id' as spaceKey.
            """)
    public String createSimplePage(String spaceKey,
                                   String title,
                                   String pageContent) {

        logger.info("AI Triggered Tool : createSimplePage() method");

        ConfluencePageBody body = new ConfluencePageBody("storage", pageContent);
        ConfluencePage page = new ConfluencePage(spaceKey, "current", title, null, body);

        return confluenceClient.createPage(page, false);
    }

    @Tool(description = """
            Return the space details for the provided type and status.
            If no status provided default to 'current'
            If no type provided default to 'personal'
            """)
    public List<SpaceApiResponse.SpaceResult> getCurrentPersonalSpace(String type, String status) {

        logger.info("AI Triggered Tool : getCurrentPersonalSpace() method");

        return confluenceClient.getSpace(type, status);
    }
}
