/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Cansei2
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErroNaoEncotrado extends RuntimeException {
    public ErroNaoEncotrado(String message) {
        super(message);
    }
}
