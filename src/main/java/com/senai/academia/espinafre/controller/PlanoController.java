/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.controller;

import com.senai.academia.espinafre.dto.PlanoRequestDTO;
import com.senai.academia.espinafre.dto.PlanoResponseDTO;
import com.senai.academia.espinafre.service.PlanoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/v1/planos")
public class PlanoController {
    
    @Autowired
    private PlanoService planoService;
    
    @Operation(summary = "Cria um novo plano", description = "Endpoint para cadastrar um novo plano no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "plano criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<PlanoResponseDTO> criarPlano(@RequestBody @Valid PlanoRequestDTO requestDTO) {
        PlanoResponseDTO responseDTO = planoService.salvar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Busca um plano por ID", description = "Retorna os detalhes de um plano específico.")
    @GetMapping("/{id}")
    public ResponseEntity<PlanoResponseDTO> buscarPorId(@PathVariable Long id) {
        PlanoResponseDTO responseDTO = planoService.buscarPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Lista todos os planos", description = "Retorna uma lista com o resumo dos dados de todos os planos.")
    @GetMapping
    public ResponseEntity<List<PlanoResponseDTO>> listarTodos() {
        List<PlanoResponseDTO> listaDeplanos = planoService.listarTodos();
        return ResponseEntity.ok(listaDeplanos);
    }

    @Operation(summary = "Atualiza um plano existente", description = "Altera os dados de um plano com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<PlanoResponseDTO> atualizarPlano(@PathVariable Long id, @RequestBody @Valid PlanoRequestDTO requestDTO) {
        PlanoResponseDTO responseDTO = planoService.atualizar(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Deletar um plano", description = "Realiza a deleção de um plano no sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
        planoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
}
