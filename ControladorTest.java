package teste;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;
import controlador.Controlador;
import model.Genero;
import model.ItemReproduzivel;

public class ControladorTest {

	@Test
	public void testCadastrarMusicaComSucesso() {
		Controlador controller = new Controlador();
		
		boolean resultado = controller.cadastrarMusica("001", "Gold Rush", 192, "Taylor Swift", Genero.POP);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testNaoCadastrarMusicaComCodigoDuplicado() {
		Controlador controller = new Controlador();
		
		controller.cadastrarMusica("001", "Gold Rush", 192, "Taylor Swift", Genero.POP);
		boolean resultado = controller.cadastrarMusica("001", "Outra Musica", 200, "Outro Artista", Genero.ROCK);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testCadastrarPodcastComSucesso() {
		Controlador controller = new Controlador();
		
		boolean resultado = controller.cadastrarPodcast("002", "Tecnologia Hoje", 1800, "Fulano", 10);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testRemoverItemExistente() {
		Controlador controller = new Controlador();
		
		controller.cadastrarMusica("001", "Gold Rush", 192, "Taylor Swift", Genero.POP);
		boolean resultado = controller.removerItem("001");
		
		assertTrue(resultado);
	}
	
	@Test
	public void testRemoverItemInexistente() {
		Controlador controller = new Controlador();
		
		boolean resultado = controller.removerItem("999");
		
		assertFalse(resultado);
	}
	
	@Test
	public void testBuscarPorGenero() {
		Controlador controller = new Controlador();
		
		controller.cadastrarMusica("001", "Gold Rush", 192, "Taylor Swift", Genero.POP);
		controller.cadastrarMusica("002", "Go Your Own Way", 218, "Fleetwood Mac", Genero.ROCK);
		
		ArrayList<ItemReproduzivel> resultado = controller.buscarPorGenero(Genero.POP);
		
		assertEquals(1, resultado.size());
	}
	
	@Test
	public void testListarTodos() {
		Controlador controller = new Controlador();
		
		controller.cadastrarMusica("001", "Gold Rush", 192, "Taylor Swift", Genero.POP);
		controller.cadastrarPodcast("002", "Tecnologia Hoje", 1800, "Fulano", 10);
		
		ArrayList<ItemReproduzivel> resultado = controller.listarTodos();
		
		assertEquals(2, resultado.size());
	}
	
	@Test
	public void testReproduzirItemExistente() {
		Controlador controller = new Controlador();
		
		controller.cadastrarMusica("001", "Gold Rush", 192, "Taylor Swift", Genero.POP);
		boolean resultado = controller.reproduzirItem("001");
		
		assertTrue(resultado);
	}
	
	@Test
	public void testReproduzirItemInexistente() {
		Controlador controller = new Controlador();
		
		boolean resultado = controller.reproduzirItem("999");
		
		assertFalse(resultado);
	}
	
	@Test
	public void testFavoritarItem() {
		Controlador controller = new Controlador();
		
		controller.cadastrarMusica("001", "Gold Rush", 192, "Taylor Swift", Genero.POP);
		boolean resultado = controller.favoritarItem("001");
		
		assertTrue(resultado);
		assertEquals(1, controller.listarFavoritos().size());
	}
	
	@Test
	public void testDesfavoritarItem() {
		Controlador controller = new Controlador();
		
		controller.cadastrarMusica("001", "Gold Rush", 192, "Taylor Swift", Genero.POP);
		controller.favoritarItem("001");
		boolean resultado = controller.desfavoritarItem("001");
		
		assertTrue(resultado);
		assertEquals(0, controller.listarFavoritos().size());
	}
	
	@Test
	public void testCriarPlaylistComSucesso() {
		Controlador controller = new Controlador();
		
		boolean resultado = controller.criarPlaylist("Favoritas de Rock");
		
		assertTrue(resultado);
	}
	
	@Test
	public void testNaoCriarPlaylistComNomeDuplicado() {
		Controlador controller = new Controlador();
		
		controller.criarPlaylist("Favoritas de Rock");
		boolean resultado = controller.criarPlaylist("Favoritas de Rock");
		
		assertFalse(resultado);
	}
	
	@Test
	public void testAdicionarItemNaPlaylist() {
		Controlador controller = new Controlador();
		
		controller.cadastrarMusica("001", "Go Your Own Way", 218, "Fleetwood Mac", Genero.ROCK);
		controller.criarPlaylist("Favoritas de Rock");
		
		boolean resultado = controller.adicionarItemNaPlaylist("Favoritas de Rock", "001");
		
		assertTrue(resultado);
	}
	
	@Test
	public void testNaoAdicionarItemDuplicadoNaPlaylist() {
		Controlador controller = new Controlador();
		
		controller.cadastrarMusica("001", "Go Your Own Way", 218, "Fleetwood Mac", Genero.ROCK);
		controller.criarPlaylist("Favoritas de Rock");
		controller.adicionarItemNaPlaylist("Favoritas de Rock", "001");
		
		boolean resultado = controller.adicionarItemNaPlaylist("Favoritas de Rock", "001");
		
		assertFalse(resultado);
	}
	
	@Test
	public void testRemoverItemDaPlaylist() {
		Controlador controller = new Controlador();
		
		controller.cadastrarMusica("001", "Go Your Own Way", 218, "Fleetwood Mac", Genero.ROCK);
		controller.criarPlaylist("Favoritas de Rock");
		controller.adicionarItemNaPlaylist("Favoritas de Rock", "001");
		
		boolean resultado = controller.removerItemDaPlaylist("Favoritas de Rock", "001");
		
		assertTrue(resultado);
	}
	
	@Test
	public void testRemoverPlaylistExistente() {
		Controlador controller = new Controlador();
		
		controller.criarPlaylist("Favoritas de Rock");
		boolean resultado = controller.removerPlaylist("Favoritas de Rock");
		
		assertTrue(resultado);
	}
	
	@Test
	public void testRemoverPlaylistInexistente() {
		Controlador controller = new Controlador();
		
		boolean resultado = controller.removerPlaylist("Playlist Fantasma");
		
		assertFalse(resultado);
	}
}
