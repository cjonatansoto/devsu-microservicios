package com.devsu.clientes.domain.entities;

import com.devsu.clientes.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "clients")
@SuperBuilder
@SQLDelete(sql = "UPDATE clients SET status = 'False' WHERE id = ?")
@Where(clause = "status = 'True'")
public class Client extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
