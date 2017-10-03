package com.compraventa.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Subcategoria;

@Repository
public class SubCategoriaDaoImpl extends GenericDaoImpl<Subcategoria, Integer> implements SubCategoriaDao {

	@SuppressWarnings("unchecked")
	public List<Subcategoria> getSubcategoriaByCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		
		Query listaSubcategoria = currentSession().createQuery("select distinct p.subcategoria from Productos p LEFT JOIN p.categoria c where c.idcategoria=:IdCategoria");
		listaSubcategoria.setParameter("IdCategoria", categoria.getIdcategoria());
		List<Subcategoria> lista = listaSubcategoria.list();
		
		System.out.println(lista.size());
		return lista;
	}	

}
