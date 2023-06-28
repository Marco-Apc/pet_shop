package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.Conexao;
import model.Animal;

public class AnimalDAO {

    private Connection conn = null;

    public AnimalDAO() {
        try {
            conn = Conexao.getConn();
        } catch (SQLException e) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void salvar(Animal entidade, String cpf_cliente) {
        String sql;
        PreparedStatement ps = null;

        sql = "INSERT INTO animal(id_cliente, nome, raca, peso, sexo) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf_cliente);
            ps.setString(2, entidade.getNome());
            ps.setString(3, entidade.getRaca());
            ps.setFloat(4, entidade.getPeso());
            ps.setString(5, entidade.getSexo());
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

    public void atualizar(Animal entidade, String cpf_cliente) {
        String sql;
        PreparedStatement ps = null;

        sql = "update animal set nome = ?, raca = ?, peso = ?, sexo = ? where id_cliente = ? and nome = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getRaca());
            ps.setFloat(3, entidade.getPeso());
            ps.setString(4, entidade.getSexo());
            ps.setString(5, cpf_cliente);
            ps.setString(6, entidade.getNome());
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

    public void remover(Animal entidade, String cpf_cliente) {
        String sql;
        PreparedStatement ps = null;

        sql = "delete from animal where id_cliente = ? and nome = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf_cliente);
            ps.setString(2, entidade.getNome());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Erro na operação de exclusão: " + e.getMessage());
        } finally {
            try {
                ps.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Animal buscarPorId(Animal entidade, String cpf_cliente) {
        Animal animal = new Animal();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from animal where id_cliente = ? and nome = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf_cliente);
            ps.setString(2, entidade.getNome());
            rs = ps.executeQuery();

            if (rs.next()) {
                animal.setNome(rs.getString("nome"));
                animal.setRaca(rs.getString("raca"));
                animal.setPeso(rs.getFloat("peso"));
                animal.setSexo(rs.getString("sexo"));
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca por id: " + e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return animal;
    }

    public ArrayList<Animal> listar() {
        ArrayList<Animal> animais = new ArrayList<>();
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from animal";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setNome(rs.getString("nome"));
                animal.setRaca(rs.getString("raca"));
                animal.setPeso(rs.getFloat("peso"));
                animal.setSexo(rs.getString("sexo"));
                animais.add(animal);
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca por id: " + e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return animais;
    }

    public boolean existeAnimal(String entidade, String cpf_cliente) {
        boolean existe = false;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from animal where id_cliente = ? and nome = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf_cliente);
            ps.setString(2, entidade);
            rs = ps.executeQuery();

            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca por id: " + e.getMessage());
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

    public boolean isEmpty(String cpf) {
        boolean isEmpty = true;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from animal where id_cliente = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();

            if (rs.next()) {
                isEmpty = false;
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca por id: " + e.getMessage());
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

    public void fecharConexao() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Animal buscarPorId(String entidade, String cpf) {
        Animal animal = new Animal();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from animal where id_cliente = ? and nome = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, entidade);
            rs = ps.executeQuery();

            if (rs.next()) {
                animal.setNome(rs.getString("nome"));
                animal.setRaca(rs.getString("raca"));
                animal.setPeso(rs.getFloat("peso"));
                animal.setSexo(rs.getString("sexo"));
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação de busca por id: " + e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                fecharConexao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return animal;
    }

}
