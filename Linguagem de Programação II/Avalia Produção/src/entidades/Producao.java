package entidades;

public abstract class Producao implements Comparable<Producao> {
    protected String curriculoId;
    protected String tituloArtigo;
    protected Integer ano;
    protected String nota;

    public Producao(String curriculoId, String tituloArtigo, Integer ano) {
        this.curriculoId = curriculoId;
        this.tituloArtigo = tituloArtigo;
        this.ano = ano;
        this.nota = null;
    }

    public String getCurriculoId() {
        return curriculoId;
    }

    public void setCurriculoId(String curriculoId) {
        this.curriculoId = curriculoId;
    }

    public String getTituloArtigo() {
        return tituloArtigo;
    }

    public void setTituloArtigo(String tituloArtigo) {
        this.tituloArtigo = tituloArtigo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Producao{" +
                "curriculoId='" + curriculoId + '\'' +
                ", tituloArtigo='" + tituloArtigo + '\'' +
                ", ano=" + ano +
                '}';
    }

    @Override
    public int compareTo(Producao o) {
        if (o.getAno() - this.ano != 0) {
            return o.getAno() - this.ano;
        }
        return this.tituloArtigo.compareTo(o.getTituloArtigo());
    }
}
