package model;

public class Podcast extends ItemReproduzivel implements Favoritavel {

	private String apresentador;
	private int episodio;
	private boolean favorito;
	
	public Podcast(String codigo, String titulo, int duracaoSegundos, String apresentador, int episodio) {
		super(codigo, titulo, duracaoSegundos);
		this.apresentador = apresentador;
		this.episodio = episodio;
		this.favorito = false;
	}
	

	@Override
	public void favoritar() {
		this.favorito = true;
	}
	@Override
	public void desfavoritar() {
		this.favorito = false;
	}
	@Override
	public boolean isFavorito() {
		if (favorito == true) {
			return true;
		}
		return false;
	}
	@Override
	public void reproduzir() {
		System.out.println("Reproduzindo: "+ getTitulo()+ " | "+ episodio +" | "+ getDuracaoSegundos());
	}


	public String getApresentador() {
		return apresentador;
	}


	public int getEpisodio() {
		return episodio;
	}
}
