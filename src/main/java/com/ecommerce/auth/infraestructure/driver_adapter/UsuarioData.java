package com.ecommerce.auth.infraestructure.driver_adapter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioData {
    @Id
    @UuidGenerator
    private String idUsuario;
    private String nombre;
    @Column(nullable = false, unique = true, length = 120)
    private String correo;
    @Column(nullable = false, length = 255)
    private String password;
    private String rol;
    private Integer edad ;
    private String numeroTelefonico;
}

