import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Game {
	private static List<Player> players = new ArrayList<Player>();;
	private static Scanner in = new Scanner(System.in);
	private static Map<String, Set<Bet>> playerBetsMap = new HashMap<>();
	private static Set<Integer> bet;

	public static void main(String[] args) {
		
		
		startGame();
	}

	// INICIA O JOGO
	public static void startGame() {
		System.out.println("$-------------JOGO DA MEGA SENA-------------$");
		Round round1 = new Round();

		System.out.println("\n");
		System.out.println("Bem vindo ao jogo da fortuna!");
		System.out.println("Digite 1 para começar um novo jogo ou 2 para sair: ");
		int option = in.nextInt();

		if (option == 1) {
			System.out.println("Iniciando novo jogo!\n");
			System.out.println("Você ja possui cadastro? (1) Sim (2) Não");
			int option2 = in.nextInt();

			if (option2 == 1) {
				System.out.println("Erro! Apenas um cadastro por edicao permitido, aguarde e boa sorte!");

			} else if (option2 == 2) {
				insertNewPlayer();

			} else {
				System.out.println("Opção inválida!");

			}

		} else if (option == 2) {
			System.out.println("Fechando Aplicativo!");
			System.exit(0);

		} else {
			System.out.println("Opção inválida!");
		}
		in.close();
	}

	// ADICIONA NOVO JOGADOR
	public static void insertNewPlayer() {
		// Limpeza do buffer
		in.nextLine();
		
		System.out.println("Digite o nome do jogador: \n");
		String name = in.nextLine();

		System.out.println("Digite o cpf do jogador: \n");
		String cpf = in.nextLine();

		Player player = new Player(name, cpf);
		players.add(player);

		System.out.println("Jogador cadastrado com sucesso!");
		System.out.println("Agora, vamos fazer a(s) aposta(s)!\n");
		addBet(cpf);
		
		
		
		System.out.println("Voce deseja cadastrar mais jogadores? (1) Sim (2) Não");
		int opcao = in.nextInt();
		if (opcao == 1) {
			insertNewPlayer();
		} else if (opcao == 2) {
			System.out.println("Obrigado por se cadastrar!");
		} else {
			System.out.println("Opção inválida!");
		}
	}

	// ADICIONA APOSTA
	public static void addBet(String cpf) {
		Set<Bet> playerBets = playerBetsMap.getOrDefault(cpf, new TreeSet<>());

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
			Set<Integer> manualBet = new TreeSet<>();
			for (int i = 0; i < 5; i++) {
				if (choice > 50) {
					System.out.println("Número inválido! Insira um número de 1 a 50: ");
					i--;
				} else {
					choice = in.nextInt();
					manualBet.add(choice);
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

	public Player getWinners() {
		return null;
	}

	public Player getPlayers() {
		return null;
	}

}
