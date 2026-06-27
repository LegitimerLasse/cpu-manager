package com.nubedian.cpuapi.dto;

import java.math.BigDecimal;

public record CpuDetailDto(
        Long id,
        String brand,
        String model,
        Long socketId,
        String socketName,
        BigDecimal clockSpeedGhz,
        Integer cores,
        Integer threads,
        Integer tdpWatt,
        BigDecimal priceEur,
        Integer version
) {
}
