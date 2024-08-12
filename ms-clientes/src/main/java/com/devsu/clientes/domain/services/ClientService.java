package com.devsu.clientes.domain.services;

import com.devsu.clientes.domain.dto.requests.ClientRequest;
import com.devsu.clientes.domain.dto.responses.ClientResponse;

import java.util.List;

public interface ClientService {
    List<ClientResponse> findAll();
    ClientResponse create(ClientRequest clientRequest);
    ClientResponse findById(Long id);
    ClientResponse update(Long id, ClientRequest clientRequest);
    void delete(Long id);
}
