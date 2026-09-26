package com.ecommerce.auth.infraestructure.driver_adapter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDataJpaRepository extends JpaRepository<UsuarioData,String> {
    boolean existsByCorreo(String correo);
}
