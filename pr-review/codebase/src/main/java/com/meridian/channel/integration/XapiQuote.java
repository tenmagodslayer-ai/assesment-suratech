package com.meridian.channel.integration;

import java.math.BigDecimal;
import java.time.Instant;

/** Quote as returned by quote-xapi. */
public record XapiQuote(String quoteId, String product, BigDecimal premium, String ratingTableVersion, Instant expiresAt) {
}
