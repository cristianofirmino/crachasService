package com.pdfservices.model;

public class Pictogram {

	private String imagem;
	private String label;

	public Pictogram() {
		super();
	}

	public Pictogram(String imagem, String label) {
		super();
		this.imagem = imagem;
		this.label = label;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
