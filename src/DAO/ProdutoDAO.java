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

public class ProdutoDAO implements CrudGeneric<Produto>{

    private Connection conn = null;

    public ProdutoDAO() {
        try {
            conn = Conexao.getConn();
        } catch (SQLException e) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void salvar(Produto entidade) {
        String sql;
        PreparedStatement ps = null;

        sql = "INSERT INTO produto(codigo, descricao, preco) VALUES (?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, entidade.getCodigo());
            ps.setString(2, entidade.getDescricao());
            ps.setFloat(3, entidade.getPreco());
            ps.execute();
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
    public void atualizar(Produto entidade) {
        String sql;
        PreparedStatement ps = null;

        sql = "update produto set codigo = ?, descricao = ?, preco = ? where codigo = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, entidade.getCodigo());
            ps.setString(2, entidade.getDescricao());
            ps.setFloat(3, entidade.getPreco());
            ps.setString(4, entidade.getCodigo());
            ps.execute();
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
    public void remover(Produto entidade) {
        String sql;
        PreparedStatement ps = null;

        sql = "delete from produto where codigo = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, entidade.getCodigo());
            ps.execute();
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
    public Produto buscarPorId(String cpf) {
        Produto produto = new Produto();

        PreparedStatement ps = null;
        ResultSet rs = null; 
        
        
        try {

            String sql = "select * from produto where codigo = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);

            rs = ps.executeQuery();

            if (rs.next()) {
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getFloat("preco"));
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca por id: " + e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return produto;
    }

    @Override
    public ArrayList<Produto> listar() {
        ArrayList<Produto> produtos = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from produto";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getFloat("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
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
        return produtos;
    }

    public boolean existeProduto(String codigo) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from produto where codigo = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca por id: " + e.getMessage());
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
            String sql = "select * from produto";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca por id: " + e.getMessage());
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
}
