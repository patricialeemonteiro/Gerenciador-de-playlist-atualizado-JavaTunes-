# Sistema de Gerenciamento de Playlists — JavaTunes

Projeto acadêmico desenvolvido em trio para as disciplinas de Linguagem de Programação II (LP1) e Laboratório de Programação II (Lab de LP1).

---

## Descrição do Projeto

O **JavaTunes** é um sistema de gerenciamento de conteúdos multimídia interativo via console. O projeto foca no gerenciamento e reprodução de faixas áudio, permitindo organizar músicas e podcasts através de playlists personalizadas, filtros por género e sistema de favoritos.

---

## Sobre a estrutura do código

* **Classe Abstrata `ItemReproduzivel`:** Serviu de classe base para abstração de conteúdos reproduzíveis (`Musica` e `Podcast`), definindo atributos comuns e métodos abstratos como `reproduzir()`.
* **Interface `Favoritavel`:** Interface que padroniza os comportamentos de favoritar, desfavoritar e checar estado dos itens.
* **Classes `Musica` e `Podcast`:** Implementações concretas que aplicam herança e polimorfismo com atributos específicos (ex: artista e gênero para músicas, apresentador e episódio para podcasts).
* **Classe `Playlist`:** Gerencia coleções de mídias, permitindo cálculo de duração total em segundos e controle de duplicados.
* **Classe `SistemaPlaylist`:** Núcleo de regras de negócio responsável pela busca e persistência em memória usando estruturas de dados otimizadas.
* **Classe `Controlador`:** Camada intermediária de controle que faz a ponte entre o menu interativo e o sistema principal.
* **Classe `ControladorTest`:** Suíte completa de testes unitários em JUnit para validação das regras do sistema.

---

## Melhorias

* **Arquitetura em Camadas:** Organização clara dos pacotes `model`, `controlador` e `teste`.
* **Estruturas de Dados Eficientes:** Uso estratégico de `HashMap` para busca instantânea de itens por código, `HashSet` para conjuntos sem duplicação e `ArrayList` para ordenação das playlists.
* **Garantia de Qualidade:** Cobertura de testes unitários automatizados com JUnit garantindo a integridade de todas as operações do controlador.
* **Tratamento de Exceções:** Validação rigorosa contra itens duplicados e códigos inexistentes.

---

## Tecnologias

* Java
* JUnit 4
