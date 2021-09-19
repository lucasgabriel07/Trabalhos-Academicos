package entidades;

public class ArtigoEmEvento extends Producao {
    private String nomeEvento;
    private String tituloAnais;

    public ArtigoEmEvento(String curriculoId, String tituloArtigo,
                          Integer ano, String nomeEvento, String tituloAnais) {
        super(curriculoId, tituloArtigo, ano);
        this.nomeEvento = nomeEvento;
        this.tituloAnais = tituloAnais;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getTituloAnais() {
        return tituloAnais;
    }

    public void setTituloAnais(String tituloAnais) {
        this.tituloAnais = tituloAnais;
    }


    @Override
    public String toString() {
        return "ArtigoEmEvento{" +
                "nomeEvento='" + nomeEvento + '\'' +
                ", tituloAnais='" + tituloAnais + '\'' +
                ", curriculoId='" + curriculoId + '\'' +
                ", tituloArtigo='" + tituloArtigo + '\'' +
                ", ano=" + ano +
                '}';
    }
}
