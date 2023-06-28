package model;

public class Produto {

	private String codigo;
	private String descricao;
	private float preco;
	
	public Produto(String codigo, String descricao, float preco) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Produto() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	
}
