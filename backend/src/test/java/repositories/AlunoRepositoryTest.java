package repositories;

import entities.Aluno;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlunoRepositoryTest {
    private AlunoRepository alunoRepository;

    @BeforeEach
    void setUp() {
        alunoRepository = new AlunoRepository();
    }

    @Test
    @Order(1)
    void testDeleteAll() throws SQLException {
        List<Aluno> alunos = alunoRepository.getAll();
        int result = alunoRepository.deleteAll();
        if (!alunos.isEmpty()) {
            System.out.println("Existe alunos antes de excluir");
            List<Aluno> alunosExcluidos = alunoRepository.getAll();
            assertEquals(alunosExcluidos.size(), result);
        } else {
            System.out.println("Não existe alunos antes de excluir");
            assertEquals(0, result);
        }
    }

    @Test
    @Order(2)
    void testSave() throws SQLException {
        Aluno aluno = new Aluno(123, "John Doe", new java.util.Date(), 25, 'M', "123456789");
        int result = alunoRepository.save(aluno);

        assertEquals(1, result);
    }

    @Test
    @Order(3)
    void testGetAll() throws SQLException {
        List<Aluno> alunos = alunoRepository.getAll();

        assertEquals(1, alunos.size());
        Aluno aluno = alunos.get(0);
        assertEquals(123, aluno.getMatricula());
        assertEquals("John Doe", aluno.getNome());
        assertEquals(25, aluno.getIdade());
        assertEquals('M', aluno.getSexo());
        assertEquals("123456789", aluno.getContato());
    }

    @Test
    @Order(4)
    void testGetById() throws SQLException {
        Aluno aluno = alunoRepository.getById(123);

        assertNotNull(aluno);
        assertEquals(123, aluno.getMatricula());
        assertEquals("John Doe", aluno.getNome());
        assertEquals(25, aluno.getIdade());
        assertEquals('M', aluno.getSexo());
        assertEquals("123456789", aluno.getContato());
    }

    @Test
    @Order(5)
    void testUpdate() throws SQLException {
        Aluno aluno = new Aluno(123, "Jane Doe", new java.util.Date(), 22, 'F', "987654321");

        Aluno result = alunoRepository.update(aluno);

        assertNotNull(result);
        assertEquals(123, result.getMatricula());
        assertEquals("Jane Doe", result.getNome());
    }

    @Test
    @Order(6)
    void testDelete() throws SQLException {
        int result = alunoRepository.delete(123);

        assertEquals(1, result);
    }
}
