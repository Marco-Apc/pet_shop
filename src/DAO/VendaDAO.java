package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.Conexao;
import model.Produto;
import model.Servico;
import model.Venda;

public class VendaDAO implements CrudGeneric<Venda> {

    private Connection conn = null;

    public VendaDAO() {
        try {
            conn = Conexao.getConn();
        } catch (SQLException e) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void salvar(Venda entidade) {
        String sql;
        PreparedStatement ps = null;

        sql = "INSERT INTO venda(data, hora, cliente_id, funcionario_id, valor_total) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, entidade.getData());
            ps.setObject(2, entidade.getHora());
            ps.setString(3, entidade.getCliente().getCpf());
            ps.setString(4, entidade.getFuncionario().getCpf());
            ps.setDouble(5, entidade.getValorTotal());
            ps.execute();

            ArrayList<Produto> produtos = entidade.getProdutos();
            for (Produto produto : produtos) {
                sql = "INSERT INTO venda_produto(venda_id, codigo, descricao, preco) VALUES (?, ?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, entidade.getId());
                ps.setString(2, produto.getCodigo());
                ps.setString(3, produto.getDescricao());
                ps.setFloat(4, produto.getPreco());
                ps.execute();
            }

            ArrayList<Servico> servicos = entidade.getServicos();
            for (Servico servico : servicos) {
                sql = "INSERT INTO venda_servico(venda_id, descricao, preco, cliente, animal, funcionario) VALUES (?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, entidade.getId());
                ps.setString(2, servico.getDescricao());
                ps.setFloat(3, servico.getPreco());
                ps.setString(4, servico.getCliente().getCpf());
                ps.setString(5, servico.getAnimal().getNome());
                ps.setString(6, servico.getFuncionario().getCpf());
                ps.execute();
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de inserção: " + e.getMessage());
        } finally {
            try {
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(Venda entidade) {
        String sql;
        PreparedStatement ps = null;

        sql = "update venda set data = ?, hora = ?, cliente_id = ?, funcionario_id = ?, valor_total = ? where id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, entidade.getData());
            ps.setObject(2, entidade.getHora());
            ps.setString(3, entidade.getCliente().getCpf());
            ps.setString(4, entidade.getFuncionario().getCpf());
            ps.setDouble(5, entidade.getValorTotal());
            ps.setInt(6, entidade.getId());
            ps.execute();

            ArrayList<Produto> produtos = entidade.getProdutos();
            for (Produto produto : produtos) {
                sql = "update venda_produto set venda_id = ?, codigo = ?, descricao = ?, preco = ? where venda_id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, entidade.getId());
                ps.setString(2, produto.getCodigo());
                ps.setString(3, produto.getDescricao());
                ps.setFloat(4, produto.getPreco());
                ps.setInt(5, entidade.getId());
                ps.execute();
            }

            ArrayList<Servico> servicos = entidade.getServicos();
            for (Servico servico : servicos) {
                sql = "update venda_servico set venda_id = ?, descricao = ?, preco = ?, cliente = ?, animal = ?, funcionario = ? where venda_id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, entidade.getId());
                ps.setString(2, servico.getDescricao());
                ps.setFloat(3, servico.getPreco());
                ps.setString(4, servico.getCliente().getCpf());
                ps.setString(5, servico.getAnimal().getNome());
                ps.setString(6, servico.getFuncionario().getCpf());
                ps.setInt(7, entidade.getId());
                ps.execute();
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de atualização: " + e.getMessage());
        } finally {
            try {
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remover(Venda entidade) {
        String sql;
        PreparedStatement ps = null;

        sql = "delete from venda where id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, entidade.getId());
            ps.execute();
            ps.close();

            sql = "delete from venda_produto where venda_id = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, entidade.getId());
                ps.execute();
                ps.close();
            } catch (SQLException e) {
                System.err.println("Erro na operação de remoção: " + e.getMessage());
            }

            sql = "delete from venda_servico where venda_id = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, entidade.getId());
                ps.execute();
                ps.close();
            } catch (SQLException e) {
                System.err.println("Erro na operação de remoção: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de remoção: " + e.getMessage());
        } finally {
            try {
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Venda buscarPorId(String id) {
        Venda venda = new Venda();

        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "select * from venda where id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();

            if (rs.next()) {
                venda.setId(rs.getInt("id"));
                venda.setCliente(new ClienteDAO().buscarPorId(rs.getString("cliente_id")));
                venda.setFuncionario(new FuncionarioDAO().buscarPorId(rs.getString("funcionario_id")));
                venda.setValorTotal(rs.getFloat("valor_total"));
            }
            ps.close();
            rs.close();

            sql = "select * from venda_produto where venda_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();

            ArrayList<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getFloat("preco"));
                produtos.add(produto);
            }
            venda.setProdutos(produtos);
            ps.close();
            rs.close();

            sql = "select * from venda_servico where venda_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();

            ArrayList<Servico> servicos = new ArrayList<>();
            while (rs.next()) {
                Servico servico = new Servico();
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getFloat("preco"));
                servico.setCliente(new ClienteDAO().buscarPorId(rs.getString("cliente")));
                servico.setAnimal(new AnimalDAO().buscarPorId(rs.getString("animal"), servico.getCliente().getCpf()));
                servico.setFuncionario(new FuncionarioDAO().buscarPorId(rs.getString("funcionario")));
                servicos.add(servico);
            }
            venda.setServicos(servicos);
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return venda;
    }

    @Override
    public ArrayList<Venda> listar() {
        ArrayList<Venda> vendas = new ArrayList<>();

        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "select * from venda";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setCliente(new ClienteDAO().buscarPorId(rs.getString("cliente_id")));
                venda.setFuncionario(new FuncionarioDAO().buscarPorId(rs.getString("funcionario_id")));
                venda.setValorTotal(rs.getFloat("valor_total"));

                ArrayList<Produto> produtos = obterProdutosPorVenda(rs.getString("id"));
                venda.setProdutos(produtos);

                ArrayList<Servico> servicos = obterServicosPorVenda(rs.getString("id"));
                venda.setServicos(servicos);

                vendas.add(venda);
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de listagem: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vendas;
    }

    public boolean existeNoBanco(String id) {
        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "select * from venda where id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean isEmpty(){
        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "select * from venda";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void fecharConexao() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Produto> obterProdutosPorVenda(String id) {
        ArrayList<Produto> produtos = new ArrayList<>();

        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "select * from venda_produto where venda_id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getFloat("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return produtos;
    }

    private ArrayList<Servico> obterServicosPorVenda(String id) {
        ArrayList<Servico> servicos = new ArrayList<>();

        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "select * from venda_servico where venda_id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getFloat("preco"));
                servico.setCliente(new ClienteDAO().buscarPorId(rs.getString("cliente")));
                servico.setAnimal(new AnimalDAO().buscarPorId(rs.getString("animal"), servico.getCliente().getCpf()));
                servico.setFuncionario(new FuncionarioDAO().buscarPorId(rs.getString("funcionario")));
                servicos.add(servico);
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return servicos;
    }

}
