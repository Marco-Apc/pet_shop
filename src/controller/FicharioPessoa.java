package controller;

import java.util.Scanner;

import DAO.ClienteDAO;
import DAO.FuncionarioDAO;

import model.Cliente;
import model.Funcionario;
import model.Pessoa;

public class FicharioPessoa {
	private ClienteDAO clienteDAO;
	private FuncionarioDAO funcionarioDAO;
	private Scanner sc;

	public FicharioPessoa() {
		sc = new Scanner(System.in);
		clienteDAO = new ClienteDAO();
		funcionarioDAO = new FuncionarioDAO();
	}

	public void cadastro() {

		int op = 0;

		do {
			System.out.println("-+=[Cadastro de Pessoas]=+-");
			System.out.println("[1] - Funcionário");
			System.out.println("[2] - Cliente");
			System.out.println("[3] - Voltar");
			System.out.println("Digite uma opção:");
			op = sc.nextInt();

			switch (op) {
				case 1:

					Funcionario f = new Funcionario();
					setDados(f);
					funcionarioDAO.salvar(f);
					System.out.println("Funcionário cadastrado com sucesso!");

					break;
				case 2:

					Cliente c = new Cliente();
					setDados(c);
					clienteDAO.salvar(c);
					System.out.println("Cliente cadastrado com sucesso!");

					break;
				case 3:
					break;
				default:
					System.out.println("Digite uma opção válida!");
					break;
			}
		} while (op != 3);

	}

	public void alteracao() {
		if (!clienteDAO.isEmpty() && !funcionarioDAO.isEmpty()) {
			int op = 0;

			do {
				System.out.println("-+=[Alteração de Pessoas]=+-");
				System.out.println("[1] - Funcionário");
				System.out.println("[2] - Cliente");
				System.out.println("[3] - Voltar");
				System.out.println("Digite uma opção:");
				op = sc.nextInt();

				switch (op) {
					case 1:
						System.out.println("-+=[Alteração de Funcionários]=+-");
						System.out.println("Digite o CPF do funcionário a ser alterado: ");
						String cpf = sc.next();

						if (funcionarioDAO.buscarPorId(cpf) != null) {
							Funcionario f = funcionarioDAO.buscarPorId(cpf);

							System.out.println("--<Dados atuais do Funcionário>--");
							getDados(f);

							int resp = 1;
							do {
								System.out.println("\nDeseja alterar esta posição? (1 - Sim / 2 - Não)");
								resp = sc.nextInt();

								switch (resp) {
									case 1:
										setDados(f);
										funcionarioDAO.atualizar(f);
										System.out.println("Alterado com sucesso!");
										break;
									case 2:
										System.out.println("Cancelado!");
										break;
									default:
										System.out.println("Digite uma opção válida!");
										break;
								}
							} while ((resp < 1) && (resp > 2));
						} else
							System.out.println("Não há um funcionário nesta posição!");
						break;
					case 2:

						System.out.println("-+=[Alteração de Clientes]=+-");
						System.out.println("Digite o CPF do clientes a ser alterado: ");
						String cpf2 = sc.next();

						if (clienteDAO.buscarPorId(cpf2) != null) {

							System.out.println("--<Dados atuais do Cliente>--");
							getDados(clienteDAO.buscarPorId(cpf2));
							Cliente c = clienteDAO.buscarPorId(cpf2);

							int resp = 1;
							do {
								System.out.println("\nDeseja alterar esta posição? (1 - Sim / 2 - Não)");
								resp = sc.nextInt();

								switch (resp) {
									case 1:
										setDados(c);
										clienteDAO.atualizar(c);
										System.out.println("Alterado com sucesso!");
										break;
									case 2:
										System.out.println("Cancelado!");
										break;
									default:
										System.out.println("Digite uma opção válida!");
										break;
								}
							} while ((resp < 1) && (resp > 2));
						} else
							System.out.println("Não há um cliente nesta posição!");
						break;
					case 3:
						break;
					default:
						System.out.println("Digite uma opção válida!");
						break;
				}
			} while (op != 3);
		} else
			System.out.println("Nenhuma pessoa cadastrada!");
	}

	public void exclusao() {

		if (!clienteDAO.isEmpty() && !funcionarioDAO.isEmpty()) {
			int op = 0;

			do {
				System.out.println("-+=[Exclusão de Pessoas]=+-");
				System.out.println("[1] - Funcionário");
				System.out.println("[2] - Cliente");
				System.out.println("[3] - Voltar");
				System.out.println("Digite uma opção:");
				op = sc.nextInt();

				switch (op) {
					case 1:
						System.out.println("-+=[Exclusão de Funcionários]=+-");
						System.out.println("Digite o CPF do funcionário a ser excluído: ");
						String cpf = sc.next();

						if (funcionarioDAO.buscarPorId(cpf) != null) {

							System.out.println("--<Dados atuais do Funcionário>--");
							getDados(funcionarioDAO.buscarPorId(cpf));
							Funcionario f = funcionarioDAO.buscarPorId(cpf);

							int resp = 1;
							do {
								System.out.println("\nDeseja excluir esta posição? (1 - Sim / 2 - Não)");
								resp = sc.nextInt();

								switch (resp) {
									case 1:
										funcionarioDAO.remover(f);
										System.out.println("Excluído com sucesso!");
										break;
									case 2:
										System.out.println("Cancelado!");
										break;
									default:
										System.out.println("Digite uma opção válida!");
										break;
								}
							} while ((resp < 1) && (resp > 2));
						} else
							System.out.println("Posição inválida ou vazia!");

						break;
					case 2:
						System.out.println("-+=[Exclusão de Clientes]=+-");
						System.out.println("Digite o CPF do clientes a ser excluído: ");
						String cpf2 = sc.next();

						if (clienteDAO.buscarPorId(cpf2) != null) {

							System.out.println("--<Dados atuais do Cliente>--");
							getDados(clienteDAO.buscarPorId(cpf2));
							Cliente c = clienteDAO.buscarPorId(cpf2);

							int resp = 1;
							do {
								System.out.println("\nDeseja alterar esta posição? (1 - Sim / 2 - Não)");
								resp = sc.nextInt();

								switch (resp) {
									case 1:
										clienteDAO.remover(c);
										System.out.println("Excluído com sucesso!");
										break;
									case 2:
										System.out.println("Cancelado!");
										break;
									default:
										System.out.println("Digite uma opção válida!");
										break;
								}
							} while ((resp < 1) && (resp > 2));
						} else
							System.out.println("Não há um cliente nesta posição!");

						break;
					case 3:
						break;
					default:
						System.out.println("Digite uma opção válida!");
						break;
				}
			} while (op != 3);
		} else
			System.out.println("Nenhuma pessoa cadastrada!");
	}

	public void consultar() {

		if (!clienteDAO.isEmpty() && !funcionarioDAO.isEmpty()) {
			int op = 0;

			do {
				System.out.println("-+=[Consulta de Pessoas]=+-");
				System.out.println("[1] - Funcionário");
				System.out.println("[2] - Cliente");
				System.out.println("[3] - Voltar");
				System.out.println("Digite uma opção:");
				op = sc.nextInt();

				switch (op) {
					case 1:
						System.out.println("-+=[Consulta de Funcionários]=+-");
						System.out.println("Digite o CPF do funcionário a ser consultado: ");
						String cpf = sc.next();

						if (funcionarioDAO.buscarPorId(cpf) != null) {
							System.out.println("--<Dados do Funcionário escolhido>--");
							getDados(funcionarioDAO.buscarPorId(cpf));
						} else
							System.out.println("Não há um funcionário nesta posição!");
						break;
					case 2:
						System.out.println("-+=[Consulta de Clientes]=+-");
						System.out.println("Digite o CPF do cliente a ser consultado: ");
						String cpf2 = sc.next();

						if (clienteDAO.buscarPorId(cpf2) != null) {
							System.out.println("--<Dados do Cliente escolhido>--");
							getDados(clienteDAO.buscarPorId(cpf2));
						} else
							System.out.println("Não há um cliente nesta posição!");

						break;
					case 3:
						break;
					default:
						System.out.println("Digite uma opção válida!");
						break;
				}
			} while (op != 3);
		} else
			System.out.println("Nenhuma pessoa cadastrada!");
	}

	public void relatorio() {

		if (!clienteDAO.isEmpty() && !funcionarioDAO.isEmpty()) {
			int op = 0;

			do {
				System.out.println("-+=[Relatório de Pessoas]=+-");
				System.out.println("[1] - Funcionário");
				System.out.println("[2] - Cliente");
				System.out.println("[3] - Voltar");
				System.out.println("Digite uma opção:");
				op = sc.nextInt();

				switch (op) {
					case 1:

						System.out.println("-+=[Relatório de Funcionários]=+-");
						for (Funcionario f : funcionarioDAO.listar()) {
							getDados(f);
							System.out.println("\n\n");
						}
						break;
					case 2:

						System.out.println("-+=[Relatório de Clientes]=+-");
						for (Cliente c : clienteDAO.listar()) {
							getDados(c);
							System.out.println("\n\n");
						}
						break;
					case 3:
						break;
					default:
						System.out.println("Digite uma opção válida!");
						break;
				}
			} while (op != 3);
		} else
			System.out.println("Nenhuma pessoa cadastrada!");

	}

	private void setDados(Pessoa p) {

		System.out.println("Digite o Nome: ");
		p.setNome(sc.next());

		System.out.println("Digite o CPF: ");
		p.setCpf(sc.next());

		System.out.println("Digite o Telefone: ");
		p.setTelefone(sc.next());

		System.out.println("Digite o endereço: ");
		p.setEndereco(sc.next());

		if (p instanceof Funcionario) {
			System.out.println("Digito o CTPS: ");
			((Funcionario) p).setCtps(sc.next());

			System.out.println("Digite o cargo: ");
			((Funcionario) p).setCargo(sc.next());
		}

	}

	private void getDados(Pessoa p) {

		System.out.println("Nome: " + p.getNome());
		System.out.println("CPF: " + p.getCpf());
		System.out.println("Telefone: " + p.getTelefone());
		System.out.println("Endereço: " + p.getEndereco());

		if (p instanceof Funcionario) {
			System.out.println("CTPS: " + ((Funcionario) p).getCtps());
			System.out.println("Cargo: " + ((Funcionario) p).getCargo());
		}
		// testar se cliente tem animal
	}
}
