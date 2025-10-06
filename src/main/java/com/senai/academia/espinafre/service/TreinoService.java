/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.service;

import com.senai.academia.espinafre.dto.TreinoRequestDTO;
import com.senai.academia.espinafre.dto.TreinoResponseDTO;
import com.senai.academia.espinafre.entity.Aluno;
import com.senai.academia.espinafre.entity.Treino;
import com.senai.academia.espinafre.mapper.TreinoMapper;
import com.senai.academia.espinafre.repository.AlunoRepository;
import com.senai.academia.espinafre.repository.TreinoRepository;
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
public class TreinoService {
    
    @Autowired
    private TreinoRepository treinoRepository;
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private TreinoMapper treinoMapper;
    
    @Transactional
    public TreinoResponseDTO salvar(TreinoRequestDTO requestDTO){
        Treino treino = treinoMapper.toEntity(requestDTO);
        Treino treinoSalvo = treinoRepository.save(treino);
        return treinoMapper.toResponseDTO(treinoSalvo);
    }
    
    @Transactional(readOnly = true)
    public List<TreinoResponseDTO> listarTodos() {
        return treinoRepository.findAll()
            .stream()
            .map(treinoMapper::toResponseDTO)
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public TreinoResponseDTO buscarPorId(Long id) {
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado. ID: " + id));
        return treinoMapper.toResponseDTO(treino);
    }
    
    @Transactional
    public TreinoResponseDTO atualizar(Long id, TreinoRequestDTO requestDTO) {
        Treino treinoExistente = treinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado. ID: " + id));

        treinoExistente.setNome(requestDTO.getNome());
        treinoExistente.setDescricao(requestDTO.getDescricao());

        Treino treinoAtualizado = treinoRepository.save(treinoExistente);
        return treinoMapper.toResponseDTO(treinoAtualizado);
    }
    
    @Transactional
    public void deletar(Long id) {
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado. ID: " + id));

        if (!treino.getAlunos().isEmpty()) {
            throw new RuntimeException("Não é possível remover treinos que ainda estejam associados a alunos."); // 
        }

        treinoRepository.delete(treino);
    }
    
    @Transactional
    public void associar(Long treinoId, Long alunoId) {
        Treino treino = treinoRepository.findById(treinoId)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado. ID: " + treinoId));

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado. ID: " + alunoId));

        treino.getAlunos().add(aluno);
        treinoRepository.save(treino);
    }
    
    @Transactional
    public void desassociar(Long treinoId, Long alunoId) {
        Treino treino = treinoRepository.findById(treinoId)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado. ID: " + treinoId));

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado. ID: " + alunoId));

        treino.getAlunos().remove(aluno);
        treinoRepository.save(treino);
    }
}
