package Presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Controlador.Controller;
import Controlador.events;

public class Tablero extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable leyenda_tabla;
	private JTextField textField;
	private JTextField textField_1;

	public Tablero(int i, int j, Controller controlador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		JPanel p = new JPanel();
		table = new JTable(i, j);
		table.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		table.setEnabled(false);
		table.setDefaultRenderer(Object.class, new CellsColor());

		p.add(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		contentPane.add(p, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_2 = new JButton("ini");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i, j;
				i = Integer.parseInt(textField.getText());
				j = Integer.parseInt(textField_1.getText());
				if (controlador.run(events.GenIni, i, j)) {
					table.setValueAt(1, i, j);
					btnNewButton_2.setVisible(false);
				}

				else
					JOptionPane.showMessageDialog(null, "error al crear el inicio", "Fatal error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		panel.add(btnNewButton_2);
		
		JButton btnwp = new JButton("Waypoint");

		JButton btnNewButton_1 = new JButton("fin");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i, j;
				i = Integer.parseInt(textField.getText());
				j = Integer.parseInt(textField_1.getText());
				if (controlador.run(events.GenFin, i, j)) {
					table.setValueAt(2, i, j);
					btnNewButton_1.setVisible(false);
					btnwp.setVisible(true);
				}

				else
					JOptionPane.showMessageDialog(null, "error al poner el el fin", "Fatal error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton = new JButton("obstaculos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i, j;
				i = Integer.parseInt(textField.getText());
				j = Integer.parseInt(textField_1.getText());
				if (controlador.run(events.GenObstaculo, i, j))
					table.setValueAt(3, i, j);

				else
					JOptionPane.showMessageDialog(null, "error al poner el obstaculo", "Fatal error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		panel.add(btnNewButton);

		
		btnwp.setVisible(false);
		btnwp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i, j;
				i = Integer.parseInt(textField.getText());
				j = Integer.parseInt(textField_1.getText());
				table.setValueAt(6, i, j);
				controlador.run(events.Waypoint, i, j);
			}
		});
		panel.add(btnwp);

		JButton btnNewButton_5 = new JButton("zona Peligrosa");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel.add(btnNewButton_5);

		JButton btnNewButton_3 = new JButton("empezar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		table.setRowHeight(60);
		panel.add(btnNewButton_3);
		pack();
		
		JPanel leyenda = new JPanel();
		init_leyenda();
		leyenda.add(leyenda_tabla);
		
		contentPane.add(leyenda, BorderLayout.EAST);
		 
		
		
	}
	private void init_leyenda() {
		leyenda_tabla = new JTable(7, 1);
		leyenda_tabla.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		leyenda_tabla.setEnabled(false);
		leyenda_tabla.setDefaultRenderer(Object.class, new LeyendaCells());
		
		leyenda_tabla.setValueAt("Inicio", 0, 0);
		leyenda_tabla.setValueAt("Final", 1, 0);
		leyenda_tabla.setValueAt("Obstáculo", 2, 0);
		leyenda_tabla.setValueAt("Camino", 3, 0);
		leyenda_tabla.setValueAt("Z.Peligrosa", 4, 0);
		leyenda_tabla.setValueAt("Waypoint", 5, 0);
		leyenda_tabla.setValueAt("Césped", 6, 0);
	}
}
