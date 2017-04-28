package com.compraventa.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.compraventa.entidades.Categoria;
import com.compraventa.util.HibernateUtil;


@Repository
public class CategoriaDaoImpl implements CategoriaDao {
	
	
	
	public void insertarCategoria(Categoria categoria){
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		sesion.save(categoria);
		
		
		
	}
	
	public List<Categoria> listaCategorias(){
		
		List<Categoria> listacategorias = null;
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		
		listacategorias = sesion.createQuery("from Categoria").list();
		
		return listacategorias;
	}

}
