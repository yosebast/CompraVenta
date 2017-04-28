package com.compraventa.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.compraventa.entidades.Productos;

public interface ProductoManager {

	
	public void insertaProducto(Productos producto, MultipartFile file);
	public List<Productos> cargaProductosServicio();
}
