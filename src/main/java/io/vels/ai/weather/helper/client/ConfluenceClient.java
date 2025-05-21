package io.vels.ai.weather.helper.client;

import io.vels.ai.weather.helper.config.AtlassianConfluenceConfig;
import io.vels.ai.weather.helper.exception.ResourceNotFoundException;
import io.vels.ai.weather.helper.exception.ServiceUnavailableException;
import io.vels.ai.weather.helper.interceptor.CurlRequestInterceptor;
import io.vels.ai.weather.helper.model.atlassian.confluence.ConfluencePage;
import io.vels.ai.weather.helper.model.atlassian.confluence.SpaceApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Component
public class ConfluenceClient {

    private static final Logger logger = LoggerFactory.getLogger(ConfluenceClient.class);

    private final RestClient restClient;
    private final AtlassianConfluenceConfig confluenceConfig;

    public ConfluenceClient(AtlassianConfluenceConfig confluenceConfig) {
        this.restClient = RestClient.builder()
                .baseUrl(confluenceConfig.confluenceBaseUrl())
                .requestInterceptor(new CurlRequestInterceptor())
                .build();
        this.confluenceConfig = confluenceConfig;
    }

    /**
     * Creates a new page in Confluence
     *
     * @param page        The page to create
     * @param isRootLevel Is the root page
     * @return The created page response
     */
    public String createPage(ConfluencePage page, boolean isRootLevel) {

        logger.info("Creating Confluence page with title: {} and isRootLevel : {}", page.title(), isRootLevel);

        String responsePage =
                restClient
                        .post()
                        .uri(
                                UriComponentsBuilder.fromUriString(confluenceConfig.pagesContextUri())
                                        .queryParam("root-level", isRootLevel)
                                        .toUriString()
                        )
                        .header(HttpHeaders.AUTHORIZATION, confluenceConfig.encodedAuthValue())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .body(page)
                        .retrieve()
                        .onStatus(HttpStatusCode::is5xxServerError,
                                (request, response) -> {
                                    throw new ServiceUnavailableException("Remote service unavailable");
                                })
                        .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                            logger.info("Err Helper :::: {}", response.getStatusCode());
                            logger.info("Err Helper :::: {}", response.getStatusText());
                            logger.info("Complete Response :::: {}", response);
                        }))
                        .body(String.class);

        logger.info("Helper Log ::: Confluence page created successfully");

        return responsePage;
    }

    public List<SpaceApiResponse.SpaceResult> getSpace(String type, String status) {
        logger.info("Listing Spaces with Type : {} and Status : {}", type, status);

        SpaceApiResponse spaceApiResponse = restClient
                .get()
                .uri(UriComponentsBuilder.fromUriString(confluenceConfig.spacesContextUri())
                        .queryParam("status", status)
                        .queryParam("type", type)
                        .toUriString())
                .header(HttpHeaders.AUTHORIZATION, confluenceConfig.encodedAuthValue())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ResourceNotFoundException("Resource not found");
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new ServiceUnavailableException("Remote service unavailable");
                })
                .onStatus(HttpStatusCode::isError, ((request, response) -> {
                    throw new ResourceNotFoundException("Unknown Error");
                }))
                .body(SpaceApiResponse.class);

        if (spaceApiResponse == null) {
            return Collections.emptyList();
        }
        List<SpaceApiResponse.SpaceResult> results = spaceApiResponse.results();

        logger.info("Helper Log ::: Listing Spaces completed, Spaces count :: {}", results.size());
        return results;

    }
}
