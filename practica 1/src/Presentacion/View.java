package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controlador.Controller;

public class View extends JFrame {

	
	private JButton aceptar;
	private JPanel panel;
	private JTextField textFieldFil;
	private JTextField textFieldCol;


	public View(Controller controlador) {

		setSize(new Dimension(500, 500));
		setTitle("Algoritmo A*"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//pone la localizacion en el centro pantalla
		setLocationRelativeTo(null);
		//Main Panel
		getContentPane().setLayout(new BorderLayout());

		JPanel panelDatos = new JPanel();
		getContentPane().add(panelDatos, BorderLayout.NORTH);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{0, 0, 0};
		gbl_panelDatos.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelDatos.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDatos.setLayout(gbl_panelDatos);

		JLabel lblFilas = new JLabel("Filas");
		GridBagConstraints gbc_lblFilas = new GridBagConstraints();
		gbc_lblFilas.anchor = GridBagConstraints.EAST;
		gbc_lblFilas.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilas.gridx = 0;
		gbc_lblFilas.gridy = 0;
		panelDatos.add(lblFilas, gbc_lblFilas);

		textFieldFil = new JTextField();
		GridBagConstraints gbc_textFieldFil = new GridBagConstraints();
		gbc_textFieldFil.anchor = GridBagConstraints.WEST;
		gbc_textFieldFil.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFil.gridx = 1;
		gbc_textFieldFil.gridy = 0;
		panelDatos.add(textFieldFil, gbc_textFieldFil);
		textFieldFil.setColumns(10);

		JLabel lblCol = new JLabel("Columnas");
		GridBagConstraints gbc_lblCol = new GridBagConstraints();
		gbc_lblCol.anchor = GridBagConstraints.EAST;
		gbc_lblCol.insets = new Insets(0, 0, 5, 5);
		gbc_lblCol.gridx = 0;
		gbc_lblCol.gridy = 1;
		panelDatos.add(lblCol, gbc_lblCol);

		textFieldCol = new JTextField();
		GridBagConstraints gbc_textFieldCol = new GridBagConstraints();
		gbc_textFieldCol.anchor = GridBagConstraints.WEST;
		gbc_textFieldCol.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCol.gridx = 1;
		gbc_textFieldCol.gridy = 1;
		panelDatos.add(textFieldCol, gbc_textFieldCol);
		textFieldCol.setColumns(10);

		JButton btnCrear = new JButton("Crear");
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.gridwidth = 2;
		gbc_btnCrear.gridx = 0;
		gbc_btnCrear.gridy = 2;
		panelDatos.add(btnCrear, gbc_btnCrear);
		
		//panel=new panelJuego(1,1,controlador);
		
		getContentPane().add(panel,BorderLayout.CENTER);
		btnCrear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (textFieldFil.getText().equals("") || textFieldCol.getText().equals(""))
					JOptionPane.showMessageDialog(null, "ERROR: Please enter the values.");
				else {			
					if (Integer.parseInt(textFieldFil.getText()) > 50 ||
							Integer.parseInt(textFieldFil.getText()) < 1 ||
							Integer.parseInt(textFieldCol.getText()) > 50 ||
							Integer.parseInt(textFieldCol.getText()) < 1)

						JOptionPane.showMessageDialog(null, "ERROR: Please enter the values "
								+ "between 1 and 50.");

					else {
						
						getContentPane().remove(panel);
						/*panel = new panelJuego(Integer.parseInt(textFieldFil.getText()), 
						Integer.parseInt(textFieldCol.getText()),controlador);*/
						panel.setVisible(true);
						getContentPane().add(panel,BorderLayout.CENTER);
						getContentPane().revalidate();
						getContentPane().repaint();
						controlador.start(Integer.parseInt(textFieldFil.getText()), Integer.parseInt(textFieldCol.getText()));
						
					}
				}	
			}	
		});
	}


}
