package api.projeto.senai.controllers;

import api.projeto.senai.dto.EnderecoDTO;
import api.projeto.senai.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Operation(summary = "Busca todos os endereços", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados.")
    })
    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarEndereco() {
        List<EnderecoDTO> enderecoDTOs = enderecoService.getAll();
        return ResponseEntity.ok(enderecoDTOs);
    }

    @Operation(summary = "Busca endereço pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> getById(@PathVariable Long id) {
        EnderecoDTO enderecoDTO = enderecoService.getById(id);
        return ResponseEntity.ok(enderecoDTO);
    }

    @Operation(summary = "Cria um novo Endereço", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao criar o endereço.")
    })
    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@Valid @RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO novoEnderecoDTO = enderecoService.create(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEnderecoDTO);
    }

    @Operation(summary = "Atualiza o Endereço pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o endereço.")
    })
    @PutMapping("/{id}")
public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @Valid @RequestBody EnderecoDTO enderecoDTO) {
    
    EnderecoDTO enderecoDTOSalvo = enderecoService.update(id, enderecoDTO); 
    
    return ResponseEntity.ok(enderecoDTOSalvo);
}

    @Operation(summary = "Deleta o Endereço pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar o endereço.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.ok("Endereço com ID " + id + " foi deletado com sucesso.");
    }
}
