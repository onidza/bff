package com.onidza.bff;

import com.onidza.bff.model.dto.client.ClientDTO;
import com.onidza.bff.model.dto.client.ClientsPageDTO;
import com.onidza.bff.model.dto.client.ClientsUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal OAuth2User user) {
        return user.getAttributes();
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        log.info("ClientController called getClient with id = {}", id);

        return clientService.getClient(id);
    }

    @GetMapping
    public ClientsPageDTO getClientsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        log.info("ClientController called getClientsPage, page = {}, size = {}", page, size);

        return clientService.getClientsPage(page, size);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        log.info("ClientController called createClient");

        return clientService.createClient(clientDTO);
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(
            @PathVariable Long id,
            @RequestBody ClientsUpdateDTO clientDTO
    ) {
        log.info("ClientController called updateClient with id = {}", id);

        return clientService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        log.info("ClientController called deleteClient with id = {}", id);

        clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }
}