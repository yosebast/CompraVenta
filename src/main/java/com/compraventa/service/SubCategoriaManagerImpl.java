package com.compraventa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compraventa.dao.SubCategoriaDao;
import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Subcategoria;


	
	@Service
	public class SubCategoriaManagerImpl extends GenericServiceImpl<Subcategoria, Integer> implements SubCategoriaManager {

			@Autowired
			SubCategoriaDao SubcategoryPersisten;
			
			
			@Transactional
			public List<Subcategoria> getSubcategoriaByCategoria(Categoria categoria) {
				// TODO Auto-generated method stub
				return SubcategoryPersisten.getSubcategoriaByCategoria(categoria);
			}	
	


	/*
	
	public void insertarsubCategory(Subcategoria subcategory){
		
		subcategorya.insertasubCategoria(subcategory);
		
	}
	
	
	public List<Subcategoria> listaSubCategoriaManag(){
		
		return subcategory.listaSubCategoriaDao();
		
	}
	*/
	
	
}
