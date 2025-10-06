/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.entity;

import com.senai.academia.espinafre.enuns.StatusPagamento;
import com.senai.academia.espinafre.enuns.TipoPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author Cansei2
 */
@Entity
@Table(name = "TB_PAGAMENTO")
public class Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column(name = "Data_Atribuido",nullable = false)
    private LocalDate dataAtribuido;
    
    @Column(nullable = false)
    private double valorPago;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipoPagamento;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento statusPagamento;
    
    @ManyToOne
    @JoinColumn(name = "Aluno_id", nullable = false)
    private Aluno aluno;

    public Long getId() {
        return id;
    }

    public double getValorPago() {
        return valorPago;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public LocalDate getDataAtribuido() {
        return dataAtribuido;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setDataAtribuido(LocalDate dataAtribuido) {
        this.dataAtribuido = dataAtribuido;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    

    @PrePersist
    public void prePersist() {
        this.dataAtribuido = LocalDate.now();
    }
}
