package com.escuela.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="alumnos")
public class Alumnos implements Serializable{

	private static final long serialVersionUID = 1L;

	//@GeneratedValue(strategy = GenerationType.IDENTITY) //para mysql
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alumnos_Seq")
	@SequenceGenerator(name = "alumnos_Seq", sequenceName = "alumnos_SEQ")
	@Column(name = "id_alumnos", updatable = false, nullable = false)
	private Long id_alumnos; 
	
	@Column(name="name")
	@NotEmpty
	private String name;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@NotEmpty
	@Email
	private String email;
	
	@Column(name="grado")
	@NotEmpty
	private String grado;
	
	//en la tabla doctores va a crear pacientes id para relacionar ambas tablas
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Maestros> maestros;
	
	public Alumnos() {
		maestros = new ArrayList<Maestros>();

	}

	public Long getId_alumnos() {
		return id_alumnos;
	}

	public void setId_alumnos(Long id_alumnos) {
		this.id_alumnos = id_alumnos;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public List<Maestros> getMaestros() {
		return maestros;
	}

	public void setMaestros(List<Maestros> maestros) {
		this.maestros = maestros;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
