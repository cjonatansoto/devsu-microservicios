package com.devsu.clientes.infrastructure.persistence;


import com.devsu.clientes.domain.dto.requests.ClientRequest;
import com.devsu.clientes.domain.dto.responses.ClientResponse;
import com.devsu.clientes.domain.entities.Client;
import com.devsu.clientes.domain.enums.Gender;
import com.devsu.clientes.domain.enums.Status;
import com.devsu.clientes.domain.repositories.ClientRepository;
import com.devsu.clientes.domain.services.ClientService;
import com.devsu.clientes.exceptions.customs.DataUnavailableException;
import com.devsu.clientes.util.mapper.ClientMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientResponse> findAll() {
        log.info("Find all clients");
        var clients = clientRepository.findAll()
                .stream()
                .map(clientMapper::entityToResponse)
                .toList();
        if (clients.isEmpty()) {
            throw new DataUnavailableException("no existen registros");
        }
        return clients;
    }

    @Override
    @Transactional
    public ClientResponse create(ClientRequest clientRequest) {
        log.info("Attempting to save client: {}", clientRequest);
        var clientSaved = clientRepository.save(clientMapper.requestToEntity(clientRequest));
        log.info("Client saved successfully: {}", clientSaved);
        return clientMapper.entityToResponse(clientSaved);
    }

    @Override
    public ClientResponse findById(Long id) {
        return clientMapper.entityToResponse(searchById(id));
    }

    @Override
    @Transactional
    public ClientResponse update(Long id, ClientRequest clientRequest) {
        var clientToUpdate= searchById(id);
        log.info("Attempting to update client: {}", clientToUpdate);
        clientToUpdate.setName( clientRequest.getNombre() );
        clientToUpdate.setAge( clientRequest.getEdad() );
        clientToUpdate.setIdentification( clientRequest.getIdentificacion() );
        clientToUpdate.setAddress( clientRequest.getDireccion() );
        clientToUpdate.setPhone( clientRequest.getTelefono() );
        clientToUpdate.setPassword( clientRequest.getContrasenia() );
        clientToUpdate.setGender(Gender.valueOf(clientRequest.getGenero()));
        clientToUpdate.setStatus( Status.valueOf(clientRequest.getEstado()));
        var clientSaved = clientRepository.save(clientToUpdate);
        log.info("Client saved successfully: {}", clientSaved);
        return clientMapper.entityToResponse(clientSaved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Attempting to delete client: {}", id);
        var clientToDelete = searchById(id);
        this.clientRepository.delete(clientToDelete);
        log.info("Client deleted successfully: {}", clientToDelete);
    }


    private Client searchById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new DataUnavailableException("Cliente no encontrado"));
    }
}
