package com.devsu.movimientos.domain.repositories;


import com.devsu.movimientos.domain.dto.responses.ClientResponse;
import com.devsu.movimientos.infrastructure.beans.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@FeignClient(name = "ms-clientes")
@LoadBalancerClient(name = "ms-clientes", configuration = LoadBalancerConfiguration.class)
public interface ClientRepository {

    @GetMapping(path = "/clientes/{id}")
    Optional<ClientResponse> findById(@PathVariable Long id);
}
