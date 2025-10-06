/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.mapper;

import com.senai.academia.espinafre.dto.PagamentoRequestDTO;
import com.senai.academia.espinafre.dto.PagamentoResponseDTO;
import com.senai.academia.espinafre.entity.Pagamento;
import com.senai.academia.espinafre.enuns.StatusPagamento;
import com.senai.academia.espinafre.enuns.TipoPagamento;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cansei2
 */
@Component 
public class PagamentoMapper {
    
    public Pagamento toEntity(PagamentoRequestDTO requestDTO){
        Pagamento pagamento = new Pagamento();
        
        pagamento.setValorPago(requestDTO.getValorPago());
        try {
            
            TipoPagamento tipo = TipoPagamento.valueOf(requestDTO.getTipoPagamento().toUpperCase());
            pagamento.setTipoPagamento(tipo);
            
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo de pagamento inválido: " + requestDTO.getTipoPagamento());
        }
        
        try {
            
            StatusPagamento status = StatusPagamento.valueOf(requestDTO.getStatus().toUpperCase());
            pagamento.setStatusPagamento(status);
            
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status do pagamento inválido: " + requestDTO.getTipoPagamento());
        }
        
        return pagamento;
    }
    
    public PagamentoResponseDTO toResponseDTO(Pagamento pagamento){
        
        Long alunoId = (pagamento.getAluno() != null) ? pagamento.getAluno().getId() : null;
        
        return new PagamentoResponseDTO(
            pagamento.getId(),
            pagamento.getDataAtribuido(),
            pagamento.getValorPago(),
            pagamento.getTipoPagamento(),
            pagamento.getStatusPagamento(),
            alunoId
        );
    }
}
