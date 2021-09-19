package entidades;

public class EstratoPeriodico {
    private String issn;
    private String nomePeriodico;
    private String nota;

    public EstratoPeriodico(String issn, String nomePeriodico, String nota) {
        this.issn = issn;
        this.nomePeriodico = nomePeriodico;
        this.nota = nota;
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
