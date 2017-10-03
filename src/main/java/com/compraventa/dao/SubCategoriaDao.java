package com.compraventa.dao;

import java.util.List;

import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Subcategoria;


public interface SubCategoriaDao extends GenericDao<Subcategoria, Integer> {

	List<Subcategoria> getSubcategoriaByCategoria(Categoria subcategoria);

	/*public void insertasubCategoria(Subcategoria subcategory);	
	public List<Subcategoria> listaSubCategoriaDao();*/
	
}
