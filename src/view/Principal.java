package view;

import java.util.Scanner;

import controller.FicharioAnimal;
import controller.FicharioPessoa;
import controller.FicharioProduto;
import controller.FicharioServico;
import controller.VendaController;

public class Principal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		FicharioAnimal ficharioAnimal = new FicharioAnimal();
		FicharioPessoa ficharioPessoa = new FicharioPessoa();
		
		FicharioServico ficharioServico = new FicharioServico();
		FicharioProduto ficharioProduto = new FicharioProduto();

		VendaController vendaController = new VendaController();

		int opcao, opcao1, opcao2, opcao3, opcao4, opcao5, opcao6;
		do {
			System.out.println("-==[Menu Principal]==-");
			System.out.println("Bem-vindo ao PetShop Cãozinho Feliz!!");
			System.out.println("Escolha a opção que deseja:");
			System.out.println("[1] - Gerenciar Clientes.");
			System.out.println("[2] - Gerenciar Funcionários.");
			System.out.println("[3] - Gerenciar Animais.");
			System.out.println("[4] - Gerenciar Serviços.");
			System.out.println("[5] - Gerenciar Produtos");
			System.out.println("[6] - Gerenciar Vendas.");
			System.out.println("[0] - Sair.");

			opcao = Integer.parseInt(sc.nextLine());

			switch (opcao) {
			case 1:
				do {
					System.out.println("-==[Gerenciamento de Clientes]==-");
					System.out.println("[1] - Cadastrar.");
					System.out.println("[2] - Alterar.");
					System.out.println("[3] - Excluir.");
					System.out.println("[4] - Consultar.");
					System.out.println("[5] - Exibir Relatório.");
					System.out.println("[0] - Voltar.");

					opcao1 = Integer.parseInt(sc.nextLine());

					switch (opcao1) {
					case 1:
						ficharioPessoa.cadastro();;
						break;
					case 2:
						ficharioPessoa.alteracao();
						break;
					case 3:
						ficharioPessoa.exclusao();
						break;
					case 4:
						ficharioPessoa.consultar();
						break;
					case 5:
						ficharioPessoa.relatorio();
						break;

					default:
						break;
					}
				} while (opcao1 != 0);
				break;
			case 2:
				do {
					System.out.println("-==[Gerenciamento de Funcionários]==-");
					System.out.println("[1] - Cadastrar.");
					System.out.println("[2] - Alterar.");
					System.out.println("[3] - Excluir.");
					System.out.println("[4] - Consultar.");
					System.out.println("[5] - Exibir Relatório.");
					System.out.println("[0] - Voltar.");

					opcao2 = Integer.parseInt(sc.nextLine());

					switch (opcao2) {
					case 1:
						ficharioPessoa.cadastro();;
						break;
					case 2:
						ficharioPessoa.alteracao();
						break;
					case 3:
						ficharioPessoa.exclusao();
						break;
					case 4:
						ficharioPessoa.consultar();
						break;
					case 5:
						ficharioPessoa.relatorio();
						break;

					default:
						break;
					}
				} while (opcao2 != 0);
				break;
			case 3:
				do {
					System.out.println("-==[Gerenciamento de Animais]==-");
					System.out.println("[1] - Cadastrar.");
					System.out.println("[2] - Alterar.");
					System.out.println("[3] - Excluir.");
					System.out.println("[4] - Consultar.");
					System.out.println("[5] - Exibir Relatório.");
					System.out.println("[0] - Voltar.");

					opcao3 = Integer.parseInt(sc.nextLine());

					switch (opcao3) {
					case 1:
						ficharioAnimal.cadastrarAnimal();
						break;
					case 2:
						ficharioAnimal.alterarAnimal();
						break;
					case 3:
						ficharioAnimal.excluirAnimal();
						break;
					case 4:
						ficharioAnimal.consultarAnimal();
						break;
					case 5:
						ficharioAnimal.relatorioDeAnimais();
						break;
						
					default:
						break;
					}
				} while (opcao3 != 0);
				break;
			case 4:
				do {
					System.out.println("-==[Gerenciamento de Serviços]==-");
					System.out.println("[1] - Cadastrar.");
					System.out.println("[2] - Alterar.");
					System.out.println("[3] - Excluir.");
					System.out.println("[4] - Consultar.");
					System.out.println("[5] - Exibir Relatório.");
					System.out.println("[0] - Voltar.");

					opcao4 = Integer.parseInt(sc.nextLine());

					switch (opcao4) {
					case 1:
						ficharioServico.cadastrarServico();
						break;
					case 2:
						ficharioServico.alteracaoServico();
						break;
					case 3:
						ficharioServico.excluirServico();
						break;
					case 4:
						ficharioServico.consultarServico();
						break;
					case 5:
						ficharioServico.relatorioServico();
						break;

					default:
						break;
					}
				} while (opcao4 != 0);
				break;
			case 5:
				do {
					System.out.println("-==[Gerenciamento de Produtos]==-");
					System.out.println("[1] - Cadastrar.");
					System.out.println("[2] - Alterar.");
					System.out.println("[3] - Excluir.");
					System.out.println("[4] - Consultar.");
					System.out.println("[5] - Exibir Relatório.");
					System.out.println("[0] - Voltar.");

					opcao5 = Integer.parseInt(sc.nextLine());

					switch (opcao5) {
					case 1:
						ficharioProduto.cadastrarProduto();
						break;
					case 2:
						ficharioProduto.alterarProduto();
						break;
					case 3:
						ficharioProduto.excluirProduto();
						break;
					case 4:
						ficharioProduto.consultarProduto();
						break;
					case 5:
						ficharioProduto.relatorioDeProdutos();
						break;
						
					default:
						break;
					}
				} while (opcao5 != 0);
				break;
			case 6:
				do {
					System.out.println("-==[Gerenciamento de Vendas]==-");
					System.out.println("[1] - Cadastrar.");
					System.out.println("[2] - Alterar.");
					System.out.println("[3] - Excluir.");
					System.out.println("[4] - Consultar.");
					System.out.println("[5] - Exibir Relatório.");
					System.out.println("[0] - Voltar.");

					opcao6 = Integer.parseInt(sc.nextLine());

					switch (opcao6) {
					case 1:
						vendaController.cadastrarVenda();
						break;
					case 2:
						vendaController.alterarVenda();
						break;
					case 3:
						vendaController.excluirVenda();
						break;
					case 4:
						vendaController.buscarVenda();
						break;
					case 5:
						vendaController.listarVenda();
						break;

					default:
						break;
					}
				} while (opcao6 != 0);
				break;
			default:

				break;
			}

		} while (opcao != 0);
		sc.close();
	}

}
