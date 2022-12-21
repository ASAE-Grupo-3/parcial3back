
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
	public ResponseEntity<?> show(@Positive(message = "{consultar.recurso.identificacion}") @PathVariable Integer id) {
		DocenteDTO objDocente = null;
		HashMap<String, Object> respuestas = new HashMap<>();
		ResponseEntity objRespuesta;

		try{
			objDocente = docenteService.findById(id);
			if(objDocente == null){
				respuestas.put("mensaje", "El docente con el ID "+id+" no existe en la bd");
				objRespuesta = new ResponseEntity<>(respuestas, HttpStatus.NOT_FOUND);
			}else{
				objRespuesta = new ResponseEntity<>(objDocente, HttpStatus.OK);
			}
		}catch(DataAccessException e){
			respuestas.put("mensaje", "Error al realizar la consulta en la base de datos");
			respuestas.put("Descripcion del error", e.getMessage());
			objRespuesta = new ResponseEntity<>(respuestas, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return objRespuesta;
	}

	@PostMapping("/docentes")
	public ResponseEntity<?> create(@Valid @RequestBody DocenteDTO cliente) {
		DocenteDTO objDocente = null;
		HashMap<String, Object> respuestas = new HashMap<>();
		ResponseEntity objRespuesta;
		try{
			objDocente = docenteService.save(cliente);
			objRespuesta = new ResponseEntity<DocenteDTO>(objDocente, HttpStatus.CREATED);
		}catch(DataAccessException e){
			respuestas.put("mensaje", "Error al realizar la insercion del recurso");
			respuestas.put("Descripcion del error", e.getMessage());
			objRespuesta = new ResponseEntity<>(respuestas, HttpStatus.BAD_REQUEST);
		}
		
		return objRespuesta;
	}

	@PutMapping("/docentes/{id}")
	public ResponseEntity<DocenteDTO> update(@RequestBody DocenteDTO cliente, @PathVariable Integer id) {
		DocenteDTO objDocente = null;
		ResponseEntity objRespuesta;
		System.out.println("actualizando cliente");
		DocenteDTO docenteActual = docenteService.findById(id);
		if (docenteActual != null) {
			objDocente = docenteService.update(id, cliente);
		}
		objRespuesta = new ResponseEntity<DocenteDTO>(objDocente, HttpStatus.OK);
		return objRespuesta;
	}

	@DeleteMapping("/docentes/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		DocenteDTO objDocente = null;
		HashMap<String, Object> respuestas = new HashMap<>();
		ResponseEntity objRespuesta;

		try{
			objDocente = docenteService.findById(id);
			
			if(objDocente == null){
				respuestas.put("mensaje", "El doncente con id "+id+" que desea eliminar no esta registrado");
				objRespuesta = new ResponseEntity<>(respuestas, HttpStatus.NOT_FOUND);
			}else{
				docenteService.delete(id);
				objRespuesta = new ResponseEntity<>(true, HttpStatus.OK);
			}
		}catch(DataAccessException e){
			respuestas.put("mensaje", "Error al realizar la eliminacion del recurso");
			respuestas.put("Descripcion del error", e.getMessage());
			objRespuesta = new ResponseEntity<>(respuestas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return objRespuesta;
	}

}
