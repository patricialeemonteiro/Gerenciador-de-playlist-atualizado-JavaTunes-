package model;

import java.util.ArrayList;

public class Playlist{
	
	private String nomePlaylist;
	private ArrayList<ItemReproduzivel> itens;
	
	public Playlist(String nomePlaylist) {
		this.nomePlaylist = nomePlaylist;
		this.itens = new ArrayList<>();
	}
		
	public String getNomePlaylist() {
		return nomePlaylist;
	}
	public ArrayList<ItemReproduzivel> getItens() {
		return itens;
	}
	
	public boolean adicionarItem(ItemReproduzivel item) {
		for (ItemReproduzivel i: itens) {
			if (i.getCodigo().equals(item.getCodigo())) {
				throw new IllegalArgumentException("O item '"+ item.getTitulo() +"' ja existe na playlist.");
			}
		}
		return itens.add(item);
	}
	
	public boolean removerItem(String codigo) {
		for (int i = 0; i < itens.size(); i++) {
			if (itens.get(i).getCodigo().equals(codigo)) {
				itens.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public int duracaoTotal() {
		int total = 0;
		for (ItemReproduzivel i: itens) {
			total += i.getDuracaoSegundos();
		}
		return total;
	}	
}
