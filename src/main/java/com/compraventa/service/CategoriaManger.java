package com.compraventa.service;

import java.util.List;

import com.compraventa.entidades.Categoria;

public interface CategoriaManger {

	
	public void insertarCategory(Categoria subcategory);
	public List<Categoria> listaCategorias();
}
