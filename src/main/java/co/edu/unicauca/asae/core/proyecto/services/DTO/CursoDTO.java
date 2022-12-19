package co.edu.unicauca.asae.core.proyecto.services.DTO;

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
    private String nombre;
    private String periodo;
    @JsonIgnoreProperties(value="cursos")
    private AsignaturaDTO objAsignatura;
}
