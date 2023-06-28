package DAO;

import java.util.ArrayList;

public interface CrudGeneric<E> {

	void salvar(E entidade);
	void atualizar(E entidade);
	void remover(E entidade);
	E buscarPorId(String id);
	ArrayList<E> listar();
}
