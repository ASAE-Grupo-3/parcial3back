package co.edu.unicauca.asae.core.proyecto.services.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class EstudianteDTO extends PersonaDTO {
	@PastOrPresent(message = "{estudiante.fechaIngreso.past}")
	@NotNull(message = "{estudiante.fechaIngreso.null}")
    private Date fechaIngreso;
    
	@NotNull(message = "{estudiante.correo.null}")
	@NotEmpty(message = "{estudiante.correo.empty}")
	@Email(message = "{estudiante.correo.mask}")
	private String correo;
	
	
    @JsonIgnoreProperties(value="objEstudiante")
    @Valid
	private DireccionDTO objDireccion;
    
    
    @JsonIgnoreProperties(value="objEstudiante")
    @Valid
    private List<TelefonoDTO> telefonos = new ArrayList<>();
}
