package com.pdfservices.model;

import java.util.List;

public class EmployeeCard {

	private String matricula;
	private String idPeople;
	private String nome;
	private String cargo;
	private String foto;
	private String template = "templates/cracha.pdf";
	private List<Pictogram> pictogramas;

	public EmployeeCard() {
		super();
	}

	public EmployeeCard(String matricula, String idPeople, String nome, String cargo, String foto, List<Pictogram> pictogramas) {
		super();
		this.matricula = matricula;
		this.idPeople = idPeople;
		this.nome = nome;
		this.cargo = cargo;
		this.foto = foto;
		this.pictogramas = pictogramas;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getIdPeople() {
		return idPeople;
	}

	public void setIdPeople(String idPeople) {
		this.idPeople = idPeople;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public List<Pictogram> getPictogramas() {
		return pictogramas;
	}

	public void setPictogramas(List<Pictogram> pictogramas) {
		this.pictogramas = pictogramas;
	}

}
