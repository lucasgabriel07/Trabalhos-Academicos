package entidades;

import jxl.format.*;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

public class Celula extends Label {

    public Celula(int c, int r, String cont) {
        super(c, r, cont);
    }

    public void centralizar() throws WriteException {
        WritableCellFormat cellFormat = new WritableCellFormat(getCellFormat());
        cellFormat.setAlignment(Alignment.CENTRE);
        setCellFormat(cellFormat);
    }

    public void posicionarNoTopo() throws WriteException {
        WritableCellFormat cellFormat = new WritableCellFormat(getCellFormat());
        cellFormat.setVerticalAlignment(VerticalAlignment.TOP);
        setCellFormat(cellFormat);
    }

    public void posicionarNoCentro() throws WriteException {
        WritableCellFormat cellFormat = new WritableCellFormat(getCellFormat());
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        setCellFormat(cellFormat);
    }

    public void bold() throws WriteException {
        WritableFont bold = new WritableFont(WritableFont.ARIAL);
        bold.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat cellFormat = new WritableCellFormat(getCellFormat());
        WritableCellFormat cellFormatBold = new WritableCellFormat(bold);
        cellFormatBold.setAlignment(cellFormat.getAlignment());
        cellFormatBold.setVerticalAlignment(cellFormat.getVerticalAlignment());
        setCellFormat(cellFormatBold);
    }

    public void inserirBorda(Border b) throws WriteException {
        WritableCellFormat cellFormat = new WritableCellFormat(getCellFormat());
        cellFormat.setBorder(b, BorderLineStyle.THIN);
        setCellFormat(cellFormat);
    }

    public void mudarCor(Colour c) throws WriteException {
        WritableCellFormat cellFormat = new WritableCellFormat(getCellFormat());
        cellFormat.setBackground(c);
        setCellFormat(cellFormat);
    }
}
