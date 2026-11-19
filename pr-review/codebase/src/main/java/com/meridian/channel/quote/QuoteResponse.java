package com.meridian.channel.quote;

import java.math.BigDecimal;
import java.time.Instant;

public record QuoteResponse(
        String quoteId,
        String product,
        BigDecimal premiumAmount,
        String currency,
        String ratingTableVersion,
        Instant expiresAt) {
}
