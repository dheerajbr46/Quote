package com.quotes.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.quotes.application.model.Quote;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class QuotesServiceImpl implements QuotesService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Gson gson = new Gson();

    private final WebClient webClient = WebClient.create();

    @Override
    public Quote getQuote() {
        Object result = fetchQuote();
        return mapper.convertValue(result, Quote.class);
    }

    private Object fetchQuote() {
        return webClient.mutate().build()
                .get()
                .uri("https://api.quotable.io/random?tags=inspirational")
                .headers(httpHeaders ->
                {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
                })
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
