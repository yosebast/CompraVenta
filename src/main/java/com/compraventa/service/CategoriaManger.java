package com.compraventa.service;


import java.util.List;

import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Seccion;


	
public interface CategoriaManger extends GenericService<Categoria, Integer> {

	
	/*public void insertarCategory(Categoria subcategory);
	public List<Categoria> listaCategorias();*/
	
	public List<Categoria> getCategoryBySeccion(Seccion seccion);
}
