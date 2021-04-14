package Presentacion;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellsColor extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		Object numero =  table.getValueAt(row, column);
		if (numero != null) {
			if ((int)numero == 1) {// ini
				setBackground(Color.GREEN);
				setForeground(Color.GREEN);
			} else if ((int)numero == 2) {// fin
				setBackground(Color.RED);
				setForeground(Color.RED);
			} else if ((int)numero == 3) {// obstaculo
				setBackground(Color.BLACK);
				setForeground(Color.BLACK);
			} 
		}
		
	      
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}
