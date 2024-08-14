package api.projeto.senai.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import api.projeto.senai.classes.Endereco;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class EnderecoController {

    private List<Endereco> enderecos = new ArrayList<>();

    @GetMapping
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    @GetMapping("/{id}")
    public Endereco getEndereco(@PathVariable Long id) {
        return enderecos.stream()
                .filter(endereco -> endereco.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Endereco createEndereco(@RequestBody Endereco endereco) {
        enderecos.add(endereco);
        return endereco;
    }

    @PutMapping("/{id}")
    public Endereco updateEndereco(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
        Endereco endereco = enderecos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (endereco != null) {
            endereco.setRua(enderecoAtualizado.getRua());
            endereco.setCidade(enderecoAtualizado.getCidade());
            endereco.setEstado(enderecoAtualizado.getEstado());
            endereco.setCep(enderecoAtualizado.getCep());
        }
        return endereco;

}
