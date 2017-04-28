package com.compraventa.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.compraventa.entidades.Subcategoria;
import com.compraventa.util.HibernateUtil;


@Repository
public class SubCategoriaDaoImpl implements SubCategoriaDao {

	
	
	public void insertasubCategoria(Subcategoria subcategory) {
		// TODO Auto-generated method stub
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		sesion.save(subcategory);
		
	}
	
	
	public List<Subcategoria> listaSubCategoriaDao(){
		
		List<Subcategoria> listaCategorias = null;		
		Session sesion = HibernateUtil.getSessionFactory().openSession();		
		listaCategorias  = sesion.createQuery("from Subcategoria").list();		
		return listaCategorias;
		
	}

}
