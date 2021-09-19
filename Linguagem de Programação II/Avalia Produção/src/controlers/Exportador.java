package controlers;

import entidades.*;

import jxl.*;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.write.*;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class Exportador {

    private File criarArquivo (String nomeArq, int anoInicial, int anoFinal) {
        if (anoInicial == anoFinal) {
            nomeArq += " (" + anoInicial + ")";
        } else {
            nomeArq += " (" + anoInicial + " a " + anoFinal + ")";
        }
        File arquivo = new File(nomeArq + ".xls");
        int i=1;
        while (arquivo.exists()) {
            arquivo = new File(nomeArq + " (" + i + ").xls");
            i++;
        }
        return arquivo;
    }

    public void relatorio1(TreeSet<Curriculo> curriculos, String setorId, int anoInicial, int anoFinal)
            throws IOException, WriteException {

        File arquivo = criarArquivo("Relatórios/" + setorId + " - Produção por Professor", anoInicial, anoFinal);
        WritableWorkbook workbook = Workbook.createWorkbook(arquivo);
        WritableSheet sheet = workbook.createSheet("Relatório", 0);

        String[] cabecalho = {"PROFESSOR", "ANO", "TÍTULO", "LOCAL", "AVALIAÇÃO"};

        for (int i=0; i<cabecalho.length; i++) {
            Celula celula = new Celula(i, 0, cabecalho[i]);
            celula.centralizar();
            celula.bold();
            celula.inserirBorda(Border.BOTTOM);
            celula.inserirBorda(Border.RIGHT);
            celula.mudarCor(Colour.GRAY_25);
            sheet.addCell(celula);
        }

        int row = 1;
        for (Curriculo curriculo : curriculos) {
            if (curriculo.getSetores().containsKey(setorId)) {
                TreeSet<Producao> producoes = new TreeSet<>();
                for (Producao producao : curriculo.getArtigosEmPeriodico()) {
                    int ano = producao.getAno();
                    if (ano >= anoInicial && ano <= anoFinal) {
                        producoes.add(producao);
                    }
                }

                for (Producao producao : curriculo.getArtigosEmEvento()) {
                    int ano = producao.getAno();
                    if (ano >= anoInicial && ano <= anoFinal) {
                        producoes.add(producao);
                    }
                }

                if (producoes.size() > 0) {
                    Celula celulaCurriculo = new Celula(0, row, curriculo.getNome());
                    celulaCurriculo.centralizar();
                    celulaCurriculo.posicionarNoTopo();
                    sheet.addCell(celulaCurriculo);
                    sheet.mergeCells(0, row, 0, row + producoes.size() - 1);
                    celulaCurriculo.inserirBorda(Border.BOTTOM);
                    celulaCurriculo.inserirBorda(Border.RIGHT);

                    for (Producao producao : producoes) {
                        String local;
                        if (producao instanceof ArtigoEmEvento) {
                            local = ((ArtigoEmEvento) producao).getNomeEvento();
                        } else {
                            local = ((ArtigoEmPeriodico) producao).getNomePeriodico();
                        }

                        Celula celulaAno = new Celula(1, row, producao.getAno().toString());
                        Celula celulaTitulo = new Celula(2, row, producao.getTituloArtigo());
                        Celula celulaLocal = new Celula(3, row, local);
                        Celula celulaNota = new Celula(4, row, producao.getNota());

                        celulaAno.centralizar();
                        celulaAno.inserirBorda(Border.RIGHT);
                        celulaTitulo.inserirBorda(Border.RIGHT);
                        celulaLocal.inserirBorda(Border.RIGHT);
                        celulaNota.centralizar();
                        celulaNota.inserirBorda(Border.RIGHT);

                        sheet.addCell(celulaAno);
                        sheet.addCell(celulaTitulo);
                        sheet.addCell(celulaLocal);
                        sheet.addCell(celulaNota);

                        if (producao == producoes.last()) {
                            celulaAno.inserirBorda(Border.BOTTOM);
                            celulaTitulo.inserirBorda(Border.BOTTOM);
                            celulaLocal.inserirBorda(Border.BOTTOM);
                            celulaNota.inserirBorda(Border.BOTTOM);
                        }
                        row++;
                    }

                    CellView cellView = new CellView();
                    cellView.setAutosize(true);
                    sheet.setColumnView(0, cellView);
                    sheet.setColumnView(2, cellView);
                    sheet.setColumnView(3, cellView);
                    sheet.setColumnView(4, cellView);
                }
            }
        }
        workbook.write();
        workbook.close();
    }

    public void relatorio2(TreeSet<Curriculo> curriculos, String setorId, int anoInicial, int anoFinal)
            throws IOException, WriteException {

        File arquivo = criarArquivo("Relatórios/" + setorId + " - Produção por Professor (Por Tipo)", anoInicial, anoFinal);
        WritableWorkbook workbook = Workbook.createWorkbook(arquivo);
        WritableSheet sheet = workbook.createSheet("Relatório", 0);

        String[] cabecalho = {"PROFESSOR", "TIPO", "ANO", "TÍTULO", "LOCAL", "AVALIAÇÃO"};

        for (int i=0; i<cabecalho.length; i++) {
            Celula celula = new Celula(i, 0, cabecalho[i]);
            celula.centralizar();
            celula.bold();
            celula.inserirBorda(Border.BOTTOM);
            celula.inserirBorda(Border.RIGHT);
            celula.mudarCor(Colour.GRAY_25);
            sheet.addCell(celula);
        }

        int row = 1;
        for (Curriculo curriculo : curriculos) {
            if (curriculo.getSetores().containsKey(setorId)) {
                TreeSet<ArtigoEmEvento> artigosEmEvento = new TreeSet<>();
                TreeSet<ArtigoEmPeriodico> artigosEmPeriodico = new TreeSet<>();

                for (ArtigoEmEvento artigo : curriculo.getArtigosEmEvento()) {
                    int ano = artigo.getAno();
                    if (ano >= anoInicial && ano <= anoFinal) {
                        artigosEmEvento.add(artigo);
                    }
                }

                for (ArtigoEmPeriodico artigo : curriculo.getArtigosEmPeriodico()) {
                    int ano = artigo.getAno();
                    if (ano >= anoInicial && ano <= anoFinal) {
                        artigosEmPeriodico.add(artigo);
                    }
                }

                if (artigosEmEvento.size() > 0 || artigosEmPeriodico.size() > 0) {
                    Celula celulaCurriculo = new Celula(0, row, curriculo.getNome());
                    celulaCurriculo.centralizar();
                    celulaCurriculo.posicionarNoTopo();
                    sheet.addCell(celulaCurriculo);
                    sheet.mergeCells(0, row, 0, row + artigosEmEvento.size() + artigosEmPeriodico.size() - 1);
                    celulaCurriculo.inserirBorda(Border.BOTTOM);
                    celulaCurriculo.inserirBorda(Border.RIGHT);

                    if (artigosEmEvento.size() > 0) {
                        Celula celulaTipo = new Celula(1, row, "Trabalho em anais de evento");
                        celulaTipo.centralizar();
                        celulaTipo.posicionarNoTopo();
                        sheet.addCell(celulaTipo);
                        sheet.mergeCells(1, row, 1, row + artigosEmEvento.size() - 1);
                        celulaTipo.inserirBorda(Border.BOTTOM);
                        celulaTipo.inserirBorda(Border.RIGHT);

                        for (ArtigoEmEvento artigo : artigosEmEvento) {
                            Celula celulaAno = new Celula(2, row, artigo.getAno().toString());
                            Celula celulaTitulo = new Celula(3, row, artigo.getTituloArtigo());
                            Celula celulaLocal = new Celula(4, row, artigo.getNomeEvento());
                            Celula celulaNota = new Celula(5, row, artigo.getNota());

                            celulaAno.centralizar();
                            celulaAno.inserirBorda(Border.RIGHT);
                            celulaTitulo.inserirBorda(Border.RIGHT);
                            celulaLocal.inserirBorda(Border.RIGHT);
                            celulaNota.centralizar();
                            celulaNota.inserirBorda(Border.RIGHT);

                            sheet.addCell(celulaAno);
                            sheet.addCell(celulaTitulo);
                            sheet.addCell(celulaLocal);
                            sheet.addCell(celulaNota);

                            if (artigo == artigosEmEvento.last()) {
                                celulaAno.inserirBorda(Border.BOTTOM);
                                celulaTitulo.inserirBorda(Border.BOTTOM);
                                celulaLocal.inserirBorda(Border.BOTTOM);
                                celulaNota.inserirBorda(Border.BOTTOM);
                            }
                            row++;
                        }
                    }

                    if (artigosEmPeriodico.size() > 0) {
                        Celula celulaTipo = new Celula(1, row, "Artigo Publicado");
                        celulaTipo.centralizar();
                        celulaTipo.posicionarNoTopo();
                        sheet.addCell(celulaTipo);
                        sheet.mergeCells(1, row, 1, row + artigosEmPeriodico.size() - 1);
                        celulaTipo.inserirBorda(Border.BOTTOM);
                        celulaTipo.inserirBorda(Border.RIGHT);

                        for (ArtigoEmPeriodico artigo : artigosEmPeriodico) {
                            Celula celulaAno = new Celula(2, row, artigo.getAno().toString());
                            Celula celulaTitulo = new Celula(3, row, artigo.getTituloArtigo());
                            Celula celulaLocal = new Celula(4, row, artigo.getNomePeriodico());
                            Celula celulaNota = new Celula(5, row, artigo.getNota());

                            celulaAno.centralizar();
                            celulaAno.inserirBorda(Border.RIGHT);
                            celulaTitulo.inserirBorda(Border.RIGHT);
                            celulaLocal.inserirBorda(Border.RIGHT);
                            celulaNota.centralizar();
                            celulaNota.inserirBorda(Border.RIGHT);

                            sheet.addCell(celulaAno);
                            sheet.addCell(celulaTitulo);
                            sheet.addCell(celulaLocal);
                            sheet.addCell(celulaNota);

                            if (artigo == artigosEmPeriodico.last()) {
                                celulaAno.inserirBorda(Border.BOTTOM);
                                celulaTitulo.inserirBorda(Border.BOTTOM);
                                celulaLocal.inserirBorda(Border.BOTTOM);
                                celulaNota.inserirBorda(Border.BOTTOM);
                            }
                            row++;
                        }
                    }

                    CellView cellView = new CellView();
                    cellView.setAutosize(true);
                    sheet.setColumnView(0, cellView);
                    sheet.setColumnView(1, cellView);
                    sheet.setColumnView(3, cellView);
                    sheet.setColumnView(4, cellView);
                    sheet.setColumnView(5, cellView);
                }
            }
        }
        workbook.write();
        workbook.close();
    }

    public void sinteseQualis(TreeSet<Curriculo> curriculos, String setorId, int anoInicial, int anoFinal)
            throws IOException, WriteException {

        File arquivo = criarArquivo("Relatórios/" + setorId + " - Síntese Quantitativo por Professor", anoInicial, anoFinal);

        WritableWorkbook workbook = Workbook.createWorkbook(arquivo);
        WritableSheet sheet = workbook.createSheet("Relatório", 0);

        String[] cabecalho = {"PROFESSOR", "NOTA", "QUANTIDADE"};

        for (int i=0; i<cabecalho.length; i++) {
            Celula celula = new Celula(i, 0, cabecalho[i]);
            celula.centralizar();
            celula.bold();
            celula.inserirBorda(Border.BOTTOM);
            celula.inserirBorda(Border.RIGHT);
            celula.mudarCor(Colour.GRAY_25);
            sheet.addCell(celula);
        }

        int row = 1;
        for (Curriculo curriculo : curriculos) {
            if (curriculo.getSetores().containsKey(setorId)) {
                TreeMap<String, Integer> qualis = new TreeMap<>();
                for (Producao producao : curriculo.getArtigosEmPeriodico()) {
                    int ano = producao.getAno();
                    if (ano >= anoInicial && ano <= anoFinal) {
                        String nota = producao.getNota();
                        if (qualis.containsKey(nota)) {
                            qualis.put(nota, qualis.get(nota) + 1);
                        } else if (!nota.equals("")) {
                            qualis.put(nota, 1);
                        }
                    }
                }

                for (Producao producao : curriculo.getArtigosEmEvento()) {
                    int ano = producao.getAno();
                    if (ano >= anoInicial && ano <= anoFinal) {
                        String nota = producao.getNota();
                        if (qualis.containsKey(nota)) {
                            qualis.put(nota, qualis.get(nota) + 1);
                        } else if (!nota.equals("")) {
                            qualis.put(nota, 1);
                        }
                    }
                }

                if (qualis.size() > 0) {
                    Celula celulaCurriculo = new Celula(0, row, curriculo.getNome());
                    celulaCurriculo.centralizar();
                    celulaCurriculo.posicionarNoCentro();
                    sheet.addCell(celulaCurriculo);
                    sheet.mergeCells(0, row, 0, row + qualis.size() - 1);
                    celulaCurriculo.inserirBorda(Border.BOTTOM);
                    celulaCurriculo.inserirBorda(Border.RIGHT);

                    for (String nota : qualis.keySet()) {
                        Celula celulaNota = new Celula(1, row, nota);
                        Celula celulaQuant = new Celula(2, row, qualis.get(nota).toString());

                        celulaNota.centralizar();
                        celulaNota.inserirBorda(Border.RIGHT);
                        celulaQuant.centralizar();
                        celulaQuant.inserirBorda(Border.RIGHT);

                        sheet.addCell(celulaNota);
                        sheet.addCell(celulaQuant);

                        if (nota.equals(qualis.lastKey())) {
                            celulaNota.inserirBorda(Border.BOTTOM);
                            celulaQuant.inserirBorda(Border.BOTTOM);
                        }
                        row++;
                    }

                    CellView cellView = new CellView();
                    cellView.setAutosize(true);
                    sheet.setColumnView(0, cellView);
                    sheet.setColumnView(2, cellView);
                }
            }
        }
        workbook.write();
        workbook.close();
    }
}
