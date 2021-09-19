import controlers.Exportador;
import controlers.Importador;
import entidades.ArtigoEmEvento;
import entidades.ArtigoEmPeriodico;
import entidades.Curriculo;
import entidades.Setor;
import gui.GUI;
import jxl.write.WriteException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

    public static void exportarArquivos(GUI gui, TreeSet<Curriculo> curriculos) {
        Exportador exp = new Exportador();
        int anoInicial = Integer.parseInt(gui.getComboBoxInicio().getSelectedItem()+"");
        int anoFinal = Integer.parseInt(gui.getComboBoxFim().getSelectedItem()+"");
        String setorId = (gui.getComboBoxSetor().getSelectedItem()+"").split(" ")[0];

        try {
            if (anoInicial <= anoFinal) {
                if (gui.getRelatorio1CheckBox().isSelected()) {
                    exp.relatorio1(curriculos, setorId, anoInicial, anoFinal);
                }
                if (gui.getRelatorio2CheckBox().isSelected()) {
                    exp.relatorio2(curriculos, setorId, anoInicial, anoFinal);
                }
                if (gui.getSinteseCheckBox().isSelected()) {
                    exp.sinteseQualis(curriculos, setorId, anoInicial, anoFinal);
                }
                JOptionPane.showMessageDialog(gui.getPanel1(), "Relatórios salvos com sucesso!");
            } else {
                JOptionPane.showMessageDialog(gui.getPanel1(), "Erro! Selecione um intervalo válido!");
            }
        } catch (WriteException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String ARTIGOS_EM_EVENTO_CSV = "arquivos/artigos_em_evento.csv";
        String ARTIGO_EM_PERIODICO_CSV = "arquivos/artigos_em_periodico.csv";
        String CURRICULO_CSV = "arquivos/curriculo.csv";
        String CURRICULO_SETOR_CSV = "arquivos/curriculo_setor.csv";
        String ESTRATO_ARTIGO_EM_EVENTO_CSV = "arquivos/estrato_artigo_em_evento.csv";
        String ESTRATO_ARTIGO_PERIODICO_CSV = "arquivos/estrato_artigo_periodico.csv";
        String SETOR_CSV = "arquivos/setor.csv";

        Importador imp = new Importador();

        try {
            TreeMap<String, Curriculo> curriculos = imp.importarCurriculos(CURRICULO_CSV);

            imp.importarArtigosEmEvento(ARTIGOS_EM_EVENTO_CSV, curriculos,
                    imp.importarEstratosEvento(ESTRATO_ARTIGO_EM_EVENTO_CSV));

            imp.importarArtigosEmPeriodico(ARTIGO_EM_PERIODICO_CSV, curriculos,
                    imp.importarEstratosPeriodico(ESTRATO_ARTIGO_PERIODICO_CSV));

            TreeMap<String, Setor> setores = imp.importarSetores(SETOR_CSV);

            imp.addSetores(CURRICULO_SETOR_CSV, setores, curriculos);

            GUI gui = new GUI();
            JFrame frame = new JFrame("GUI");
            frame.setContentPane(gui.getPanel1());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(600, 420);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setTitle("Gerar Relatórios");
            frame.setVisible(true);

            gui.getBotao().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    exportarArquivos(gui, new TreeSet<>(curriculos.values()));
                }
            });

            for (Setor setor : setores.values()) {
                String item = setor.getId() + " - " + setor.getNome() + " (" + setor.getSigla() + ")";
                gui.getComboBoxSetor().addItem(item);
            }

            TreeSet<Integer> anos = new TreeSet<>();
            for (Curriculo curriculo : curriculos.values()) {
                for (ArtigoEmEvento artigo : curriculo.getArtigosEmEvento()) {
                    anos.add(artigo.getAno());
                }
                for (ArtigoEmPeriodico artigo : curriculo.getArtigosEmPeriodico()) {
                    anos.add(artigo.getAno());
                }
            }

            for (Integer ano : anos) {
                gui.getComboBoxInicio().addItem(ano);
                gui.getComboBoxFim().addItem(ano);
            }
            gui.getComboBoxFim().setSelectedIndex(anos.size()-1);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao importar arquivos.");
        }
    }

}
