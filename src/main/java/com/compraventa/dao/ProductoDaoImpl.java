package com.compraventa.dao;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.compraventa.entidades.Productos;

import com.compraventa.util.HibernateUtil;


@Repository
public class ProductoDaoImpl implements ProductoDao {

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

	public List<Productos> cargaProductosDao() {
		// TODO Auto-generated method stub		
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();	
		
		return sesion.createQuery("from Productos").list();
	}

	
	
	
	
	
	
}
