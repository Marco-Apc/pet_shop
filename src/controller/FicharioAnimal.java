package controller;

import java.util.Iterator;
import java.util.Scanner;

import DAO.AnimalDAO;
import DAO.ClienteDAO;
import model.Animal;
import model.Cliente;

public class FicharioAnimal {
	
    private Scanner sc;
	private ClienteDAO clienteDAO;
	private AnimalDAO animalDAO;

    public FicharioAnimal(){
		sc = new Scanner(System.in);

		clienteDAO = new ClienteDAO();
		animalDAO = new AnimalDAO();
    }

    public void cadastrarAnimal(){
		
		if (!clienteDAO.isEmpty()) {
			System.out.println("-+=[Cadastro de Animais]=+-");
			System.out.println("Digite o CPF do cliente dono do animal: ");
			String cpf = sc.nextLine();
			
			if (clienteDAO.existeCliente(cpf)) {
				
				Animal animal = new Animal();
				
				System.out.println("Digite o nome do animal: ");
				animal.setNome(sc.nextLine());
				
				System.out.println("Digite a raça do animal: ");
				animal.setRaca(sc.nextLine());
				
				System.out.println("Digite o peso do animal (KG): ");
				animal.setPeso(Float.parseFloat(sc.nextLine()));

		        System.out.println("Digite o sexo do animal");
		        animal.setSexo(sc.nextLine());

				animalDAO.salvar(animal, cpf);
			} else {
				System.out.println("Cliente não encontrado!");
			}
		} else {
			System.out.println("Não há clientes cadastrados!");
		}
    }

    public void alterarAnimal(){
		Animal animal = new Animal();
    	
        if (!clienteDAO.isEmpty()) {
        	
        	System.out.println("-+=[Alteração de Animais]=+-");
            System.out.println("Digite o CPF do cliente dono do animal: ");
            String cpf = sc.nextLine();
            
			if (clienteDAO.existeCliente(cpf)) {
				if (!animalDAO.isEmpty(cpf)) {
					
					System.out.println("Digite o nome do animal a ser alterado: ");
			        String nomeAnimal = sc.nextLine();
					animal = animalDAO.buscarPorId(nomeAnimal, cpf);

					if (animalDAO.existeAnimal(nomeAnimal, cpf)) {
						
						getDados(animalDAO.buscarPorId(nomeAnimal, cpf), clienteDAO.buscarPorId(cpf));
						System.out.println("\nDeseja alterar esta posição? (1 - Sim / 2 - Não)");
						int resposta = Integer.parseInt(sc.nextLine());

						if (resposta == 1) {
							setDados(animal);
							animalDAO.atualizar(animal, cpf);
							System.out.println("Animal alterado com sucesso!");
						}
						
					} else {
						System.out.println("Animal não encontrado!");
					}
				} else {
					System.out.println("Não há animais cadastrados!");
				}
			} else {
				System.out.println("Cliente não encontrado!");
			}
		} else {
			System.out.println("Não há clientes cadastrados!");
		}
    }
    
    public void excluirAnimal() {
		Animal animal = new Animal();
		
		if (!clienteDAO.isEmpty()) {
			
			System.out.println("-+=[Exclusão de Animais]=+-");
			System.out.println("Digite o CPF do cliente dono do animal: ");
			String cpf = sc.nextLine();
			
			if (clienteDAO.existeCliente(cpf)) {
				if (!animalDAO.isEmpty(cpf)) {

					System.out.println("Digite o nome do animal a ser excluído: ");
					String nomeAnimal = sc.nextLine();
					
					if (animalDAO.existeAnimal(nomeAnimal, cpf)) {
						
						getDados(animalDAO.buscarPorId(nomeAnimal, cpf), clienteDAO.buscarPorId(cpf));
						System.out.println("\nDeseja excluir esta posição? (1 - Sim / 2 - Não)");
						int resposta = Integer.parseInt(sc.nextLine());
						animal = animalDAO.buscarPorId(nomeAnimal, cpf);

						if (resposta == 1) {
							animalDAO.remover(animal, cpf);
							System.out.println("Animal excluído com sucesso!");
						}
						
					} else {
						System.out.println("Animal não encontrado!");
					}
				} else {
					System.out.println("Não há animais cadastrados!");
				}
			} else {
				System.out.println("Cliente não encontrado!");
			}
		} else {
			System.out.println("Não há clientes cadastrados!");
		}
	}

    public void consultarAnimal() {
    	
		if (!clienteDAO.isEmpty()) {
			
			System.out.println("-+=[Consulta de Animais]=+-");
			System.out.println("Digite o CPF do cliente dono do animal: ");
			String cpf = sc.nextLine();
			
			if (clienteDAO.existeCliente(cpf)) {
				if (!animalDAO.isEmpty(cpf)) {
					
					System.out.println("Digite o nome do animal que deseja consultar: ");
					String nomeAnimal = sc.nextLine();
					
					if (animalDAO.existeAnimal(nomeAnimal, cpf)) {

						getDados(animalDAO.buscarPorId(nomeAnimal, cpf), clienteDAO.buscarPorId(cpf));
						
					} else {
						System.out.println("Animal não encontrado!");
					}
				} else {
					System.out.println("Não há animais cadastrados!");
				}
			} else {
				System.out.println("Cliente não encontrado!");
			}
		} else {
			System.out.println("Não há clientes cadastrados!");
		}
	}
	
	public void relatorioDeAnimais() {
		
		if (!clienteDAO.isEmpty()) {
			
			System.out.println("-+=[Relatório de Animais]=+-");
			System.out.println("Digite o CPF do cliente dono do animal: ");
			String cpf = sc.nextLine();
			
			if (clienteDAO.existeCliente(cpf)) {
				if ((!animalDAO.isEmpty(cpf))) {
					
					Iterator<Animal> it = animalDAO.listar().iterator();
					
					while (it.hasNext()) {
						Animal animal = it.next();
						
						System.out.println("Dono: " + clienteDAO.buscarPorId(cpf).getNome());
						System.out.println("Nome: " + animal.getNome());
						System.out.println("Raça: " + animal.getRaca());
						System.out.println("Peso: " + animal.getPeso());
						System.out.println("Sexo: " + animal.getSexo());
						System.out.println("-------------------------------");
					}
					
				} else {
					System.out.println("Não há animais cadastrados!");
				}
			} else {
				System.out.println("Cliente não encontrado!");
			}
		} else {
			System.out.println("Não há clientes cadastrados!");
		}
	}

    private void setDados(Animal animal) {
    	
		System.out.println("Digite o nome do animal: ");
		animal.setNome(sc.nextLine());
		
		System.out.println("Digite a raça do animal: ");
		animal.setRaca(sc.nextLine());
		
		System.out.println("Digite o peso do animal (KG): ");
		animal.setPeso(Float.parseFloat(sc.nextLine()));

        System.out.println("Digite o sexo do animal");
        animal.setSexo(sc.nextLine());
        
    }
    
    public void getDados(Animal animal, Cliente cliente) {
    	
    	System.out.println("Dono: " + cliente.getNome());
        System.out.println("Nome: " + animal.getNome());
        System.out.println("Raça: " + animal.getRaca());
        System.out.println("Peso: " + animal.getPeso());
        System.out.println("Sexo: " + animal.getSexo());
    }

}
