package com.devsu.movimientos.domain.repositories;

import com.devsu.movimientos.domain.entities.Movement;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findAllByDateBetweenAndAccount_ClientId(LocalDate dateStart, LocalDate dateEnd, Long clientId, Sort sort);
}
