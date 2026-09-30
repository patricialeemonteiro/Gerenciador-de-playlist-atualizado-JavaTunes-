package model;

public abstract class ItemReproduzivel {
	private String codigo;
	private String titulo;
	private int duracaoSegundos;
	
	public ItemReproduzivel(String codigo, String titulo, int duracaoSegundos) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.duracaoSegundos = duracaoSegundos;
	}
	
	public abstract void reproduzir();
	
	public String getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getDuracaoSegundos() {
		return duracaoSegundos;
	}
}