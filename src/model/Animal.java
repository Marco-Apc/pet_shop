package model;


public class Animal {
	
	private String cpf_dono;
	private String nome;
	private String raca;
	private float peso;
	private String sexo;
	
	public Animal(String nome, String raca, float peso, String sexo) {
		this.nome = nome;
		this.raca = raca;
		this.peso = peso;
		this.sexo = sexo;
	}

	public Animal() {}

	public String getCpf_dono() {
		return cpf_dono;
	}

	public void setCpf_dono(String cpf_dono) {
		this.cpf_dono = cpf_dono;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
}
