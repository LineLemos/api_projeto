package api.projeto.senai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import api.projeto.senai.dto.TarefaDTO;
import api.projeto.senai.services.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @Operation(summary = "Criar nova Tarefa", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao criar a tarefa.")
    })
    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@Valid @RequestBody TarefaDTO tarefaDTO) {
        TarefaDTO novaTarefaDTO = tarefaService.criarTarefa(tarefaDTO);
        return ResponseEntity.status(201).body(novaTarefaDTO);
    }

    @Operation(summary = "Busca tarefa pela tag", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada com a tag fornecida."),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar a tarefa.")
    })
    @GetMapping("/{tag}")
    public ResponseEntity<TarefaDTO> buscarTarefaPorTag(@RequestBody String tag) {
        TarefaDTO tarefaDTO = tarefaService.buscarPorTag(tag);
        return ResponseEntity.ok(tarefaDTO);
    }

    @Operation(summary = "Lista todas as tarefas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao listar as tarefas.")
    })
    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        List<TarefaDTO> tarefasDTO = tarefaService.getAll();
        return ResponseEntity.ok(tarefasDTO);
    }

    @Operation(summary = "Busca tarefa pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada com o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar a tarefa.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarTarefaPorId(@PathVariable Long id) {
        TarefaDTO tarefaDTO = tarefaService.getById(id);
        return ResponseEntity.ok(tarefaDTO);
    }

    @Operation(summary = "Edita tarefa pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada com o ID fornecido."),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar a tarefa.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody TarefaDTO tarefaDTO) {
        TarefaDTO tarefaAtualizada = tarefaService.update(id, tarefaDTO);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @Operation(summary = "Deleta tarefa pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada com o ID fornecido."),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar a tarefa.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarTarefa(@PathVariable Long id) {
        TarefaDTO tarefaDTO = tarefaService.getById(id);
        if (tarefaDTO == null) {
            return ResponseEntity.notFound().build();
        }
        tarefaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa com ID " + id + " foi deletada com sucesso.");
    }
}