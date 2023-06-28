package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import model.Cliente;
import model.Funcionario;
import model.Venda;
import model.Pessoa;
import model.Produto;
import model.Servico;

public class FicharioVenda {

	private Scanner sc;

	private ArrayList<Venda> vendas;
	private ArrayList<Servico> servicos;
	private ArrayList<Pessoa> pessoas;
	private ArrayList<Produto> produtos;
	private ArrayList<Servico> servicosVenda;
	private ArrayList<Produto> produtosVenda;
	
	private DateTimeFormatter formatadorBarra;
	private DateTimeFormatter formatadorHora;

	public FicharioVenda(ArrayList<Venda> vendas, ArrayList<Servico> servicos, ArrayList<Pessoa> pessoas, ArrayList<Produto> produtos) {
		this.vendas = vendas;
		this.servicos = servicos;
		this.pessoas=pessoas;
		this.produtos = produtos;

		sc = new Scanner(System.in);
		servicosVenda = new ArrayList<>();
		produtosVenda = new ArrayList<>();
		formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		formatadorHora = DateTimeFormatter.ofPattern("HH:mm:ss");
	}

	public void cadastrarVenda() {
		if (verifPessoas()==1){
		if ((!servicos.isEmpty()) || (!produtos.isEmpty())) {
			int verif1=0,verif2=0;
			
			Venda venda = new Venda();
			
			do {
				System.out.println("Qual a posição do cliente?");
				int posCli = Integer.parseInt(sc.nextLine());
				
				if((posCli>=0)&&(posCli<pessoas.size())) {
					if(pessoas.get(posCli) instanceof Cliente) {
						venda.setCliente((Cliente)pessoas.get(posCli));
						
						do {
							System.out.println("Qual a posição do Funcionário?");
							int posFunc = Integer.parseInt(sc.nextLine());
							
							if((posFunc>=0)&&(posFunc<pessoas.size())) {
								if(pessoas.get(posFunc) instanceof Funcionario) {
									venda.setFuncionario((Funcionario)pessoas.get(posFunc));
									verif2=1;
									
									if (!servicos.isEmpty()) {
										
										System.out.println("Deseja adicionar um serviço? (1 - Sim / 2 - Não)");
										int resp = Integer.parseInt(sc.nextLine());
										
										while (resp == 1) {
											System.out.println("Qual a posição do serviço?");
											int posS = Integer.parseInt(sc.nextLine());

											if ((posS >= 0) && (posS < servicos.size())) {
												System.out.println("Serviço adicionado com sucesso!");
												
												servicosVenda.add(servicos.get(posS));
												venda.setServicos(servicosVenda);
												
												System.out.println("Deseja adicionar outro serviço? (1 - Sim / 2 - Não)");
												resp = Integer.parseInt(sc.nextLine());

											} else {
												System.out.println("Posição vazia ou inválida!");
												System.out.println("Deseja tentar novamente? (1 - Sim / 2 - Não)");
												resp = Integer.parseInt(sc.nextLine());
											}

										}
									} else
										System.out.println(
												"Nenhum serviço cadastrado! Cadastre um serviço para adicioná-lo aqui!");

									if (!produtos.isEmpty()) {
										System.out.println("Deseja adicionar um produto?(1 - Sim / 2 - Não)");
										int resp2 = Integer.parseInt(sc.nextLine());
										while (resp2 == 1) {
											System.out.println("Qual a posição do produto?");
											int posP = Integer.parseInt(sc.nextLine());

											if ((posP >= 0) && (posP < produtos.size())) {
												System.out.println("Produto adicionado com sucesso!");

												produtosVenda.add(produtos.get(posP));
												venda.setProdutos(produtosVenda);

												System.out.println("Deseja adicionar outro produto? (1 - Sim / 2 - Não)");
												resp2 = Integer.parseInt(sc.nextLine());
											} else {
												System.out.println("Posição vazia ou inválida!");
												System.out.println("Deseja tentar novamente? (1 - Sim / 2 - Não)");
												resp2 = Integer.parseInt(sc.nextLine());
											}
										}
									}else
										System.out.println("Nenhum produto cadastrado! Cadastre um produto para adicioná-lo aqui!");
							
										if ((!venda.getProdutos().isEmpty()) || (!venda.getServicos().isEmpty())) {
											
											venda.setData(LocalDate.now());
											venda.setHora(LocalTime.now());
											vendas.add(venda);
											verif1=1;
											System.out.println("Venda criada com Sucesso!");
										} else{
											System.out.println("Não foi possível gerar uma nova Venda! A venda precisa possuir no mínimo um produto ou serviço!");
											verif1=1;
										}
									
								}else
									System.out.println("Não há um funcionário nesta posição!");
							}else
								System.out.println("Posição inválida!");
							
						}while(verif2!=1);
						
					}else
						System.out.println("Não há um cliente nesta posição!");
				}else
					System.out.println("Posição inválida!");
			}while(verif1!=1);
					
				
		} else
			System.out.println("Para gerar uma venda, é preciso de pelo menos um serviço ou produto cadastrado!");

		}else if(verifPessoas()==2)
			System.out.println("Nenhum funcionário cadastrado!");
		else if(verifPessoas()==3)
			System.out.println("Nenhum cliente cadastrado!");
		else
			System.out.println("Nenhuma pessoa cadastrada!");
	}

	public void alterarVenda() {
		if(!vendas.isEmpty()){
			if((!produtos.isEmpty())||(!servicos.isEmpty())){
				Venda venda;
				System.out.println("Qual posição da venda?");
				int posVenda = Integer.parseInt(sc.nextLine());
				
				getDados(vendas.get(posVenda), posVenda);
				
				System.out.println("Deseja alterar os dados dessa posição? (1 - Sim / 2 - Não)");
				int resposta = Integer.parseInt(sc.nextLine());
				if(resposta==1) {
					if ((posVenda >= 0) && (posVenda < vendas.size())) {
						venda = vendas.get(posVenda);
						
						if (!produtos.isEmpty()) {
							System.out.println("Deseja alterar um produto?(1 - Sim / 2 - Não)");
							int resp2 = Integer.parseInt(sc.nextLine());
							while (resp2 == 1) {
								System.out.println("Qual a posição do produto?");
								int posP = Integer.parseInt(sc.nextLine());

								if ((posP >= 0) && (posP < produtos.size())) {
									System.out.println("Produto alterado com sucesso!");

									produtosVenda.add(produtos.get(posP));
									venda.setProdutos(produtosVenda);

									System.out.println("Deseja alterar outro produto? (1 - Sim / 2 - Não)");
									resp2 = Integer.parseInt(sc.nextLine());
								} else {
									System.out.println("Posição vazia ou inválida!");
									System.out.println("Deseja tentar novamente? (1 - Sim / 2 - Não)");
									resp2 = Integer.parseInt(sc.nextLine());
								}
							}
						}
						
						if (!servicos.isEmpty()) {
							System.out.println("Deseja alterar um serviço?(1 - Sim / 2 - Não)");
							int resp = Integer.parseInt(sc.nextLine());
							while (resp == 1) {
								System.out.println("Qual a posição do serviço?");
								int posS = Integer.parseInt(sc.nextLine());

								if ((posS >= 0) && (posS < servicos.size())) {
									System.out.println("Serviço alterado com sucesso!");

									servicosVenda.add(servicos.get(posS));
									venda.setServicos(servicosVenda);

									System.out
											.println("Deseja alterar outro serviço? (1 - Sim / 2 - Não)");
									resp = Integer.parseInt(sc.nextLine());

								} else {
									System.out.println("Posição vazia ou inválida!");
									System.out.println("Deseja tentar novamente? (1 - Sim / 2 - Não)");
									resp = Integer.parseInt(sc.nextLine());
								}

							}
						} else
							System.out.println("Nenhum serviço cadastrado! Cadastre um serviço para adicioná-lo aqui!");
					}else
						System.out.println("Posição da venda inválida!");
				}else
					System.out.println("Alteração cancelada");
			}else
				System.out.println("Para gerar uma venda, é preciso de pelo menos um serviço ou produto cadastrado!");
		}else
			System.out.println("Nenhuma venda cadastrada!");
	}

	public void excluirVenda() {
		if (!vendas.isEmpty()) {
			System.out.println("Qual a posição da venda?");
			int posVenda = Integer.parseInt(sc.nextLine());

			if ((posVenda >= 0) && (posVenda < vendas.size())) {
				getDados(vendas.get(posVenda), posVenda);

				System.out.println("Deseja realmente excluir esta venda? (1 - Sim / 2 - Não)");
				int resposta = Integer.parseInt(sc.nextLine());

				if (resposta == 1) {
					vendas.remove(posVenda);
					System.out.println("Venda excluída com sucesso!");
				} else
					System.out.println("Venda não excluída!");
			} else
				System.out.println("Nenhuma venda cadastrada!");
		}
	}

	public void consultarVenda() {
		if (!vendas.isEmpty()) {
			System.out.println("Qual a posição da venda?");
			int posVenda = Integer.parseInt(sc.nextLine());

			if ((posVenda >= 0) && (posVenda < vendas.size())) {
				getDados(vendas.get(posVenda), posVenda);
			} else
				System.out.println("Nenhuma venda cadastrada!");
		}
	}

	public void relatorioVenda() {
		if (!vendas.isEmpty()) {
			Iterator<Venda> it = vendas.iterator();

			while (it.hasNext()) {
				Venda venda = it.next();
				System.out.println("==================================================");
				getDados(venda, vendas.indexOf(venda));
			}
		} else
			System.out.println("Nenhuma venda cadastrada!");
	}

	private float calculaValorTotal(Venda venda) {
		float valorTotal = 0;
		Iterator<Servico> it = venda.getServicos().iterator();
		while (it.hasNext()) {
			Servico servico = it.next();
			valorTotal += servico.getPreco();
		}
		Iterator<Produto> it2 = venda.getProdutos().iterator();
		while (it2.hasNext()) {
			Produto produto = it2.next();
			valorTotal += produto.getPreco();
		}
		return valorTotal;
	}

	private void getDados(Venda venda, int pos) {
		System.out.println("Cliente:            " + venda.getCliente().getNome());
		System.out.println("Funcionário:        " + venda.getFuncionario().getNome());
		
		if(!venda.getServicos().isEmpty()) {
			System.out.println("Serviços:         ");
			for (Servico servico : venda.getServicos()) {
				System.out.println("Descrição:          " + servico.getDescricao());
				System.out.println("Preço:              " + servico.getPreco());
				System.out.println("Cliente:            " + servico.getCliente().getNome());
				System.out.println("Animal:             " + servico.getAnimal().getNome());
				System.out.println("Funcionário:        " + servico.getFuncionario().getNome());
			}	
		}
		
		if(!venda.getServicos().isEmpty()) {
			System.out.println("Produtos:         ");
			Iterator<Produto> it2 = venda.getProdutos().iterator();
			while (it2.hasNext()) {
				Produto produto = it2.next();
				System.out.println("Código:             " + produto.getCodigo());
				System.out.println("Descrição:          " + produto.getDescricao());
				System.out.println("Preço:              " + produto.getPreco());
			}
		}
		
		venda.setValorTotal(calculaValorTotal(venda));
		System.out.println("Valor total:        " + venda.getValorTotal());
		System.out.println("Data:               " + venda.getData().format(formatadorBarra));
		System.out.println("Hora:               " + venda.getHora().format(formatadorHora));
	}
	
	private int verifPessoas() {
		boolean verifCli = false, verifFunc = false;
		for(int i=0;i<pessoas.size();i++) {
			if((pessoas.get(i) instanceof Cliente)&&(pessoas.get(i)!=null)) {
				verifCli=true;
			}else if(pessoas.get(i)!=null)
				verifFunc=true;
		}
		
		if((verifCli==true)&&(verifFunc==true)) 
			return 1;
		else if((verifCli==true)&&(verifFunc==false))
			return 2;
		else if((verifCli==false)&&(verifFunc==true))
			return 3;
		else
			return 4;
	}
}
