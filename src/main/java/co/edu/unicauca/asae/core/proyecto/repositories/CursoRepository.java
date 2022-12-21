package co.edu.unicauca.asae.core.proyecto.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.core.proyecto.models.CursoEntity;

public interface CursoRepository extends CrudRepository<CursoEntity, Integer> {
    public List<CursoEntity> findByNombre(String nombre);
}
