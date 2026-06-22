package com.onidza.bff.model.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDTO(
        Long id,
        LocalDateTime orderDate,
        BigDecimal totalAmount,
        Long clientId
) {
}
