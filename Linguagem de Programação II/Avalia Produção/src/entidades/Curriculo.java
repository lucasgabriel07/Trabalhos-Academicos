package entidades;

import java.util.ArrayList;
import java.util.TreeMap;

public class Curriculo implements Comparable<Curriculo> {
    private String id;
    private String nome;
    private ArrayList<ArtigoEmEvento> artigosEmEvento;
    private ArrayList<ArtigoEmPeriodico> artigosEmPeriodico;
    private TreeMap<String, Setor> setores;

    public Curriculo(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.artigosEmEvento = new ArrayList<>();
        this.artigosEmPeriodico = new ArrayList<>();
        this.setores = new TreeMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<ArtigoEmEvento> getArtigosEmEvento() {
        return artigosEmEvento;
    }

    public ArrayList<ArtigoEmPeriodico> getArtigosEmPeriodico() {
        return artigosEmPeriodico;
    }

    public void addArtigoEmEvento(ArtigoEmEvento artigo) {
        artigosEmEvento.add(artigo);
    }

    public void addArtigoEmPeriodico(ArtigoEmPeriodico artigo) {
        artigosEmPeriodico.add(artigo);
    }

    public TreeMap<String, Setor> getSetores() {
        return setores;
    }

    public void addSetor(Setor setor) {
        setores.put(setor.getId(), setor);
    }

    @Override
    public int compareTo(Curriculo o) {
        return nome.compareTo(o.getNome());
    }
}
