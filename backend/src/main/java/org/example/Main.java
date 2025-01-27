package org.example;

import jdbc.ConnectionDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnectionDatabase.getConnection()) {
            // Tente criar o Statement logo após abrir a conexão
            try (Statement statement = conn.createStatement()) {
                System.out.println("Statement criado com sucesso!");

                // Executa uma query simples
                String sql = "SELECT * FROM aula";
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    System.out.println("Query executada com sucesso! Resultados:");

                    while (resultSet.next()) {
                        Date id = resultSet.getDate("data");  // Assumindo que "id" seja o nome da coluna
                        String nome = resultSet.getString("tema");  // Assumindo que "nome" seja o nome da coluna
                        System.out.println("ID: " + id + ", Nome: " + nome);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Erro ao criar Statement ou executar query: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer conexão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}