package api.projeto.senai.repository;
import api.projeto.senai.classes.Tarefa;
import api.projeto.senai.dto.TarefaDTO;
import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Tarefa save(@Valid TarefaDTO tarefa);

    Optional<Tarefa> findByTag(String tag);

}
