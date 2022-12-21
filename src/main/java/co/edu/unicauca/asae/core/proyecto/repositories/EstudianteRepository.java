

package co.edu.unicauca.asae.core.proyecto.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import co.edu.unicauca.asae.core.proyecto.models.EstudianteEntity;

public interface EstudianteRepository extends CrudRepository<EstudianteEntity, Integer> {

	public List<EstudianteEntity> findBynoIdentificacion(String noIdentificacion);
	public List<EstudianteEntity> findByCorreo(String correo);

	public List<EstudianteEntity> findBynoIdentificacionContaining(Integer id);

}
