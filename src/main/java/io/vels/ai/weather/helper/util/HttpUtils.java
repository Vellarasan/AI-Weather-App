package io.vels.ai.weather.helper.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpRequest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HttpUtils {

    private static final ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    private HttpUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Encodes username and password for use in HTTP Basic Authentication header.
     * This method combines the username and password with a colon separator, then
     * encodes the resulting string using Base64.
     *
     * @param username The username for authentication
     * @param password The password or API key for authentication
     * @return Base64 encoded string ready to be used as the value for Authorization header
     */
    public static String encodeBasicAuth(String username, String password) {
        // Combine username and password with colon separator
        String credentials = username + ":" + password;

        // Encode the credentials using Base64
        byte[] encodedBytes = Base64.getEncoder().encode(credentials.getBytes(StandardCharsets.UTF_8));

        // Convert the encoded bytes back to a string
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

    public static String formatJson(String json) {
        try {
            Object jsonObject = new ObjectMapper().readValue(json, Object.class);
            return prettyPrinter.writeValueAsString(jsonObject);
        } catch (Exception e) {
            return "Invalid JSON: " + e.getMessage();
        }
    }

    public static String generateCurlCommand(HttpRequest request, byte[] body) {
        StringBuilder curlCommand = new StringBuilder("curl ");

        // Append the URL
        curlCommand
                .append(" --location ")
                .append("\'")
                .append(request.getURI())
                .append("\'")
                .append("\n");

        // Append the HTTP Method
        curlCommand.append(request.getMethod().name()).append(" ").append("\n");

        // Append the Headers
        request.getHeaders().forEach((name, values) ->
                values
                        .forEach(value ->
                                curlCommand
                                        .append("--header \'")
                                        .append(name)
                                        .append(": ")
                                        .append(value)
                                        .append("\' ")
                                        .append("\n"))
        );

        // Append the Request Body if any
        if (body.length > 0) {
            curlCommand
                    .append(" --data '")
                    .append(formatJson(new String(body, StandardCharsets.UTF_8)))
                    .append("'");
        }
        return curlCommand.toString();
    }
}
