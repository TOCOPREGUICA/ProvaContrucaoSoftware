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
public class TreinoRequestDTO {
    
    @NotNull
    @Size(min = 3, max =100)
    private String nome;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String descricao;
    
    @NotNull
    private String nivel;

    public String getNome(){
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    
}
