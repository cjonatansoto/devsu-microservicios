package com.devsu.clientes.domain.entities;

import com.devsu.clientes.domain.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class Person  {
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
}
