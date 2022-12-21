
package co.edu.unicauca.asae.core.proyecto.services.services.clienteServices;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.EntidadNoExisteException;
import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.EntidadYaExisteException;
import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.ErrorAlmacenamientoBDException;
import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.ReglaNegocioExcepcion;
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
		}else {
			EntidadNoExisteException objException = new EntidadNoExisteException("Estudiante con idPersona: "
					+ id+" no existe en la Base De Datos.");
			throw objException;
		}
		
		return estudianteDTO;
	}
	
	@Override
	public List<EstudianteDTO> findByNoIdentificacionContaining(Integer id){
		List<EstudianteEntity> listaEntity = servicioAccesoBaseDatos.findBynoIdentificacionContaining(id);

		List<EstudianteDTO> listaDTO = this.modelMapper.map(listaEntity, new TypeToken<List<EstudianteDTO>>() {}.getType());
		return listaDTO;
	}

	@Override
	@Transactional()
	public EstudianteDTO save(EstudianteDTO estudiante) {
		this.validarIdPersona(estudiante);
		validarTipoIdandNoId(estudiante);

		if (!this.servicioAccesoBaseDatos.findByCorreo(estudiante.getCorreo()).isEmpty()) {
			ErrorAlmacenamientoBDException objExcepcion = new ErrorAlmacenamientoBDException("ESTUDIANTE con Correo: "
				+estudiante.getCorreo() +" existe en la Base De Datos.");
			throw objExcepcion;
		}
		
		EstudianteDTO estudianteDTO = null;
		EstudianteEntity EstudianteEntity = this.modelMapper.map(estudiante, EstudianteEntity.class);
		if (EstudianteEntity.getObjDireccion() == null) {
			ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion("ESTUDIANTE debe tener al menos una direccion");
				throw objExcepcion;
		}
		
		EstudianteEntity.getObjDireccion().setObjEstudiante(EstudianteEntity);

		if(EstudianteEntity.getTelefonos().size() < 2){
			ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion("ESTUDIANTE debe tener al menos dos telefonos");
				throw objExcepcion;
		};

		EstudianteEntity.getTelefonos().forEach(objTelefono -> objTelefono.setObjEstudiante(EstudianteEntity));

		EstudianteEntity objEstudianteEntity = this.servicioAccesoBaseDatos.save(EstudianteEntity);
		estudianteDTO = this.modelMapper.map(objEstudianteEntity, EstudianteDTO.class);
		
		return estudianteDTO;
	}
	
	private void validarIdPersona(EstudianteDTO estudiante) {
		if (estudiante.getIdPersona()!=null) {
			if (this.servicioAccesoBaseDatos.existsById(estudiante.getIdPersona())) {
				EntidadYaExisteException objExcepcion = new EntidadYaExisteException("ESTUDIANTE con IdPersona: "
			+estudiante.getIdPersona()+" existe en la Base De Datos.");
				throw objExcepcion;
			}
		}
		
	}
	
	private void validarTipoIdandNoId(EstudianteDTO estudiante) {
		List<EstudianteEntity> docentesEntityRequest = this.servicioAccesoBaseDatos.findBynoIdentificacion(estudiante.getNoIdentificacion());
		
		if(this.servicioAccesoBaseDatos.existeNoIdandTipoId(estudiante.getTipoIdentificacion(), estudiante.getNoIdentificacion())>=1) {
			EntidadYaExisteException objExcepcion = new EntidadYaExisteException("ESTUDIANTE con tipoId: "+estudiante.getTipoIdentificacion()+
					" y número Id: "+ estudiante.getNoIdentificacion()+" existe en la Base De Datos.");
			throw objExcepcion;
		}
		
//		if (!docentesEntityRequest.isEmpty()) {
//			boolean isEstudiante = false;
////			for (EstudianteEntity objEstudiante : docentesEntityRequest) {
////				
////				
//				
////				if (objEstudiante.getNoIdentificacion().equals(estudiante.getNoIdentificacion())
////						&& objEstudiante.getTipoIdentificacion().equals(estudiante.getTipoIdentificacion())) {
////					isEstudiante = true;
////					break;
////				}
////			}
//			
//			if (isEstudiante) {
//				EntidadYaExisteException objExcepcion = new EntidadYaExisteException("ESTUDIANTE con tipoId: "+estudiante.getTipoIdentificacion()+
//						" y número Id: "+ estudiante.getNoIdentificacion()+" existe en la Base De Datos.");
//				throw objExcepcion;
//			}
//		}
	}

	@Override
	@Transactional(readOnly = false)
	public EstudianteDTO update(Integer id, EstudianteDTO objEstudianteConDatosNuevos) {
		EstudianteEntity EstudianteEntity = this.modelMapper.map(objEstudianteConDatosNuevos, EstudianteEntity.class);
		if (EstudianteEntity.getObjDireccion() == null) {
			ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion("ESTUDIANTE debe tener al menos una direccion");
				throw objExcepcion;
		}
		
		EstudianteEntity.getObjDireccion().setObjEstudiante(EstudianteEntity);

		if(EstudianteEntity.getTelefonos().size() < 2){
			ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion("ESTUDIANTE debe tener al menos dos telefonos");
				throw objExcepcion;
		};
		
		EstudianteEntity.getObjDireccion().setObjEstudiante(EstudianteEntity);

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
		else {
			EntidadNoExisteException objException = new EntidadNoExisteException("Estudiante con idPersona: "
					+ id+" no existe en la Base De Datos.");
			throw objException;
		}

		return bandera;
	}

}
