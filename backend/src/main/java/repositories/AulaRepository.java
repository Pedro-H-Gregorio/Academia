package repositories;

import entities.Aula;
import jdbc.BaseRespository;
import jdbc.interfaces.JDBCRepository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AulaRepository extends BaseRespository implements JDBCRepository<Aula, Date> {

    @Override
    public int save(Aula object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO aula (data, tema, descricao) ")
                .append("VALUES (?, ?, ?)");

        try (PreparedStatement stmt =  getConnection().prepareStatement(sql.toString())) {
            stmt.setDate(1, new java.sql.Date(object.getData().getTime()));
            stmt.setString(2, object.getTema());
            stmt.setString(3, object.getDescricao());
            return stmt.executeUpdate();
        }
    }

    @Override
    public Aula update(Aula object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE aula SET tema = ?, descricao = ? ")
                .append("WHERE data = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setString(1, object.getTema());
            stmt.setString(2, object.getDescricao());
            stmt.setDate(3, new java.sql.Date(object.getData().getTime()));
            stmt.executeUpdate();
        }
        return object;
    }

    @Override
    public int delete(Date object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM aula WHERE data = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setDate(1, new java.sql.Date(object.getTime()));
            return stmt.executeUpdate();
        }
    }

    @Override
    public List<Aula> getAll() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT data, tema, descricao FROM aula");

        List<Aula> aulas = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                aulas.add(new Aula(
                        rs.getDate("data"),
                        rs.getString("tema"),
                        rs.getString("descricao")
                ));
            }
        }
        return aulas;
    }

    @Override
    public Aula getById(Date object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT data, tema, descricao FROM aula WHERE data = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setDate(1, new java.sql.Date(object.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Aula(
                            rs.getDate("data"),
                            rs.getString("tema"),
                            rs.getString("descricao")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public int deleteAll() throws SQLException {
        String sql = "DELETE FROM aula";
        try (Statement stmt = getConnection().createStatement()) {
            return stmt.executeUpdate(sql);
        }
    }
}
