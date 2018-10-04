package com.escuela.Services;

import java.util.List;
import com.escuela.entities.Maestros;

public interface IMaestrosService {

	public List<Maestros> findAll();
	
	public void save(Maestros maestros);
	
	public Maestros findOne(Long id);
	
	public void delete(Long id);
}
