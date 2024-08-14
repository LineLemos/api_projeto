package api.projeto.senai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.projeto.senai.classes.Tarefas;
import api.projeto.senai.services.TarefasService;

@RestController
@RequestMapping("tarefas")
public class TarefasController {

    @Autowired
    private TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<?> criarTarefas(@RequestBody Tarefas tarefas) {
        try {
            Tarefas novaTarefa = tarefasService.create(tarefas);
            return ResponseEntity.ok(novaTarefa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Tarefas>> listarTarefas() {
        List<Tarefas> tarefas = tarefasService.getAll();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefas> buscarTarefasPorId(@PathVariable Long id) {
        Tarefas tarefas = tarefasService.getById(id);
        if (tarefas == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(tarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefas> update(@PathVariable Long id, @RequestBody Tarefas tarefasnovas) {
        Tarefas novasTarefa = tarefasService.getById(id);
        if (novasTarefa == null) {
            return ResponseEntity.notFound().build();
        }

        Tarefas tarefasSalvas = tarefasService.update(tarefasnovas,id, novasTarefa);

        return ResponseEntity.ok(tarefasSalvas);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        Tarefas tarefas = tarefasService.getById(id);
        if (tarefas == null) {
            return ResponseEntity.notFound().build();

        }
        tarefasService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
