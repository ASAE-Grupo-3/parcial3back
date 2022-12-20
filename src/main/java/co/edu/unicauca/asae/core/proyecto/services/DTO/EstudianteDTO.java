package co.edu.unicauca.asae.core.proyecto.services.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO extends PersonaDTO {
	@PastOrPresent(message = "{estudiante.fechaIngreso.fechaFuturo}")
	@NotNull(message = "{estudiante.fechaIngreso.null}")
    private Date fechaIngreso;
    
	@NotNull(message = "{estudiante.correo.null}")
	@NotEmpty(message = "{estudiante.correo.empty}")
	@Email(message = "{estudiante.correo.mask}")
	private String correo;
	
	@Valid
    @JsonIgnoreProperties(value="objEstudiante")
	private DireccionDTO objDireccion;
    
    @Valid
    @JsonIgnoreProperties(value="objEstudiante")
	private List<TelefonoDTO> telefonos = new ArrayList<>();
}
