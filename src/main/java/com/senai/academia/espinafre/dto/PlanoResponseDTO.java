/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.dto;

import com.senai.academia.espinafre.entity.Aluno;
import java.util.List;

/**
 *
 * @author Cansei2
 */
public class PlanoResponseDTO {
    private Long id;
    private String nome;
    private double mensalidade;
    private List<Long> alunosIDs;

    public PlanoResponseDTO(Long id, String nome, double mensalidade, List<Long> alunosIDs) {
        this.id = id;
        this.nome = nome;
        this.mensalidade = mensalidade;
        this.alunosIDs = alunosIDs;
    }
    
}
