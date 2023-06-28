package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Venda {
	
	private int id;
	private float valorTotal;
	private LocalDate data;
	private LocalTime hora;
	private Cliente cliente;
	private Funcionario funcionario;
	private ArrayList<Produto> produtos;
	private ArrayList<Servico> servicos;
	
	public Venda(float valorTotal, LocalDate data, LocalTime hora, Cliente cliente, Funcionario funcionario, ArrayList<Produto> produtos,
			ArrayList<Servico> servicos) {
		this.valorTotal = valorTotal;
		this.data = data;
		this.hora = hora;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.produtos = produtos;
		this.servicos = servicos;
	}
	
	public Venda() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(ArrayList<Servico> servicos) {
		this.servicos = servicos;
	}

}
