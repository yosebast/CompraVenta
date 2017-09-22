package com.compraventa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.compraventa.dao.ProductoDao;

import com.compraventa.entidades.Productos;



	@Service
	public class ProductoManagerImpl extends GenericServiceImpl<Productos, Integer> implements ProductoManager {

			@Autowired
			ProductoDao ProductoPersisten;	

	@Transactional(propagation = Propagation.REQUIRED)
	public void insertaProducto(Productos producto, MultipartFile file) {
				// TODO Auto-generated method stub

		 ProductoPersisten.InsertaProducto(producto, file);
				
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void actualizaProducto(Productos product, MultipartFile file) {
		// TODO Auto-generated method stub
		ProductoPersisten.actualizaProducto(product, file);
	}

}
