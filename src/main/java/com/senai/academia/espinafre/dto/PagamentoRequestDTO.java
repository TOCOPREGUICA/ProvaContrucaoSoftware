/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *
 * @author Cansei2
 */
public class PagamentoRequestDTO {
    
    @NotNull
    private double valorPago;
    
    @NotNull
    private String tipoPagamento;
    
    @NotNull
    private String status;
    
    @NotNull
    private Long alunoId;

    public double getValorPago() {
        return valorPago;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public Long getAlunoId() {
        return alunoId;
    }
    
    
    
}
