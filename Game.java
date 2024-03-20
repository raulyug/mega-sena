import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

		Round round1 = new Round();

		insertNewPlayer();
	}

	// ADICIONA NOVO JOGADOR
	public static void insertNewPlayer() {
		String name = "";
		String cpf = "";
		//LIMPA BUFFER
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

			System.out.println("O que deseja fazer agora?");
			System.out.println("(1) Listar Apostas");
			System.out.print("(2) Finalizar apostas e executar o sorteio\n");

			option = in.nextInt();

			switch (option) {
				case 1:
					listGameBets();
					break;

				case 2:
					// executeDraw();
			}

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
						return;
					}
				}
			}

		}
	}

	public Player getWinners() {
		return null;
	}

	public Player getPlayers() {
		return null;
	}

}
