/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.dto;

import com.senai.academia.espinafre.enuns.StatusPagamento;
import com.senai.academia.espinafre.enuns.TipoPagamento;
import java.time.LocalDate;

/**
 *
 * @author Cansei2
 */
public class PagamentoResponseDTO {
    
    private Long id;
    private LocalDate dataAtribuido;
    private double valorPago;
    private TipoPagamento tipoPagamento;
    private StatusPagamento statusPagamento;
    private Long alunoId;

    public PagamentoResponseDTO(Long id, LocalDate dataAtribuido, double valorPago, TipoPagamento tipoPagamento, StatusPagamento statusPagamento, Long alunoId) {
        this.id = id;
        this.dataAtribuido = dataAtribuido;
        this.valorPago = valorPago;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = statusPagamento;
        this.alunoId = alunoId;
    }
    
    
}
