/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.mapper;

import com.senai.academia.espinafre.dto.PlanoRequestDTO;
import com.senai.academia.espinafre.dto.PlanoResponseDTO;
import com.senai.academia.espinafre.entity.Aluno;
import com.senai.academia.espinafre.entity.Plano;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cansei2
 */
@Component
public class PlanoMapper {
    
    public Plano toEntity(PlanoRequestDTO RequestDTO){
        Plano plano = new Plano();
        
        plano.setNome(RequestDTO.getNome());
        plano.setMensalidade(RequestDTO.getMensalidade());
        return plano;
    }
    
    public PlanoResponseDTO toResponseDTO(Plano plano){
        
            List<Long> alunosIDs = (plano.getAlunos()!= null)
                ? plano.getAlunos().stream().map(Aluno::getId).collect(Collectors.toList())
                : Collections.emptyList();  
            
        return new PlanoResponseDTO(
                plano.getId(),
                plano.getNome(),
                plano.getMensalidade(),
                alunosIDs  
        );
    }
}
