package co.edu.unicauca.asae.core.proyecto.services.DTO;


import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class PersonaDTO {

	private Integer idPersona;
	@NotNull(message = "{persona.identificacion.null}")
	@NotEmpty(message = "{persona.identificacion.empty}")
	private String noIdentificacion;
	@NotNull(message = "{persona.tipoIdentificacion.null}")
	@NotEmpty(message = "{persona.tipoIdentificacion.empty}")
	private String tipoIdentificacion;
	@NotNull(message = "{persona.nombres.null}")
	@NotEmpty(message = "{persona.nombres.empty}")
	@Size(min=4, max = 49, message = "{persona.nombres.size}")
	private String nombres;
	@NotNull(message = "{persona.apellidos.null}")
	@NotEmpty(message = "{persona.apellidos.empty}")
	@Size(min=4, max = 49, message = "{persona.apellidos.size}")
	private String apellidos;
	
}
