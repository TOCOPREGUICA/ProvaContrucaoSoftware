/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.controller;

import com.senai.academia.espinafre.dto.AlunoRequestDTO;
import com.senai.academia.espinafre.dto.AlunoResponseDTO;
import com.senai.academia.espinafre.service.AlunoService;
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
@RequestMapping("/api/v1/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;
    
    @Operation(summary = "Cria um novo aluno", description = "Endpoint para cadastrar um novo aluno no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criarAluno(@RequestBody @Valid AlunoRequestDTO requestDTO) {
        AlunoResponseDTO responseDTO = alunoService.salvar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Busca um aluno por ID", description = "Retorna os detalhes de um aluno específico.")
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable Long id) {
        AlunoResponseDTO responseDTO = alunoService.buscarPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Lista todos os alunos", description = "Retorna uma lista com o resumo dos dados de todos os alunos.")
    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarTodos() {
        List<AlunoResponseDTO> listaDeAlunos = alunoService.listarTodos();
        return ResponseEntity.ok(listaDeAlunos);
    }

    @Operation(summary = "Atualiza um aluno existente", description = "Altera os dados de um aluno com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoRequestDTO requestDTO) {
        AlunoResponseDTO responseDTO = alunoService.atualizar(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Inativa um aluno", description = "Realiza a inativação de um aluno no sistema, sem excluí-lo fisicamente.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarAluno(@PathVariable Long id) {
        alunoService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
