package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class SistemaPlaylist {
	
	private HashMap<String, ItemReproduzivel> itens;
	private HashSet <Genero> generos;
	private ArrayList <Playlist> playlists;
	
	
	public SistemaPlaylist (){
		this.itens = new HashMap<String, ItemReproduzivel>();
		this.generos = new HashSet<Genero>();
		this.playlists = new ArrayList<Playlist>();
	}
	
	public boolean cadastrar (ItemReproduzivel item) {
		if (item == null || itens.containsKey(item.getCodigo())) {
			return false;
		}
		itens.put(item.getCodigo(), item);
		return true;
	}
	
	public ArrayList<ItemReproduzivel> buscarPorTitulo (String titulo) {
		ArrayList<ItemReproduzivel> resultado = new ArrayList<ItemReproduzivel>();
		for (ItemReproduzivel item: itens.values()) {
			if (item.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
				resultado.add(item);
			}
		}
		return resultado;
	}
	
	public ArrayList<ItemReproduzivel> buscarPorGenero (Genero genero) {
		ArrayList<ItemReproduzivel> resultado = new ArrayList<ItemReproduzivel>();
		for (ItemReproduzivel item: itens.values()) {
			if (item instanceof Musica musica && musica.getGenero() == genero) {
				resultado.add(item);
			}
		}
		return resultado;
	}
	
	public boolean reproduzirItem (String codigo) {
		ItemReproduzivel item = itens.get(codigo);
		if (item == null) {
			return false;
		}
		item.reproduzir();
		return true;
	}
	
	public boolean favoritarItem (String codigo) {
		ItemReproduzivel item = itens.get(codigo);
		if (item instanceof Favoritavel favoritavel) {
			favoritavel.favoritar();
			return true;
		}
		return false;
	}
	
	public boolean desfavoritarItem (String codigo) {
		ItemReproduzivel item = itens.get(codigo);
		if (item instanceof Favoritavel favoritavel) {
			favoritavel.desfavoritar();
			return true;
		}
		return false;
	}
	
	public boolean removerPlaylist (String nomePlaylist) {
		for (int i = 0; i < playlists.size(); i++) {
			if (playlists.get(i).getNomePlaylist().equals(nomePlaylist)) {
				playlists.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean remover (String codigo) {
		return itens.remove(codigo) != null;
	}
	
	public ArrayList<ItemReproduzivel> listarFavoritos (){
		ArrayList<ItemReproduzivel> favoritos = new ArrayList<>();
		for (ItemReproduzivel item: itens.values()) {
			if (item instanceof Favoritavel favoritavel && favoritavel.isFavorito()) {
				favoritos.add(item);
				
			}
		}
		return favoritos;
	}
	
	public void adicionarPlaylist (Playlist playlist) {
		if (playlist != null) {
			this.playlists.add(playlist);
		}
	}
	
	public Playlist buscarPlaylistPorNome (String nomePlaylist) {
		for (Playlist p: playlists) {
			if (p.getNomePlaylist().equals(nomePlaylist)) {
				return p;
			}
		}
		return null;
	}

	public HashMap<String, ItemReproduzivel> getItens() {
		return itens;
	}

	public HashSet<Genero> getGeneros() {
		return generos;
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}
	
	
}
