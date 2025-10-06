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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataAtribuido() {
        return dataAtribuido;
    }

    public void setDataAtribuido(LocalDate dataAtribuido) {
        this.dataAtribuido = dataAtribuido;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }
    
    
}
