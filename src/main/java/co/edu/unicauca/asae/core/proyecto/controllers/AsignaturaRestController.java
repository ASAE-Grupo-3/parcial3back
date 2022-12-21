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
	public ResponseEntity<?> show(@Positive(message = "{consultar.recurso.identificacion}") @PathVariable Integer id) {
		AsignaturaDTO objAsignatura = null;
		HashMap<String, Object> respuestas = new HashMap<>();
		ResponseEntity objRespuesta;

		try{
			objAsignatura = asignaturaService.findById(id);
			if(objAsignatura == null){
				respuestas.put("mensaje", "La asignatura con el ID "+id+" no existe en la bd");
				objRespuesta = new ResponseEntity<>(respuestas, HttpStatus.NOT_FOUND);
			}else{
				objRespuesta = new ResponseEntity<>(objAsignatura, HttpStatus.OK);
			}
		}catch(DataAccessException e){
			respuestas.put("mensaje", "Error al realizar la consulta en la base de datos");
			respuestas.put("Descripcion del error", e.getMessage());
			objRespuesta = new ResponseEntity<>(respuestas, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return objRespuesta;
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
    
}
