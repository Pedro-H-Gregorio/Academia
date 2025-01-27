package entities;

import java.math.BigDecimal;

public class Mensalidade {
    private int id;
    private int quantidadeAulas;
    private BigDecimal valor;
    private Boolean situacao;
    private Integer comprovante;
    private String periodo;
    private Integer matriculaAluno;

    public Mensalidade(int id, int quantidadeAulas, BigDecimal valor, Boolean situacao, Integer comprovante, String periodo, Integer matriculaAluno) {
        this.id = id;
        this.quantidadeAulas = quantidadeAulas;
        this.valor = valor;
        this.situacao = situacao;
        this.comprovante = comprovante;
        this.periodo = periodo;
        this.matriculaAluno = matriculaAluno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeAulas() {
        return quantidadeAulas;
    }

    public void setQuantidadeAulas(int quantidadeAulas) {
        this.quantidadeAulas = quantidadeAulas;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public Integer getComprovante() {
        return comprovante;
    }

    public void setComprovante(Integer comprovante) {
        this.comprovante = comprovante;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(Integer matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mensalidade that = (Mensalidade) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Mensalidade{" +
                "id=" + id +
                ", quantidadeAulas=" + quantidadeAulas +
                ", valor=" + valor +
                ", situacao=" + situacao +
                ", comprovante=" + comprovante +
                ", periodo='" + periodo + '\'' +
                ", matriculaAluno=" + matriculaAluno +
                '}';
    }
}
