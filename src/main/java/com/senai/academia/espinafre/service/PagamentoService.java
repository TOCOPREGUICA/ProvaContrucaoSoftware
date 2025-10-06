/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.service;

import com.senai.academia.espinafre.dto.PagamentoRequestDTO;
import com.senai.academia.espinafre.dto.PagamentoResponseDTO;
import com.senai.academia.espinafre.entity.Aluno;
import com.senai.academia.espinafre.entity.Pagamento;
import com.senai.academia.espinafre.enuns.StatusPagamento;
import com.senai.academia.espinafre.mapper.PagamentoMapper;
import com.senai.academia.espinafre.repository.AlunoRepository;
import com.senai.academia.espinafre.repository.PagamentoRepository;
import java.time.LocalDate;
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
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Transactional
    public PagamentoResponseDTO salvar(PagamentoRequestDTO requestDTO) {
        Aluno aluno = alunoRepository.findById(requestDTO.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado. ID: " + requestDTO.getAlunoId()));

        if (aluno.getPlano() == null) {
            throw new RuntimeException("O aluno não possui um plano ativo para gerar um pagamento.");
        }
        
        Pagamento pagamento = pagamentoMapper.toEntity(requestDTO);
        pagamento.setValorPago(aluno.getPlano().getMensalidade());
        pagamento.setStatusPagamento(StatusPagamento.PENDENTE);
        pagamento.setAluno(aluno);
        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
        return pagamentoMapper.toResponseDTO(pagamentoSalvo);
    }
    
    @Transactional(readOnly = true)
    public List<PagamentoResponseDTO> listarTodos() {
        return pagamentoRepository.findAll()
                .stream()
                .map(pagamentoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PagamentoResponseDTO buscarPorId(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado. ID: " + id));
        return pagamentoMapper.toResponseDTO(pagamento);
    }

    @Transactional
    public PagamentoResponseDTO confirmarPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado. ID: " + id));

        pagamento.setStatusPagamento(StatusPagamento.PAGO);

        Pagamento pagamentoConfirmado = pagamentoRepository.save(pagamento);
        return pagamentoMapper.toResponseDTO(pagamentoConfirmado);
    }
    
    @Transactional
    public PagamentoResponseDTO cancelarPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado. ID: " + id));

        pagamento.setStatusPagamento(StatusPagamento.CANCELADO);

        Pagamento pagamentoCancelado = pagamentoRepository.save(pagamento);
        return pagamentoMapper.toResponseDTO(pagamentoCancelado);
    }
    
}
