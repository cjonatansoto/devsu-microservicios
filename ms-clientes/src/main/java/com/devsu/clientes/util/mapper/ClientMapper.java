package com.devsu.clientes.util.mapper;


import com.devsu.clientes.domain.dto.requests.ClientRequest;
import com.devsu.clientes.domain.dto.responses.ClientResponse;
import com.devsu.clientes.domain.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "id", target = "clienteId")
    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "gender", target = "genero")
    @Mapping(source = "age", target = "edad")
    @Mapping(source = "identification", target = "identificacion")
    @Mapping(source = "address", target = "direccion")
    @Mapping(source = "phone", target = "telefono")
    @Mapping(source = "password", target = "contrasenia")
    @Mapping(source = "status", target = "estado")
    ClientResponse entityToResponse(Client client);

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "genero", target = "gender")
    @Mapping(source = "edad", target = "age")
    @Mapping(source = "identificacion", target = "identification")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "telefono", target = "phone")
    @Mapping(source = "contrasenia", target = "password")
    @Mapping(source = "estado", target = "status")
    Client requestToEntity(ClientRequest clientRequest);

}

