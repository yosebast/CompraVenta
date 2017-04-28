package com.compraventa.dao;

import java.util.List;

import com.compraventa.entidades.Usuarios;

public interface UsuarioDao {
	
	
	
	//aqui las opreaciones del crud
	
	// la operacion de insertar
	
	
	public void insertaUsuario(Usuarios user);
	
	public List<Usuarios> cargaUsuariosDao();
	
	public Usuarios getById(Integer id);
	
	public void updateUsuarioDao(Usuarios usu, int idusu);
	
	public void deleteUsuarioDao(Integer idUsuario);
}
