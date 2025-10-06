/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.academia.espinafre.controller;

import com.senai.academia.espinafre.dto.TreinoRequestDTO;
import com.senai.academia.espinafre.dto.TreinoResponseDTO;
import com.senai.academia.espinafre.service.TreinoService;
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
@RequestMapping("/api/v1/treinos")
public class TreinoController {
    
    @Autowired
    private TreinoService treinoService;
    
    @Operation(summary = "Cria um novo treino", description = "Endpoint para cadastrar um novo treino no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "treino criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<TreinoResponseDTO> criarTreino(@RequestBody @Valid TreinoRequestDTO requestDTO) {
        TreinoResponseDTO responseDTO = treinoService.salvar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Busca um treino por ID", description = "Retorna os detalhes de um treino específico.")
    @GetMapping("/{id}")
    public ResponseEntity<TreinoResponseDTO> buscarPorId(@PathVariable Long id) {
        TreinoResponseDTO responseDTO = treinoService.buscarPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Lista todos os treinos", description = "Retorna uma lista com o resumo dos dados de todos os treinos.")
    @GetMapping
    public ResponseEntity<List<TreinoResponseDTO>> listarTodos() {
        List<TreinoResponseDTO> listaDetreinos = treinoService.listarTodos();
        return ResponseEntity.ok(listaDetreinos);
    }

    @Operation(summary = "Atualiza um treino existente", description = "Altera os dados de um treino com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<TreinoResponseDTO> atualizarTreino(@PathVariable Long id, @RequestBody @Valid TreinoRequestDTO requestDTO) {
        TreinoResponseDTO responseDTO = treinoService.atualizar(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Deletar um treino", description = "Realiza a deleção de um treino no sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTreino(@PathVariable Long id) {
        treinoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Associar um aluno a um treino", description = "Cria a associação entre um aluno e um treino usando os IDs de cada um.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Treino ou Aluno não encontrado")
    })
    @PostMapping("/{treinoId}/alunos/{alunoId}") 
    public ResponseEntity<Void> associarAluno(@PathVariable Long treinoId, @PathVariable Long alunoId) {
        treinoService.associar(treinoId, alunoId);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Desassociar um aluno de um treino", description = "Remove a associação entre um aluno e um treino.")
    @DeleteMapping("/{treinoId}/alunos/{alunoId}")
    public ResponseEntity<Void> desassociarAluno(
            @PathVariable Long treinoId,
            @PathVariable Long alunoId) {

        treinoService.desassociar(treinoId, alunoId); // Você precisaria criar este método no service
        return ResponseEntity.noContent().build();
    }
}
