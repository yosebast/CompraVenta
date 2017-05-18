package com.compraventa.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.compraventa.entidades.Productos;

public interface ProductoDao {

	
	public void InsertaProducto(Productos producto, MultipartFile file);

public List<Productos> cargaProductosDao();

public Productos cargaUsuariosServicio(int idproducto);

public void actualizaProducto(Productos product, MultipartFile file);

	
}
