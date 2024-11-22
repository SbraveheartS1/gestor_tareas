package com.gestor_tareas.demo.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;

@Document(collection = "tareas")
public class Tareas {

	@Id
	private String id;
	@NotEmpty
	private String titulo;
	@NotEmpty
	private String descripcion;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date fechacreacion;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date fechavencimiento;

	private Boolean completada;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public Date getFechavencimiento() {
		return fechavencimiento;
	}
	public void setFechavencimiento(Date fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}
	public Boolean getCompletada() {
		return completada;
	}
	public void setCompletada(Boolean completada) {
		this.completada = completada;
	}
	
}
