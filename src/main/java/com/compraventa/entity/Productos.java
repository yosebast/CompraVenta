package com.compraventa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="productos")
public class Productos implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idproducto", nullable= false)	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idproducto;
	
	
	@ManyToOne
	@JoinColumn(name = "nombre", nullable = false)
	private Integer id_Usuario;
	
	
	@Column(name="titulo", nullable= false)
	private String titulo;
	
	@Column(name="descripcion", nullable= false)
	private String descripcion;
	
	@Column(name="precio", nullable= false)
	private double precio;
	
	@Column(name="fechaPublicacion")
	private Date fechaPublicacion;
	
	@ManyToOne
	@JoinColumn(name = "nomcategoria", nullable = false)
	@Column(name="categoria", nullable= false)
	private String categoria;
	
	
	@Column(name="estado", nullable= false)
	private String estado;
	
	@Column(name="visitas")
	private Integer visitas;
	
	@Column(name="urlFoto1")
	private String urlFoto1;
	
	@Column(name="urlFoto2")
	private String urlFoto2;
	
	public Integer getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public Integer getId_Usuario() {
		return id_Usuario;
	}

	public void setId_Usuario(Integer id_Usuario) {
		this.id_Usuario = id_Usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getVisitas() {
		return visitas;
	}

	public void setVisitas(Integer visitas) {
		this.visitas = visitas;
	}

	public String getUrlFoto1() {
		return urlFoto1;
	}

	public void setUrlFoto1(String urlFoto1) {
		this.urlFoto1 = urlFoto1;
	}

	public String getUrlFoto2() {
		return urlFoto2;
	}

	public void setUrlFoto2(String urlFoto2) {
		this.urlFoto2 = urlFoto2;
	}

	
	
	

	
}
