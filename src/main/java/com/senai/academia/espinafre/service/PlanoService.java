/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.service;

import com.senai.academia.espinafre.dto.PlanoRequestDTO;
import com.senai.academia.espinafre.dto.PlanoResponseDTO;
import com.senai.academia.espinafre.entity.Plano;
import com.senai.academia.espinafre.mapper.PlanoMapper;
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
public class PlanoService {
    
    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private PlanoMapper planoMapper;

    @Transactional
    public PlanoResponseDTO salvar(PlanoRequestDTO requestDTO) {
        Plano plano = planoMapper.toEntity(requestDTO);
        Plano planoSalvo = planoRepository.save(plano);
        return planoMapper.toResponseDTO(planoSalvo);
    }

    @Transactional(readOnly = true)
    public List<PlanoResponseDTO> listarTodos() {
        return planoRepository.findAll()
                .stream()
                .map(planoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PlanoResponseDTO buscarPorId(Long id) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado. ID: " + id));
        return planoMapper.toResponseDTO(plano);
    }

    @Transactional
    public PlanoResponseDTO atualizar(Long id, PlanoRequestDTO requestDTO) {
        Plano planoExistente = planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado. ID: " + id));

        planoExistente.setNome(requestDTO.getNome());
        planoExistente.setMensalidade(requestDTO.getMensalidade());

        Plano planoAtualizado = planoRepository.save(planoExistente);
        return planoMapper.toResponseDTO(planoAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado. ID: " + id));

        if (!plano.getAlunos().isEmpty()) {
            throw new RuntimeException("Não é possível remover um plano que possui alunos matriculados.");
        }

        planoRepository.delete(plano);
    }
}
