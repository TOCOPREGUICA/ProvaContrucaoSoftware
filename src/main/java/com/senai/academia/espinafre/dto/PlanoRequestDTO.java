/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Cansei2
 */
public class PlanoRequestDTO {
    
    @NotNull
    @Size(min = 2, max = 100)
    private String nome;
    
    @NotNull
    private double mensalidade;
    

    public String getNome() {
        return nome;
    }

    public double getMensalidade() {
        return mensalidade;
    }
       
}
