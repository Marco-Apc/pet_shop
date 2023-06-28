package model;

public class Funcionario extends Pessoa{

	private String ctps;
	private String cargo;
	
	
	public Funcionario(String ctps, String cargo) {
		super();
		this.ctps = ctps;
		this.cargo = cargo;
	}

	public Funcionario() {}
	
	
	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}
