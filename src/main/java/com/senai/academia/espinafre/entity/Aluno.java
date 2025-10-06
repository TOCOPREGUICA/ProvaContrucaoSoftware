/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Cansei2
 */
@Entity
@Table(name = "TB_ALUNO")
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String cpf;
    
    @Column(name = "data_nascimento",nullable = false)
    private LocalDate dataNascimento;
    
    @Column(nullable = false)
    private boolean status;
    
    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Plano plano;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "Aluno_Treino",
        joinColumns = @JoinColumn(name = "aluno_id"),
        inverseJoinColumns = @JoinColumn(name = "treino_id")
    )
    private List<Treino> treinos;
    
    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public boolean isStatus() {
        return status;
    }

    public Plano getPlano() {
        return plano;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    
}
