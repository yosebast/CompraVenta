package com.compraventa.dao;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.compraventa.entidades.Productos;

import com.compraventa.util.HibernateUtil;


@Repository
public class ProductoDaoImpl implements ProductoDao {
	
	/*
	 * todo este codigo es repetitivo y esta mal esto se desarrolla a traves de las interfaces y generics   de spring
	 */

	public void InsertaProducto(Productos producto, MultipartFile file){
		// TODO Auto-generated method stub
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Blob image = Hibernate.getLobCreator(sesion).createBlob(file.getInputStream(), file.getSize());
			producto.setUrlFoto1(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sesion.save(producto);	
	}

	@SuppressWarnings("unchecked")
	public List<Productos> cargaProductosDao() {
		// TODO Auto-generated method stub		
		
		
		List<Productos>  listadoProductos = null;
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();	
		
		 listadoProductos = sesion.createQuery("from Productos").list();
		 
		 return listadoProductos;
	}

	public Productos cargaUsuariosServicio(int idproducto) {
		// TODO Auto-generated method stub
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		Productos product = (Productos) sesion.get(Productos.class, idproducto);
		
		return product;
	}

	public void actualizaProducto(Productos product, MultipartFile file) {
		// TODO Auto-generated method stub
		Session sesion = HibernateUtil.getSessionFactory().openSession();		
		
		Query query = sesion.createQuery("update Productos set seccion = :seccion," +" categoria = :categoria," + " subcategoria = :subcategoria," + " titulo = :titulo," + " precio = :precio,"
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
				image = Hibernate.getLobCreator(sesion).createBlob(file.getInputStream(), file.getSize());
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
