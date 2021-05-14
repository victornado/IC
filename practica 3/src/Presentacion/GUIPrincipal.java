package Presentacion;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controlador.Controller;

public class GUIPrincipal extends JFrame {

	private File fichero, ficheroEjemplo;
	private JLabel lblNewLabel_2;
	private JPanel panelparaLabel;
	private JComboBox comboBox;
	private JLabel lblNewLabel_3;

	public GUIPrincipal(Controller c) {

		setTitle("Practica 3");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		// --------------fichero Entrada------------------

		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("selecciona fichero de entrada:");
		panel_1.add(lblNewLabel_1, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		JButton FileButton = new JButton("fichero de entrada");
		panel_3.add(FileButton);

		JPanel panelparaLabel = new JPanel();
		panel_1.add(panelparaLabel, BorderLayout.SOUTH);

		lblNewLabel_2 = new JLabel("el fichero seleccionado es: ");
		FileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser ficheroEntrada = new JFileChooser("resources");

				ficheroEntrada.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int resultado = ficheroEntrada.showOpenDialog(new JFrame());
				fichero = ficheroEntrada.getSelectedFile();
				if (fichero != null) {
					lblNewLabel_2.setText("el fichero seleccionado es: " + fichero.getName());
					pack();
				}

			}
		});
		panelparaLabel.add(lblNewLabel_2);

		// ----------FIN fichero de entrada----------------

		// ------------comboBox---------------
		JPanel cPanel = new JPanel();
		JLabel lblNewLabel = new JLabel("Selecciona el algoritmo a ejecutar:");
		cPanel.add(lblNewLabel, BorderLayout.NORTH);
		getContentPane().add(cPanel, BorderLayout.SOUTH);
		cPanel.setLayout(new BorderLayout(0, 0));

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Algoritmo Borroso (K medias)", "Algoritmo Bayes", "Algoritmo Lloyd" }));
		cPanel.add(comboBox, BorderLayout.NORTH);
		// -----------FIN comboBox-----------

		// ---------separador------------
		JPanel panel_2 = new JPanel();
		cPanel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		cPanel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		// ------fin Separador---------
		// -------añadir ejemplo-----------
		JLabel lblNewLabel_3 = new JLabel("");
		panel_4.add(lblNewLabel_3, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("Aniadir ejemplo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser ficheroEjemploC = new JFileChooser("resources");
				ficheroEjemploC.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int resultado = ficheroEjemploC.showOpenDialog(new JFrame());
				ficheroEjemplo = ficheroEjemploC.getSelectedFile();
				if (ficheroEjemplo != null) {
					lblNewLabel_3.setText(ficheroEjemplo.getName());
					pack();
				}
			}
		});
		// -------FIN añadir ejemplo-----------
		// ----------ejecutar----------
		JButton EjecutarButton = new JButton("ejecutar");
		panel_4.add(EjecutarButton, BorderLayout.SOUTH);

		panel_4.add(btnNewButton, BorderLayout.WEST);
		EjecutarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fichero == null) {
					JOptionPane.showMessageDialog(null, "inserta un fichero de entrada", "File error",
							JOptionPane.ERROR_MESSAGE);
				} else if (ficheroEjemplo == null) {
					JOptionPane.showMessageDialog(null, "inserta un fichero de ejemplo", "File error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String salida = c.run(comboBox.getSelectedItem().toString(), fichero, ficheroEjemplo);
					if (!salida.equals("")) {
						JOptionPane.showMessageDialog(null, "El ejemplo insertado se encuentra en la clase: " + salida,
								"Salida", JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});
		// -----------fin ejecutar-----------

		pack();
	}

}