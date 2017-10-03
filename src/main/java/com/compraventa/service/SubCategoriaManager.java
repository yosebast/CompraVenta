package com.compraventa.service;



import java.util.List;

import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Subcategoria;


	public interface SubCategoriaManager extends GenericService<Subcategoria, Integer> {

		List<Subcategoria> getSubcategoriaByCategoria(Categoria categoria);
	
	/*public void insertarsubCategory(Subcategoria subcategory);
	
	public List<Subcategoria> listaSubCategoriaManag();*/
	
}
