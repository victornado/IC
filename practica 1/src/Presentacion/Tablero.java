package Presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	private JTextField textField;
	private JTextField textField_1;

	
	public Tablero(int i, int j,Controller controlador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		table = new JTable(i, j);
		table.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		table.setEnabled(false);
		table.setDefaultRenderer(Object.class, new CellsColor());
		contentPane.add(table, BorderLayout.CENTER);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
				table.setValueAt(1, i, j);
				btnNewButton_2.setVisible(false);
				controlador.run(events.GenIni, i, j);
			}
		});
		panel.add(btnNewButton_2);
		

		JButton btnNewButton_1 = new JButton("fin");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i, j;
				i = Integer.parseInt(textField.getText());
				j = Integer.parseInt(textField_1.getText());
				table.setValueAt(2, i, j);
				btnNewButton_1.setVisible(false);
				controlador.run(events.GenFin, i, j);
			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton = new JButton("obstaculos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i, j;
				i = Integer.parseInt(textField.getText());
				j = Integer.parseInt(textField_1.getText());
				table.setValueAt(3, i, j);
				controlador.run(events.GenObstaculo, i, j);
			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_3 = new JButton("empezar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.run(events.Run, 1, 1);
			}
		});
		panel.add(btnNewButton_3);
	}

}
