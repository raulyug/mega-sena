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

	public static void main(String[] args) {

		System.out.println("Bem vindo ao jogo da fortuna!");
		System.out.println("(1) Começar Novo Jogo");
		System.out.println("(2) Listar Apostas");

		option = in.nextInt();
		switch (option) {

			case 1:
				Game.startGame();
				break;

			case 2:
				listGameBets();
		}
	}

	// INICIA O JOGO
	public static void startGame() {
		System.out.println("$-------------JOGO DA FORTUNA-------------$\n");
		System.out.println("Iniciando novo jogo...");

		insertNewPlayer();
	}

	// ADICIONA NOVO JOGADOR
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
					break;
				}
			}
			if (!isDuplicate) {
				break;
			}
		}
		players.add(new Player(cpf, name));
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
	public static void addBet(String cpf) {
		Set<Bet> playerBets = playerBetsMap.getOrDefault(cpf, new TreeSet<>());
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
			playerBets.add(new Bet(bet));
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

			playerBets.add(new Bet(manualBet));
			System.out.println("Seus números são: ");
			System.out.println(manualBet.toString());

		} else {
			System.out.println("Escolha inválida. Por favor, escolha 1 ou 2.");
			addBet(cpf);
		}
		playerBetsMap.put(cpf, playerBets);

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
	public static void listGameBets() {
		System.out.println("Listando apostas...\n");
		for (String cpf : playerBetsMap.keySet()) {
			for (Player player : players) {
				if (player.getCpf().equals(cpf)) {
					System.out.println("|---------------------------|");
					System.out.println(" Jogador: " + player.getName());
					System.out.println(" CPF: " + cpf);
					Set<Bet> bets = playerBetsMap.get(cpf);
					for (Bet bet : bets) {
						System.out.println(" Aposta: " + bet.getNumbers());

					}
				}
			}
			listOrExecute();
		}
	}

	// EXECUTAR SORTEIO
	public static void executeDraw(List<Player> players) {
		Round round1 = new Round();
		round1.generateWinnerNumbers();
		System.out.println("Números sorteados: " + Round.getWinNumbers() + "\n");
		checkWinners(players);
	}

	// VERIFICA SE HÁ VENCEDORES
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
	public static void calculatePrize(){
		double totalPrize = (Bet.getValue() + Bet.getQuantity())/2;

		if(countBetWinners(getPlayers()) > 0){
			System.out.println("O valor do prêmio é: " + totalPrize);
		}
		else{
			System.out.println("Nenhum vencedor encontrado. O ganhador teria sido premiado com: R$" + totalPrize);
		}
	}

	// ADICIONA MAIS UM NÚMERO VENCEDOR
	public static void addOneMoreWinnerNumber() {
		Random random = new Random();
		Round.idIncrement();
		Round.winNumbers.add(random.nextInt(50) + 1);

		checkWinners(getPlayers());

	}

	// FIM DO JOGO
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
	public static void showAllNumbersAndFrequency() {
		Map<Integer, Integer> numberFrequency = new HashMap<>(); // armazena a frequência de cada número.
		for (Set<Bet> bets : playerBetsMap.values()) { // cada valor é um CONJUNTO de apostas.
			for (Bet bet : bets) { // iterando sobre CADA aposta.
				for (int number : bet.getNumbers()) { // iterando sobre cada NÚMERO na aposta.
					numberFrequency.put(number, numberFrequency.getOrDefault(number, 0) + 1); // Contando frequencia de
																								// cada numero
				}
			}
		}

		System.out.println("Número - Frequência");
		for (Map.Entry<Integer, Integer> par : numberFrequency.entrySet()) { // Loop que itera sobre cada par do mapa
																				// numberFrequency

			System.out.println(par.getKey() + " - " + par.getValue()); //

		}

	}

	// MOSTRE NOME DO VENCEDOR E SUA APOSTA
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
