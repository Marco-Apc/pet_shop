package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.Conexao;
import model.Funcionario;

public class FuncionarioDAO implements CrudGeneric<Funcionario> {

    private Connection conn = null;

    public FuncionarioDAO() {
        try {
            conn = Conexao.getConn();
        } catch (SQLException e) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void salvar(Funcionario entidade) {
        String sql;

        PreparedStatement ps = null;

        sql = "INSERT INTO funcionario(nome, cpf, telefone, endereco, ctps, cargo) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getCpf());
            ps.setString(3, entidade.getTelefone());
            ps.setString(4, entidade.getEndereco());
            ps.setString(5, entidade.getCtps());
            ps.setString(6, entidade.getCargo());
            ps.execute();
        } catch (Exception e) {
            System.err.println("Erro ao salvar funcionario: " + e.getMessage());
        } finally {
            try {
                ps.close();
                closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(Funcionario entidade) {
        String sql;

        PreparedStatement ps = null;

        sql = "update funcionario set nome = ?, cpf = ?, telefone = ?, endereco = ?, ctps = ?, cargo = ? where cpf = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getCpf());
            ps.setString(3, entidade.getTelefone());
            ps.setString(4, entidade.getEndereco());
            ps.setString(5, entidade.getCtps());
            ps.setString(6, entidade.getCargo());
            ps.setString(7, entidade.getCpf());
            ps.execute();
        } catch (Exception e) {
            System.err.println("Erro ao atualizar funcionario: " + e.getMessage());
        } finally {
            try {
                ps.close();
                closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remover(Funcionario entidade) {
        String sql;

        PreparedStatement ps = null;

        sql = "delete from funcionario where cpf = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, entidade.getCpf());
            ps.execute();
        } catch (Exception e) {
            System.err.println("Erro ao remover funcionario: " + e.getMessage());
        } finally {
            try {
                ps.close();
                closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Funcionario buscarPorId(String cpf) {
        Funcionario funcionario = new Funcionario();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from funcionario where cpf = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);

            rs = ps.executeQuery();

            if (rs.next()) {
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setCtps(rs.getString("ctps"));
                funcionario.setCargo(rs.getString("cargo"));
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar funcionario: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return funcionario;
    }

    @Override
    public ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from funcionario";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setCtps(rs.getString("ctps"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar funcionarios: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return funcionarios;
    }

    public boolean existeFuncionario(String cpf) {
        boolean existe = false;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from funcionario where cpf = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);

            rs = ps.executeQuery();

            if (rs.next()) {
                existe = true;
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar funcionario: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return existe;
    }

    public boolean isEmpty() {
        boolean isEmpty = true;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from funcionario";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                isEmpty = false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar funcionario: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isEmpty;
    }

    private void closeConn() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
