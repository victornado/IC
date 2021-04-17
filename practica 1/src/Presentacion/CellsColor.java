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
				setBackground(Color.LIGHT_GRAY);
				setForeground(Color.LIGHT_GRAY);
			} else if ((int) numero == 2) {// fin
				setBackground(Color.DARK_GRAY);
				setForeground(Color.DARK_GRAY);
			} else if ((int) numero == 3) {// obstaculo
				setBackground(Color.RED);
				setForeground(Color.RED);
			} else if ((int) numero == 4) {// camino
				setBackground(new Color(200,100,0));
				setForeground(new Color(200,100,0));
			} else if ((int) numero == 5) {// zonaPeligrosa
				setBackground(Color.YELLOW);
				setForeground(Color.YELLOW);
			} else if ((int) numero == 6) { // waypoint
				setBackground(new Color(128,64,0));
				setForeground(new Color(128,64,0));
			}

		} else {

			setBackground(Color.GREEN);
			setForeground(Color.GREEN);

		}

		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}
