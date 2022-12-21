

package co.edu.unicauca.asae.core.proyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.unicauca.asae.core.proyecto.models.EstudianteEntity;

public interface EstudianteRepository extends CrudRepository<EstudianteEntity, Integer> {

	public List<EstudianteEntity> findBynoIdentificacion(String noIdentificacion);
	public List<EstudianteEntity> findByApellidosIgnoreCase(String apellidos);
	public List<EstudianteEntity> findByNombresIgnoreCase(String nombre);
	public List<EstudianteEntity> findByCorreoIgnoreCase(String correo);
	public List<EstudianteEntity> findBynoIdentificacionContaining(Integer id);
	
	@Query(value = "SELECT count(*) FROM `estudiante` e WHERE e.correo =:correo", nativeQuery = true)
	public int findByCorreo(@Param("correo") String correo);
	
//	@Modifying
	@Query(value = "SELECT count(*) FROM `estudiante` e WHERE e.tipoIdentificacion =:tipoId and e.noIdentificacion =:noId", nativeQuery = true)
	public int existeNoIdandTipoId(@Param("tipoId") String tipoIdentificacion, @Param("noId") String noIdentificacion);

	
}
