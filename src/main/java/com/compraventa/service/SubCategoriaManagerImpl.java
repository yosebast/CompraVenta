package com.compraventa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compraventa.dao.SubCategoriaDao;
import com.compraventa.entidades.Subcategoria;


	
	@Service
	public class SubCategoriaManagerImpl extends GenericServiceImpl<Subcategoria, Integer> implements SubCategoriaManager {

			@Autowired
			SubCategoriaDao SubcategoryPersisten;	
	


	/*
	
	public void insertarsubCategory(Subcategoria subcategory){
		
		subcategorya.insertasubCategoria(subcategory);
		
	}
	
	
	public List<Subcategoria> listaSubCategoriaManag(){
		
		return subcategory.listaSubCategoriaDao();
		
	}
	*/
	
	
}
