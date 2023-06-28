package model;

public class Servico {
	
	private int id;
	private String descricao;
	private float preco;
	private Cliente cliente;
	private Animal animal;
	private Funcionario funcionario;
	
	public Servico(String descricao, float preco) {
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Servico() {}

	public Servico(String descricao, float preco, Cliente cliente, Animal animal, Funcionario funcionario) {
		this.descricao = descricao;
		this.preco = preco;
		this.cliente = cliente;
		this.animal = animal;
		this.funcionario = funcionario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
