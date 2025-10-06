/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.mapper;

import com.senai.academia.espinafre.dto.TreinoRequestDTO;
import com.senai.academia.espinafre.dto.TreinoResponseDTO;
import com.senai.academia.espinafre.entity.Aluno;
import com.senai.academia.espinafre.entity.Treino;
import com.senai.academia.espinafre.enuns.Nivel;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cansei2
 */

@Component
public class TreinoMapper {
    
    public Treino toEntity(TreinoRequestDTO requestDTO){
        Treino treino = new Treino();
        
        treino.setNome(requestDTO.getNome());
        treino.setDescricao(requestDTO.getDescricao());
        
        try {
            
            Nivel nivel = Nivel.valueOf(requestDTO.getNivel().toUpperCase());
            treino.setNivel(nivel);
            
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Nivel inválido: " + requestDTO.getNivel());
        }
        return treino;
    }
    
    public TreinoResponseDTO toResponseDTO(Treino treino){
        
        List<Long> alunoIDs = (treino.getAlunos()!= null)
                ? treino.getAlunos().stream().map(Aluno::getId).collect(Collectors.toList())
                : Collections.emptyList(); 
        return new TreinoResponseDTO(
                treino.getId(),
                treino.getNome(),
                treino.getDescricao(),
                treino.getNivel(),
                alunoIDs
            );
    }
}
