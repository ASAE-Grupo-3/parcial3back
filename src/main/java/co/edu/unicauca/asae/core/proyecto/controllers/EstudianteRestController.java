
package co.edu.unicauca.asae.core.proyecto.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.core.proyecto.services.DTO.EstudianteDTO;
import co.edu.unicauca.asae.core.proyecto.services.services.clienteServices.IEstudianteService;


@RestController
@RequestMapping("/api")
@Validated
public class EstudianteRestController {

	@Autowired
	private IEstudianteService estudianteService;

	@GetMapping("/estudiantes")
	public List<EstudianteDTO> index() {
		return estudianteService.findAll();
	}

	@GetMapping("/estudiantes/consulta2/{id}")
	public List<EstudianteDTO> consulta2(@PathVariable Integer id) {
		return estudianteService.findByNoIdentificacionContaining(id);
	}

	@GetMapping("/estudiantes/{id}")
	public EstudianteDTO show(@Positive(message = "{consultar.recurso.identificacion}") @PathVariable Integer id) {
		EstudianteDTO objEstudiante = null;
		objEstudiante = estudianteService.findById(id);
		return objEstudiante;
	}

	@PostMapping("/estudiantes")
	public EstudianteDTO create(@Valid @RequestBody EstudianteDTO estudiante) {
		EstudianteDTO objEstudiante = null;
		objEstudiante = estudianteService.save(estudiante);
		return objEstudiante;
	}

	@PutMapping("/estudiantes/{id}")
	public EstudianteDTO update(@Valid @RequestBody EstudianteDTO estudiante, @PathVariable Integer id) {
		EstudianteDTO objEstudiante = null;
		System.out.println("actualizando cliente");
		EstudianteDTO EstudianteActual = estudianteService.findById(id);
		if (EstudianteActual != null) {
			objEstudiante = estudianteService.update(id, estudiante);
		}
		return objEstudiante;
	}

	@DeleteMapping("/estudiantes/{id}")
	public Boolean delete(@PathVariable Integer id) {
		return this.estudianteService.delete(id);

	}

}
