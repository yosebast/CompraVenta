package com.compraventa.dao;


import org.springframework.web.multipart.MultipartFile;

import com.compraventa.entidades.Productos;



	
public interface ProductoDao extends GenericDao<Productos, Integer> {

public void InsertaProducto(Productos producto, MultipartFile file);	
public void actualizaProducto(Productos product, MultipartFile file);

	
}
