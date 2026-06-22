package com.onidza.bff.model.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderFilterDTO(
        OrderStatus status,
        LocalDateTime fromDate,
        LocalDateTime toDate,
        BigDecimal minAmount,
        BigDecimal maxAmount
) {
}
