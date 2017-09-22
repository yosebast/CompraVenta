package com.compraventa.service;

import com.compraventa.entidades.Usuarios;

public interface UsuarioManager extends GenericService<Usuarios, Integer> {
	
	
	/*public void insertUsuario(Usuarios usu);
	
	public List<Usuarios> cargaUsuariosServicio();
	
	public Usuarios getById(Integer id);
	
	public void updateUsuario(Usuarios usuario, int idusu);
	
	public void deleteUsuario(Integer idUsuario);*/

	public boolean isNotUserClaveUnique(String clave);
}
