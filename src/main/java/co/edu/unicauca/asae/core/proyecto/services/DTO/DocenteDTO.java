package co.edu.unicauca.asae.core.proyecto.services.DTO;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTO extends PersonaDTO {
    
	private String universidad;
    private String tipoDocente;
    private Float salario;
    @JsonIgnoreProperties(value="docentes")
	private List<AsignaturaDTO> asignaturas = new ArrayList<>();    
    
}
