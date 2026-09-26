package com.ecommerce.auth.domain.usecase;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGetaway;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioUseCase {
    private UsuarioGetaway usuarioGetaway;

    public UsuarioUseCase(UsuarioGetaway usuarioGetaway){
        this.usuarioGetaway = usuarioGetaway;
    }


    public Usuario guardarUsuario(Usuario usuario) {
        validarNoNulo(usuario.getNombre(), "nombre");
        validarNoNulo(usuario.getCorreo(), "correo");
        validarNoNulo(usuario.getPassword(), "password");
        validarNoNulo(usuario.getRol(), "rol");
        validarNoNulo(usuario.getEdad(), "edad");
        validarNoNulo(usuario.getNumeroTelefonico(), "numeroTelefonico");

        if (usuario.getEdad() <=18){
            throw new NullPointerException("Usuario menor de edad, la edad es mayor a 18");
        }
        if (usuarioGetaway.existeUsuarioPorCorreo(usuario.getCorreo())) {
            throw new IllegalStateException(
                    "Ya existe un usuario registrado con este correo."
            );
        }
        Usuario usuarioGuardado= usuarioGetaway.guardarUsuario(usuario);
        return usuarioGuardado;
    }

    private void validarNoNulo (Object valor, String campo) {
            if (valor == null) {
                throw new IllegalArgumentException("El campo " + campo + " no puede ser nulo");
            }
    }
    public Usuario buscarUsuarioPorId(String usuarioId) {
        return usuarioGetaway.buscarUsuarioPorId(usuarioId);
    }

    public Usuario actualizarUsuario(Usuario usuario) {

        // Primero verifica que el usuario exista
        Usuario usuarioActual = usuarioGetaway.buscarUsuarioPorId(
                usuario.getIdUsuario()
        );

        // EDAD
        if (usuario.getEdad() != null) {

            if (usuario.getEdad() <= 18) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Usuario menor de edad"
                );
            }
        }

        // NOMBRE - NO SE PUEDE MODIFICAR
        if (usuario.getNombre() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre no se puede modificar"
            );
        }

        // ROL - NO SE PUEDE MODIFICAR
        if (usuario.getRol() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El rol no se puede modificar"
            );
        }

        // CORREO
        if (usuario.getCorreo() != null) {

            if (usuario.getCorreo().isBlank()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "El correo no puede estar vacío"
                );
            }

            if (!usuario.getCorreo().equals(usuarioActual.getCorreo())
                    && usuarioGetaway.existeUsuarioPorCorreo(usuario.getCorreo())) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Ya existe un usuario registrado con este correo"
                );
            }
        }

        // PASSWORD
        if (usuario.getPassword() != null) {

            if (usuario.getPassword().isBlank()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "La contraseña no puede estar vacía"
                );
            }
        }

        // TELÉFONO
        if (usuario.getNumeroTelefonico() != null) {

            if (usuario.getNumeroTelefonico().isBlank()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "El número telefónico no puede estar vacío"
                );
            }
        }

        return usuarioGetaway.actualizarUsuario(usuario);
    }
    public void eliminarUsuarioPorId(String usuarioId) {
        usuarioGetaway.eliminarUsuarioPorId(usuarioId);
    }

}
