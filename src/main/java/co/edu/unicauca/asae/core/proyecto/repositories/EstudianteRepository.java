

package co.edu.unicauca.asae.core.proyecto.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.core.proyecto.models.EstudianteEntity;

public interface EstudianteRepository extends CrudRepository<EstudianteEntity, Integer> {

	public Optional<EstudianteEntity> findBynoIdentificacion(String noIdentificacion);
}
