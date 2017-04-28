package com.compraventa.service;

import java.util.List;

import com.compraventa.entidades.Usuarios;

public interface UsuarioManager {
	
	
	public void insertUsuario(Usuarios usu);
	
	public List<Usuarios> cargaUsuariosServicio();
	
	public Usuarios getById(Integer id);
	
	public void updateUsuario(Usuarios usuario, int idusu);
	
	public void deleteUsuario(Integer idUsuario);
}
