package entities;

public class Comprovante {
    private int id;
    private int matriculaAluno;
    private int mensalidadeId;
    private String tipoArquivo;
    private String urlArquivo;
    private String nomeArquivo;
    private String descricaoArquivo;
    private String bucketArquivo;

    public Comprovante(int id, int matriculaAluno, int mensalidadeId, String tipoArquivo, String urlArquivo, String nomeArquivo, String descricaoArquivo, String bucketArquivo) {
        this.id = id;
        this.matriculaAluno = matriculaAluno;
        this.mensalidadeId = mensalidadeId;
        this.tipoArquivo = tipoArquivo;
        this.urlArquivo = urlArquivo;
        this.nomeArquivo = nomeArquivo;
        this.descricaoArquivo = descricaoArquivo;
        this.bucketArquivo = bucketArquivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(int matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public int getMensalidadeId() {
        return mensalidadeId;
    }

    public void setMensalidadeId(int mensalidadeId) {
        this.mensalidadeId = mensalidadeId;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public void setUrlArquivo(String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getDescricaoArquivo() {
        return descricaoArquivo;
    }

    public void setDescricaoArquivo(String descricaoArquivo) {
        this.descricaoArquivo = descricaoArquivo;
    }

    public String getBucketArquivo() {
        return bucketArquivo;
    }

    public void setBucketArquivo(String bucketArquivo) {
        this.bucketArquivo = bucketArquivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comprovante that = (Comprovante) o;
        return id == that.id && matriculaAluno == that.matriculaAluno && mensalidadeId == that.mensalidadeId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + matriculaAluno;
        result = 31 * result + mensalidadeId;
        return result;
    }

    @Override
    public String toString() {
        return "Comprovante{" +
                "id=" + id +
                ", matriculaAluno=" + matriculaAluno +
                ", mensalidadeId=" + mensalidadeId +
                ", tipoArquivo='" + tipoArquivo + '\'' +
                ", urlArquivo='" + urlArquivo + '\'' +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", descricaoArquivo='" + descricaoArquivo + '\'' +
                ", bucketArquivo='" + bucketArquivo + '\'' +
                '}';
    }
}
