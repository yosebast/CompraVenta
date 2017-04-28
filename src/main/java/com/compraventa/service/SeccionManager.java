package com.compraventa.service;

import java.util.List;

import com.compraventa.entidades.Seccion;

public interface SeccionManager {
	
	public List<Seccion> listaSeccion();
	
	public void insertaSeccion(Seccion seccion);

}
