package entities;

import java.util.Date;
import java.util.Optional;

public class Aula {
    private Date data;
    private String tema;
    private String descricao;

    public Aula(Date data, String tema, String descricao) {
        this.data = data;
        this.tema = tema;
        Optional<String> descricaoOptional = descricao == null ? Optional.empty() : Optional.of(descricao);
        this.descricao = descricaoOptional.orElse("");
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aula aula = (Aula) o;
        return data.equals(aula.data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public String toString() {
        return "Aula{" +
                "data=" + data +
                ", tema='" + tema + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
