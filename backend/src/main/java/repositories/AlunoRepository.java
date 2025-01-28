package repositories;

import entities.*;
import jdbc.BaseRespository;
import jdbc.ConnectionDatabase;
import jdbc.interfaces.JDBCRepository;

import java.sql.*;
import java.util.*;

public class AlunoRepository extends BaseRespository implements JDBCRepository<Aluno, Integer> {

    @Override
    public int save(Aluno aluno) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO aluno (matricula, nome, data_nascimento, idade, contato, sexo) ")
                .append("VALUES (?, ?, ?, ?, ?, ?)");

        try (PreparedStatement stmt =  getConnection().prepareStatement(sql.toString())) {
            stmt.setInt(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNome());
            stmt.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
            stmt.setInt(4, aluno.getIdade());
            stmt.setString(5, aluno.getContato());
            stmt.setString(6, String.valueOf(aluno.getSexo()));
            return stmt.executeUpdate();
        }
    }

    @Override
    public Aluno update(Aluno aluno) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE aluno SET nome = ?, data_nascimento = ?, idade = ?, contato = ?, sexo = ? ")
                .append("WHERE matricula = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setString(1, aluno.getNome());
            stmt.setDate(2, new java.sql.Date(aluno.getDataNascimento().getTime()));
            stmt.setInt(3, aluno.getIdade());
            stmt.setString(4, aluno.getContato());
            stmt.setString(5, String.valueOf(aluno.getSexo()));
            stmt.setInt(6, aluno.getMatricula());
            stmt.executeUpdate();
        }
        return aluno;
    }

    @Override
    public int delete(Integer matricula) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM aluno WHERE matricula = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setInt(1, matricula);
            return stmt.executeUpdate();
        }
    }

    @Override
    public List<Aluno> getAll() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT matricula, nome, data_nascimento, idade, contato, sexo FROM aluno");

        List<Aluno> alunos = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getInt("matricula"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento"),
                        rs.getInt("idade"),
                        rs.getString("sexo").charAt(0),
                        rs.getString("contato")
                ));
            }
        }
        return alunos;
    }

    @Override
    public Aluno getById(Integer matricula) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT matricula, nome, data_nascimento, idade, contato, sexo FROM aluno WHERE matricula = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setInt(1, matricula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                            rs.getInt("matricula"),
                            rs.getString("nome"),
                            rs.getDate("data_nascimento"),
                            rs.getInt("idade"),
                            rs.getString("sexo").charAt(0),
                            rs.getString("contato")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public int deleteAll() throws SQLException {
        String sql = "DELETE FROM aluno";
        try (Statement stmt = getConnection().createStatement()) {
            return stmt.executeUpdate(sql);
        }
    }
}