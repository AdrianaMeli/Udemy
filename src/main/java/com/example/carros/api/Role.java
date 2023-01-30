package com.example.carros.api;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}