package io.vels.ai.weather.helper.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static io.vels.ai.weather.helper.util.HttpUtils.generateCurlCommand;

@Component
public class CurlRequestInterceptor implements ClientHttpRequestInterceptor {

    Logger logger = LoggerFactory.getLogger(CurlRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        String curlCommand = generateCurlCommand(request, body);
        logger.info("Generated Curl Command: {}", curlCommand);
        return execution.execute(request, body);
    }
}
