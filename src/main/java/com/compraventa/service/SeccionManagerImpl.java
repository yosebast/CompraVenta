package com.compraventa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compraventa.dao.SeccionDao;
import com.compraventa.entidades.Seccion;


@Service
public class SeccionManagerImpl implements SeccionManager {

	@Autowired	
	SeccionDao secDao;
	
	public List<Seccion> listaSeccion() {
		// TODO Auto-generated method stub
		return secDao.listaSeccion();
	}

	public void insertaSeccion(Seccion seccion) {
		// TODO Auto-generated method stub
		
		secDao.insertaSeccionDao(seccion);
		
	}

	public List<Seccion> cargaSeccionServicio() {
		// TODO Auto-generated method stub
		
		List<Seccion> listaSeccion = secDao.cargaSeccionServicio();
		return listaSeccion;
	}

	public Seccion cargaSeccionById(int idseccion) {
		// TODO Auto-generated method stub
		
		Seccion sec = secDao.cargaSeccionById(idseccion);
		return sec;
	}
	
	

}
