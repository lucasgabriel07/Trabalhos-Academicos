package entidades;

public class EstratoEvento {
    private String sigla;
    private String nomeEvento;
    private String nota;

    public EstratoEvento(String sigla, String nomeEvento, String nota) {
        this.sigla = sigla;
        this.nomeEvento = nomeEvento;
        this.nota = nota;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
