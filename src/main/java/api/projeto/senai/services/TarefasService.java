package api.projeto.senai.services;

import api.projeto.senai.repository.TarefasRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import api.projeto.senai.classes.Tarefas;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Server
public class TarefasService {

    @Autowired
    private TarefasRepository tarefasRepository;

    public Tarefas create(Tarefas tarefas) {
        Tarefas novasTarefas = tarefasRepository.save(tarefas);
        return novasTarefas;
    }

    public List<Tarefas> getAll() {
        return tarefasRepository.findAll();
    }

    public Tarefas getById(Long id) {
        return tarefasRepository.findById(id).orElse(null);
    }

    public Tarefas update(Tarefas tarefas, Long id, Tarefas novasTarefa) {
        if (novasTarefa.getNomeTarefa() != null) {
            tarefas.setNomeTarefa(novasTarefa.getNomeTarefa());
        }
        if (novasTarefa.getPrazo() != null) {
            tarefas.setPrazo(novasTarefa.getPrazo());
        }
        if (novasTarefa.getTag() != null) {
            tarefas.setTag(novasTarefa.getTag());
        }
        if (novasTarefa.getDescricao() != null) {
            tarefas.setDescricao(novasTarefa.getDescricao());

        }
        if (novasTarefa.getDataAtualizacao() != null) {
            tarefas.setDataAtualizacao(novasTarefa.getDataAtualizacao());
        }
        return tarefasRepository.save(novasTarefa);
    }

    public Tarefas delete(Long id){
      tarefasRepository.deleteById(id);
      Tarefas tarefas = getById(id);
      return tarefasRepository.save(tarefas);
    }
}