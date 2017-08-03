package com.compraventa.service;

import java.util.List;

import com.compraventa.entidades.Seccion;

public interface SeccionManager {
	
	public List<Seccion> listaSeccion();
	
	public void insertaSeccion(Seccion seccion);

	public List<Seccion> cargaSeccionServicio();

	public Seccion cargaSeccionById(int idseccion);
	
	public void borrarSeccion(Integer idseccion);

	public void actualizaSeccion(Integer idseccion, Seccion seccion);

}
