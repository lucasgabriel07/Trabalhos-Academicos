package entidades;

public class ArtigoEmPeriodico extends Producao {
    private String issn;
    private String nomePeriodico;

    public ArtigoEmPeriodico(String curriculoId, String tituloArtigo,
                             Integer ano, String issn, String nomePeriodico) {
        super(curriculoId, tituloArtigo, ano);
        this.issn = issn;
        this.nomePeriodico = nomePeriodico;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getNomePeriodico() {
        return nomePeriodico;
    }

    public void setNomePeriodico(String nomePeriodico) {
        this.nomePeriodico = nomePeriodico;
    }

    @Override
    public String toString() {
        return "ArtigoEmPeriodico{" +
                "issn='" + issn + '\'' +
                ", nomePeriodico='" + nomePeriodico + '\'' +
                ", curriculoId='" + curriculoId + '\'' +
                ", tituloArtigo='" + tituloArtigo + '\'' +
                ", ano=" + ano +
                '}';
    }
}
