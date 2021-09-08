package com.example.baseapi.persistence.repository;

import com.example.baseapi.persistence.entity.DatosPersonales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosPersonalesRepository extends JpaRepository<DatosPersonales, Integer> {
    DatosPersonales findByCorreo(String correo);
}
