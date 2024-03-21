import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Game {
	private static List<Player> players = new ArrayList<Player>();
	private static Scanner in = new Scanner(System.in);
	private static Map<String, Set<Bet>> playerBetsMap = new HashMap<>();
	private static Set<Integer> bet;
	private static int option;
	private static Player newPlayer;
	private static int betCounter = 1000;

	public static void main(String[] args) {
		// Mostra o Menu de Bem-vindo
		System.out.println("Bem vindo ao jogo da fortuna!");
		System.out.println("(1) Começar Novo Jogo");
		System.out.println("(2) Listar Apostas");

		option = in.nextInt();
		switch (option) {

			case 1:
				startGame();
				break;

			case 2:
				listGameBets();
		}
	}

	// INICIA JOGO
	// Printa na tela que esta iniciando jogo e chama metodo Inserir Player
	public static void startGame() {
		System.out.println("$-------------JOGO DA FORTUNA-------------$\n");
		System.out.println("Iniciando novo jogo...");

		insertNewPlayer();
	}

	// ADICIONA NOVO JOGADOR
	// Enquanto o CPF for duplicado, ele pedira para inserir os dados novamente,
	// pois não aceitara CPF duplicado. Se o CPF nao for duplicado, ele adiciona
	// o novo jogador e chama o metodo addBet.

	// Após chamar o metodo addBet, ele pergunta se deseja cadastrar mais jogadores.
	// Pois dentro do addBet
	// é chamado o metodo insertNewPlayer, que pergunta se deseja cadastrar mais
	// jogadores.

	// Se a opção for 1, ele chama o metodo insertNewPlayer, se a opção for 2, ele
	// printa na tela "Obrigado por se cadastrar!"
	// e chama o metodo listOrExecute.

	public static void insertNewPlayer() {

		String name = "";
		String cpf = "";
		// LIMPA BUFFER
		in.nextLine();

		while (true) {

			System.out.println("Digite o nome do jogador: ");
			name = in.nextLine();

			System.out.println("Digite o cpf do jogador: ");
			cpf = in.nextLine();

			// Verifica CPF Duplicata
			boolean isDuplicate = false;
			for (Player player : players) {
				if (player.getCpf().equals(cpf)) {
					System.out.println("\n");
					System.out.println("Erro. Player já existente! Tente novamente.\n");
					isDuplicate = true;

				}
			}
			if (!isDuplicate) {

				break;
			}
		}
		newPlayer = new Player(cpf, name); // Cria novo jogador
		players.add(newPlayer); // Adiciona jogador na lista de jogadores
		System.out.println("Jogador cadastrado com sucesso!");
		System.out.println("Agora, vamos fazer a(s) aposta(s)!\n");
		addBet(cpf);

		System.out.println("\n");
		System.out.println("Voce deseja cadastrar mais jogadores? (1) Sim (2) Não");
		int opcao = in.nextInt();
		if (opcao == 1) {
			insertNewPlayer();
		} else if (opcao == 2) {
			System.out.println("Obrigado por se cadastrar!\n");
			listOrExecute();

		} else {
			System.out.println("Opção inválida!");
		}
	}

	// ADICIONA APOSTA
	// Pede para o jogador escolher o modo de aposta, se for 1, ele chama o metodo
	// numbersGenerator
	// e printa na tela os numeros gerados. Se for 2, ele pede para o jogador
	// inserir 5 numeros de 1 a 50.
	// Se o numero for < 50 ou < 1, ele pede para inserir um numero valido.
	// Se o numero ja foi escolhido, ele pede para inserir outro numero.
	// Se for valido, é adicionado na lista de numeros escolhidos e incrementa o
	// contador de apostas.
	// Após criar a aposta, ele pergunta se deseja fazer mais uma aposta. Se a opção
	// for 1, ele chama o metodo addBet
	// se a opção for 2, ele printa na tela "Obrigado por apostar!".
	// Após isso, ele chama o metodo listOrExecute.

	public static void addBet(String cpf) {
		Set<Integer> manualBet = new TreeSet<>();

		System.out.println("Digite o número correspondente à sua escolha! \n");
		System.out.println("Modo de aposta: ");
		System.out.println("1 - Modo Surpresinha");
		System.out.println("2 - Manual");

		int choice = in.nextInt();

		if (choice == 1) {
			System.out.println("Você escolheu o Modo Surpresinha!\n");
			System.out.println("Gerando números aleatórios...");
			System.out.println("Seus números são: ");
			bet = Round.numbersGenerator();
			newPlayer.addNewBet(new Bet(bet, betCounter++));

			System.out.println(bet.toString());

		} else if (choice == 2) {
			System.out.println("Você escolheu o Modo Manual!");
			System.out.println("Insira 5 números de 1 a 50: ");

			while (manualBet.size() != 5) {
				int manualNumber = in.nextInt();

				for (int number : manualBet) {
					if (number == manualNumber) {
						System.out.println("Número já escolhido! Insira outro número: ");
						manualNumber = in.nextInt();
					}
				}

				if (manualNumber > 50 || manualNumber < 1) {
					System.out.println("Número inválido! Insira um número de 1 a 50: ");
				} else {
					manualBet.add(manualNumber);
				}
			}

			newPlayer.addNewBet(new Bet(manualBet, betCounter++));
			System.out.println("Seus números são: ");
			System.out.println(manualBet.toString());

		} else {
			System.out.println("Escolha inválida. Por favor, escolha 1 ou 2.");
			addBet(cpf);
		}

		System.out.println("\n");

		System.out.println("Gostaria de fazer mais uma aposta? (1) Sim (2) Não");
		int opcao = in.nextInt();
		if (opcao == 1) {
			addBet(cpf);
		} else if (opcao == 2) {
			System.out.println("Obrigado por apostar!");
		} else {
			System.out.println("Opção inválida!");
			addBet(cpf);
		}
	}

	// LISTA OU EXECUTA O SORTEIO
	// Pede para o jogador escolher entre listar apostas, finalizar apostas e
	// executar o sorteio ou adicionar mais jogadores.
	// Se a opcao for 1, ele chama o metodo listGameBets, se a opcao for 2, ele
	// chama o metodo confirmExecute.
	// Se a opcao for 3, ele chama o metodo insertNewPlayer. Se a opcao for
	// diferente de 1, 2 ou 3, ele printa na tela "Opcao inválida!"
	// e se a opcao for invalida, ele atua como metodo recurssivo, se chamando e
	// listando novamente as opcoes.

	public static void listOrExecute() {
		System.out.println("O que deseja fazer agora?");
		System.out.println("(1) Listar Apostas");
		System.out.println("(2) Finalizar apostas e executar o sorteio");
		System.out.println("(3) Adicionar mais jogadores");

		option = in.nextInt();

		switch (option) {
			case 1:
				listGameBets();
				break;
			case 2:
				confirmExecute();
				break;

			case 3:
				insertNewPlayer();

			default:
				System.out.println("Opção inválida!");
				listOrExecute();
		}
	}

	// CONFIRMA EXECUÇÃO DO SORTEIO
	// Pede para o jogador confirmar se deseja executar o sorteio.
	// Se a opcao for 1, ele chama o metodo executeDraw, se a opcao for 2, ele chama
	// o metodo listOrExecute.
	// Se a opcao for diferente de 1 ou 2, ele printa na tela "Opcao inválida!" e se
	// a opcao for invalida, ele atua como metodo recurssivo,
	public static void confirmExecute() {
		System.out.println("\n");
		System.out.println("Você escolheu executar o sorteio.");
		System.out.println("Confirmar? (1) Sim (2) Não");
		option = in.nextInt();
		switch (option) {

			case 1:
				executeDraw(players);
				break;

			case 2:
				listOrExecute();
				break;

			default:
				System.out.println("Opção inválida!");
				confirmExecute();

		}
	}

	// LISTA TODAS AS APOSTAS
	// Printa na tela para cada jogador o seu nome, entra em um for onde busca todas
	// as apostas do tipo Objeto e busca dentro dos objetos
	// os numeros apostados e printa todos eles na tela junto com o betId de cada
	// aposta.
	public static void listGameBets() {
		System.out.println("Listando apostas...\n");
		for (Player player : players) {
			System.out.println("\n");
			System.out.println("------------------------");
			System.out.println("Jogador: " + player.getName());
			for (Bet bet : player.getBets()) {
				System.out.println("Aposta " + bet.getbetId() + ":" + bet.getNumbers());

			}
		}
		listOrExecute();
	}

	// EXECUTAR SORTEIO
	// Gera os numeros vencedores, printa na tela os numeros vencedores e chama o
	// metodo checkWinners.
	public static void executeDraw(List<Player> players) {
		Round round1 = new Round();
		round1.generateWinnerNumbers();
		System.out.println("Números sorteados: " + Round.getWinNumbers() + "\n");
		checkWinners(players);
	}

	// VERIFICA SE HÁ VENCEDORES
	// Verifica se ha vencedores, se houver, chama o metodo endMatch, se nao houver,
	// chama o metodo addOneMoreWinnerNumber. Ele fara isso ate alcançar 26 rodadas
	// se nao achar vencedores, e entao chamara o metodo endMatch.
	public static void checkWinners(List<Player> players) {

		boolean hasWinner = false;

		for (Player player : players) {
			if (player != null && !player.getIsWinner()) {
				for (Bet bet : player.getBets()) {
					if (bet.getNumbers().equals(Round.winNumbers)) {
						player.setIsWinner(true);
						hasWinner = true;
					}
				}
			}
		}
		if (!hasWinner && Round.getRoundId() < 26) {
			addOneMoreWinnerNumber();
		} else {
			endMatch(players);
		}
	}

	// CALCULA O VALOR DO PREMIO
	// Calcula o valor do premio, se houver vencedores, printa na tela o valor do
	// premio,
	// se nao houver, notifica na tela que nenhum vencedor foi encontrado. E a
	// estimativa do premio.
	public static void calculatePrize() {
		double totalPrize = (Bet.getValue() + Bet.getQuantity()) / 2;

		if (countBetWinners(getPlayers()) > 0) {
			System.out.println("O valor do prêmio é: " + totalPrize);
		} else {
			System.out.println("Nenhum vencedor encontrado. O ganhador teria sido premiado com: R$" + totalPrize);
		}
	}

	// ADICIONA MAIS UM NÚMERO VENCEDOR
	// Se nao houver vencedores, este metodo adiciona mais um numero para a lista de
	// numeros vencedores e chama o metodo checkWinners
	// para verificar se ha vencedores.
	public static void addOneMoreWinnerNumber() {
		Random random = new Random();
		Round.idIncrement();
		Round.winNumbers.add(random.nextInt(50) + 1);

		checkWinners(getPlayers());

	}

	// FIM DO JOGO
	// Printa na tela os numeros sorteados, o total de rodadas, a quantidade de
	// apostas vencedoras e se houver vencedores, printa na tela
	// o nome do vencedor e sua aposta. Se nao houver vencedores, printa na tela
	// "Nenhum vencedor encontrado."
	// Chama o metodo showAllNumbersAndFrequency e o metodo calculatePrize.

	public static void endMatch(List<Player> players) {
		System.out.println("-------------------FIM DO JOGO-------------------");
		System.out.println("Números sorteados: " + Round.getWinNumbers());
		System.out.println("Total de rodadas: " + Round.getRoundId());
		System.out.println("Quantidade de apostas vencedoras: " + countBetWinners(players) + "\n");

		if (countBetWinners(players) > 0) {
			System.out.println("Aposta(s) vencedora(s): ");
			getWinnerPlayersAndBet();
		} else {
			System.out.println("Nenhum vencedor encontrado." + "\n");
		}
		showAllNumbersAndFrequency();
		calculatePrize();
	}

	// CONTADOR DE APOSTAS VENCEDORAS
	// Conta a quantidade de apostas vencedoras.
	public static int countBetWinners(List<Player> players) {
		int count = 0;
		for (Player player : players) {
			if (player.getIsWinner() == true) {
				for (Bet bet : player.getBets()) {
					if (bet.getNumbers().equals(Round.getWinNumbers())) {
						count++;
					}
				}
			}
		}
		return count;
	}

	// MOSTRE TODOS OS NUMEROS SORTEADOS
	// e suas frequencias.
	public static void showAllNumbersAndFrequency() {
		System.out.println("Números apostados e suas frequências: ");
		Map<Integer, Integer> numbersFrequency = new HashMap<>();
		for (Player player : players) {
			for (Bet bet : player.getBets()) {
				for (int number : bet.getNumbers()) {
					if (numbersFrequency.containsKey(number)) {
						numbersFrequency.put(number, numbersFrequency.get(number) + 1);
					} else {
						numbersFrequency.put(number, 1);
					}
				}
			}
		}

	}

	// MOSTRA NOME DO VENCEDOR E SUA APOSTA
	// ou informa que nenhum vencedor foi encontrado.
	public static void getWinnerPlayersAndBet() {
		// Round round = new Round();
		for (Player player : players) {
			if (player.getIsWinner() == true) {
				for (Bet bet : player.getBets()) {
					if (bet.getNumbers().equals(Round.getWinNumbers())) {
						System.out.println("\nPlayer: " + player.getName() + "Aposta: " + player.getBets());
					}
				}
			} else {
				System.out.println("Nenhum vencedor encontrado.");
			}
		}

	}

	// RETORNA A LISTA DE VENCEDORES
	// ou null se nenhum vencedor foi encontrado.
	public List<Player> getWinners(List<Player> players) {

		for (Player player : players) {
			List<Player> winners = new ArrayList<Player>();
			if (player.getIsWinner() == true) {
				winners.add(player);
			}
			return winners;
		}

		return null;
	}

	// RETORNA A LISTA DE JOGADORES
	public static List<Player> getPlayers() {
		return players;
	}

}
