package repositories;

import entities.Aluno;
import entities.Comprovante;
import entities.Mensalidade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    private AlunoRepository alunoRepository;
    private MensalidadeRepository mensalidadeRepository;
    private ComprovanteRepository comprovanteRepository;
    private ComprovanteArquivoRepository comprovanteArquivoRepository;
    private Aluno aluno;
    private Mensalidade mensalidade;
    private Comprovante comprovante;

    @BeforeEach
    void setUp() throws SQLException {
        alunoRepository = new AlunoRepository();
        mensalidadeRepository = new MensalidadeRepository();
        comprovanteRepository = new ComprovanteRepository();
        comprovanteArquivoRepository = new ComprovanteArquivoRepository();
        this.aluno = new Aluno(Long.valueOf(System.currentTimeMillis() * (int)(Math.random() * 90) + 10).intValue(), "John Doe", new java.util.Date(), 25, 'M', "123456789");
        this.mensalidade =  new Mensalidade(Long.valueOf(System.currentTimeMillis() * (int)(Math.random() * 90) + 10).intValue(),2, new BigDecimal("50.00"), false, 0, "A01", aluno.getMatricula(), "pix", "2025");
        alunoRepository.save(aluno);
        mensalidadeRepository.save(mensalidade);
    }

    @Test
    void createComprovanteTest() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("Comprovante.pdf");
         this.comprovante = new Comprovante(
                aluno.getMatricula() + mensalidade.getId(),
                aluno.getMatricula(),
                mensalidade.getId(),
                "pdf",
                "Comprovante",
                "Descrição do comprovante 1",comprovanteArquivoRepository.getBucket()
        );

        try{
            int resultArquivo = comprovanteArquivoRepository.uploadFile(comprovante.getNomeArquivo(), comprovante.getTipoArquivo(), file);
            int resultComprovanteTexto = comprovanteRepository.save(comprovante);
            mensalidade.setComprovante(1);
            Mensalidade mensalidadeAtualizada = mensalidadeRepository.update(mensalidade);
            Comprovante comprovanteAtualizado = comprovanteRepository.getById(comprovante.getId());
            assertEquals(1,resultArquivo, "Verifica se o arquivo foi enviado.");
            assertEquals(1,resultComprovanteTexto, "Verifica se o texto foi enviado.");
            assertEquals(1,mensalidadeAtualizada.getComprovante(), "Verifica se a tupla foi atualizada");
            System.out.println(mensalidadeAtualizada);
            System.out.println(comprovanteAtualizado.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void downloadComprovanteTest() throws IOException {
        InputStream file = comprovanteArquivoRepository.getObjectFile("Comprovante", "pdf");
        file.transferTo(new FileOutputStream("Comprovante1.pdf"));
        assertNotNull(file);
    }
}
