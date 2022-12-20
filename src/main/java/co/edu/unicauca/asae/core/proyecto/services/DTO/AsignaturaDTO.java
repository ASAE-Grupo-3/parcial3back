package co.edu.unicauca.asae.core.proyecto.services.DTO;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaDTO {

    private Integer idAsignatura;
	@NotNull(message = "{asignatura.nombre.null}")
	@NotEmpty(message = "{asignatura.nombre.empty}")
    @Size(min=5, max = 25, message = "{asignatura.nombre.size}")
    private String nombre;
	
	@Valid
	@JsonIgnoreProperties(value="asignaturas")
    private List<DocenteDTO> docentes;
	@Valid
	@JsonIgnoreProperties(value="objAsignatura")
    private List<CursoDTO> cursos;
}
