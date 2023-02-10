package co.edu.unicauca.asae.core.proyecto.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.asae.core.proyecto.services.DTO.CursoDTO;
import co.edu.unicauca.asae.core.proyecto.services.services.clienteServices.ICursoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@Validated
public class CursoRestController {

    @Autowired
	private ICursoService CursoService;

	@GetMapping("/cursos")
	public List<CursoDTO> index() {
		return CursoService.findAll();
	}

	@GetMapping("/cursos/{id}")
	public CursoDTO show(@Positive(message = "{consultar.recurso.identificacion}") @PathVariable Integer id) {
		CursoDTO objCurso = null;
		objCurso = CursoService.findById(id);
		return objCurso;
	}

	@PostMapping("/cursos")
	public CursoDTO create(@Valid @RequestBody CursoDTO curso) {
		CursoDTO objCurso = null;
		objCurso = CursoService.save(curso);
		return objCurso;
	}

	@PutMapping("/cursos/{id}")
	public CursoDTO update(@RequestBody CursoDTO curso, @PathVariable Integer id) {
		CursoDTO objCurso = null;
		System.out.println("actualizando cliente");
		CursoDTO CursoActual = CursoService.findById(id);
		if (CursoActual != null) {
			objCurso = CursoService.update(id, curso);
		}
		return objCurso;
	}

	@DeleteMapping("/cursos/{id}")
	public Boolean delete(@PathVariable Integer id) {
		return this.CursoService.delete(id);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, List<String>> result = new HashMap<>();
		Map<String, String> errors = new HashMap<>();
		List<String> listErrors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();

			errors.put(fieldName,errorMessage);

		});
		for (Map.Entry<String, String> entry : errors.entrySet()) {
			String stringError = "Campo '"+entry.getKey()+" ' "+entry.getValue();
			listErrors.add(stringError);
			//System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
		}



		result.put("errors", listErrors);

		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

}
