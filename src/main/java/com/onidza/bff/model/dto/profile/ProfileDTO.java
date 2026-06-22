package com.onidza.bff.model.dto.profile;

public record ProfileDTO(
        Long id,
        String address,
        String phone,
        Long clientId
) {
}
