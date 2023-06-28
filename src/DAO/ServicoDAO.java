package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.Conexao;
import model.Servico;

public class ServicoDAO {

    private Connection conn;

    public ServicoDAO() {
        try {
            conn = Conexao.getConn();
        } catch (SQLException e) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void salvar(Servico entidade) {
        String sql;
        PreparedStatement ps = null;

        sql = "INSERT INTO servico(descricao, preco, cliente_id, animal_id, funcionario_id) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, entidade.getDescricao());
            ps.setFloat(2, entidade.getPreco());
            ps.setString(3, entidade.getCliente().getCpf());
            ps.setString(4, entidade.getAnimal().getNome());
            ps.setString(5, entidade.getFuncionario().getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar serviço: " + e.getMessage());
        } finally {
            try {
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void atualizar(Servico entidade) {
        String sql;
        PreparedStatement ps = null;

        sql = "UPDATE servico SET descricao = ?, preco = ?, cliente_id = ?, animal_id = ?, funcionario_id = ? WHERE cliente_id = ? AND animal_id = ? AND funcionario_id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, entidade.getDescricao());
            ps.setFloat(2, entidade.getPreco());
            ps.setString(3, entidade.getCliente().getCpf());
            ps.setString(4, entidade.getAnimal().getNome());
            ps.setString(5, entidade.getFuncionario().getCpf());
            ps.setString(6, entidade.getCliente().getCpf());
            ps.setString(7, entidade.getAnimal().getNome());
            ps.setString(8, entidade.getFuncionario().getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar serviço: " + e.getMessage());
        } finally {
            try {
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void remover(Servico entidade) {
        String sql;
        PreparedStatement ps = null;

        sql = "DELETE FROM servico WHERE cliente_id = ? AND animal_id = ? AND funcionario_id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, entidade.getCliente().getCpf());
            ps.setString(2, entidade.getAnimal().getNome());
            ps.setString(3, entidade.getFuncionario().getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao remover serviço: " + e.getMessage());
        } finally {
            try {
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Servico buscarPorId(int id) {
        Servico servico = new Servico();

        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "SELECT * FROM servico WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getFloat("preco"));
                servico.setCliente(new ClienteDAO().buscarPorId(rs.getString("cliente_id")));
                servico.setFuncionario(new FuncionarioDAO().buscarPorId(rs.getString("funcionario_id")));
                servico.setAnimal(new AnimalDAO().buscarPorId("cliente_id", "animal_id"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar serviço por id: " + e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return servico;
    }

    public Servico buscarPorId(String cliente, String animal, String funcionario) {
        Servico servico = new Servico();

        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "SELECT * FROM servico WHERE cliente_id = ? AND animal_id = ? AND funcionario_id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente);
            ps.setString(2, animal);
            ps.setString(3, funcionario);
            rs = ps.executeQuery();

            if (rs.next()) {
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getFloat("preco"));
                servico.setCliente(new ClienteDAO().buscarPorId(rs.getString("cliente_id")));
                servico.setFuncionario(new FuncionarioDAO().buscarPorId(rs.getString("funcionario_id")));
                servico.setAnimal(new AnimalDAO().buscarPorId("cliente_id", "animal_id"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar serviço por id: " + e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return servico;
    }

    public ArrayList<Servico> listar() {
        ArrayList<Servico> servicos = new ArrayList<>();

        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "SELECT * FROM servico";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getFloat("preco"));
                servico.setCliente(new ClienteDAO().buscarPorId(rs.getString("cliente_id")));
                servico.setAnimal(new AnimalDAO().buscarPorId(rs.getString("animal_id"), rs.getString("cliente_id")));
                servico.setFuncionario(new FuncionarioDAO().buscarPorId(rs.getString("funcionario_id")));
                servicos.add(servico);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar serviços: " + e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return servicos;
    }

    public boolean existeServico(String cpfCliente, String nomeAnimal, String cpfFuncionario) {
        boolean existe = false;

        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "SELECT * FROM servico WHERE cliente_id = ? AND animal_id = ? AND funcionario_id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpfCliente);
            ps.setString(2, nomeAnimal);
            ps.setString(3, cpfFuncionario);
            rs = ps.executeQuery();

            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar se existe serviço: " + e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return existe;
    }

    public boolean isEmpty() {
        boolean isEmpty = true;

        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;

        sql = "SELECT * FROM servico";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                isEmpty = false;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar se tabela de serviços está vazia: " + e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isEmpty;
    }

    private void fecharConexao() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

}
