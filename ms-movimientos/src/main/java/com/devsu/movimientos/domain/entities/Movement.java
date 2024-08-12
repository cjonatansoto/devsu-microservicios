package com.devsu.movimientos.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hibernate.generator.EventType.INSERT;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movements")
public class Movement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "initial_balance")
    private BigDecimal balanceInitial;
    private BigDecimal amount;
    private BigDecimal balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;
    @CurrentTimestamp(event = {INSERT})
    private LocalDate date;
}
