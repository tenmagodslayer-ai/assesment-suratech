# channel-bff — snapshot of `main` before PR-388

These are the files of `channel-bff` on `main` that PR-388 touches or relies on. Use them to understand the existing patterns of the codebase.

Not included, because they are not relevant to the review: the build files, the application class, `XapiQuoteRequest`, and the interceptors `CorrelationIdInterceptor` (propagates the correlation ID) and `ApiManagerAuthInterceptor` (obtains a client-credentials token for the API Manager and caches it until it expires).
