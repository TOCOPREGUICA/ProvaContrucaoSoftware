/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 *
 * @author Cansei2
 */
public class AlunoRequestDTO {
    
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Size(min = 11, max = 11) 
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

    private Long planoId;

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Long getPlanoId() {
        return planoId;
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

    public void setPlanoId(Long planoId) {
        this.planoId = planoId;
    }
    
    
}
