package com.escuela.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="maestros")
public class Maestros {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "maestros_Seq")
	@SequenceGenerator(name = "maestros_Seq", sequenceName = "MAESTROS_SEQ")
	private Long id_maestros;
	
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="maestros_id")// como la relacion es en un solo sentido tenemos que indicar cual es la llave foranea.
	private List<Alumnos> alumnos;
	
	@Column(name="nombres")
	@NotEmpty
	private String nombres;
	
	@Column(name="apellidos")
	@NotEmpty
	private String apellidos;
	
	@Column(name="dui")
	@NotEmpty
	private String dui;
	
	
	
	@OneToOne(fetch=FetchType.LAZY)
	private Alumnos alumno;
	
	public void Alumnos() {
		this.alumnos= new ArrayList<>();
	}

	public Long getId_maestros() {
		return id_maestros;
	}

	public void setId_maestros(Long id_maestros) {
		this.id_maestros = id_maestros;
	}

	public List<Alumnos> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumnos> alumnos) {
		this.alumnos = alumnos;
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

	public String getDui() {
		return dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
	}

	public Alumnos getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumnos alumno) {
		this.alumno = alumno;
	}

	

	
}
