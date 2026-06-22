package com.onidza.bff.model.dto.client;

import com.onidza.bff.model.dto.coupon.CouponDTO;
import com.onidza.bff.model.dto.order.OrderDTO;
import com.onidza.bff.model.dto.profile.ProfileDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ClientDTO(
        Long id,
        String name,
        String email,
        LocalDateTime registrationDate,
        ProfileDTO profile,
        List<OrderDTO> orders,
        List<CouponDTO> coupons
) {
}
