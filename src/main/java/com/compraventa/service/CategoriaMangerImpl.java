package com.compraventa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.compraventa.dao.CategoriaDao;
import com.compraventa.entidades.Categoria;


@Service
public class CategoriaMangerImpl extends GenericServiceImpl<Categoria, Integer> implements CategoriaManger {

			@Autowired
			CategoriaDao CategoriaPersisten;	
	

	/*
	
	
	
	
	public void insertarCategory(Categoria subcategory){
		
		categorya.insertarCategoria(subcategory);
		
	}
	
	
	public List<Categoria> listaCategorias(){
		
		return categorya.listaCategorias();
		
	}*/
}




