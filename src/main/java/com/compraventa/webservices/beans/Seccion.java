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


public class Seccion {
	
	private int idseccion;

	private String nomseccion;


	private List<Productos> producto;
	
	
	
	public Seccion() {
	}
	
	public int getIdseccion() {
		return idseccion;
	}

	public void setIdseccion(int idseccion) {
		this.idseccion = idseccion;
	}

	public String getNomseccion() {
		return nomseccion;
	}

	public void setNomseccion(String nomseccion) {
		this.nomseccion = nomseccion;
	}

}
