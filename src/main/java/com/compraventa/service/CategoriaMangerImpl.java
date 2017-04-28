package com.compraventa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compraventa.dao.CategoriaDao;
import com.compraventa.entidades.Categoria;

@Service
public class CategoriaMangerImpl implements CategoriaManger {

	
	@Autowired
	CategoriaDao categorya;
	
	
	
	
	
	public void insertarCategory(Categoria subcategory){
		
		categorya.insertarCategoria(subcategory);
		
	}
	
	
	public List<Categoria> listaCategorias(){
		
		return categorya.listaCategorias();
		
	}
}




