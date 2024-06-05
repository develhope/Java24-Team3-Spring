package com.develhope.spring.services;

import com.develhope.spring.models.dtos.AddressDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {
    private final WebClient webClient;

    private final String APIKEY = "6cba6eee747f4f4496edf3a7cbfb2617";

    public WebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    // ritorna un indirizzo preciso, nel caso quello messo sia impreciso
    public AddressDto getGeoCoordinates(AddressDto address){
        // .replace
return null ;
    }




}
