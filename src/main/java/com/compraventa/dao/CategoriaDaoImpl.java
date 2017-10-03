package com.compraventa.dao;



import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.compraventa.entidades.Categoria;
import com.compraventa.entidades.Seccion;

	
@Repository
public class CategoriaDaoImpl extends GenericDaoImpl<Categoria, Integer> implements CategoriaDao {

	
	@SuppressWarnings("unchecked")
	public List<Categoria> getCategoryBySeccion(Seccion seccion) {
		// TODO Auto-generated method stub		
		Query listaCategorias = currentSession().createQuery("select distinct p.categoria from Productos p LEFT JOIN p.seccion s where s.nomseccion=:Nomseccion");		
		listaCategorias.setParameter("Nomseccion", seccion.getNomseccion());	
		List<Categoria> lista = listaCategorias.list();
		
		System.out.println(lista.size());
		
		return lista;
	}	
	

}
