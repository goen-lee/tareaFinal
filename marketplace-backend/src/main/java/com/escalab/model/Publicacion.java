package com.escalab.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "publicacion")
public class Publicacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPublicacion;
	
	@ManyToOne
	@JoinColumn(name = "id_cuenta", nullable = false, foreignKey = @ForeignKey(name = "FK_publicacion_cuenta"))
	private Cuenta cuenta;
	
	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "FK_publicacion_producto"))
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "id_comentario", nullable = false, foreignKey = @ForeignKey(name = "FK_publicacion_comentario"))
	private Comentario comentario;
	
	@ApiModelProperty(notes = "Titulo debe tener minimo 3 caracteres")
	@Size(min = 3, message = "Titulo debe tener minimo 3 caracteres")
	@Column(name = "titulo", nullable = false, length = 20)
	private String titulo;
	
	@ApiModelProperty(notes = "Sub Titulo debe tener minimo 3 caracteres")
	@Size(min = 3, message = "Sub Titulo debe tener minimo 3 caracteres")
	@Column(name = "sub_titulo", nullable = false, length = 20)
	private String Subtitulo;
	
	@ApiModelProperty(notes = "Encabezado debe tener minimo 3 caracteres")
	@Size(min = 3, message = "Encabezado debe tener minimo 3 caracteres")
	@Column(name = "encabezado", nullable = false, length = 30)
	private String encabezado;

	@ApiModelProperty(notes = "Descripcion debe tener minimo 3 caracteres")
	@Size(min = 3, message = "Descripcion debe tener minimo 3 caracteres")
	@Column(name = "descripcion", nullable = false, length = 100)
	private String descripcion;

	@Column(name = "estado", nullable = false)
	private boolean estado;

	private LocalDateTime fecha;

	public Integer getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(Integer idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getSubtitulo() {
		return Subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		Subtitulo = subtitulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
