package com.compraventa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.compraventa.dao.ProductoDao;
import com.compraventa.entidades.Productos;


@Service
public class ProductoManagerImpl implements ProductoManager {

	
	@Autowired
	ProductoDao productoBBDD;
	
	
	public void insertaProducto(Productos producto, MultipartFile file) {
		// TODO Auto-generated method stub

		productoBBDD.InsertaProducto(producto, file);
		
	}


	public List<Productos> cargaProductosServicio() {
		
		List<Productos> listadoProductos = null;
		// TODO Auto-generated method stub
		
		listadoProductos = productoBBDD.cargaProductosDao();
		
		return listadoProductos;
	}

}
