package fr.kelig.countryclient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@AllArgsConstructor
public class ClientController {

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    private static final String BASEURL = "http://localhost:9000/countries";

    public static final String CLIENT = "client";

    @GetMapping("/")
    @CircuitBreaker(name=CLIENT, fallbackMethod = "getCountries")
    public List<Country> handleRequest() {
        return restTemplate.getForObject(BASEURL, ArrayList.class);
    }

    public List<Country> getCountries(Exception e) {
        return Stream.of(new Country("France", "Paris", "Euro"))
                .collect(Collectors.toList());
    }
}