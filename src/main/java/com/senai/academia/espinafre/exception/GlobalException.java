/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.exception;

import com.senai.academia.espinafre.dto.ErroDTO;
import com.senai.academia.espinafre.exception.handler.ErroContraRegrasNegocio;
import com.senai.academia.espinafre.exception.handler.ErroNaoEncotrado;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Cansei2
 */
@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErroNaoEncotrado.class)
    public ResponseEntity<ErroDTO> erroNaoEncotradoException(ErroNaoEncotrado ex) {
        ErroDTO erroDTO = new ErroDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return new ResponseEntity<>(erroDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErroContraRegrasNegocio.class)
    public ResponseEntity<ErroDTO> erroContraRegrasNegocioException(ErroContraRegrasNegocio ex) {
        ErroDTO erroDTO = new ErroDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return new ResponseEntity<>(erroDTO, HttpStatus.BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        
        ErroDTO erroDTO = new ErroDTO(HttpStatus.BAD_REQUEST.value(), "Erro de validação", validationErrors);
        return new ResponseEntity<>(erroDTO, HttpStatus.BAD_REQUEST);
    }
}
