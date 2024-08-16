package api.projeto.senai.services;

import api.projeto.senai.classes.Endereco;
import api.projeto.senai.repository.EnderecoRepository;
import api.projeto.senai.exception.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository EnderecoRepository;

    public List<Endereco> getAll() {
        return EnderecoRepository.findAll();
    }


    public Endereco getById(Long Id) {
        if (Id == null ){
            throw new InvalidInputException("Id não pode ser nula ou vazia.");
        }
        return EnderecoRepository.findById(Id)
                                               .orElseThrow(() -> new EntityNotFoundException("Endereco não encontrado com ID: " + Id));
    }

    public Endereco create(Endereco endereco) {
        return EnderecoRepository.save(endereco);

    }

    public Endereco update(Endereco endereco, Long Id) {
        endereco.setId(Id);
        return EnderecoRepository.save(endereco);
    }

    public Endereco delete(Long Id) {
        Endereco endereco = getById(Id);
        EnderecoRepository.delete(endereco);
        return endereco;
    }

}
