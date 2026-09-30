package console;

import java.util.List;
import java.util.Scanner;

import controlador.Controlador;
import model.Genero;
import model.ItemReproduzivel;
import model.Musica;
import model.Playlist;
import model.Podcast;

public class TelaConsole {

	private Scanner scanner;
	private Controlador controlador;

	public TelaConsole() {
		scanner = new Scanner(System.in);
		controlador = new Controlador();
	}

	public void iniciar() {
		int opcao;

		do {
			exibirMenu();
			opcao = lerInteiro("Opcao: ");

			executarOpcao(opcao);

			if (opcao != 0) {
				System.out.println();
			}

		} while (opcao != 0);

		scanner.close();
	}

	private void exibirMenu() {
		System.out.println("==================================");
		System.out.println("            JAVATUNES");
		System.out.println("==================================");
		System.out.println("1  - Cadastrar item no acervo");
		System.out.println("2  - Listar acervo");
		System.out.println("3  - Consultar item por codigo");
		System.out.println("4  - Reproduzir item");
		System.out.println("5  - Favoritar ou desfavoritar item");
		System.out.println("6  - Listar favoritos");
		System.out.println("7  - Criar playlist");
		System.out.println("8  - Adicionar item a playlist");
		System.out.println("9  - Remover item da playlist");
		System.out.println("10 - Listar playlist");
		System.out.println("11 - Remover playlist");
		System.out.println("12 - Exibir resumo do sistema");
		System.out.println("0  - Sair");
		System.out.println();
	}

	private void executarOpcao(int opcao) {
		switch (opcao) {
		case 1:
			cadastrarItem();
			break;

		case 2:
			listarAcervo();
			break;

		case 3:
			consultarItem();
			break;

		case 4:
			reproduzirItem();
			break;

		case 5:
			alterarFavorito();
			break;

		case 6:
			listarFavoritos();
			break;

		case 7:
			criarPlaylist();
			break;

		case 8:
			adicionarItemPlaylist();
			break;

		case 9:
			removerItemPlaylist();
			break;

		case 10:
			listarPlaylist();
			break;

		case 11:
			removerPlaylist();
			break;

		case 12:
			exibirResumoSistema();
			break;

		case 0:
			System.out.println("Programa encerrado.");
			break;

		default:
			System.out.println("Opcao invalida.");
		}
	}

	private void cadastrarItem() {
		System.out.println("TIPO DO ITEM");
		System.out.println("1 - Musica");
		System.out.println("2 - Podcast");
		System.out.println("0 - Voltar");
		int tipo = lerInteiro("Tipo: ");

		if (tipo == 0) {
			return;
		}

		if (tipo < 1 || tipo > 2) {
			System.out.println("Tipo invalido.");
			return;
		}

		String codigo = lerTexto("Codigo: ");
		String titulo = lerTexto("Titulo: ");
		int duracaoSegundos = lerInteiro("Duracao (segundos): ");

		if (tipo == 1) {
			String artista = lerTexto("Artista: ");
			Genero genero = lerGenero();
			if (genero == null) {
				System.out.println("Genero invalido.");
				return;
			}
			boolean sucesso = controlador.cadastrarMusica(codigo, titulo, duracaoSegundos, artista, genero);
			System.out.println(sucesso ? "Musica cadastrada com sucesso." : "Codigo ja esta em uso.");
		} else {
			String apresentador = lerTexto("Apresentador: ");
			int episodio = lerInteiro("Episodio: ");
			boolean sucesso = controlador.cadastrarPodcast(codigo, titulo, duracaoSegundos, apresentador, episodio);
			System.out.println(sucesso ? "Podcast cadastrado com sucesso." : "Codigo ja esta em uso.");
		}
	}

	private Genero lerGenero() {
		System.out.println("GENERO");
		System.out.println("1 - POP");
		System.out.println("2 - ROCK");
		System.out.println("3 - JAZZ");
		System.out.println("4 - MPB");
		System.out.println("5 - ELETRONICA");
		System.out.println("6 - SERTANEJO");
		int opcao = lerInteiro("Genero: ");

		switch (opcao) {
		case 1:
			return Genero.POP;
		case 2:
			return Genero.ROCK;
		case 3:
			return Genero.JAZZ;
		case 4:
			return Genero.MPB;
		case 5:
			return Genero.ELTRONICA;
		case 6:
			return Genero.SERTANEJO;
		default:
			return null;
		}
	}

	private void listarAcervo() {
		List<ItemReproduzivel> itens = controlador.listarTodos();

		if (itens.isEmpty()) {
			System.out.println("Nenhum item cadastrado.");
			return;
		}

		for (ItemReproduzivel item : itens) {
			String linha = "-> " + item.getCodigo() + " | " + item.getTitulo()
					+ " | " + item.getDuracaoSegundos() + "s";

			if (item instanceof Musica musica) {
				linha += " | " + musica.getArtista() + " | " + musica.getGenero();
			} else if (item instanceof Podcast podcast) {
				linha += " | " + podcast.getApresentador() + " | Episodio " + podcast.getEpisodio();
			}

			System.out.println(linha);
		}
	}

	private void consultarItem() {
		String codigo = lerTexto("Codigo do item: ");

		for (ItemReproduzivel item : controlador.listarTodos()) {
			if (item.getCodigo().equals(codigo)) {
				System.out.println("Codigo: " + item.getCodigo());
				System.out.println("Titulo: " + item.getTitulo());
				System.out.println("Duracao (segundos): " + item.getDuracaoSegundos());
				return;
			}
		}

		System.out.println("Item nao encontrado.");
	}

	private void reproduzirItem() {
		String codigo = lerTexto("Codigo do item: ");
		boolean sucesso = controlador.reproduzirItem(codigo);
		if (!sucesso) {
			System.out.println("Item nao encontrado.");
		}
	}

	private void alterarFavorito() {
		String codigo = lerTexto("Codigo do item: ");

		System.out.println("1 - Favoritar");
		System.out.println("2 - Desfavoritar");
		int opcao = lerInteiro("Opcao: ");

		String resultado;

		switch (opcao) {
		case 1:
			resultado = controlador.favoritarItem(codigo) ? "Item favoritado com sucesso." : "Item nao encontrado.";
			break;

		case 2:
			resultado = controlador.desfavoritarItem(codigo) ? "Item desfavoritado com sucesso." : "Item nao encontrado.";
			break;

		default:
			resultado = "Opcao invalida.";
		}

		System.out.println(resultado);
	}

	private void listarFavoritos() {
		List<ItemReproduzivel> favoritos = controlador.listarFavoritos();

		if (favoritos.isEmpty()) {
			System.out.println("Nenhum item favoritado.");
			return;
		}

		for (ItemReproduzivel item : favoritos) {
			System.out.println("- " + item.getCodigo() + " | " + item.getTitulo());
		}
	}

	private void criarPlaylist() {
		String nome = lerTexto("Nome da playlist: ");
		boolean sucesso = controlador.criarPlaylist(nome);
		System.out.println(sucesso ? "Playlist criada com sucesso." : "Ja existe uma playlist com esse nome.");
	}

	private void adicionarItemPlaylist() {
		String nomePlaylist = lerTexto("Nome da playlist: ");
		String codigoItem = lerTexto("Codigo do item: ");
		boolean sucesso = controlador.adicionarItemNaPlaylist(nomePlaylist, codigoItem);
		System.out.println(sucesso ? "Item adicionado a playlist com sucesso."
				: "Playlist ou item nao encontrado, ou item ja esta na playlist.");
	}

	private void removerItemPlaylist() {
		String nomePlaylist = lerTexto("Nome da playlist: ");
		String codigoItem = lerTexto("Codigo do item: ");
		boolean sucesso = controlador.removerItemDaPlaylist(nomePlaylist, codigoItem);
		System.out.println(sucesso ? "Item removido da playlist com sucesso." : "Playlist ou item nao encontrado.");
	}

	private void listarPlaylist() {
		String nome = lerTexto("Nome da playlist: ");
		Playlist playlist = controlador.buscarPlaylist(nome);

		if (playlist == null) {
			System.out.println("Playlist nao encontrada.");
			return;
		}

		System.out.println("Playlist: " + playlist.getNomePlaylist());
		System.out.println("Duracao total (segundos): " + playlist.duracaoTotal());

		if (playlist.getItens().isEmpty()) {
			System.out.println("A playlist nao possui itens.");
			return;
		}

		for (ItemReproduzivel item : playlist.getItens()) {
			System.out.println("- " + item.getCodigo() + " | " + item.getTitulo());
		}
	}

	private void removerPlaylist() {
		String nome = lerTexto("Nome da playlist: ");
		boolean sucesso = controlador.removerPlaylist(nome);
		System.out.println(sucesso ? "Playlist removida com sucesso." : "Playlist nao encontrada.");
	}

	private void exibirResumoSistema() {
		int quantidadeItens = controlador.listarTodos().size();
		int quantidadeFavoritos = controlador.listarFavoritos().size();

		System.out.println("Quantidade de itens cadastrados: " + quantidadeItens);
		System.out.println("Quantidade de itens favoritos: " + quantidadeFavoritos);
	}

	private int lerInteiro(String mensagem) {
		while (true) {
			System.out.print(mensagem);
			String entrada = scanner.nextLine();

			try {
				return Integer.parseInt(entrada);

			} catch (NumberFormatException e) {
				System.out.println("Digite um numero inteiro valido.");
			}
		}
	}

	private String lerTexto(String mensagem) {
		System.out.print(mensagem);
		return scanner.nextLine();
	}

}
