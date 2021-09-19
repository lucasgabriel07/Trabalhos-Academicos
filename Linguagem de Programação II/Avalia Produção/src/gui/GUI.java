package gui;

import javax.swing.*;

public class GUI {
    private JComboBox<String> comboBoxSetor;
    private JComboBox<Integer> comboBoxInicio;
    private JComboBox<Integer> comboBoxFim;
    private JButton botao;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JCheckBox relatorio1CheckBox;
    private JCheckBox relatorio2CheckBox;
    private JCheckBox sinteseCheckBox;

    public GUI() {
        relatorio1CheckBox.setSelected(true);
        relatorio2CheckBox.setSelected(true);
        sinteseCheckBox.setSelected(true);
    }

    public JComboBox<String> getComboBoxSetor() {
        return comboBoxSetor;
    }

    public JComboBox<Integer> getComboBoxInicio() {
        return comboBoxInicio;
    }

    public JComboBox<Integer> getComboBoxFim() {
        return comboBoxFim;
    }

    public JButton getBotao() {
        return botao;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JPanel getPanel2() {
        return panel2;
    }

    public JPanel getPanel3() {
        return panel3;
    }

    public JPanel getPanel4() {
        return panel4;
    }

    public JCheckBox getRelatorio1CheckBox() {
        return relatorio1CheckBox;
    }

    public JCheckBox getRelatorio2CheckBox() {
        return relatorio2CheckBox;
    }

    public JCheckBox getSinteseCheckBox() {
        return sinteseCheckBox;
    }

}