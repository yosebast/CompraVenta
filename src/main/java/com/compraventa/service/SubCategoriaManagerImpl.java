package com.compraventa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compraventa.dao.SubCategoriaDao;
import com.compraventa.entidades.Subcategoria;


@Service
public class SubCategoriaManagerImpl implements SubCategoriaManager {

	
	@Autowired
	SubCategoriaDao subcategorya;
	
	@Autowired	
	SubCategoriaDao   subcategory;  
	
	
	
	public void insertarsubCategory(Subcategoria subcategory){
		
		subcategorya.insertasubCategoria(subcategory);
		
	}
	
	
	public List<Subcategoria> listaSubCategoriaManag(){
		
		return subcategory.listaSubCategoriaDao();
		
	}
	
	
	
}
