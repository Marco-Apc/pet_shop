package model;

public class Cliente extends Pessoa{

	private boolean clienteEspecial;

	public Cliente(boolean clienteEspecial) {
		super();
		this.clienteEspecial = clienteEspecial;
	}
	
	public Cliente() {}

	public boolean isClienteEspecial() {
		return clienteEspecial;
	}

	public void setClienteEspecial(boolean clienteEspecial) {
		this.clienteEspecial = clienteEspecial;
	}
	
}
