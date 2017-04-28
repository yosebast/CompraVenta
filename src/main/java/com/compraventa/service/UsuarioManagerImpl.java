package com.compraventa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compraventa.dao.UsuarioDao;
import com.compraventa.entidades.Usuarios;

@Service
public class UsuarioManagerImpl implements UsuarioManager {

	
	@Autowired
	UsuarioDao UsuarioPersisten;
	

	public void insertUsuario(Usuarios usu) {	
		
		UsuarioPersisten.insertaUsuario(usu);	
		
	}

	
	public List<Usuarios> cargaUsuariosServicio() {
		// TODO Auto-generated method stub
		
		return UsuarioPersisten.cargaUsuariosDao();		
	}


	public Usuarios getById(Integer id){	
		
		return UsuarioPersisten.getById( id );	
		
	}

	
	
	public void updateUsuario(Usuarios usuario, int idusu) {
		// TODO Auto-generated method stub
		
		 UsuarioPersisten.updateUsuarioDao(usuario, idusu);
		
	}
	
	
	public void deleteUsuario(Integer idUsuario){
		
		
		UsuarioPersisten.deleteUsuarioDao(idUsuario);
		
	}

}
