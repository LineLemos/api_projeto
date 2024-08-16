package api.projeto.senai.controllers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import api.projeto.senai.classes.Endereco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("endereco")
public class EnderecoController {

    private List<Endereco> enderecos = new ArrayList<>();


    @Operation(summary = "Busca endereços", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados."),
    })

    @GetMapping
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    @Operation(summary = "Busca todos os endereços", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados."),
    })

    @GetMapping("/{id}")
    public Endereco getEndereco(@PathVariable Long id) {
        return enderecos.stream()
                .filter(endereco -> endereco.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Operation(summary = "Criar novo Endereço", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados."),
    })

    @PostMapping
    public Endereco createEndereco(@RequestBody Endereco endereco) {
        enderecos.add(endereco);
        return endereco;
    }

    @Operation(summary = "Edita o Endereço pela ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço editado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados."),
    })


    @PutMapping("/{id}")
    public Endereco updateEndereco(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
        Endereco endereco = enderecos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (endereco != null) {
            endereco.setCep(enderecoAtualizado.getCep());
            endereco.setUf(enderecoAtualizado.getUf());
        }
        return endereco;
    }

    @Operation(summary = "Deleta o Endereço pela ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Dados de requisição inválida."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados."),
    })

    @DeleteMapping("/{id}")
    public void deleteEndereco(@PathVariable Long id) {
        enderecos.removeIf(endereco -> endereco.getId().equals(id));
    }
}
