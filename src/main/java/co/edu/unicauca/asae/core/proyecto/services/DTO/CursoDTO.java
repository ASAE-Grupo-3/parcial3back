package co.edu.unicauca.asae.core.proyecto.services.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class CursoDTO { 
	private Integer idCurso;
	@NotNull(message = "{curso.nombre.null}")
	@NotEmpty(message = "{curso.nombre.empty}")
    @Size(min=5, max = 25, message = "{curso.nombre.size}")
    private String nombre;
	@Min(1)
	@Max(2)
	private Integer periodo;
    @JsonIgnoreProperties(value="cursos")
    public AsignaturaDTO objAsignatura;
}
