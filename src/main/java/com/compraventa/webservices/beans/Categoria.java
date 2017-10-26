package com.compraventa.webservices.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class Categoria  {

	private int idcategoria;	

	private String nomcategoria;	

	private String descripcion;

	private List<Productos> producto;

	private List<Subcategoria> subcategoria;

	public int getIdcategoria() {
		return this.idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	

	public String getNomcategoria() {
		return this.nomcategoria;
	}

	public void setNomcategoria(String nomcategoria) {
		this.nomcategoria = nomcategoria;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

}
