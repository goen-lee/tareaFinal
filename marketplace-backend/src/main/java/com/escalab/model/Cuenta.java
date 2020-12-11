package com.escalab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Informacion de la Cuenta")
@Entity
@Table(name = "cuenta")
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCuenta;
	
	
	@ApiModelProperty(notes = "Usuario debe tener minimo 3 caracteres")
	@Size(min = 3, message = "Usuario debe tener minimo 3 caracteres")
	@Column(name = "usuario", nullable = false, length = 70)
	private String usuario;
	
	@ApiModelProperty(notes = "Nombres debe tener minimo 3 caracteres")
	@Size(min = 3, message = "Nombres debe tener minimo 3 caracteres")
	@Column(name = "nombres", nullable = false, length = 70)
	private String nombres;
	
	@ApiModelProperty(notes = "Apellidos debe tener minimo 3 caracteres")
	@Size(min = 3, message = "Apellidos debe tener minimo 3 caracteres")
	@Column(name = "apellidos", nullable = false, length = 70)
	private String apellidos;
	
	@Email
	@Size(min = 6, message = "Email debe tener minimo 6 caracteres")
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	
	@ApiModelProperty(notes = "Domicilio debe tener minimo 5 caracteres")
	@Size(min = 5, max = 150, message = "Domicilio debe tener minimo 5 caracteres")
	@Column(name = "domicilio", nullable = false, length = 150)
	private String domicilio;
	
	@ApiModelProperty(notes = "Telefono debe tener 9 caracteres")
	@Size(min = 9, max = 9, message = "Telefono debe tener 9 caracteres")
	@Column(name = "telefono", nullable = true, length = 9)
	private String telefono;
	
	@ApiModelProperty(notes = "Codigo Pais debe tener 4 caracteres")
	@Size(min = 3, max = 4, message = "Codigo Pais debe tener 4 caracteres")
	@Column(name = "codigo_pais", nullable = false, length = 4)
	private String codigoPais;
	
	@ApiModelProperty(notes = "Movil debe tener 10 caracteres")
	@Size(min = 10, max = 10, message = "Movil debe tener 10 caracteres")
	@Column(name = "movil", nullable = false, length = 10)
	private String movil;
	
	@ApiModelProperty(notes = "tarjeta debe tener 16 caracteres")
	@Size(min = 16, max = 16, message = "tarjeta debe tener 16 caracteres")
	@Column(name = "tarjeta", nullable = false, length = 16)
	private String tarjeta;
	
	@Column(name = "estado", nullable = false)
	private boolean estado;

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCuenta == null) ? 0 : idCuenta.hashCode());
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
		Cuenta other = (Cuenta) obj;
		if (idCuenta == null) {
			if (other.idCuenta != null)
				return false;
		} else if (!idCuenta.equals(other.idCuenta))
			return false;
		return true;
	}
}
