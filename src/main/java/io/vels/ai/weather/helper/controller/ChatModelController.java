package io.vels.ai.weather.helper.controller;

import io.vels.ai.weather.helper.tools.DateTimeTools;
import io.vels.ai.weather.helper.tools.WeatherTools;
import io.vels.ai.weather.helper.tools.ZipcodeTools;
import io.vels.ai.weather.helper.tools.atlassian.ConfluenceTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/chat")
public class ChatModelController {

    // The ChatClient bean is configured under config/ChatClientConfig
    private final ChatClient chatClient;
    private final DateTimeTools dateTimeTools;
    private final ZipcodeTools zipcodeTools;
    private final WeatherTools weatherTools;
    private final ConfluenceTools confluenceTools;
    Logger log = LoggerFactory.getLogger(ChatModelController.class);

    public ChatModelController(ChatClient chatClient,
                               DateTimeTools dateTimeTools,
                               ZipcodeTools zipcodeTools,
                               WeatherTools weatherTools, ConfluenceTools confluenceTools) {
        this.chatClient = chatClient;
        this.dateTimeTools = dateTimeTools;
        this.zipcodeTools = zipcodeTools;
        this.weatherTools = weatherTools;
        this.confluenceTools = confluenceTools;
    }

    @GetMapping("/simple-chat")
    String simpleChat(@RequestParam(value = "question", defaultValue = "Tell me a joke") String question) {
        ChatResponse chatResponse = chatClient.prompt(question).call().chatResponse();
        return chatResponse != null ?
                chatResponse.getResult().getOutput().getText() :
                "No response received. Try again";
    }

    @GetMapping("/withToolCalling")
    String chatWithFunction(
            @RequestParam(value = "question", defaultValue = "What is the weather tomorrow in Plano?") String question) {

        return invokeAIClientWithTools(question);
    }

    @GetMapping("/weatherToConfluence")
    String weatherToConfluencePage(
            @RequestParam(value = "query",
                    defaultValue = "Get tomorrow weather in Bothell and make it as page title and generate content based on the weather response and create the page") String query) {
        return invokeAIClientWithTools(query);
    }

    /**
     * This method invokes the AI chat client with a given question and uses DateTimeTools for the interaction.
     * It makes a call to the chat client and returns the content of the response.
     *
     * @param question The question that is to be asked to the AI chat client. It should not be null.
     * @return The content of the response from the AI chat client after asking the question. If the chat client
     * doesn't return a response, this method will return null.
     */
    private String invokeAIClientWithTools(String question) {
        return chatClient
                .prompt(question)
                .tools(zipcodeTools, dateTimeTools, weatherTools, confluenceTools)
                .call()
                .content();
    }
}
