package com.meridian.channel.integration;

import com.meridian.channel.config.IntegrationProperties;
import com.meridian.channel.customer.CustomerContext;
import com.meridian.channel.quote.QuoteRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * Client for quote-xapi, published in the API Manager.
 * Timeouts and the circuit breaker are mandatory for every outbound call (cell standard).
 */
@Component
public class QuoteExperienceClient {

    private final RestClient restClient;

    public QuoteExperienceClient(RestClient.Builder builder, IntegrationProperties properties) {
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.defaults()
                .withConnectTimeout(properties.connectTimeout())
                .withReadTimeout(properties.readTimeout());
        this.restClient = builder
                .baseUrl(properties.quoteExperienceApiUrl())
                .requestFactory(ClientHttpRequestFactoryBuilder.detect().build(settings))
                .requestInterceptor(new CorrelationIdInterceptor())
                .requestInterceptor(new ApiManagerAuthInterceptor(properties.clientRegistrationId()))
                .build();
    }

    @CircuitBreaker(name = "quote-xapi")
    public XapiQuote createQuote(CustomerContext customer, QuoteRequest request) {
        return restClient.post()
                .uri("/quotes")
                .body(XapiQuoteRequest.from(customer, request))
                .retrieve()
                .body(XapiQuote.class);
    }

    @CircuitBreaker(name = "quote-xapi")
    public XapiQuote getQuote(CustomerContext customer, String quoteId) {
        return restClient.get()
                .uri("/quotes/{quoteId}?country={country}", quoteId, customer.country())
                .retrieve()
                .body(XapiQuote.class);
    }
}
