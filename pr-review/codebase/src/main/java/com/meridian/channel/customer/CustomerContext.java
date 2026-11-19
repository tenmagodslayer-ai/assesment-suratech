package com.meridian.channel.customer;

import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Identity of the caller. Always built from the validated access token,
 * never from request headers or request bodies.
 */
public record CustomerContext(String customerId, String salesforceAccountId, Country country) {

    public static CustomerContext from(Jwt jwt) {
        return new CustomerContext(
                jwt.getSubject(),
                jwt.getClaimAsString("sf_account_id"),
                Country.valueOf(jwt.getClaimAsString("ctry")));
    }
}
