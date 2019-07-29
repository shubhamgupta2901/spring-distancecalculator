### A Spring Boot Application which returns the distance between two airports by reading their lat/long from a CSV file.

* Call the webservice as ```java http://localhost:8080/distance?src=GKA&dst=MAG```.
* MainApplication.java is the bootstrapping file for spring application. It is annotated with ```@SpringBootApplication```. This class starts our application and after starting the application, it can listen to requests through the allotted ports in the server.
* AppController.java is annotated with ```@RestController``` is a REST Controller which exposes the services to clients.
* We have stored a CSV file in ```src/main/resources```. Once a request is recieved in ```distance()``` in ```AppController.java```, it calls the  ```readCSV``` method in ```Utils.java``` with source and destination airport codes specified in the request parameters.
* ```readCSV``` method reads the CSV file and returns the latitude and longitude of the source and destination airport codes in a ```HashMap```.
* The distance between these two Latitude and Longitude is calculated and the result is returned to client in a json format. 
