package br.com.esg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorRequestDTO {
    @NotBlank(message = "O nome do sensor é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;
    
    @NotBlank(message = "A localização do sensor é obrigatória")
    @Size(min = 3, max = 200, message = "A localização deve ter entre 3 e 200 caracteres")
    private String localizacao;
} 