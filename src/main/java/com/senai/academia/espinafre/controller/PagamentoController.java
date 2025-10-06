/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.controller;

import com.senai.academia.espinafre.dto.PagamentoRequestDTO;
import com.senai.academia.espinafre.dto.PagamentoResponseDTO;
import com.senai.academia.espinafre.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cansei2
 */
@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {
    
    @Autowired
    private PagamentoService pagamentoService;
    
    @Operation(summary = "Cria um novo pagamento", description = "Endpoint para cadastrar um novo pagamento no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "pagamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> criarPagamento(@RequestBody @Valid PagamentoRequestDTO requestDTO) {
        PagamentoResponseDTO responseDTO = pagamentoService.salvar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Busca um pagamento por ID", description = "Retorna os detalhes de um pagamento específico.")
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        PagamentoResponseDTO responseDTO = pagamentoService.buscarPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Lista todos os pagamentos", description = "Retorna uma lista com o resumo dos dados de todos os pagamentos.")
    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listarTodos() {
        List<PagamentoResponseDTO> listaDepagamentos = pagamentoService.listarTodos();
        return ResponseEntity.ok(listaDepagamentos);
    }

    @Operation(summary = "Confirma o recebimento de um pagamento", description = "Altera o status de um pagamento para PAGO e registra a data de pagamento.")
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<PagamentoResponseDTO> confirmarPagamento(@PathVariable Long id) {
        PagamentoResponseDTO responseDTO = pagamentoService.confirmarPagamento(id);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Cancela um pagamento", description = "Altera o status de um pagamento para CANCELADO.")
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<PagamentoResponseDTO> cancelarPagamento(@PathVariable Long id) {
        PagamentoResponseDTO responseDTO = pagamentoService.cancelarPagamento(id);
        return ResponseEntity.ok(responseDTO);
    }
}
