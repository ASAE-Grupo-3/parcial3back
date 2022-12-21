package co.edu.unicauca.asae.core.proyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.unicauca.asae.core.proyecto.models.DocenteEntity;

public interface DocenteRepository extends CrudRepository<DocenteEntity,Integer>{
	public List<DocenteEntity> findBynoIdentificacion(String noIdentificacion);
	
	@Query(value = "SELECT count(*) FROM `docente` e WHERE e.tipoIdentificacion =:tipoId and e.noIdentificacion =:noId", nativeQuery = true)
	public int existeNoIdandTipoId(@Param("tipoId") String tipoIdentificacion, @Param("noId") String noIdentificacion);
}
