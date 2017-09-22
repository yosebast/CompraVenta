package com.compraventa.service;


import org.springframework.web.multipart.MultipartFile;

import com.compraventa.entidades.Productos;
	
	public interface ProductoManager extends GenericService<Productos, Integer> {	

	public void insertaProducto(Productos producto, MultipartFile file);
	public void actualizaProducto(Productos product, MultipartFile file);
}
