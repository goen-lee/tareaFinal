package com.escalab.model;

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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Informacion del Producto")
@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false, foreignKey = @ForeignKey(name = "FK_producto_categoria"))
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_sub_categoria", nullable = false, foreignKey = @ForeignKey(name = "FK_producto_sub_categoria"))
	private SubCategoria subCategoria;
	
	@ApiModelProperty(notes = "Nombre Producto debe tener minimo 3 caracteres")
	@Size(min = 3, max = 50, message = "Nombre Producto debe tener minimo 3 caracteres")
	@Column(name = "nombre_producto", nullable = false, length = 50)
	private String nombreProducto;
	
	@ApiModelProperty(notes = "Descripcion debe tener minimo 3 caracteres")
	@Size(min = 3, max = 70, message = "Descripcion debe tener minimo 3 caracteres")
	@Column(name = "descripcion", nullable = false, length = 70)
	private String descripcion;

	@ApiModelProperty(notes = "Atlo debe tener minimo 1 caracteres, hasta 6 maximo")
	@Size(min = 1, max = 6, message = "Atlo debe tener minimo 1 caracteres")
	@Column(name = "alto", nullable = false, length = 6)
	private String alto;
	
	@ApiModelProperty(notes = "Ancho debe tener minimo 1 caracteres, hasta 6 maximo")
	@Size(min = 1, max = 6, message = "Ancho debe tener minimo 1 caracteres")
	@Column(name = "ancho", nullable = false, length = 6)
	private String ancho;
	
	@ApiModelProperty(notes = "Largo debe tener minimo 1 caracteres, hasta 6 maximo")
	@Size(min = 1, max = 6, message = "Largo debe tener minimo 1 caracteres")
	@Column(name = "largo", nullable = false, length = 6)
	private String largo;
	
	@ApiModelProperty(notes = "Peso debe tener minimo 1 caracteres, hasta 6 maximo ")
	@Size(min = 1, max = 6, message = "Peso debe tener minimo 1 caracteres")
	@Column(name = "peso", nullable = false, length = 6)
	private String peso;
	
	@ApiModelProperty(notes = "Marca debe tener minimo 2 caracteres")
	@Size(min = 2, max = 70, message = "Marca debe tener minimo 2 caracteres")
	@Column(name = "marca",nullable = false)
	private String marca;
	
	@ApiModelProperty(notes = "Foto URL debe tener minimo 2 caracteres")
	@Size(min = 2, max = 250, message = "Foto URL debe tener minimo 2 caracteres")
	@Column(name = "foto_url", nullable = true)
	private String fotoUrl;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAlto() {
		return alto;
	}

	public void setAlto(String alto) {
		this.alto = alto;
	}

	public String getAncho() {
		return ancho;
	}

	public void setAncho(String ancho) {
		this.ancho = ancho;
	}

	public String getLargo() {
		return largo;
	}

	public void setLargo(String largo) {
		this.largo = largo;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProducto == null) ? 0 : idProducto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (idProducto == null) {
			if (other.idProducto != null)
				return false;
		} else if (!idProducto.equals(other.idProducto))
			return false;
		return true;
	}
}
