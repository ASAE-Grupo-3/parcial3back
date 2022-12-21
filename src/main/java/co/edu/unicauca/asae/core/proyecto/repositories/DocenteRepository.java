package co.edu.unicauca.asae.core.proyecto.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.core.proyecto.models.DocenteEntity;

public interface DocenteRepository extends CrudRepository<DocenteEntity,Integer>{
	public List<DocenteEntity> findBynoIdentificacion(String noIdentificacion);
}
