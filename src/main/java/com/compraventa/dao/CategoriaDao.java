package com.compraventa.dao;

import java.util.List;

import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Seccion;
	
public interface CategoriaDao extends GenericDao<Categoria, Integer> {

	List<Categoria> getCategoryBySeccion(Seccion seccion);
	
	/*public void insertarCategoria(Categoria categoria);
	public List<Categoria> listaCategorias();*/
}
