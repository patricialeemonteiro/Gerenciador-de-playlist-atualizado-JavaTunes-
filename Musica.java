package model;

public class Musica extends ItemReproduzivel implements Favoritavel {
	private String artista;
	private Genero genero;
	private boolean favorito;
	
	public Musica(String codigo, String titulo, int duracaoSegundos, String artista, Genero genero) {
		super(codigo, titulo, duracaoSegundos);
		this.artista = artista;
		this.genero = genero;
		this.favorito = false;
	}

	public String getArtista() {
		return artista;
	}

	public Genero getGenero() {
		return genero;
	}

	public boolean isFavorito() {
	if (favorito == true) {
		return true;
	}
	return false;
	}
	
	@Override
	public void reproduzir() {
		System.out.println("Tocando música: "+ getTitulo() + " - "+ artista);
	}
	
	public void favoritar() {
		this.favorito = true;
	}
	
	public void desfavoritar() {
		this.favorito = false;
	}
}
