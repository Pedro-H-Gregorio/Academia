package entities;

import java.util.Date;

public class AlunosAula {
    private Integer matricula;
    private Date idAula;
    private Integer presenca;
    private String id;

    public AlunosAula(Integer matricula, Date idAula, Integer presenca) {
        this.id = matricula.toString() + "(+)" + idAula.toString();
        this.matricula = matricula;
        this.idAula = idAula;
        this.presenca = presenca;
    }

    public AlunosAula(String id, Integer matricula, Date idAula, Integer presenca) {
        this.id = id;
        this.matricula = matricula;
        this.idAula = idAula;
        this.presenca = presenca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Date getIdAula() {
        return idAula;
    }

    public void setIdAula(Date idAula) {
        this.idAula = idAula;
    }

    public Integer getPresenca() {
        return presenca;
    }

    public void setPresenca(Integer presenca) {
        this.presenca = presenca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlunosAula that = (AlunosAula) o;
        return matricula.equals(that.matricula) && idAula.equals(that.idAula);
    }

    @Override
    public int hashCode() {
        int result = matricula.hashCode();
        result = 31 * result + idAula.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AlunosAula{" +
                "matricula=" + matricula +
                ", idAula=" + idAula +
                ", presenca=" + presenca +
                '}';
    }
}
