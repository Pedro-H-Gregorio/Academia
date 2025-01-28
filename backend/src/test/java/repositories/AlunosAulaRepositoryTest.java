package repositories;

import entities.Aluno;
import entities.AlunosAula;
import entities.Aula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlunosAulaRepositoryTest {

    private AlunosAulaRepository alunoAulaRepository;
    private AlunoRepository alunoRepository;
    private AulaRepository aulaRepository;
    private Aluno aluno;
    private Aula aula;


    @BeforeEach
    void setUp() throws SQLException {
        alunoAulaRepository = new AlunosAulaRepository();
        alunoRepository = new AlunoRepository();
        aulaRepository = new AulaRepository();
        this.aluno = new Aluno(Long.valueOf(System.currentTimeMillis() * (int)(Math.random() * 90) + 10).intValue(), "John Doe", new java.util.Date(), 25, 'M', "123456789");
        this.aula = new Aula(new java.sql.Date(System.currentTimeMillis() * ((int)(Math.random() * 90) + 10)), "Aula de chutes", "");
        this.alunoRepository.save(aluno);
        this.aulaRepository.save(aula);

    }

    @Test
    void testSave() throws SQLException {
        AlunosAula alunosAula = new AlunosAula(aluno.getMatricula(), aula.getData(), 1);
        int rowsAffected = alunoAulaRepository.save(alunosAula);

        assertEquals(1, rowsAffected);
    }

    @Test
    void testUpdate() throws SQLException {
        AlunosAula alunosAula = new AlunosAula(aluno.getMatricula(), aula.getData(), 1);
        alunoAulaRepository.save(alunosAula);

        alunosAula.setPresenca(0);
        AlunosAula updated = alunoAulaRepository.update(alunosAula);

        assertEquals(0, updated.getPresenca());
    }

    @Test
    void testDelete() throws SQLException {
        AlunosAula alunosAula = new AlunosAula(aluno.getMatricula(), aula.getData(), 1);
        alunoAulaRepository.save(alunosAula);

        int rowsAffected = alunoAulaRepository.delete(alunosAula.getId());

        assertEquals(1, rowsAffected);
    }

    @Test
    void testGetAll() throws SQLException {
        AlunosAula alunosAula1 = new AlunosAula(aluno.getMatricula(), aula.getData(), 1);

        alunoAulaRepository.save(alunosAula1);

        List<AlunosAula> alunosAulas = alunoAulaRepository.getAll();

        assertNotNull(alunosAulas);
        assertEquals(alunosAulas.size(), alunosAulas.size());
    }

    @Test
    void testGetById() throws SQLException {
        AlunosAula alunosAula = new AlunosAula(aluno.getMatricula(), aula.getData(), 1);
        alunoAulaRepository.save(alunosAula);

        AlunosAula fetched = alunoAulaRepository.getById(alunosAula.getId());

        assertNotNull(fetched);
        assertEquals(alunosAula.getId(), fetched.getId());
    }

    @Test
    void testDeleteAll() throws SQLException {
        AlunosAula alunosAula1 = new AlunosAula(aluno.getMatricula(), aula.getData(), 1);

        alunoAulaRepository.save(alunosAula1);
        List<AlunosAula> alunosAulas = alunoAulaRepository.getAll();
        int rowsAffected = alunoAulaRepository.deleteAll();
        assertEquals(alunosAulas.size(), rowsAffected);
    }
}
