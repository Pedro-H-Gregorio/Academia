package repositories;

import entities.Mensalidade;
import jdbc.BaseRespository;
import jdbc.interfaces.JDBCRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensalidadeRepository extends BaseRespository implements JDBCRepository<Mensalidade, Integer> {

    @Override
    public int save(Mensalidade object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO public.mensalidade (id ,quantidade_aula, valor, situacao, comprovante, periodo, matricula_aluno, tipo_pagamento, ano) ")
                .append("VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setInt(1, object.getId());
            stmt.setInt(2, object.getQuantidadeAulas());
            stmt.setBigDecimal(3, object.getValor());
            stmt.setBoolean(4, object.getSituacao());
            stmt.setInt(5, object.getComprovante());
            stmt.setString(6, object.getPeriodo());
            stmt.setInt(7, object.getMatriculaAluno());
            stmt.setString(8, object.getTipoPagamento());
            stmt.setString(9, object.getAno());
            return stmt.executeUpdate();
        }
    }

    @Override
    public Mensalidade update(Mensalidade object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE public.mensalidade SET quantidade_aula = ?, valor = ?, situacao = ?, comprovante = ?, periodo = ?, matricula_aluno = ?, tipo_pagamento = ?, ano = ? ")
                .append("WHERE id = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setInt(1, object.getQuantidadeAulas());
            stmt.setBigDecimal(2, object.getValor());
            stmt.setBoolean(3, object.getSituacao());
            stmt.setInt(4, object.getComprovante());
            stmt.setString(5, object.getPeriodo());
            stmt.setInt(6, object.getMatriculaAluno());
            stmt.setString(7, object.getTipoPagamento());
            stmt.setString(8, object.getAno());
            stmt.setInt(9, object.getId());
            stmt.executeUpdate();
        }
        return object;
    }

    @Override
    public int delete(Integer object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM public.mensalidade WHERE id = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setInt(1, object);
            return stmt.executeUpdate();
        }
    }

    @Override
    public List<Mensalidade> getAll() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, quantidade_aula, valor, situacao, comprovante, periodo, matricula_aluno, tipo_pagamento, ano  FROM public.mensalidade");

        List<Mensalidade> mensalidades = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                mensalidades.add(new Mensalidade(
                        rs.getInt("id"),
                        rs.getInt("quantidade_aula"),
                        rs.getBigDecimal("valor"),
                        rs.getBoolean("situacao"),
                        rs.getInt("comprovante"),
                        rs.getString("periodo"),
                        rs.getInt("matricula_aluno"),
                        rs.getString("tipo_pagamento"),
                        rs.getString("ano")
                ));
            }
        }
        return mensalidades;
    }

    @Override
    public Mensalidade getById(Integer object) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, quantidade_aula, valor, situacao, comprovante, periodo, matricula_aluno, tipo_pagamento, ano FROM public.mensalidade WHERE id = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            stmt.setInt(1, object);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Mensalidade(
                                    rs.getInt("id"),
                                    rs.getInt("quantidade_aula"),
                                    rs.getBigDecimal("valor"),
                                    rs.getBoolean("situacao"),
                                    rs.getInt("comprovante"),
                                    rs.getString("periodo"),
                                    rs.getInt("matricula_aluno"),
                                    rs.getString("tipo_pagamento"),
                                    rs.getString("ano")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public int deleteAll() throws SQLException {
        String sql = "DELETE FROM public.mensalidade";
        try (Statement stmt = getConnection().createStatement()) {
            return stmt.executeUpdate(sql);
        }
    }
}
