# AI-Weather-App

## Project Overview
AI-Weather-App is an intelligent weather application that integrates with various APIs to provide weather forecasts based on zipcode and city search functionality. It leverages AI-driven chat functionality to improve user interaction and provides integration capabilities with Atlassian Confluence for document management.

## Installation Instructions

To run the AI-Weather-App locally, follow these steps:

1. **Clone the Repository**
   ```sh
   git clone https://github.com/yourusername/AI-Weather-App.git
   cd AI-Weather-App
   ```

2. **Set Up Your Environment**

   Ensure you have Java and Maven installed on your system.

3. **Configuration**

   The application fetches configuration properties from `application.properties`. Ensure you have the relevant API keys and URLs set up in your configuration.

4. **Build the Application**

   Utilize Maven to install dependencies and build the application:

   ```sh
   mvn clean install
   ```

5. **Run the Application**

   ```sh
   mvn spring-boot:run
   ```

## Usage Examples

- **Fetch Weather Forecast by Zip Code:**

  The application uses a REST client to fetch weather data by zip code:

  ```java
  WeatherResponse response = weatherClient.getForecastByZipForDays(12345, 5);
  ```

- **Interact with Atlassian Confluence:**

  Use the provided Confluence client for interactions:

  ```java
  ConfluencePage page = confluenceClient.retrievePageById(pageId);
  ```

## Key Features

- **Weather Forecasts:** Fetch weather data based on zip codes or city names.
- **AI Chat Integration:** Using AI chat models to provide enhanced user interactions.
- **Atlassian Confluence Integration:** Seamless retrieval and management of pages and spaces.
- **Request Interception:** Intercept requests to generate curl commands and log them for auditing.

## File Structure Overview

```
AI-Weather-App/
│
├── src/
│   ├── main/
│   │   ├── java/io/vels/ai/weather/helper/
│   │   │   ├── Application.java
│   │   │   ├── config/
│   │   │   │   ├── ZipCodeBaseConfig.java
│   │   │   │   ├── WeatherConfig.java
│   │   │   │   ├── AtlassianConfluenceConfig.java
│   │   │   │   ├── ChatClientConfig.java
│   │   │   ├── client/
│   │   │   │   ├── WeatherClient.java
│   │   │   │   ├── ConfluenceClient.java
│   │   │   │   ├── ZipCodeClient.java
│   │   │   ├── interceptor/
│   │   │   │   ├── CurlRequestInterceptor.java
│   │   │   ├── model/
│   │   │   │   ├── weather/
│   │   │   │   ├── zipcode/
│   │   │   │   │   ├── ZipcodeResponse.java
│   │   │   │   │   ├── ...
│   │   ─── resources/
│   └── test/
├── pom.xml
└── README.md
```

## Prerequisites or Dependencies

- **Java 17** or higher
- **Maven 3.6** or later
- **Spring Boot** for application configuration management

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any proposed changes or features.

## License Information

This project is licensed under the MIT License. See the `LICENSE` file for more information. If the LICENSE file is not present, it's recommended to reach out to the repository owner for clarity on the licensing terms.

---

Happy coding! If you have any questions or need further assistance, feel free to reach out.