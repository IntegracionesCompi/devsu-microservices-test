package com.devsu.cuenta.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ClienteServiceClient {

    private final RestClient restClient;

    public ClienteServiceClient(@Value("${cliente.service.base-url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public boolean existeCliente(String clienteId) {
        try {
            ResponseEntity<String> resp = restClient.get()
                    .uri("/clientes/{id}", clienteId)
                    .retrieve()
                    .toEntity(String.class);

            return resp.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
