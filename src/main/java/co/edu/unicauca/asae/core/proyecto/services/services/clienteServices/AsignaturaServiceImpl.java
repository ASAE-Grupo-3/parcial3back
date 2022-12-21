package co.edu.unicauca.asae.core.proyecto.services.services.clienteServices;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.EntidadNoExisteException;
import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.EntidadYaExisteException;
import co.edu.unicauca.asae.core.proyecto.exceptionControllers.exceptions.ReglaNegocioExcepcion;
import co.edu.unicauca.asae.core.proyecto.models.AsignaturaEntity;
import co.edu.unicauca.asae.core.proyecto.repositories.AsignaturaRepository;
import co.edu.unicauca.asae.core.proyecto.services.DTO.AsignaturaDTO;

@Service
public class AsignaturaServiceImpl implements IAsignaturaService {

	@Autowired
	private AsignaturaRepository servicioAccesoBaseDatos;

	@Autowired
	@Qualifier("mapperAsignatura")
	ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public List<AsignaturaDTO> findAll() {
		Iterable<AsignaturaEntity> asignaturasEntity = this.servicioAccesoBaseDatos.findAll();
		System.out.println("antes de la consulta" + asignaturasEntity);
		List<AsignaturaDTO> asignaturaDTO = this.modelMapper.map(asignaturasEntity,
				new TypeToken<List<AsignaturaDTO>>() {
				}.getType());
		return asignaturaDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public AsignaturaDTO findById(Integer id) {
		Optional<AsignaturaEntity> optional = this.servicioAccesoBaseDatos.findById(id);
		AsignaturaDTO asignaturaDTO = null;
		if (optional.isPresent()) {
			AsignaturaEntity user = optional.get();
			System.out.println("antes de la consulta");
			asignaturaDTO = this.modelMapper.map(user, AsignaturaDTO.class);
		}else {
			EntidadNoExisteException objException = new EntidadNoExisteException("Asignatura con idAsignatura: "
					+ id+" no existe en la Base De Datos.");
			throw objException;
		}
		
		
		return asignaturaDTO;
	}

	@Override
	@Transactional
	public AsignaturaDTO save(AsignaturaDTO asignaturaDTO) {
		AsignaturaDTO objasignaturaDTO = null;
		this.validarIdAsignatura(asignaturaDTO);
		this.validarNombreAsignatura(asignaturaDTO);
		if (asignaturaDTO.getCursos().size() > 0 && asignaturaDTO.getDocentes().size() >= 1) {

//        	AsignaturaEntity asignaturaAux = new AsignaturaEntity();
//        	asignaturaAux.setNombre(asignaturaDTO.getNombre());
//        	AsignaturaEntity asignaturaSave = servicioAccesoBaseDatos.save(asignaturaAux);
//        	
//        	asignaturaDTO.setIdAsignatura(asignaturaSave.getIdAsignatura());
			AsignaturaEntity asignaturaEntity = this.modelMapper.map(asignaturaDTO, AsignaturaEntity.class);
			asignaturaEntity.getCursos().forEach(objCurso -> objCurso.setObjAsignatura(asignaturaEntity));
			
//			asignaturaEntity.getDocentes().forEach(objDocente -> objDocente.getAsignaturas().add(asignaturaEntity));
//			asignaturaEntity.getDocentes().forEach(objDocente -> objDocente.addAsignatura(asignaturaEntity));
			
			AsignaturaEntity objAsignaturaEntity = servicioAccesoBaseDatos.save(asignaturaEntity);
			objasignaturaDTO = this.modelMapper.map(objAsignaturaEntity, AsignaturaDTO.class);
		}else {
			ReglaNegocioExcepcion objExcepcion = new ReglaNegocioExcepcion("La asignatura con Nombre: '"+
					asignaturaDTO.getNombre()+"' debe asociar a un curso y minimo a un docente");
			throw objExcepcion;
		}
		return objasignaturaDTO;
	}
	
	private void validarNombreAsignatura(AsignaturaDTO asignatura) {
		List<AsignaturaEntity> asignaturasEntityRequest = this.servicioAccesoBaseDatos.findByNombre(asignatura.getNombre());
		
		if (!asignaturasEntityRequest.isEmpty()) {
			boolean isAsignatura = false;
			for (AsignaturaEntity objAsignatura : asignaturasEntityRequest) {
				if (objAsignatura.getNombre().equals(asignatura.getNombre())) {
					isAsignatura = true;
					break;
				}
			}
			if (isAsignatura) {
				EntidadYaExisteException objExcepcion = new EntidadYaExisteException("ASIGNATURA con nombre: "+asignatura.getNombre()+
						" existe en la Base De Datos.");
				throw objExcepcion;
			}
		}
	}
	
	private void validarIdAsignatura(AsignaturaDTO asignatura) {
		if (asignatura.getIdAsignatura() != null) {
			if (this.servicioAccesoBaseDatos.existsById(asignatura.getIdAsignatura())) {
				EntidadYaExisteException objExcepcion = new EntidadYaExisteException("ASIGNATURA con IdAsignatura: "
			+asignatura.getIdAsignatura()+" existe en la Base De Datos.");
				throw objExcepcion;
			}
		}
		
	}

	@Override
	public AsignaturaDTO update(Integer id, AsignaturaDTO asignaturaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		boolean estado = false;
		if(this.servicioAccesoBaseDatos.existsById(id)) {
			this.servicioAccesoBaseDatos.deleteById(id);
			estado = !this.servicioAccesoBaseDatos.existsById(id);
		}
		else {
			EntidadNoExisteException objException = new EntidadNoExisteException("Asignatura con idAsignatura: "
					+ id+" no existe en la Base De Datos.");
			throw objException;
		}
		return estado;
	}

}
