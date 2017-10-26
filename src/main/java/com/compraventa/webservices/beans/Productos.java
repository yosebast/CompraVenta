package com.compraventa.webservices.beans;

import java.util.Date;
public class Productos  {


	private int idproducto;
	

	private Seccion seccion;
	

	private Categoria categoria;
	

	private Subcategoria subcategoria;
	

	private String titulo;	
	

	private Double precio;
	

	private Date fechaPublicacion;
	

	private String estado;
	
	
	private Integer visitas;
	

	private String nomImagen;
	

	private byte[] urlFoto1;	

	public Productos() {
	}

	

	public int getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Subcategoria getSubcategoria() {
		return subcategoria;
	}



	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}



	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	

	public String getNomImagen() {
		return nomImagen;
	}

	public void setNomImagen(String nomImagen) {
		this.nomImagen = nomImagen;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getFechaPublicacion() {
		return this.fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getVisitas() {
		return this.visitas;
	}

	public void setVisitas(Integer visitas) {
		this.visitas = visitas;
	}	
	

	
	public byte[] getUrlFoto1() {
		return urlFoto1;
	}



	public void setUrlFoto1(byte[] urlFoto1) {
		this.urlFoto1 = urlFoto1;
	}



	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

}
