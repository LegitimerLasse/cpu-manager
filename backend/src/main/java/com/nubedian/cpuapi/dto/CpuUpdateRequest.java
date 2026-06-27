package com.nubedian.cpuapi.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CpuUpdateRequest(
        @NotBlank String brand,
        @NotBlank String model,
        @NotNull Long socketId,
        @NotNull @DecimalMin("0.1") BigDecimal clockSpeedGhz,
        @NotNull @Min(1) Integer cores,
        @NotNull @Min(1) Integer threads,
        @Min(1) Integer tdpWatt,
        @NotNull @DecimalMin("0.01") BigDecimal priceEur,
        @NotNull Integer version
) {
}
