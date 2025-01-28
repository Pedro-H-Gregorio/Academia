package repositories;

import entities.Aula;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AulaRepositoryTest {
    private AulaRepository aulaRepository;
    private Date data = new Date(System.currentTimeMillis());

    @BeforeEach
    void setUp() {
        aulaRepository = new AulaRepository();
    }

    @Test
    @Order(1)
    void testDeleteAll() throws SQLException {
        List<Aula> aulas = aulaRepository.getAll();
        int result = aulaRepository.deleteAll();
        if (!aulas.isEmpty()) {
            System.out.println("Existe aulas antes de excluir");
            assertEquals(1, result);
        } else {
            System.out.println("Não existe aulas antes de excluir");
            assertEquals(0, result);
        }
    }

    @Test
    @Order(2)
    void testSave() throws SQLException {
        Aula aula = new Aula(new java.util.Date(data.getTime()), "Aula de chutes", "");
        int result = aulaRepository.save(aula);

        assertEquals(1, result);
    }

    @Test
    @Order(3)
    void testGetAll() throws SQLException {
        List<Aula> aulas = aulaRepository.getAll();

        assertEquals(1, aulas.size());
        Aula aula = aulas.get(0);
        System.out.println(aula);
    }

    @Test
    @Order(4)
    void testGetById() throws SQLException {
        Aula aula = aulaRepository.getById(data);

        assertNotNull(aula);
        assertEquals(new java.sql.Date(data.getTime()).toString(), aula.getData().toString());
        assertEquals("Aula de chutes", aula.getTema());
        assertEquals("", aula.getDescricao());
    }

    @Test
    @Order(5)
    void testUpdate() throws SQLException {
        Aula aula = new Aula(data, "Aula de chutes", "A aula foi sobre chute costela e pipes");

        Aula result = aulaRepository.update(aula);

        assertNotNull(result);
        assertEquals(data, result.getData());
        assertEquals("A aula foi sobre chute costela e pipes", result.getDescricao());
    }

    @Test
    @Order(6)
    void testDelete() throws SQLException {
        int result = aulaRepository.delete(data);

        assertEquals(1, result);
    }
}
