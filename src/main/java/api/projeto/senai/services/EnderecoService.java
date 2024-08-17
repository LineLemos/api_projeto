package api.projeto.senai.services;

import api.projeto.senai.classes.Endereco;
import api.projeto.senai.dto.EnderecoDTO;
import api.projeto.senai.repository.EnderecoRepository;
import api.projeto.senai.exception.EntityNotFoundException;
import api.projeto.senai.exception.InvalidInputException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EnderecoDTO convertToDto(Endereco endereco) {
        return modelMapper.map(endereco, EnderecoDTO.class);
    }

    public Endereco convertToEntity(EnderecoDTO enderecoDTO) {
        return modelMapper.map(enderecoDTO, Endereco.class);
    }

    public List<EnderecoDTO> getAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public EnderecoDTO getById(Long id) {
        if (id == null) {
            throw new InvalidInputException("Id não pode ser nulo ou vazio.");
        }
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + id));
        return convertToDto(endereco);
    }

    public EnderecoDTO create(EnderecoDTO enderecoDTO) {
        Endereco endereco = convertToEntity(enderecoDTO);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return convertToDto(enderecoSalvo);
    }

  public EnderecoDTO update(Long id, EnderecoDTO enderecoDTO) {
    if (id == null || enderecoDTO == null) {
        throw new InvalidInputException("Id e EnderecoDTO não podem ser nulos ou vazios.");
    }

    Endereco endereco = enderecoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + id));

    if (enderecoDTO.getCep() != null) {
        endereco.setCep(enderecoDTO.getCep());
    }
    Endereco enderecoSalvo = enderecoRepository.save(endereco);
    return convertToDto(enderecoSalvo);
}

    public void delete(Long id) {
        if (id == null) {
            throw new InvalidInputException("Id não pode ser nulo ou vazio.");
        }
        if (!enderecoRepository.existsById(id)) {
            throw new EntityNotFoundException("Endereço não encontrado com ID: " + id);
        }
        enderecoRepository.deleteById(id);
    }
}


