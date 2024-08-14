package api.projeto.senai.classes;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tarefas")

public class Tarefas {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeTarefa;

    @Column(nullable = false)
    private String tag;

    @Column
    private String descricao;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;

    @Column(name = "prazo")
    private LocalDate prazo;

    @Column
    private boolean concluida;

    

}


