package com.meridian.channel.quote;

import com.meridian.channel.customer.CustomerContext;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<QuoteResponse> create(@AuthenticationPrincipal Jwt jwt,
                                                @Valid @RequestBody QuoteRequest request) {
        QuoteResponse quote = quoteService.create(CustomerContext.from(jwt), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(quote);
    }

    @GetMapping("/{quoteId}")
    public QuoteResponse get(@AuthenticationPrincipal Jwt jwt, @PathVariable String quoteId) {
        return quoteService.get(CustomerContext.from(jwt), quoteId);
    }
}
