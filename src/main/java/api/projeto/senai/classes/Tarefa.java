package api.projeto.senai.classes;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tarefa")

public class Tarefa {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @NotBlank
    private String nomeTarefa;

    @Column
    private String tag;

    @Column
    private String descricao;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;

    @Column(name = "prazo")
    private LocalDate prazo;

    @Column(name = "tarefa_concluida")
    private boolean concluida = false;

    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    

}


