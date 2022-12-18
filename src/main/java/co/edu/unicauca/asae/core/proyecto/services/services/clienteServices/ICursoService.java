package co.edu.unicauca.asae.core.proyecto.services.services.clienteServices;

import java.util.List;

import co.edu.unicauca.asae.core.proyecto.services.DTO.CursoDTO;

public interface ICursoService {

    public List<CursoDTO> findAll();

	public CursoDTO findById(Integer id);

	public CursoDTO save(CursoDTO curso);

	public CursoDTO update(Integer id, CursoDTO curso);

	public boolean delete(Integer id);
    
}
