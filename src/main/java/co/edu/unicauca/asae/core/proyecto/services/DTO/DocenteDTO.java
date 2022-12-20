package co.edu.unicauca.asae.core.proyecto.services.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
	@NotNull(message = "{docente.universidad.null}")
	@NotEmpty(message = "{docente.universidad.empty}")
	private String universidad;
	@NotNull(message = "{docente.tipoDocente.null}")
	@NotEmpty(message = "{docente.tipoDocente.empty}")
    private String tipoDocente;
    @NotNull(message = "{docente.salario.null}")
    @PositiveOrZero(message = "{docente.salario.positivo}")
    private Float salario;
    @JsonIgnoreProperties(value="docentes")
	private List<AsignaturaDTO> asignaturas = new ArrayList<>();    
    
}
