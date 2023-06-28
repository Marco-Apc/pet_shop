package controller;

import java.util.Iterator;
import java.util.Scanner;

import DAO.AnimalDAO;
import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.ServicoDAO;
import model.Servico;

public class FicharioServico {
	private ClienteDAO clienteDAO;
	private FuncionarioDAO funcionarioDAO;
	private AnimalDAO animalDAO;
	private ServicoDAO servicoDAO;

	private Scanner sc;

	public FicharioServico() {
		sc = new Scanner(System.in);
		clienteDAO = new ClienteDAO();
		funcionarioDAO = new FuncionarioDAO();
		animalDAO = new AnimalDAO();
		servicoDAO = new ServicoDAO();
	}

	public void cadastrarServico() {
		if (verifPessoas() == 1) {

			Servico servico = new Servico();
			int verif = 0, verif2 = 0, verif3 = 0;

			System.out.println("-+=[Cadastro de Serviços]=+-");
			System.out.println("Digite a descrição do serviço: ");
			servico.setDescricao(sc.nextLine());

			System.out.println("Digite o preço do serviço: ");
			servico.setPreco(Float.parseFloat(sc.nextLine()));

			do {
				if (!clienteDAO.isEmpty() && !funcionarioDAO.isEmpty()) {
					System.out.println("Digite o CPF do cliente que deseja vincular ao serviço: ");
					String cpf = sc.nextLine();
					servico.setCliente(clienteDAO.buscarPorId(cpf));

					if (!animalDAO.isEmpty(cpf)) {

						do {
							System.out.println("Digite o nome do animal que deseja vincular ao serviço: ");
							String nomeAnimal = sc.nextLine();

							if (animalDAO.existeAnimal(nomeAnimal, cpf)) {

								servico.setAnimal(animalDAO.buscarPorId(nomeAnimal, cpf));
								verif2 = 1;
								do {
									System.out.println(
											"Digite o CPF do funcionário que deseja vincular ao serviço: ");
									String cpfFuncionario = sc.nextLine();

									if (funcionarioDAO.existeFuncionario(cpfFuncionario)) {
										servico.setFuncionario(funcionarioDAO.buscarPorId(cpfFuncionario));
										verif3 = 1;
										verif = 1;
										servicoDAO.salvar(servico);
										System.out.println("Serviço cadastrado com sucesso!");
									} else
										System.out.println("Posição inválida!");

								} while (verif3 != 1);

							} else
								System.out.println("Posição inválida!");

						} while (verif2 != 1);

					} else {

						System.out.println(
								"O cliente não possui um animal para vincular ao serviço! Deseja cancelar o cadastro do Serviço? (1 - SIM / 2 - NÃO)");
						int resp = Integer.parseInt(sc.nextLine());
						if (resp != 1)
							verif = 1;

					}
				} else
					System.out.println("Posição inválida!");

			} while (verif != 1);

		} else if (verifPessoas() == 2)
			System.out.println("Nenhum funcionário cadastrado!");
		else if (verifPessoas() == 3)
			System.out.println("Nenhum cliente cadastrado!");
		else
			System.out.println("Nenhuma pessoa cadastrada!");

	}

	public void alteracaoServico() {
		if (!servicoDAO.isEmpty()) {

			String cpf_cliente, cpf_funcionario, nomeAnimal;

			System.out.println("-+=[Alteração de Serviços]=+-");
			System.out.println("Digite o CPF do cliente do serviço que deseja alterar: ");
			cpf_cliente = sc.nextLine();

			System.out.println("Digite o CPF do funcionario: ");
			cpf_funcionario = sc.nextLine();

			System.out.println("Digite o nome do animal: ");
			nomeAnimal = sc.nextLine();

			if (!clienteDAO.isEmpty() && !funcionarioDAO.isEmpty() && !animalDAO.isEmpty(cpf_cliente)) {

				getDados(servicoDAO.buscarPorId(cpf_cliente, nomeAnimal, cpf_funcionario));
				System.out.println("Deseja alterar os dados dessa posição? (1 - Sim / 2 - Não)");
				int resposta = Integer.parseInt(sc.nextLine());

				if (resposta == 1) {
					Servico servico = new Servico();
					servico = servicoDAO.buscarPorId(cpf_cliente, nomeAnimal, cpf_funcionario);

					System.out.println("Digite a descrição do serviço: ");
					servico.setDescricao(sc.nextLine());

					System.out.println("Digite o preço do serviço: ");
					servico.setPreco(Float.parseFloat(sc.nextLine()));

					servicoDAO.atualizar(servico);

					System.out.println("Serviço alterado com sucesso!");
				} else
					System.out.println("Operação cancelada!");

			} else
				System.out.println("Posição inválida!");

		} else
			System.out.println("Nenhum serviço cadastrado!");

	}

	public void excluirServico() {
		if (!servicoDAO.isEmpty()) {

			System.out.println("-+=[Exclusão de Serviços]=+-");
			System.out.println("Digite a posição do serviço que deseja excluir: ");
			String cpf_cliente = sc.nextLine();

			System.out.println("Digite o CPF do funcionario: ");
			String cpf_funcionario = sc.nextLine();

			System.out.println("Digite o nome do animal: ");
			String nomeAnimal = sc.nextLine();
			if (!clienteDAO.isEmpty() && !funcionarioDAO.isEmpty() && !animalDAO.isEmpty(cpf_cliente)) {
				getDados(servicoDAO.buscarPorId(cpf_cliente, nomeAnimal, cpf_funcionario));

				System.out.println("Deseja excluir os dados dessa posição? (1 - Sim / 2 - Não)");
				int resposta = Integer.parseInt(sc.nextLine());

				if (resposta == 1) {
					Servico servico = new Servico();
					servico = servicoDAO.buscarPorId(cpf_cliente, nomeAnimal, cpf_funcionario);
					servicoDAO.remover(servico);
					System.out.println("Serviço excluído com sucesso!");
				} else
					System.out.println("Operação cancelada!");
			} else
				System.out.println("Posição inválida!");
		} else {
			System.out.println("Não há serviços cadastrados!");
		}
	}

	public void consultarServico() {
		if (!servicoDAO.isEmpty()) {
			
			System.out.println("-+=[Consulta de Serviços]=+-");
			System.out.println("Digite a posição do serviço que deseja excluir: ");
			String cpf_cliente = sc.nextLine();

			System.out.println("Digite o CPF do funcionario: ");
			String cpf_funcionario = sc.nextLine();

			System.out.println("Digite o nome do animal: ");
			String nomeAnimal = sc.nextLine();

			if (!clienteDAO.isEmpty() && funcionarioDAO.isEmpty() && animalDAO.isEmpty(cpf_cliente)) {
				getDados(servicoDAO.buscarPorId(cpf_cliente, nomeAnimal, cpf_funcionario));
			} else
				System.out.println("Posição inválida");
		} else {
			System.out.println("Não há serviços cadastrados!");
		}
	}

	public void relatorioServico() {
		// usando foreach para percorrer a lista
		Iterator<Servico> it = servicoDAO.listar().iterator();
		while (it.hasNext()) {
			Servico servico = (Servico) it.next();
			getDados(servico);
			System.out.println("--------------------------------------------------");
		}
	}

	private void getDados(Servico servico) {

		System.out.println("Descrição:        " + servico.getDescricao());
		System.out.println("Preço:            " + servico.getPreco());
		System.out.println("Cliente:          " + servico.getCliente().getNome());
		System.out.println("Animal:           " + servico.getAnimal().getNome());
		System.out.println("Funcionário:      " + servico.getFuncionario().getNome());

	}

	private int verifPessoas() {
		if (clienteDAO.isEmpty() && funcionarioDAO.isEmpty())
			return 4;
		else if (clienteDAO.isEmpty())
			return 3;
		else if (funcionarioDAO.isEmpty())
			return 2;
		else
			return 1;
		// boolean verifCli = false, verifFunc = false;
		// for(int i=0;i<pessoas.size();i++) {
		// if((pessoas.get(i) instanceof Cliente)&&(pessoas.get(i)!=null)) {
		// verifCli=true;
		// }else if(pessoas.get(i)!=null)
		// verifFunc=true;
		// }

		// if((verifCli==true)&&(verifFunc==true))
		// return 1;
		// else if((verifCli==true)&&(verifFunc==false))
		// return 2;
		// else if((verifCli==false)&&(verifFunc==true))
		// return 3;
		// else
		// return 4;
	}
}
