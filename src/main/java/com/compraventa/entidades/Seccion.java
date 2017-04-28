package com.compraventa.entidades;

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


@Entity
@Table(name="seccion")
public class Seccion implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="idseccion", nullable= false)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idseccion;
	
	
	@Column(name="nomseccion", nullable= false)
	private String nomseccion;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="idseccion")
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
