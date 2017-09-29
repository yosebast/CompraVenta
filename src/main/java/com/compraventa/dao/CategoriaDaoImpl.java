package com.compraventa.dao;


import java.util.List;

import org.springframework.stereotype.Repository;
import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Seccion;

	
@Repository
public class CategoriaDaoImpl extends GenericDaoImpl<Categoria, Integer> implements CategoriaDao {

	public List<Categoria> getCategoryBySeccion(Seccion seccion) {
		// TODO Auto-generated method stub
		return null;
	}	
	

}
