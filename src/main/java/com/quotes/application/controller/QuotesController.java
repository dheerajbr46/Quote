package com.quotes.application.controller;

import com.quotes.application.model.Quote;
import com.quotes.application.service.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
public class QuotesController {

    @Autowired
    private QuotesService quotesService;

    @GetMapping
    public Quote getQuote() throws Exception {
        return quotesService.getQuote();
    }

}
