package Presentacion;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellsColor extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Object numero = table.getValueAt(row, column);
		if (numero != null) {
			if ((int) numero == 1) {// ini
				setBackground(Color.ORANGE);
				setForeground(Color.ORANGE);
			} else if ((int) numero == 2) {// fin
				setBackground(Color.LIGHT_GRAY);
				setForeground(Color.LIGHT_GRAY);
			} else if ((int) numero == 3) {// obstaculo
				setBackground(Color.RED);
				setForeground(Color.RED);
			} else if ((int) numero == 4) {// camino
				setBackground(Color.GREEN);
				setForeground(Color.GREEN);
			}else if ((int) numero == 5) {// zonaPeligrosa
				setBackground(Color.YELLOW);
				setForeground(Color.YELLOW);
			}
			else if((int) numero == 6)
			{
				setBackground(Color.PINK);
				setForeground(Color.PINK);
			}

		} else {

			setBackground(Color.WHITE);
			setForeground(Color.WHITE);

		}

		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}
