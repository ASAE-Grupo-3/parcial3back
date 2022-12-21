
package co.edu.unicauca.asae.core.proyecto.controllers;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.core.proyecto.services.DTO.DocenteDTO;
import co.edu.unicauca.asae.core.proyecto.services.services.clienteServices.IDocenteService;


@RestController
@RequestMapping("/api")
@Validated
public class DocenteRestController {

	@Autowired
	private IDocenteService docenteService;

	@GetMapping("/docentes")
	public ResponseEntity<List<DocenteDTO>> index() {
		return new ResponseEntity<>(docenteService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/docentes/{id}")
	public DocenteDTO show(@Positive(message = "{consultar.recurso.identificacion}") @PathVariable Integer id) {
		DocenteDTO objDocente = null;
		objDocente = docenteService.findById(id);
		return objDocente;
	}

	@PostMapping("/docentes")
	public DocenteDTO create(@Valid @RequestBody DocenteDTO cliente) {
		DocenteDTO objDocente = null;
		objDocente = docenteService.save(cliente);
		return objDocente;
	}

	@PutMapping("/docentes/{id}")
	public DocenteDTO update(@RequestBody DocenteDTO cliente, @PathVariable Integer id) {
		DocenteDTO objDocente = null;
		System.out.println("actualizando cliente");
		DocenteDTO docenteActual = docenteService.findById(id);
		if (docenteActual != null) {
			objDocente = docenteService.update(id, cliente);
		}
		return objDocente;
	}

	@DeleteMapping("/docentes/{id}")
	public Boolean delete(@PathVariable Integer id) {
		return this.docenteService.delete(id);
	}

}
