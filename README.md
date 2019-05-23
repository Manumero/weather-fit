**Weather**

This exercise using microservice architecture, using spring boot 2.0 and Spring Cloud 

## Components

1. Config-server: This component has all the configurations about the microservices
2. Discovery-service: This microservice help to registrer all the instance about the microservices
3. Proxy-service: This microservice help to balnce the calls
5. Weather-service: This microservice make the call to openweathermap.org



## Setup

Get API KEY from https://openweathermap.org/appid, after you get the Key, replace the key for 
the new Key into the file **weather.properties** with the property named **key.weather** ,
this file is in weather-service under package **(src/main/resources)**

1. After download the code, can import the project into eclipse as maven project.
2. Start config-service **mvn clean spring-boot:run**
3. Start discovery-service **mvn clean spring-boot:run**
4. Start proxy-service **mvn clean spring-boot:run**
5. Finally start weather-service **mvn clean spring-boot:run**

**It is required to run the 4 projects before start weather-front**

Start Front project with the comand **mvn clean install jetty:run** and then type url http://localhost:8085/weather 

## Use APP

You can select one of the Cities avalible (London and Hong Kong) and get:

* Today's date
* The city name
* Overall description of the weather (e.g. "Light rain", "Clear sky", etc.)
* Temperature in Fahrenheit and Celsius
* Sunrise and sunset times in 12 hour format (e.g. 9:35am; 11:47pm)