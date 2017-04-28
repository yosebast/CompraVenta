package com.compraventa.service;

import java.util.List;

import com.compraventa.entidades.Subcategoria;

public interface SubCategoriaManager {
	
	public void insertarsubCategory(Subcategoria subcategory);
	
	public List<Subcategoria> listaSubCategoriaManag();
	
}
