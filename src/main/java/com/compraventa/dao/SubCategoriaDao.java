package com.compraventa.dao;


import java.util.List;

import com.compraventa.entidades.Subcategoria;

public interface SubCategoriaDao {

	public void insertasubCategoria(Subcategoria subcategory);
	
	public List<Subcategoria> listaSubCategoriaDao();
	
}
