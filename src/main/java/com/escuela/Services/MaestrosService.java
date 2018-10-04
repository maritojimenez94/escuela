package com.escuela.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escuela.Dao.IMaestrosDao;
import com.escuela.entities.Maestros;





@Service
public class MaestrosService implements IMaestrosService {

	@Autowired
	private IMaestrosDao maestrosDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Maestros> findAll() {		
		return (List<Maestros>) maestrosDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public 	Maestros findOne(Long id_maestros) {		
		return maestrosDao.findById(id_maestros).orElse(null);
	}

	@Override
	@Transactional
	public void save(Maestros maestros) {
		maestrosDao.save(maestros);
	}

	@Override
	@Transactional
	public void delete(Long id_maestros) {
		maestrosDao.deleteById(id_maestros);
	}
}
