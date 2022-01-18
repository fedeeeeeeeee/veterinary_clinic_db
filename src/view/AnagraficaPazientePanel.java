package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import model.Paziente;

import java.awt.Insets;

public class AnagraficaPazientePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textNome;
	private JTextField textProprietario;
	private JTextField textGenere;
	private JTextField textRazza;
	private JTextField textNascita;
	private JTextField textSpecie;


	public AnagraficaPazientePanel(Paziente paziente) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gridBagLayout);
		
		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 1;
		mainPanel.add(lblNome, gbc_lblNome);
		
		textNome = new JTextField();
		textNome.setText(paziente.getAnimale().getNome());
		textNome.setEditable(false);
		GridBagConstraints gbc_textNome = new GridBagConstraints();
		gbc_textNome.insets = new Insets(0, 0, 5, 5);
		gbc_textNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNome.gridx = 3;
		gbc_textNome.gridy = 1;
		mainPanel.add(textNome, gbc_textNome);
		textNome.setColumns(10);
		
		JLabel lblProprietario = new JLabel("Proprietario");
		GridBagConstraints gbc_lblProprietario = new GridBagConstraints();
		gbc_lblProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_lblProprietario.gridx = 5;
		gbc_lblProprietario.gridy = 1;
		mainPanel.add(lblProprietario, gbc_lblProprietario);
		
		textProprietario = new JTextField();
		textProprietario.setText(paziente.getAnimale().getProprietario().getCF());
		textProprietario.setEditable(false);
		GridBagConstraints gbc_textProprietario = new GridBagConstraints();
		gbc_textProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_textProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textProprietario.gridx = 7;
		gbc_textProprietario.gridy = 1;
		mainPanel.add(textProprietario, gbc_textProprietario);
		textProprietario.setColumns(10);
		
		JLabel lblGenere = new JLabel("Genere");
		GridBagConstraints gbc_lblGenere = new GridBagConstraints();
		gbc_lblGenere.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenere.gridx = 1;
		gbc_lblGenere.gridy = 3;
		mainPanel.add(lblGenere, gbc_lblGenere);
		
		textGenere = new JTextField();
		textGenere.setText(paziente.getAnimale().getGenere());
		textGenere.setEditable(false);
		GridBagConstraints gbc_textGenere = new GridBagConstraints();
		gbc_textGenere.insets = new Insets(0, 0, 5, 5);
		gbc_textGenere.fill = GridBagConstraints.HORIZONTAL;
		gbc_textGenere.gridx = 3;
		gbc_textGenere.gridy = 3;
		mainPanel.add(textGenere, gbc_textGenere);
		textGenere.setColumns(10);
		
		JLabel lblNascita = new JLabel("Data di Nascita");
		GridBagConstraints gbc_lblNascita = new GridBagConstraints();
		gbc_lblNascita.insets = new Insets(0, 0, 5, 5);
		gbc_lblNascita.gridx = 5;
		gbc_lblNascita.gridy = 3;
		mainPanel.add(lblNascita, gbc_lblNascita);
		
		textNascita = new JTextField();
		textNascita.setText(paziente.getAnimale().getDataDiNascita());
		textNascita.setEditable(false);
		GridBagConstraints gbc_textNascita = new GridBagConstraints();
		gbc_textNascita.insets = new Insets(0, 0, 5, 5);
		gbc_textNascita.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNascita.gridx = 7;
		gbc_textNascita.gridy = 3;
		mainPanel.add(textNascita, gbc_textNascita);
		textNascita.setColumns(10);
		
		JLabel lblRazza = new JLabel("Razza");
		GridBagConstraints gbc_lblRazza = new GridBagConstraints();
		gbc_lblRazza.insets = new Insets(0, 0, 5, 5);
		gbc_lblRazza.gridx = 1;
		gbc_lblRazza.gridy = 5;
		mainPanel.add(lblRazza, gbc_lblRazza);
		
		textRazza = new JTextField();
		textRazza.setText(paziente.getAnimale().getRazza());
		textRazza.setEditable(false);
		GridBagConstraints gbc_textRazza = new GridBagConstraints();
		gbc_textRazza.insets = new Insets(0, 0, 5, 5);
		gbc_textRazza.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRazza.gridx = 3;
		gbc_textRazza.gridy = 5;
		mainPanel.add(textRazza, gbc_textRazza);
		textRazza.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Specie");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 5;
		gbc_lblNewLabel_5.gridy = 5;
		mainPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		textSpecie = new JTextField();
		textSpecie.setText(paziente.getAnimale().getSpecie());
		textSpecie.setEditable(false);
		GridBagConstraints gbc_textSpecie = new GridBagConstraints();
		gbc_textSpecie.insets = new Insets(0, 0, 5, 5);
		gbc_textSpecie.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSpecie.gridx = 7;
		gbc_textSpecie.gridy = 5;
		mainPanel.add(textSpecie, gbc_textSpecie);
		textSpecie.setColumns(10);
		
		JPanel upperPanel = new JPanel();
		add(upperPanel, BorderLayout.NORTH);
		
	}

}
