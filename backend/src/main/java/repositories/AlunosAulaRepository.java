package repositories;

import entities.AlunosAula;
import jdbc.BaseRespository;
import jdbc.interfaces.JDBCRepository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlunosAulaRepository extends BaseRespository implements JDBCRepository<AlunosAula, String> {

    @Override
    public int save(AlunosAula object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO alunos_aula (id, matricula, id_aula, presenca) ")
                .append("VALUES (?, ?, ?, ?)");

        try (PreparedStatement stmt =  getConnection().prepareStatement(sql.toString())) {
            stmt.setString(1, object.getId());
            stmt.setInt(2, object.getMatricula());
            stmt.setDate(3, new java.sql.Date(object.getIdAula().getTime()));
            stmt.setInt(4, object.getPresenca());
            return stmt.executeUpdate();
        }
    }

    @Override
    public AlunosAula update(AlunosAula object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE alunos_aula SET presenca = ? ")
                .append("WHERE id = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setInt(1, object.getPresenca());
            stmt.setString(2, object.getId());
            stmt.executeUpdate();
        }
        return object;
    }

    @Override
    public int delete(String object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM alunos_aula WHERE id = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setString(1, object);
            return stmt.executeUpdate();
        }
    }

    @Override
    public List<AlunosAula> getAll() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, matricula, id_aula, presenca FROM alunos_aula");

        List<AlunosAula> alunosAulas = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                alunosAulas.add(new AlunosAula(
                        rs.getString("id"),
                        rs.getInt("matricula"),
                        rs.getDate("id_aula"),
                        rs.getInt("presenca")
                ));
            }
        }
        return alunosAulas;
    }

    @Override
    public AlunosAula getById(String object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, matricula, id_aula, presenca FROM alunos_aula WHERE id = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setString(1, object);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AlunosAula(
                            rs.getString("id"),
                            rs.getInt("matricula"),
                            rs.getDate("id_aula"),
                            rs.getInt("presenca"));
                }
            }
        }
        return null;
    }

    @Override
    public int deleteAll() throws SQLException {
        String sql = "DELETE FROM alunos_aula";
        try (Statement stmt = getConnection().createStatement()) {
            return stmt.executeUpdate(sql);
        }
    }
}
