package api.projeto.senai.dto;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TarefaDTO {

    private Long id;

    @NotBlank(message = "Campo não pode estar em branco.")
    private String nomeTarefa;
    
    private String tag;

    private LocalDateTime dataCriacao;

    private boolean concluida;


}