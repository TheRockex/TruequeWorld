package com.dam.truequeworld.repositories;

import com.dam.truequeworld.models.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje ,Integer> {
}
