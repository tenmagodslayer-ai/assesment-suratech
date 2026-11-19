package com.meridian.channel.quote;

import com.meridian.channel.config.CountryProperties;
import com.meridian.channel.customer.CustomerContext;
import com.meridian.channel.integration.QuoteExperienceClient;
import com.meridian.channel.integration.XapiQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private static final Logger log = LoggerFactory.getLogger(QuoteService.class);

    private final QuoteExperienceClient quoteExperienceClient;
    private final CountryProperties countryProperties;

    public QuoteService(QuoteExperienceClient quoteExperienceClient, CountryProperties countryProperties) {
        this.quoteExperienceClient = quoteExperienceClient;
        this.countryProperties = countryProperties;
    }

    public QuoteResponse create(CustomerContext customer, QuoteRequest request) {
        XapiQuote quote = quoteExperienceClient.createQuote(customer, request);
        log.info("Quote created quoteId={} country={} product={}", quote.quoteId(), customer.country(), quote.product());
        return toResponse(customer, quote);
    }

    public QuoteResponse get(CustomerContext customer, String quoteId) {
        return toResponse(customer, quoteExperienceClient.getQuote(customer, quoteId));
    }

    private QuoteResponse toResponse(CustomerContext customer, XapiQuote quote) {
        return new QuoteResponse(
                quote.quoteId(),
                quote.product(),
                quote.premium(),
                countryProperties.forCountry(customer.country()).currency(),
                quote.ratingTableVersion(),
                quote.expiresAt());
    }
}
