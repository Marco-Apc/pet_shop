package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.ProdutoDAO;
import DAO.ServicoDAO;
import DAO.VendaDAO;
import model.Produto;
import model.Servico;
import model.Venda;

// implementação da classe VendaController

public class VendaController {
    private Scanner sc;

    private ClienteDAO clienteDAO;
    private FuncionarioDAO funcionarioDAO;
    private ProdutoDAO produtoDAO;
    private ServicoDAO servicoDAO;
    private ArrayList<Produto> produtos;
    private ArrayList<Servico> servicos;

    private DateTimeFormatter formatterData;
    private DateTimeFormatter formatterHora;

    public VendaController() {
        sc = new Scanner(System.in);
        clienteDAO = new ClienteDAO();
        funcionarioDAO = new FuncionarioDAO();
        produtos = new ArrayList<>();

        formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    public void cadastrarVenda() {
        Venda venda = new Venda();

        System.out.println("--=[Cadastro]=--");
        setDados(venda);

        try {
            VendaDAO vendaDAO = new VendaDAO();
            vendaDAO.salvar(venda);
            System.out.println("Venda cadastrada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro na operação de incluir registro: " + e.getMessage());
        }
    }

    public void alterarVenda() {
        Venda venda = new Venda();

        System.out.println("--=[Alteração]=--");
        System.out.println("Digite o código da venda a ser alterada: ");
        int codigo = Integer.parseInt(sc.nextLine());

        getDados(venda, codigo);

        System.out.println("\nDeseja alterar esta posição? (1 - Sim / 2 - Não)");
        int resposta = Integer.parseInt(sc.nextLine());

        if (resposta == 1) {
            setDados(venda);

            try {
                VendaDAO vendaDAO = new VendaDAO();
                vendaDAO.atualizar(venda);
                System.out.println("Alterado com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro na operação de alterar registro: " + e.getMessage());
            }
        } else
            System.out.println("Cancelado!");
    }

    public void excluirVenda() {
        Venda venda = new Venda();

        System.out.println("--=[Exclusão]=--");
        System.out.println("Digite o código da venda a ser excluída: ");
        int codigo = Integer.parseInt(sc.nextLine());

        getDados(venda, codigo);

        System.out.println("\nDeseja excluir esta posição? (1 - Sim / 2 - Não)");
        int resposta = Integer.parseInt(sc.nextLine());

        if (resposta == 1) {
            try {
                VendaDAO vendaDAO = new VendaDAO();
                vendaDAO.remover(venda);
                System.out.println("Excluído com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro na operação de excluir registro: " + e.getMessage());
            }
        } else
            System.out.println("Cancelado!");
    }

    public void listarVenda() {
        ArrayList<Venda> vendas = new ArrayList<Venda>();

        System.out.println("--=[Listagem]=--");

        try {
            VendaDAO vendaDAO = new VendaDAO();
            vendas = vendaDAO.listar();

            for (Venda venda : vendas) {
                System.out.println("Código:             " + venda.getId());
                System.out.println("Cliente:            " + venda.getCliente().getNome());
                System.out.println("Funcionário:        " + venda.getFuncionario().getNome());
                System.out.println("Serviços:         ");
                for (Servico servico : venda.getServicos()) {
                    System.out.println("Descrição:          " + servico.getDescricao());
                    System.out.println("Preço:              " + servico.getPreco());
                }
                System.out.println("Produtos:         ");
                for (Produto produto : venda.getProdutos()) {
                    System.out.println("Descrição:          " + produto.getDescricao());
                    System.out.println("Preço:              " + produto.getPreco());
                }
                System.out.println("Data:               " + venda.getData().format(formatterData));
                System.out.println("Hora:               " + venda.getHora().format(formatterHora));
                System.out.println("Valor total:        " + venda.getValorTotal());
                System.out.println("--------------------------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Erro na operação de listar registros: " + e.getMessage());
        }
    }

    public void buscarVenda() {
        Venda venda = new Venda();
        VendaDAO vendaDAO = new VendaDAO();
        venda = vendaDAO.buscarPorId(String.valueOf(venda.getId()));
    }

    private void setDados(Venda venda) {
        System.out.println("Digite o CPF do cliente: ");
        venda.setCliente(clienteDAO.buscarPorId(sc.nextLine()));

        System.out.println("Digite o CPF do funcionário: ");
        venda.setFuncionario(funcionarioDAO.buscarPorId(sc.nextLine()));

        System.out.println("Deseja cadastrar um produto? (1 - Sim / 2 - Não)");
        int opcao = Integer.parseInt(sc.nextLine());

        while (opcao == 1) {
            System.out.println("Digite o código do produto: ");
            produtos.add(produtoDAO.buscarPorId(sc.nextLine()));

            System.out.println("Deseja adicionar novo produto? (1 - Sim / 2 - Não)");
            opcao = Integer.parseInt(sc.nextLine());
        }
        venda.setProdutos(produtos);

        System.out.println("Deseja cadastrar um serviço? (1 - Sim / 2 - Não)");
        opcao = Integer.parseInt(sc.nextLine());

        while (opcao == 1) {
            System.out.println("Digite o código do serviço: ");
            servicos.add(servicoDAO.buscarPorId(Integer.parseInt(sc.nextLine())));

            System.out.println("Deseja adicionar novo serviço? (1 - Sim / 2 - Não)");
            opcao = Integer.parseInt(sc.nextLine());
        }
        venda.setServicos(servicos);

        LocalDate data = LocalDate.now();
        venda.setData(data);
        LocalTime hora = LocalTime.now();
        venda.setHora(hora);

    }

    private void getDados(Venda venda, int pos) {
        System.out.println("Cliente:            " + venda.getCliente().getNome());
        System.out.println("Funcionário:        " + venda.getFuncionario().getNome());
        System.out.println("Serviços:         ");
        for (Servico servico : venda.getServicos()) {
            System.out.println("Descrição:          " + servico.getDescricao());
            System.out.println("Preço:              " + servico.getPreco());
            System.out.println("Cliente:            " + servico.getCliente().getNome());
            System.out.println("Animal:             " + servico.getAnimal().getNome());
            System.out.println("Funcionário:        " + servico.getFuncionario().getNome());
        }
        System.out.println("Produtos:         ");
        Iterator<Produto> it2 = venda.getProdutos().iterator();
        while (it2.hasNext()) {
            Produto produto = it2.next();
            System.out.println("Código:             " + produto.getCodigo());
            System.out.println("Descrição:          " + produto.getDescricao());
            System.out.println("Preço:              " + produto.getPreco());
        }
        venda.setValorTotal(calculaValorTotal(venda));
        System.out.println("Valor total:        " + venda.getValorTotal());
        System.out.println("Data:               " + venda.getData().format(formatterData));
        System.out.println("Hora:               " + venda.getHora().format(formatterHora));
    }

    private float calculaValorTotal(Venda venda) {
        float valorTotal = 0;
        for (Servico servico : venda.getServicos()) {
            valorTotal += servico.getPreco();
        }
        for (Produto produto : venda.getProdutos()) {
            valorTotal += produto.getPreco();
        }
        return valorTotal;
    }

}
