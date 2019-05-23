POC Weather

This POC using microservice architecture, using spring boot 2.0 and Spring Cloud 

This poc has the next components
1.Config-server: This componente has all the configurations about the microservices
2.Discovery-service: This microservice help to registrer all the instance about the microservices
3.Proxy-service: This microservice help to balnce the calls
4.Weather-service: is the microservice who make the call to openweathermap.org

SetUP

Get API KEY from https://openweathermap.org/appid, after you get the Key, replace the key for the new Key into the file weather.properties with the propertie name key.weather , this file is in weather-service (src/main/resources/weather.properties)
1.After download the code, can import the project into eclipse as maven project.
2.Start config-service
3.Start discovery-service
4.Start proxy-service
5.Finally start weather-service

You can verify if everything is ok with this URL
1.Eureka: verify the instance http://localhost:8061/

Start Front project with the comand mvn clean install jetty:run and then type url http://localhost:8080/weather 

Use APP

You can select one of the Cities avalible (London and Hong Kong) and get:
付odayｴs date
付he city name
賓verall description of the weather (e.g. "Light rain", "Clear sky", etc.)
付emperature in Fahrenheit and Celsius
不unrise and sunset times in 12 hour format (e.g. 9:35am; 11:47pm)
