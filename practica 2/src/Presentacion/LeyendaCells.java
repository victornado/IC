package Presentacion;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class LeyendaCells extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Object valor = table.getValueAt(row, column);
		if (valor != null) {
			if ((String) valor == "Inicio") {// ini
				setBackground(Color.LIGHT_GRAY);
			} else if ((String) valor == "Final") {// fin
				setBackground(Color.GRAY);
			} else if ((String) valor == "Obstáculo") {// obstaculo
				setBackground(Color.RED);
			} else if ((String) valor == "Camino") {// camino
				setBackground(new Color(200,100,0));
			} else if ((String) valor == "Z.Peligrosa") {// zonaPeligrosa
				setBackground(Color.YELLOW);
			} else if ((String) valor == "Waypoint") { // waypoint
				setBackground(new Color(128,64,0));
			} else if ((String) valor == "Césped") {
				setBackground(Color.GREEN);
			}
			
		} else {
			setBackground(Color.WHITE);
		}

		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}