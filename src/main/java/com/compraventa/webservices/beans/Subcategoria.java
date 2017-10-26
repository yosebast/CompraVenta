package com.compraventa.webservices.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class Subcategoria  {

	
	private int idsubcategoria;	

	private String nomsubcategoria;	

	private Categoria categoria;	

	private List<Productos> producto;	
	

	public Subcategoria() {
	}
	

	public int getIdsubcategoria() {
		return this.idsubcategoria;
	}

	public void setIdsubcategoria(int idsubcategoria) {
		this.idsubcategoria = idsubcategoria;
	}

	public String getNomsubcategoria() {
		return this.nomsubcategoria;
	}

	public void setNomsubcategoria(String nomsubcategoria) {
		this.nomsubcategoria = nomsubcategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	

	

}
