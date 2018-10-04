package com.escuela.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escuela.Dao.IAlumnosDao;
import com.escuela.entities.Alumnos;



@Service
public class AlumnosService implements IAlumnosService {

	@Autowired
	private IAlumnosDao alumnosDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Alumnos> findAll() {		
		return (List<Alumnos>) alumnosDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public 	Alumnos findOne(Long id_alumnos) {		
		return alumnosDao.findById(id_alumnos).orElse(null);
	}

	@Override
	@Transactional
	public void save(Alumnos alumnos) {
		alumnosDao.save(alumnos);
	}

	@Override
	@Transactional
	public void delete(Long id_alumnos) {
		alumnosDao.deleteById(id_alumnos);
	}
}
