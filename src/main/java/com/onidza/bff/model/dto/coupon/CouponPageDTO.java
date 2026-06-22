package com.onidza.bff.model.dto.coupon;

import java.util.List;

public record CouponPageDTO(
        List<CouponDTO> items,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
) {
}
