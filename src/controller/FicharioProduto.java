package controller;

import java.util.Iterator;
import java.util.Scanner;

import DAO.ProdutoDAO;
import model.Produto;

public class FicharioProduto {
	private ProdutoDAO produtoDAO;
	private Scanner sc;
	
	public FicharioProduto() {
		sc = new Scanner(System.in);
		produtoDAO = new ProdutoDAO();
	}
	
	public void cadastrarProduto() {
		
		Produto produto = new Produto();
		System.out.println("-+=[Cadastro de Produtos]=+-");
		setDados(produto);
		
		produtoDAO.salvar(produto);
		
		System.out.println("Produto cadastrado com sucesso!");
	}
	
	public void alterarProduto() {
		String codigo;
		
		System.out.println("-+=[Alteração de Produtos]=+-");
		System.out.println("Digite o código do produto a ser alterado: ");
		codigo = sc.nextLine();
		
		if (!produtoDAO.isEmpty()) {
			getDados(produtoDAO.buscarPorId(codigo));
			
			System.out.println("\nDeseja alterar esta posição? (1 - Sim / 2 - Não)");
			int resposta = Integer.parseInt(sc.nextLine());
			
			if (resposta == 1) {
				Produto produto = new Produto();
				produto = produtoDAO.buscarPorId(codigo);
				setDados(produto);
				produtoDAO.atualizar(produto);
				System.out.println("Alterado com sucesso!");
				
			} else
				System.out.println("Cancelado!");
		} else
			System.out.println("Produto inexistente!");
	}
	
	public void excluirProduto() {
		String codigo;
		
		System.out.println("-+=[Exclusão de Produtos]=+-");
		System.out.println("Digite o código do produto a ser excluído: ");
		codigo = sc.nextLine();
		
		if (!produtoDAO.isEmpty()) {
			getDados(produtoDAO.buscarPorId(codigo));
			
			System.out.println("\nDeseja excluir esta posição? (1 - Sim / 2 - Não)");
			int resposta = Integer.parseInt(sc.nextLine());
			
			if (resposta == 1) {
				Produto produto = new Produto();
				produto = produtoDAO.buscarPorId(codigo);
				produtoDAO.remover(produto);
				System.out.println("Excluído com sucesso!");
				
			} else
				System.out.println("Cancelado!");
		} else
			System.out.println("Produto inexistente!");
	}
	
	public void consultarProduto() {
		String codigo;
		
		System.out.println("-+=[Consulta de Produtos]=+-");
		System.out.println("Digite a posição que deseja consultar: ");
		codigo = sc.nextLine();
		
		if (!produtoDAO.isEmpty()) {
			getDados(produtoDAO.buscarPorId(codigo));
		} else
			System.out.println("Produto inexistente!");
	}
	
	public void relatorioDeProdutos() {
		Iterator<Produto> itr = produtoDAO.listar().iterator();
		
		while(itr.hasNext()) {
			System.out.println("==================================================");
			getDados(itr.next());
		}
	}
	
	private void setDados(Produto produto) {
		System.out.println("Digito o código do produto: ");
		produto.setCodigo(sc.nextLine());
		
		System.out.println("Digite a descrição do produto: ");
		produto.setDescricao(sc.nextLine());
		
		System.out.println("Digite o preço do produto: ");
		produto.setPreco(Float.parseFloat(sc.nextLine()));
	}
	
	private void getDados(Produto produto) {
		System.out.println("Código:              " + produto.getCodigo());
		System.out.println("Descrição:           " + produto.getDescricao());
		System.out.println("Preço:               " + produto.getPreco());
	}
	
}
