package DAO;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Conexao;
import model.Cliente;

public class ClienteDAO implements CrudGeneric<Cliente> {

    private Connection conn = null;

    public ClienteDAO() {
        try {
            conn = Conexao.getConn();
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void salvar(Cliente cliente) {
        String sql;
        PreparedStatement ps = null;

        sql = "INSERT INTO cliente(nome, cpf, telefone, endereco, cliente_especial) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEndereco());
            ps.setBoolean(5, cliente.isClienteEspecial());
            ps.execute();
        } catch (Exception e) {
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
    public void atualizar(Cliente cliente) {
        String sql;
        PreparedStatement ps = null;

        sql = "update cliente set nome = ?, cpf = ?, telefone = ?, endereco = ?, cliente_especial = ? where cpf = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEndereco());
            ps.setBoolean(5, cliente.isClienteEspecial());
            ps.setString(6, cliente.getCpf());
            ps.execute();
        } catch (Exception e) {
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
    public void remover(Cliente cliente) {
        String sql;
        PreparedStatement ps = null;

        sql = "delete from cliente where cpf = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getCpf());
            ps.execute();
        } catch (Exception e) {
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
    public Cliente buscarPorId(String cpf) {
        Cliente cliente = new Cliente();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "select * from cliente where cpf = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);

            rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setClienteEspecial(rs.getBoolean("clienteEspecial"));
            }
        } catch (Exception e) {
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
        return cliente;
    }

    @Override
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "select * from cliente";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setClienteEspecial(rs.getBoolean("clienteEspecial"));

                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.err.println("Erro na operação de listar: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }

    public boolean existeCliente(String cpf) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "select * from cliente where cpf = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);

            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
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

    public boolean isEmpty() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "select * from cliente";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
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

    public void fecharConexao() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erro fechando conexão: " + e.getMessage());
        }
    }

}
