/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a3conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author noobg
 */
// Pega toda a paremetrização e passa pro banco de dados de volta.
public class SapatariaDAO {
    public static void main(String[] args) {
    
}


    
    //Criando um "pedido", baseado na sapataria
    public List<Sapataria> findAll() throws SQLException {
        String sql = "SELECT nome, telefone, tipocalçado, tipodeconserto FROM sapataria ";
        try (
                Connection conn = A3Conexao.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            List<Sapataria> Lista = new ArrayList<>();
            while (rs.next()) {
                Lista.add(new Sapataria(
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("tipocalçado"),
                        rs.getString("tipodeconserto")
                ));
            }
            return Lista;
        }

    }
   //Ele salva o que foi feito no método anterior
    public void save(Sapataria e) throws SQLException {

        String sql = "INSERT INTO sapataria (nome, telefone, tipocalçado, tipodeconserto )"
                + " VALUES (?, ?, ?, ?)";
        try (
                Connection conn = A3Conexao.getConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNome());
            ps.setString(2, e.getTelefone());
            ps.setString(3, e.getTipocalçado());
            ps.setString(4, e.getTipodeconserto());
        }
    }
    //Altera qualquer mudança feita
    public void update(Sapataria e) throws SQLException {
        String sql = "UPDATE sapataria SET nome, telefone, tipocalçado, tipodeconserto = ? WHERE sigla = ?";
        try (
                Connection conn = A3Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNome());
            ps.setString(2, e.getTelefone());
            ps.setString(3, e.getTipocalçado());
            ps.setString(4, e.getTipodeconserto());
            ps.executeUpdate();
        }
    }
    //Deleta
    public void delete(String nome) throws SQLException {
        String sql = "DELETE FROM sapataria WHERE nome = ?";
        try (
                Connection conn = A3Conexao.getConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.executeUpdate();
        }
    }
}
