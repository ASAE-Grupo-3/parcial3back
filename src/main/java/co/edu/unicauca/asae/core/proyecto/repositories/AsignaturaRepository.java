package co.edu.unicauca.asae.core.proyecto.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.core.proyecto.models.AsignaturaEntity;
import co.edu.unicauca.asae.core.proyecto.models.DocenteEntity;

public interface AsignaturaRepository extends CrudRepository<AsignaturaEntity, Integer>{
	public List<AsignaturaEntity> findBynombre(String nombre);
}
