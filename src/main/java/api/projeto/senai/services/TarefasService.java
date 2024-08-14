package api.projeto.senai.services;
import api.projeto.senai.repository.TarefasRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import api.projeto.senai.classes.Tarefas;
import org.springframework.beans.factory.annotation.Autowired;


@Server
public class TarefasService {

@Autowired
TarefasRepository tarefasRepository;

public Tarefas create(Tarefas tarefas){

}


}
