/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.dto;

import com.senai.academia.espinafre.enuns.Nivel;
import java.util.List;

/**
 *
 * @author Cansei2
 */
public class TreinoResponseDTO {
    
    private Long id;
    private String nome;
    private String descricao;
    private Nivel nivel;
    private List<Long> alunosIDs;

    public TreinoResponseDTO(Long id, String nome, String descricao, Nivel nivel, List<Long> alunosIDs) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.nivel = nivel;
        this.alunosIDs = alunosIDs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public List<Long> getAlunosIDs() {
        return alunosIDs;
    }

    public void setAlunosIDs(List<Long> alunosIDs) {
        this.alunosIDs = alunosIDs;
    }
    
}
