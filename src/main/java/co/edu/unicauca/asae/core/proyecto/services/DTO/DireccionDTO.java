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
public class DireccionDTO {

	private Integer idEstudiante;
	private String calle;
	private String localidad;
	@JsonIgnoreProperties(value="objDireccion")
	private EstudianteDTO objEstudiante;

}
