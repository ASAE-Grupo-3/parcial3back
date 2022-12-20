package co.edu.unicauca.asae.core.proyecto.services.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelefonoDTO {

    private Integer idTelefono;
    @NotNull(message = "{telefono.tipo.null}")
    private String tipo;
    @NotNull(message = "{telefono.numero.null}")
	@NotEmpty(message = "{telefono.numero.empty}")
    @Pattern(regexp = "[8][0-9]{4,19}", message = "{telefono.numero.pattern}")
    private String numero;
    @JsonIgnoreProperties(value="telefonos")
	private EstudianteDTO objEstudiante;
}
