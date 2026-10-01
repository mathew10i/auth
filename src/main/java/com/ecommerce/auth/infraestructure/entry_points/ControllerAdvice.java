package com.ecommerce.auth.infraestructure.entry_points;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> manejarDatosInvalidos(
            IllegalArgumentException error){


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error.getMessage());
    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> manejarNoEncontrado(
            RuntimeException error){


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error.getMessage());
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejarGeneral(
            Exception error){


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        "Error interno: " + error.getMessage()
                );
    }

}
