package com.meridian.channel.quote;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record QuoteRequest(@NotBlank String product, @NotNull Map<String, String> answers) {
}
