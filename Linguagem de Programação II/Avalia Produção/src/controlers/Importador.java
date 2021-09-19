package controlers;

import entidades.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Importador {

    public TreeMap<String, Curriculo> importarCurriculos(String arquivo) throws IOException {
        TreeMap<String, Curriculo> curriculos = new TreeMap<>();
        BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha = leitor.readLine();

        while ((linha = leitor.readLine()) != null) {
            try {
                String[] v = linha.split(";");
                String id = v[0];
                String nome = v[1];
                curriculos.put(id, new Curriculo(id, nome));
            } catch (Exception e) {
                System.out.println("Erro ao importar linha " + linha);
            }
        }
        leitor.close();

        return curriculos;
    }

    public void importarArtigosEmEvento(
            String arquivo, TreeMap<String, Curriculo> curriculos, HashSet<EstratoEvento> estratos)
            throws IOException {
        ArrayList<ArtigoEmEvento> artigos = new ArrayList<>();
        BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha = leitor.readLine();

        while ((linha = leitor.readLine()) != null) {
            try {
                String[] v = linha.split(";");
                String curriculoId = v[0];
                String tituloArtigo = v[1];
                String nomeEvento = v[2];
                String tituloAnais = v[3];
                Integer ano = Integer.parseInt(v[4]);
                ArtigoEmEvento artigo = new ArtigoEmEvento(curriculoId, tituloArtigo, ano, nomeEvento, tituloAnais);
                curriculos.get(curriculoId).addArtigoEmEvento(artigo);
                artigos.add(artigo);
                String nota = buscarNotaEvento(artigo, estratos);
                artigo.setNota(nota);
            } catch (Exception e) {
                System.out.println("Erro ao importar linha " + linha);
            }
        }
        leitor.close();
    }

    public void importarArtigosEmPeriodico(
            String arquivo, TreeMap<String, Curriculo> curriculos, TreeMap<String, EstratoPeriodico> estratos)
            throws IOException {
        ArrayList<ArtigoEmPeriodico> artigos = new ArrayList<>();
        BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha = leitor.readLine();

        while ((linha = leitor.readLine()) != null) {
            try {
                String[] v = linha.split(";");
                String curriculoId = v[0];
                String tituloArtigo = v[1];
                String issn = v[2];
                String nomePeriodico = v[3];
                Integer ano = Integer.parseInt(v[4]);
                ArtigoEmPeriodico artigo = new ArtigoEmPeriodico(curriculoId, tituloArtigo, ano, issn, nomePeriodico);
                Curriculo c = curriculos.get(curriculoId);
                curriculos.get(curriculoId).addArtigoEmPeriodico(artigo);
                artigos.add(artigo);
                String nota = buscarNotaPeriodico(artigo, estratos);
                artigo.setNota(nota);
            }
            catch (Exception e) {
                System.out.println("Erro ao importar linha " + linha);
            }
        }
        leitor.close();
    }

    public TreeMap<String, Setor> importarSetores(String arquivo) throws IOException {
        TreeMap<String, Setor> setores = new TreeMap<>();
        BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha = leitor.readLine();

        while((linha = leitor.readLine()) != null) {
            try {
                String[] v = linha.split(";");
                String id = v[0];
                String nome = v[1];
                String sigla = v[2];
                setores.put(id, new Setor(id, nome, sigla));
            } catch (Exception e) {
                System.out.println("Erro ao importar linha " + linha);
            }
        }
        leitor.close();

        return setores;
    }

    public void addSetores(String arquivo, TreeMap<String, Setor> setores,
                           TreeMap<String, Curriculo> curriculos) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha = leitor.readLine();

        while ((linha = leitor.readLine()) != null) {
            try {
                String[] v = linha.split(";");
                String curriculoId = v[0];
                String setorId = v[1];
                Curriculo curriculo = curriculos.get(curriculoId);
                Setor setor = setores.get(setorId);
                curriculo.addSetor(setor);
            } catch (Exception e) {
                System.out.println("Erro ao importar linha " + linha);
            }
        }
        leitor.close();
    }

    public TreeMap<String, EstratoPeriodico> importarEstratosPeriodico(String arquivo) throws IOException {
        TreeMap<String, EstratoPeriodico> estratos = new TreeMap<>();
        BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha = leitor.readLine();

        while ((linha = leitor.readLine()) != null) {
            try {
                String[] v = linha.split(";");
                String issn = v[0];
                String nome = v[1];
                String nota = v[2];
                estratos.put(issn, new EstratoPeriodico(issn, nome, nota));
            } catch (Exception e) {
                System.out.println("Erro ao importar linha " + linha);
            }
        }
        leitor.close();

        return estratos;
    }

    public HashSet<EstratoEvento> importarEstratosEvento(String arquivo) throws IOException {
        HashSet<EstratoEvento> estratos = new HashSet<>();
        BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha = leitor.readLine();

        while ((linha = leitor.readLine()) != null) {
            try {
                String[] v = linha.split(";");
                String sigla = v[0];
                String nome = v[1];
                String nota = v[2];
                estratos.add(new EstratoEvento(sigla, nome, nota));
            } catch (Exception e) {
                System.out.println("Erro ao importar linha " + linha);
            }
        }
        leitor.close();

        return estratos;
    }

    public String buscarNotaPeriodico(ArtigoEmPeriodico artigo, TreeMap<String, EstratoPeriodico> estratos) {
        try {
            return estratos.get(artigo.getIssn()).getNota();
        } catch (Exception e) {
            return "";
        }
    }

    public String buscarNotaEvento(ArtigoEmEvento artigo, HashSet<EstratoEvento> estratos) {
        String nomeEvento = artigo.getNomeEvento();
        String tituloAnais = artigo.getTituloAnais();
        for (EstratoEvento estrato : estratos) {
            if (containsIgnoreCase(nomeEvento, estrato.getSigla()) ||
                    containsIgnoreCase(nomeEvento, estrato.getNomeEvento()) ||
                    containsIgnoreCase(tituloAnais, estrato.getSigla()) ||
                    containsIgnoreCase(tituloAnais, estrato.getNomeEvento())) {
                return estrato.getNota();
            }
        }
        return "";
    }

    public boolean containsIgnoreCase(String str1, String str2) {
        return str1.toLowerCase().contains(str2.toLowerCase());
    }

}
