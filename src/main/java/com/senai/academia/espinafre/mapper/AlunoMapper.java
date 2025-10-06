/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.mapper;

import com.senai.academia.espinafre.dto.AlunoRequestDTO;
import com.senai.academia.espinafre.dto.AlunoResponseDTO;
import com.senai.academia.espinafre.entity.Aluno;
import com.senai.academia.espinafre.entity.Pagamento;
import com.senai.academia.espinafre.entity.Treino;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cansei2
 */
@Component
public class AlunoMapper {
    
    public Aluno toEntity(AlunoRequestDTO requestDTO){
        Aluno aluno = new Aluno();
        aluno.setName(requestDTO.getName());
        aluno.setCpf(requestDTO.getCpf());
        aluno.setDataNascimento(requestDTO.getDataNascimento());
        return aluno;
    }
    
    public AlunoResponseDTO toResponseDTO(Aluno aluno){
        
        Long planoId = (aluno.getPlano() != null) ? aluno.getPlano().getId() : null;

        List<Long> treinosIds = (aluno.getTreinos() != null) 
            ? aluno.getTreinos().stream().map(Treino::getId).collect(Collectors.toList())
            : Collections.emptyList();
 
        return new AlunoResponseDTO(
            aluno.getId(),
            aluno.getName(),
            aluno.getCpf(),
            aluno.getDataNascimento(),
            aluno.isStatus(),
            planoId,
            treinosIds    
        );
    }
}
