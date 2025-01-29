package repositories;

import entities.Aluno;
import entities.Mensalidade;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MensalidadeRepositoryTest {

    private MensalidadeRepository mensalidadeRepository;
    private AlunoRepository alunoRepository;
    private Aluno aluno;

    @BeforeEach
    void setUp() throws SQLException {
        mensalidadeRepository = new MensalidadeRepository();
        alunoRepository = new AlunoRepository();
        this.aluno = new Aluno(Long.valueOf(System.currentTimeMillis()).intValue(), "John Doe", new java.util.Date(), 25, 'M', "123456789");
        alunoRepository.save(aluno);
    }

    @Test
    @Order(1)
    void testSave() throws SQLException {
        Mensalidade mensalidade = new Mensalidade(1,2, new BigDecimal("50.00"), false, 0, "A01", aluno.getMatricula(), "pix", "2025");
        int rowsAffected = mensalidadeRepository.save(mensalidade);

        assertEquals(1, rowsAffected, "A inserção da mensalidade deveria afetar uma linha.");
    }

    @Test
    @Order(2)
    void testUpdate() throws SQLException {
        Mensalidade mensalidade = new Mensalidade(2,2, new BigDecimal("50.00"), true, 0, "A02", aluno.getMatricula(), "pix", "2025");
        mensalidadeRepository.save(mensalidade);
        mensalidade.setQuantidadeAulas(12);
        mensalidade.setValor(new BigDecimal("600.00"));

        Mensalidade updated = mensalidadeRepository.update(mensalidade);

        assertEquals(12, updated.getQuantidadeAulas(), "O número de aulas deveria ser atualizado.");
        assertEquals(new BigDecimal("600.00"), updated.getValor(), "O valor da mensalidade deveria ser atualizado.");
    }

    @Test
    @Order(5)
    void testDelete() throws SQLException {
        Mensalidade mensalidade = new Mensalidade(Long.valueOf(System.currentTimeMillis()).intValue(), 10, new BigDecimal("500.00"), true, 0, "A03", aluno.getMatricula(), "pix", "2025");
        mensalidadeRepository.save(mensalidade);

        int rowsAffected = mensalidadeRepository.delete(mensalidade.getId());

        assertEquals(1, rowsAffected, "A exclusão da mensalidade deveria afetar uma linha.");
    }

    @Test
    @Order(3)
    void testGetAll() throws SQLException {
        Mensalidade mensalidade1 = new Mensalidade(Long.valueOf(System.currentTimeMillis()).intValue(), 10, new BigDecimal("500.00"), true, 0, "A12", aluno.getMatricula(), "pix", "2025");
        Mensalidade mensalidade2 = new Mensalidade(Long.valueOf(System.currentTimeMillis()).intValue() + 1, 15, new BigDecimal("750.00"), false, 0, "A11", aluno.getMatricula(), "pix", "2025");

        mensalidadeRepository.save(mensalidade1);
        mensalidadeRepository.save(mensalidade2);

        List<Mensalidade> mensalidades = mensalidadeRepository.getAll();

        assertNotNull(mensalidades, "A lista de mensalidades não deve ser nula.");
        assertTrue(mensalidades.size() > 0, "A lista de mensalidades deve conter itens.");
    }

    @Test
    @Order(4)
    void testGetById() throws SQLException {
        Mensalidade mensalidade = new Mensalidade(0, 10, new BigDecimal("500.00"), true, 0, "A07", aluno.getMatricula(), "pix", "2025");
        mensalidadeRepository.save(mensalidade);

        Mensalidade fetched = mensalidadeRepository.getById(mensalidade.getId());
        assertNotNull(fetched, "A mensalidade com o ID especificado deveria ser encontrada.");
        assertEquals(mensalidade.getId(), fetched.getId(), "O ID da mensalidade deve ser o mesmo.");
    }

    @Test
    @Order(6)
    void testDeleteAll() throws SQLException {
        Mensalidade mensalidade1 = new Mensalidade(Long.valueOf(System.currentTimeMillis()).intValue(), 10, new BigDecimal("500.00"), true, 0, "A08", aluno.getMatricula(), "pix", "2025");
        Mensalidade mensalidade2 = new Mensalidade(Long.valueOf(System.currentTimeMillis()).intValue() + 1, 15, new BigDecimal("750.00"), false, 0, "A06", aluno.getMatricula(), "pix", "2025");

        mensalidadeRepository.save(mensalidade1);
        mensalidadeRepository.save(mensalidade2);
        List<Mensalidade> mensalidades = mensalidadeRepository.getAll();

        int rowsAffected = mensalidadeRepository.deleteAll();

        assertEquals(mensalidades.size(), rowsAffected, "A exclusão de todas as mensalidades deveria afetar duas linhas.");
    }


}

