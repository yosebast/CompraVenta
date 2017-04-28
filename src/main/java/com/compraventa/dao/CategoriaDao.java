package com.compraventa.dao;

import java.util.List;

import com.compraventa.entidades.Categoria;

public interface CategoriaDao {

	
	public void insertarCategoria(Categoria categoria);
	public List<Categoria> listaCategorias();
}
