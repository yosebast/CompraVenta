package com.compraventa.dao;


import java.io.IOException;
import java.sql.Blob;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.compraventa.entidades.Productos;


	
	@Repository
	public class ProductoDaoImpl extends GenericDaoImpl<Productos, Integer> implements ProductoDao {	
		
		public void InsertaProducto(Productos producto, MultipartFile file){
			// TODO Auto-generated method stub		
			
			try {
				Blob image = Hibernate.getLobCreator(currentSession()).createBlob(file.getInputStream(), file.getSize());
				producto.setUrlFoto1(image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			currentSession().persist(producto);
		}
		

	public void actualizaProducto(Productos product, MultipartFile file) {
		// TODO Auto-generated method stub
	
	        Query query = currentSession().createQuery("update Productos set seccion = :seccion," +" categoria = :categoria," + " subcategoria = :subcategoria," + " titulo = :titulo," + " precio = :precio,"
				+ " fechaPublicacion = :fechaPublicacion," + " estado = :estado," + " visitas = :visitas," + " nomImagen = :nomImagen," + " urlFoto1 = :urlFoto1" +
	    				" where idproducto = :idproducto");
		query.setParameter("seccion", product.getSeccion());
		query.setParameter("categoria", product.getCategoria());
		query.setParameter("subcategoria", product.getSubcategoria());
		query.setParameter("titulo", product.getTitulo());
		query.setParameter("precio", product.getPrecio());
		query.setParameter("fechaPublicacion", product.getFechaPublicacion());
		query.setParameter("estado", product.getEstado());
		query.setParameter("visitas", product.getVisitas());
		query.setParameter("nomImagen", product.getNomImagen());
		
		
		if(file.getSize() != 0 || !file.isEmpty()){			
			Blob image = null;
			try {
				image = Hibernate.getLobCreator(currentSession()).createBlob(file.getInputStream(), file.getSize());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			query.setParameter("urlFoto1", image);
			
		}else{
			
			query.setParameter("urlFoto1", product.getUrlFoto1());
		
		}
		
		
		query.setParameter("idproducto", product.getIdproducto());
	
	 query.executeUpdate();
		
	}

		
	
}
