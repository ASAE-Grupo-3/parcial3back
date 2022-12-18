
package co.edu.unicauca.asae.core.proyecto.services.services.clienteServices;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.asae.core.proyecto.models.EstudianteEntity;
import co.edu.unicauca.asae.core.proyecto.repositories.EstudianteRepository;
import co.edu.unicauca.asae.core.proyecto.services.DTO.EstudianteDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private EstudianteRepository servicioAccesoBaseDatos;

	@Autowired
	@Qualifier("mapperEstudiante")
	private ModelMapper modelMapper;
	
	@Autowired
	@Qualifier("messageResourceSB")
	MessageSource messageSource;

	@Override
	@Transactional(readOnly = true)
	public List<EstudianteDTO> findAll() {
		Iterable<EstudianteEntity> estudiantesEntity = this.servicioAccesoBaseDatos.findAll();
		System.out.println("antes de la consulta");	
		List<EstudianteDTO> estudiantesDTO = this.modelMapper.map(estudiantesEntity, new TypeToken<List<EstudianteDTO>>() {}.getType());
		return estudiantesDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public EstudianteDTO findById(Integer id) {
		System.out.println("antes de la consulta");
		Optional<EstudianteEntity> optional = this.servicioAccesoBaseDatos.findById(id);
		
		EstudianteDTO estudianteDTO = null;
		if (optional.isPresent()) {
			
			EstudianteEntity user = optional.get();
			
			estudianteDTO = this.modelMapper.map(user, EstudianteDTO.class);
		}
		
		return estudianteDTO;
	}
	

	@Override
	@Transactional()
	public EstudianteDTO save(EstudianteDTO estudiante) {

		EstudianteDTO estudianteDTO = null;
		if (!this.servicioAccesoBaseDatos.findBynoIdentificacion(estudiante.getNoIdentificacion()).isPresent()) {
			EstudianteEntity EstudianteEntity = this.modelMapper.map(estudiante, EstudianteEntity.class);
			EstudianteEntity.getObjDireccion().setObjEstudiante(EstudianteEntity);

			EstudianteEntity.getTelefonos().forEach(objTelefono -> objTelefono.setObjEstudiante(EstudianteEntity));

			EstudianteEntity objEstudianteEntity = this.servicioAccesoBaseDatos.save(EstudianteEntity);
			estudianteDTO = this.modelMapper.map(objEstudianteEntity, EstudianteDTO.class);
		}
		
		return estudianteDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public EstudianteDTO update(Integer id, EstudianteDTO objEstudianteConDatosNuevos) {
		EstudianteEntity EstudianteEntity = this.modelMapper.map(objEstudianteConDatosNuevos, EstudianteEntity.class);
//		EstudianteEntity.getObjDireccion().setObjEstudiante(EstudianteEntity);

		EstudianteEntity.getTelefonos().forEach(objTelefono -> objTelefono.setObjEstudiante(EstudianteEntity));
		
		Optional<EstudianteEntity> optional = this.servicioAccesoBaseDatos.findById(id);
		EstudianteDTO EstudianteDTOActualizado = null;
		EstudianteEntity objEstudianteAlmacenado = optional.get();

		if (objEstudianteAlmacenado != null) {

			objEstudianteAlmacenado = EstudianteEntity;
			EstudianteEntity EstudianteEntityActualizado = this.servicioAccesoBaseDatos.save(objEstudianteAlmacenado);
			EstudianteDTOActualizado = this.modelMapper.map(EstudianteEntityActualizado, EstudianteDTO.class);
			 
		}
		return EstudianteDTOActualizado;
	}
	
	@Override
	@Transactional(readOnly = false)
	public boolean delete(Integer id) {
		boolean bandera = false;
		
		if (this.servicioAccesoBaseDatos.existsById(id)) {
			this.servicioAccesoBaseDatos.deleteById(id);
			bandera = !this.servicioAccesoBaseDatos.existsById(id);
		}

		return bandera;
	}

}
