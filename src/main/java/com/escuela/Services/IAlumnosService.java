package com.escuela.Services;

import java.util.List;
import com.escuela.entities.Alumnos;

public interface IAlumnosService {

	public List<Alumnos> findAll();
	
	public void save(Alumnos alumnos);
	
	public Alumnos findOne(Long id);
	
	public void delete(Long id);
}
