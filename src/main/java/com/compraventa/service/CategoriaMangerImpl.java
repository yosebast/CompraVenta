package com.compraventa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.compraventa.dao.CategoriaDao;
import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Seccion;


@Service
public class CategoriaMangerImpl extends GenericServiceImpl<Categoria, Integer> implements CategoriaManger {

			@Autowired
			CategoriaDao CategoriaPersisten;

		
			
			@Transactional(propagation = Propagation.REQUIRED)
			public List<Categoria> getCategoryBySeccion(Seccion seccion) {
				// TODO Auto-generated method stub
				return CategoriaPersisten.getCategoryBySeccion(seccion);
			}	
	

	/*
	
	
	
	
	public void insertarCategory(Categoria subcategory){
		
		categorya.insertarCategoria(subcategory);
		
	}
	
	
	public List<Categoria> listaCategorias(){
		
		return categorya.listaCategorias();
		
	}*/
}




