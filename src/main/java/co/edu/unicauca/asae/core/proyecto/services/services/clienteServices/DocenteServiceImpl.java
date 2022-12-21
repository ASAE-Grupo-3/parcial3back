
package co.edu.unicauca.asae.core.proyecto.services.services.clienteServices;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.EntidadNoExisteException;
import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.EntidadYaExisteException;
import co.edu.unicauca.asae.core.proyecto.models.DocenteEntity;
import co.edu.unicauca.asae.core.proyecto.repositories.DocenteRepository;
import co.edu.unicauca.asae.core.proyecto.services.DTO.DocenteDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class DocenteServiceImpl implements IDocenteService {

	@Autowired
	private DocenteRepository servicioAccesoBaseDatos;

	@Autowired
	@Qualifier("mapperDocente")
	private ModelMapper modelMapper;

	@Autowired
	@Qualifier("messageResourceSB")
	MessageSource messageSource;
	
	@Override
	@Transactional(readOnly = true)
	public List<DocenteDTO> findAll() {
		Iterable<DocenteEntity> docentesEntity = this.servicioAccesoBaseDatos.findAll();
		System.out.println("antes de la consulta"+ docentesEntity);	
		List<DocenteDTO> docentesDTO = this.modelMapper.map(docentesEntity, new TypeToken<List<DocenteDTO>>() {
		}.getType());
		return docentesDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public DocenteDTO findById(Integer id) {
		Optional<DocenteEntity> optional = this.servicioAccesoBaseDatos.findById(id);
		DocenteDTO DocenteDTO = null;
		if (optional.isPresent()) {
			DocenteEntity docente = optional.get();
			System.out.println("antes de la consulta");
			DocenteDTO = this.modelMapper.map(docente, DocenteDTO.class);
		}else {
			EntidadNoExisteException objException = new EntidadNoExisteException("DOCENTE con idPersona: "
					+ id+" no existe en la Base De Datos.");
			throw objException;
		}
		return DocenteDTO;
	}
	

	@Override
	@Transactional()
	public DocenteDTO save(DocenteDTO docente) {
		
		this.validarIdPersona(docente);
		this.validarTipoIdandNoId(docente);
		
		DocenteEntity docenteEntity = this.modelMapper.map(docente, DocenteEntity.class);
		docenteEntity.setIdPersona(null);
		DocenteEntity objDocenteEntity = this.servicioAccesoBaseDatos.save(docenteEntity);
		DocenteDTO DocenteDTO = this.modelMapper.map(objDocenteEntity, DocenteDTO.class);
		return DocenteDTO;
	}
	
	private void validarIdPersona(DocenteDTO docente) {
		if (docente.getIdPersona()!=null) {
			if (this.servicioAccesoBaseDatos.existsById(docente.getIdPersona())) {
				EntidadYaExisteException objExcepcion = new EntidadYaExisteException("DOCENTE con IdPersona: "
			+docente.getIdPersona()+" existe en la Base De Datos.");
				throw objExcepcion;
			}
		}
		
	}

	private void validarTipoIdandNoId(DocenteDTO docente) {
		
		if(this.servicioAccesoBaseDatos.existeNoIdandTipoId(docente.getTipoIdentificacion(), docente.getNoIdentificacion())>=1) {
			EntidadYaExisteException objExcepcion = new EntidadYaExisteException("DOCENTE con tipoId: "+docente.getTipoIdentificacion()+
					" y número Id: "+ docente.getNoIdentificacion()+" existe en la Base De Datos.");
			throw objExcepcion;
		}
//		List<DocenteEntity> docentesEntityRequest = this.servicioAccesoBaseDatos.findBynoIdentificacion(docente.getNoIdentificacion());
//		
//		if (!docentesEntityRequest.isEmpty()) {
//			boolean isDocente = false;
//			for (DocenteEntity objDocente : docentesEntityRequest) {
//				if (objDocente.getNoIdentificacion().equals(docente.getNoIdentificacion())
//						&& objDocente.getTipoIdentificacion().equals(docente.getTipoIdentificacion())) {
//					isDocente = true;
//					break;
//				}
//			}
//			if (isDocente) {
//				EntidadYaExisteException objExcepcion = new EntidadYaExisteException("DOCENTE con tipoId: "+docente.getTipoIdentificacion()+
//						" y número Id: "+ docente.getNoIdentificacion()+" existe en la Base De Datos.");
//				throw objExcepcion;
//			}
//		}
	}
	
	@Override
	@Transactional()
	public DocenteDTO update(Integer id, DocenteDTO objDocenteConDatosNuevos) {
		Optional<DocenteEntity> optional = this.servicioAccesoBaseDatos.findById(id);
		DocenteDTO docenteDTOActualizado = null;
		DocenteEntity objDocenteAlmacenado = optional.get();

		
		if (objDocenteAlmacenado != null) {

			objDocenteAlmacenado.setIdPersona(objDocenteConDatosNuevos.getIdPersona());
			objDocenteAlmacenado.setNombres(objDocenteConDatosNuevos.getNombres());
			objDocenteAlmacenado.setApellidos(objDocenteConDatosNuevos.getApellidos());
			objDocenteAlmacenado.setTipoIdentificacion(objDocenteConDatosNuevos.getTipoIdentificacion());
			objDocenteAlmacenado.setNoIdentificacion(objDocenteConDatosNuevos.getNoIdentificacion());
			
			objDocenteAlmacenado.setUniversidad(objDocenteConDatosNuevos.getUniversidad());
			objDocenteAlmacenado.setTipoDocente(objDocenteConDatosNuevos.getTipoDocente());
			objDocenteAlmacenado.setSalario(objDocenteConDatosNuevos.getSalario());
			
			DocenteEntity DocenteEntityActualizado = this.servicioAccesoBaseDatos.save(objDocenteAlmacenado);
			docenteDTOActualizado = this.modelMapper.map(DocenteEntityActualizado, DocenteDTO.class);
			 
		}
		
		return docenteDTOActualizado;
	}
	
	@Override
	@Transactional()
	public boolean delete(Integer id) {
		boolean estado = false;
		if(this.servicioAccesoBaseDatos.existsById(id)) {
			this.servicioAccesoBaseDatos.deleteById(id);
			estado = !this.servicioAccesoBaseDatos.existsById(id);
		}
		else {
			EntidadNoExisteException objException = new EntidadNoExisteException("DOCENTE con idPersona: "
					+ id+" no existe en la Base De Datos.");
			throw objException;
		}
		return estado;
	}

}
