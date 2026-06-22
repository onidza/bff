package com.onidza.bff.model.dto.client;

import com.onidza.bff.model.dto.profile.ProfileDTO;

public record ClientsUpdateDTO(
        String name,
        String email,
        ProfileDTO profile
) {
}
