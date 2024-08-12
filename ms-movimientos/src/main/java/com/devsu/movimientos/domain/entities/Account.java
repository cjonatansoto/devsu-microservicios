package com.devsu.movimientos.domain.entities;


import com.devsu.movimientos.domain.enums.AccountType;
import com.devsu.movimientos.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"number"})
})
@SQLDelete(sql = "UPDATE accounts SET status = 'False' WHERE id = ?")
@Where(clause = "status = 'True'")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "client_name")
    private String clientName;
    @Column(unique = true, nullable = false)
    private String number;
    private BigDecimal balance;
    @Column(nullable = false, name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
