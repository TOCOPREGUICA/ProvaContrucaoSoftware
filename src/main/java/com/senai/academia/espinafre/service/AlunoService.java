/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.service;

import com.senai.academia.espinafre.dto.AlunoRequestDTO;
import com.senai.academia.espinafre.dto.AlunoResponseDTO;
import com.senai.academia.espinafre.entity.Aluno;
import com.senai.academia.espinafre.entity.Plano;
import com.senai.academia.espinafre.mapper.AlunoMapper;
import com.senai.academia.espinafre.repository.AlunoRepository;
import com.senai.academia.espinafre.repository.PlanoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cansei2
 */
@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private AlunoMapper alunoMapper;
    
    // Dentro da classe AlunoService

    @Transactional
    public AlunoResponseDTO salvar(AlunoRequestDTO requestDTO) {
        
        if (alunoRepository.existsByCpf(requestDTO.getCpf())) {
            throw new RuntimeException("Já existe um aluno cadastrado com este CPF.");
        }

        Aluno aluno = alunoMapper.toEntity(requestDTO);
        Plano plano = planoRepository.findById(requestDTO.getPlanoId())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado."));
        aluno.setPlano(plano);
        aluno.setStatus(true);
        Aluno alunoSalvo = alunoRepository.save(aluno);

        return alunoMapper.toResponseDTO(alunoSalvo);
    }
    
    @Transactional(readOnly = true)
    public List<AlunoResponseDTO> listarTodos() {
        return alunoRepository.findAll()
            .stream()
            .map(alunoMapper::toResponseDTO)
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public AlunoResponseDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado. ID: " + id));

        return alunoMapper.toResponseDTO(aluno);
    }
    
    @Transactional
    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO requestDTO) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado. ID: " + id));

        Plano plano = planoRepository.findById(requestDTO.getPlanoId())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado."));

        alunoExistente.setName(requestDTO.getName());
        alunoExistente.setCpf(requestDTO.getCpf());
        alunoExistente.setDataNascimento(requestDTO.getDataNascimento());
        alunoExistente.setPlano(plano);

        Aluno alunoAtualizado = alunoRepository.save(alunoExistente);

        return alunoMapper.toResponseDTO(alunoAtualizado);
    }
    
    @Transactional
    public void inativar(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado. ID: " + id));

        // Lógica de inativação
        aluno.setStatus(false);

        // Salva a alteração
        alunoRepository.save(aluno);
    }
    
}
