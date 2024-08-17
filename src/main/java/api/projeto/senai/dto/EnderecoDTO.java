package api.projeto.senai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoDTO {

    @NotBlank(message = "Campo não pode estar em branco.")
    private String cep;

    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

}
