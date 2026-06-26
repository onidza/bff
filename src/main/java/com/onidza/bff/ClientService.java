package com.onidza.bff;

import com.onidza.bff.model.dto.client.ClientDTO;
import com.onidza.bff.model.dto.client.ClientsPageDTO;
import com.onidza.bff.model.dto.client.ClientsUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final RestClient clientRestClient;

    public ClientDTO getClient(Long id) {
        return clientRestClient.get()
                .uri("/clients/{id}", id)
                .attributes(clientRegistrationId("keycloak"))
                .retrieve()
                .body(ClientDTO.class);
    }

    public ClientsPageDTO getClientsPage(int page, int size) {
        return clientRestClient.get()
                .uri(builder -> builder
                        .path("/clients")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build()
                )
                .attributes(clientRegistrationId("keycloak"))
                .retrieve()
                .body(ClientsPageDTO.class);
    }

    public ResponseEntity<ClientDTO> createClient(ClientDTO clientDTO) {
        ResponseEntity<ClientDTO> backendResponse = clientRestClient.post()
                .uri("/clients")
                .attributes(clientRegistrationId("keycloak"))
                .body(clientDTO)
                .retrieve()
                .toEntity(ClientDTO.class);

        return ResponseEntity
                .status(backendResponse.getStatusCode())
                .body(backendResponse.getBody());
    }

    public ClientDTO updateClient(Long id, ClientsUpdateDTO clientDTO) {
        return clientRestClient.put()
                .uri("/clients/{id}", id)
                .attributes(clientRegistrationId("keycloak"))
                .body(clientDTO)
                .retrieve()
                .body(ClientDTO.class);
    }

    public void deleteClient(Long id) {
        clientRestClient.delete()
                .uri("/clients/{id}", id)
                .attributes(clientRegistrationId("keycloak"))
                .retrieve()
                .toBodilessEntity();
    }
}
