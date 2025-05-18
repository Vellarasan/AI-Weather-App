# AI-Weather-App

## Overview
AI-Weather-App is an intelligent weather forecasting application that leverages AI capabilities to provide detailed weather forecasts and location-based zip code information. The application retrieves weather data from external APIs and delivers forecasts based on zip codes. Additionally, it utilizes chat client integrations for enhanced communication capabilities.

## Key Features
- **Weather Forecasting**: Obtain weather forecasts by zip code.
- **Zip Code Retrieval**: Fetch all zip codes associated with a specific city in the US.
- **AI Integrations**: Supports communication via AI chat clients with OpenAI models.
  
## Installation Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/AI-Weather-App.git
   cd AI-Weather-App
   ```

2. **Set up environment variables**:
   Ensure that you have the necessary API keys and other configurations set up for weather and zip code API integrations.

3. **Build and run the application**:
   Using Maven, you can build and run the application using:

   ```bash
   ./mvnw spring-boot:run
   ```

## Usage Examples

To demonstrate the use of AI-Weather-App:

### Get Weather Forecast by Zip Code
Using the web API client or any REST client tool, you can make a request to retrieve weather data for a specific zip code:

```bash
GET /weather?zipCode=90210&daysCount=5
```

### Retrieve Zip Codes for a City
Fetch zip codes by city using the following request:

```bash
GET /zipcode?cityName=LosAngeles
```

## File Structure Overview

- `src/main/java/io/vels/ai/weather/helper/Application.java`: Main entry point for the Spring Boot application.
- `src/main/java/io/vels/ai/weather/helper/client`: Contains clients for interacting with external services such as WeatherClient and ZipCodeClient.
- `src/main/java/io/vels/ai/weather/helper/config`: Configuration classes for external APIs.
- `src/main/java/io/vels/ai/weather/helper/model`: Model representations including `WeatherResponse`, `ZipcodeResponse`, and others.
  
## Prerequisites or Dependencies

- **Java 17**: Ensure that Java 17 is installed on your machine.
- **Maven**: Required for building and running the application.
- **Spring Boot**: Utilized for the application's framework.
- **API Keys**: You must acquire API keys for both weather and zip code services.

## Contributing

We welcome contributions from the community. Please adhere to the following guidelines:
1. Fork the repository.
2. Create a new branch with a descriptive name.
3. Make your changes and test them thoroughly.
4. Submit a pull request with a detailed description of your changes.

## License

Currently, there is no license file available in this repository. Ensure you add a LICENSE file if you intend to open source the application. 

**Disclaimer**: Ensure that all utilized external APIs comply with their respective terms of service and usage limits.

--- 

This README provides comprehensive details on setting up and using the AI-Weather-App, while outlining its robust features and configuration requirements. Happy coding!