package co.edu.unicauca.asae.core.proyecto.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.core.proyecto.models.AsignaturaEntity;

public interface AsignaturaRepository extends CrudRepository<AsignaturaEntity, Integer>{
	public List<AsignaturaEntity> findByNombre(String nombre);
	public List<AsignaturaEntity> findByNombreIgnoreCaseContainingOrderByNombreAsc(String nombre);
}
