package repositories;

import entities.Comprovante;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ComprovanteRepositoryTest {

    private ComprovanteRepository comprovanteRepository;

    @BeforeEach
    void setUp() {
        comprovanteRepository = new ComprovanteRepository();
    }

    @Test
    @Order(1)
    void testDeleteAll() throws SQLException {
        List<Comprovante> comprovantes = comprovanteRepository.getAll();
        int result = comprovanteRepository.deleteAll();

        if (!comprovantes.isEmpty()) {
            System.out.println("Existem comprovantes antes de excluir");
            List<Comprovante> comprovantesExcluidos = comprovanteRepository.getAll();
            assertEquals(0, comprovantesExcluidos.size());
            assertEquals(1, result);
        } else {
            System.out.println("Não existem comprovantes antes de excluir");
            assertEquals(0, result);
        }
    }

    @Test
    @Order(2)
    void testSave() throws SQLException {
        Comprovante comprovante = new Comprovante(
                1,
                1001,
                2001,
                "PDF",
                "Comprovante1",
                "Descrição do comprovante 1",
                "bucket1"
        );

        int result = comprovanteRepository.save(comprovante);
        assertEquals(1, result);
    }

    @Test
    @Order(3)
    void testGetAll() {
        List<Comprovante> comprovantes = comprovanteRepository.getAll();

        assertNotNull(comprovantes);
        assertFalse(comprovantes.isEmpty(), "A lista de comprovantes está vazia!");
        Comprovante comprovante = comprovantes.get(0);
        System.out.println(comprovante);
    }

    @Test
    @Order(4)
    void testGetById() throws SQLException {
        Comprovante comprovante = comprovanteRepository.getById(1);

        assertNotNull(comprovante, "O comprovante não foi encontrado!");
        assertEquals(1, comprovante.getId());
        assertEquals(1001, comprovante.getMatriculaAluno());
        assertEquals("PDF", comprovante.getTipoArquivo());
    }

    @Test
    @Order(5)
    void testUpdate() throws SQLException {
        Comprovante comprovante = new Comprovante(
                1,
                1001,
                2001,
                "PNG",
                "Comprovante1Updated",
                "Descrição atualizada do comprovante 1",
                "bucket1Updated"
        );

        Comprovante updatedComprovante = comprovanteRepository.update(comprovante);

        assertNotNull(updatedComprovante, "O comprovante não foi atualizado!");
        assertEquals("PNG", updatedComprovante.getTipoArquivo());
    }

    @Test
    @Order(6)
    void testDelete() throws SQLException {
        int result = comprovanteRepository.delete(1);

        assertEquals(1, result, "O comprovante não foi excluído!");
        Comprovante deletedComprovante = comprovanteRepository.getById(1);
        assertNull(deletedComprovante, "O comprovante ainda está presente após exclusão!");
    }
}
