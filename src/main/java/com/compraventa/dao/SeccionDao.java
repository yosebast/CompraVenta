package com.compraventa.dao;

import java.util.List;

import com.compraventa.entidades.Seccion;

public interface SeccionDao {

	public List<Seccion> listaSeccion();
	
	public void insertaSeccionDao(Seccion seccion);

	public List<Seccion> cargaSeccionServicio();

	public Seccion cargaSeccionById(int idseccion);
}
