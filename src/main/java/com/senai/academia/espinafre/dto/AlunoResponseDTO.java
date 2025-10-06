/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.dto;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Cansei2
 */

public class AlunoResponseDTO {
    
    private Long id;
    private String name;
    private String cpf;
    private LocalDate dataNascimento;
    private Boolean status;
    private Long planoId; 
    private List<Long> treinosIds; 

    public AlunoResponseDTO(Long id, String name, String cpf, LocalDate dataNascimento, Boolean status, Long planoId, List<Long> treinosIds) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.status = status;
        this.planoId = planoId;
        this.treinosIds = treinosIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getPlanoId() {
        return planoId;
    }

    public void setPlanoId(Long planoId) {
        this.planoId = planoId;
    }

    public List<Long> getTreinosIds() {
        return treinosIds;
    }

    public void setTreinosIds(List<Long> treinosIds) {
        this.treinosIds = treinosIds;
    }
    
    
}
