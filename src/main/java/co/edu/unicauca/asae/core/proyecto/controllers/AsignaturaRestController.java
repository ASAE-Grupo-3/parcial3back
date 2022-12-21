package co.edu.unicauca.asae.core.proyecto.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.core.proyecto.services.DTO.AsignaturaDTO;
import co.edu.unicauca.asae.core.proyecto.services.services.clienteServices.IAsignaturaService;

@RestController
@RequestMapping("/api")
@Validated
public class AsignaturaRestController {

    @Autowired
    IAsignaturaService asignaturaService;

    @GetMapping("/asignaturas")
	public List<AsignaturaDTO> index() {
		return asignaturaService.findAll();
	}

	@GetMapping("/asignaturas/{id}")
	public AsignaturaDTO show(@Positive(message = "{consultar.recurso.identificacion}") @PathVariable Integer id) {
		AsignaturaDTO objasignatura = null;
		objasignatura = asignaturaService.findById(id);
		return objasignatura;
	}

	@PostMapping("/asignaturas")
	public AsignaturaDTO create(@Valid @RequestBody AsignaturaDTO asignaturaDTO) {
		AsignaturaDTO objAsignaturaDTO = null;
		objAsignaturaDTO = asignaturaService.save(asignaturaDTO);
		return objAsignaturaDTO;
	}
	
	@DeleteMapping("/asignaturas/{id}")
	public Boolean delete(@PathVariable Integer id) {
		return this.asignaturaService.delete(id);
	}

	@GetMapping("/asignaturas/consulta3/{nombre}")
	public List<AsignaturaDTO> consulta3(@PathVariable String nombre) {
		return asignaturaService.consulta3(nombre);
	}
}
