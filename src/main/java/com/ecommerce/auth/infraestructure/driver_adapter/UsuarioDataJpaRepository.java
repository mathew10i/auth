package com.ecommerce.auth.infraestructure.driver_adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioDataJpaRepository extends JpaRepository<UsuarioData,String> {
    boolean existsByCorreo(String correo);
    Optional<UsuarioData> findByCorreo(String correo);
}
