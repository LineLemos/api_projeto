package api.projeto.senai.services;

import api.projeto.senai.repository.TarefaRepository;
import api.projeto.senai.classes.Tarefa;
import api.projeto.senai.dto.TarefaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.projeto.senai.exception.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TarefaDTO convertToDto(Tarefa tarefa) {
        return modelMapper.map(tarefa, TarefaDTO.class);
    }

    public Tarefa convertToEntity(TarefaDTO tarefaDTO) {
        return modelMapper.map(tarefaDTO, Tarefa.class);
    }

    public TarefaDTO criarTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = convertToEntity(tarefaDTO);
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);

        return convertToDto(tarefaSalva);
    }

    public List<TarefaDTO> getAll() {
        List<Tarefa> tarefa = tarefaRepository.findAll();
        return tarefa.stream().map(this::convertToDto).toList();
    }

    public List<TarefaDTO> buscarPorTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            throw new InvalidInputException("Tag não pode ser nula ou vazia.");
        }

        List<Tarefa> tarefas = tarefaRepository.findByTag(tag);
        List<TarefaDTO> tarefaDTOs = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            TarefaDTO dto = convertToDto(tarefa);
            tarefaDTOs.add(dto);
        }
        return tarefaDTOs;
    }

    public TarefaDTO getById(Long id) {
        if (id == null) {
            throw new InvalidInputException("Id não pode ser nulo ou vazio.");
        }
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com ID: " + id));
        return convertToDto(tarefa);
    }

    public TarefaDTO update(Long id, TarefaDTO tarefaDTO) {
        if (id == null) {
            throw new InvalidInputException("Id não pode ser nulo ou vazio.");
        }
        Tarefa tarefaExistente = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com ID: " + id));

        modelMapper.map(tarefaDTO, tarefaExistente);
        Tarefa tarefaAtualizada = tarefaRepository.save(tarefaExistente);

        return convertToDto(tarefaAtualizada);
    }

    public void delete(Long id) {

        if (id == null) {
            throw new InvalidInputException("Id não pode ser nulo ou vazio.");
        }
        tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com ID: " + id));
        tarefaRepository.deleteById(id);
    }

    public Tarefa update(Tarefa tarefanovas, Long id, TarefaDTO novasTarefa) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}