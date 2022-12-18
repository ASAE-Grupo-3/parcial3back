package co.edu.unicauca.asae.core.proyecto.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String tipo;
    private String numero;
    @JsonIgnoreProperties(value="telefonos")
	private EstudianteDTO objEstudiante;
}
