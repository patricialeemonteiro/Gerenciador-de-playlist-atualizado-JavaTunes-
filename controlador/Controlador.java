package controlador;

import java.util.ArrayList;

import model.Genero;
import model.ItemReproduzivel;
import model.Musica;
import model.Playlist;
import model.Podcast;
import model.SistemaPlaylist;

public class Controlador {

	private SistemaPlaylist sistema;

	public Controlador() {
		this.sistema = new SistemaPlaylist();
	}

	public boolean cadastrarMusica(String codigo, String titulo, int duracaoSegundos, String artista, Genero genero) {
		Musica musica = new Musica(codigo, titulo, duracaoSegundos, artista, genero);
		return sistema.cadastrar(musica);
	}

	public boolean cadastrarPodcast(String codigo, String titulo, int duracaoSegundos, String apresentador, int episodio) {
		Podcast podcast = new Podcast(codigo, titulo, duracaoSegundos, apresentador, episodio);
		return sistema.cadastrar(podcast);
	}

	public ArrayList<ItemReproduzivel> buscarPorGenero(Genero genero) {
		return sistema.buscarPorGenero(genero);
	}

	public ArrayList<ItemReproduzivel> listarTodos() {
		return new ArrayList<ItemReproduzivel>(sistema.getItens().values());
	}

	public boolean removerItem(String codigo) {
		return sistema.remover(codigo);
	}

	public boolean reproduzirItem(String codigo) {
		return sistema.reproduzirItem(codigo);
	}

	public boolean favoritarItem(String codigo) {
		return sistema.favoritarItem(codigo);
	}

	public boolean desfavoritarItem(String codigo) {
		return sistema.desfavoritarItem(codigo);
	}

	public ArrayList<ItemReproduzivel> listarFavoritos() {
		return sistema.listarFavoritos();
	}

	public boolean criarPlaylist(String nomePlaylist) {
		if (sistema.buscarPlaylistPorNome(nomePlaylist) != null) {
			return false;
		}
		sistema.adicionarPlaylist(new Playlist(nomePlaylist));
		return true;
	}

	public boolean adicionarItemNaPlaylist(String nomePlaylist, String codigoItem) {
		Playlist playlist = sistema.buscarPlaylistPorNome(nomePlaylist);
		ItemReproduzivel item = sistema.getItens().get(codigoItem);

		if (playlist == null || item == null) {
			return false;
		}

		try {
			playlist.adicionarItem(item);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public boolean removerItemDaPlaylist(String nomePlaylist, String codigoItem) {
		Playlist playlist = sistema.buscarPlaylistPorNome(nomePlaylist);
		if (playlist == null) {
			return false;
		}
		return playlist.removerItem(codigoItem);
	}

	public Playlist buscarPlaylist(String nomePlaylist) {
		return sistema.buscarPlaylistPorNome(nomePlaylist);
	}

	public boolean removerPlaylist(String nomePlaylist) {
		return sistema.removerPlaylist(nomePlaylist);
	}
}
