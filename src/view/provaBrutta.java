package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class provaBrutta {
/*
 * 
 * JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Dati personali", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_anagraficaPanel = new GridBagLayout();
		gbl_anagraficaPanel.columnWidths = new int[]{109, 109, 93, 68, 59, 0};
		gbl_anagraficaPanel.rowHeights = new int[]{0, 29, 31, 27, 30, 31, 27, 85, 0};
		gbl_anagraficaPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_anagraficaPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		anagraficaPanel.setLayout(gbl_anagraficaPanel);
				
				JLabel labelNome = new JLabel("Nome");
				labelNome.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_labelNome = new GridBagConstraints();
				gbc_labelNome.fill = GridBagConstraints.BOTH;
				gbc_labelNome.insets = new Insets(0, 0, 5, 5);
				gbc_labelNome.gridx = 0;
				gbc_labelNome.gridy = 1;
				anagraficaPanel.add(labelNome, gbc_labelNome);
				labelNome.setLabelFor(textFieldNome);
				
						textFieldNome = new JTextField();
						GridBagConstraints gbc_textFieldNome = new GridBagConstraints();
						gbc_textFieldNome.fill = GridBagConstraints.BOTH;
						gbc_textFieldNome.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldNome.gridx = 1;
						gbc_textFieldNome.gridy = 1;
						anagraficaPanel.add(textFieldNome, gbc_textFieldNome);
						
								textFieldNome.setColumns(10);
				JLabel labelCognome = new JLabel("Cognome");
				labelCognome.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_labelCognome = new GridBagConstraints();
				gbc_labelCognome.fill = GridBagConstraints.BOTH;
				gbc_labelCognome.insets = new Insets(0, 0, 5, 5);
				gbc_labelCognome.gridx = 2;
				gbc_labelCognome.gridy = 1;
				anagraficaPanel.add(labelCognome, gbc_labelCognome);
		
				textFieldCognome = new JTextField();
				GridBagConstraints gbc_textFieldCognome = new GridBagConstraints();
				gbc_textFieldCognome.gridwidth = 2;
				gbc_textFieldCognome.fill = GridBagConstraints.BOTH;
				gbc_textFieldCognome.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldCognome.gridx = 3;
				gbc_textFieldCognome.gridy = 1;
				anagraficaPanel.add(textFieldCognome, gbc_textFieldCognome);
				labelCognome.setLabelFor(textFieldCognome);

						textFieldCognome.setColumns(10);
								JLabel labelCf = new JLabel("CF");
								labelCf.setHorizontalAlignment(SwingConstants.LEFT);
								GridBagConstraints gbc_labelCf = new GridBagConstraints();
								gbc_labelCf.fill = GridBagConstraints.BOTH;
								gbc_labelCf.insets = new Insets(0, 0, 5, 5);
								gbc_labelCf.gridx = 0;
								gbc_labelCf.gridy = 2;
								anagraficaPanel.add(labelCf, gbc_labelCf);
						
								textFieldCodiceFiscale = new JTextField();
								GridBagConstraints gbc_textFieldCodiceFiscale = new GridBagConstraints();
								gbc_textFieldCodiceFiscale.fill = GridBagConstraints.BOTH;
								gbc_textFieldCodiceFiscale.insets = new Insets(0, 0, 5, 5);
								gbc_textFieldCodiceFiscale.gridx = 1;
								gbc_textFieldCodiceFiscale.gridy = 2;
								anagraficaPanel.add(textFieldCodiceFiscale, gbc_textFieldCodiceFiscale);
								textFieldCodiceFiscale.setColumns(10);
								labelCf.setLabelFor(textFieldCodiceFiscale);

								JLabel labelDataNascita = new JLabel("Data di nascita");
								GridBagConstraints gbc_labelDataNascita = new GridBagConstraints();
								gbc_labelDataNascita.fill = GridBagConstraints.BOTH;
								gbc_labelDataNascita.insets = new Insets(0, 0, 5, 5);
								gbc_labelDataNascita.gridx = 2;
								gbc_labelDataNascita.gridy = 2;
								anagraficaPanel.add(labelDataNascita, gbc_labelDataNascita);
								labelDataNascita.setHorizontalAlignment(SwingConstants.LEFT);
						
						textFieldDataNascita = new JTextField();
						GridBagConstraints gbc_textFieldDataNascita = new GridBagConstraints();
						gbc_textFieldDataNascita.gridwidth = 2;
						gbc_textFieldDataNascita.fill = GridBagConstraints.BOTH;
						gbc_textFieldDataNascita.insets = new Insets(0, 0, 5, 0);
						gbc_textFieldDataNascita.gridx = 3;
						gbc_textFieldDataNascita.gridy = 2;
						anagraficaPanel.add(textFieldDataNascita, gbc_textFieldDataNascita);
						textFieldDataNascita.setColumns(10);
						
								JLabel labelVia = new JLabel("Via");
								GridBagConstraints gbc_labelVia = new GridBagConstraints();
								gbc_labelVia.fill = GridBagConstraints.BOTH;
								gbc_labelVia.insets = new Insets(0, 0, 5, 5);
								gbc_labelVia.gridx = 0;
								gbc_labelVia.gridy = 3;
								anagraficaPanel.add(labelVia, gbc_labelVia);
								labelVia.setHorizontalAlignment(SwingConstants.LEFT);
				
						textFieldVia = new JTextField();
						GridBagConstraints gbc_textFieldVia = new GridBagConstraints();
						gbc_textFieldVia.fill = GridBagConstraints.BOTH;
						gbc_textFieldVia.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldVia.gridx = 1;
						gbc_textFieldVia.gridy = 3;
						anagraficaPanel.add(textFieldVia, gbc_textFieldVia);
						textFieldVia.setColumns(10);
						labelVia.setLabelFor(textFieldVia);

				JLabel labelNumeroCivico = new JLabel("Nr.");
				GridBagConstraints gbc_labelNumeroCivico = new GridBagConstraints();
				gbc_labelNumeroCivico.fill = GridBagConstraints.BOTH;
				gbc_labelNumeroCivico.insets = new Insets(0, 0, 5, 5);
				gbc_labelNumeroCivico.gridx = 2;
				gbc_labelNumeroCivico.gridy = 3;
				anagraficaPanel.add(labelNumeroCivico, gbc_labelNumeroCivico);
				labelNumeroCivico.setHorizontalAlignment(SwingConstants.LEFT);
		labelNumeroCivico.setLabelFor(textFieldNumeroCivico);
		
				textFieldNumeroCivico = new JTextField();
				GridBagConstraints gbc_textFieldNumeroCivico = new GridBagConstraints();
				gbc_textFieldNumeroCivico.fill = GridBagConstraints.BOTH;
				gbc_textFieldNumeroCivico.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldNumeroCivico.gridx = 3;
				gbc_textFieldNumeroCivico.gridy = 3;
				anagraficaPanel.add(textFieldNumeroCivico, gbc_textFieldNumeroCivico);
				textFieldNumeroCivico.setColumns(10);
								
										JLabel labelCitta = new JLabel("Citta");
										GridBagConstraints gbc_labelCitta = new GridBagConstraints();
										gbc_labelCitta.fill = GridBagConstraints.BOTH;
										gbc_labelCitta.insets = new Insets(0, 0, 5, 5);
										gbc_labelCitta.gridx = 0;
										gbc_labelCitta.gridy = 4;
										anagraficaPanel.add(labelCitta, gbc_labelCitta);
										labelCitta.setHorizontalAlignment(SwingConstants.LEFT);
						
								textFieldCitta = new JTextField();
								GridBagConstraints gbc_textFieldCitta = new GridBagConstraints();
								gbc_textFieldCitta.fill = GridBagConstraints.BOTH;
								gbc_textFieldCitta.insets = new Insets(0, 0, 5, 5);
								gbc_textFieldCitta.gridx = 1;
								gbc_textFieldCitta.gridy = 4;
								anagraficaPanel.add(textFieldCitta, gbc_textFieldCitta);
								textFieldCitta.setColumns(10);
								labelCitta.setLabelFor(textFieldCitta);

						JLabel labelCap = new JLabel("CAP");
						GridBagConstraints gbc_labelCap = new GridBagConstraints();
						gbc_labelCap.fill = GridBagConstraints.BOTH;
						gbc_labelCap.insets = new Insets(0, 0, 5, 5);
						gbc_labelCap.gridx = 2;
						gbc_labelCap.gridy = 4;
						anagraficaPanel.add(labelCap, gbc_labelCap);
						labelCap.setHorizontalAlignment(SwingConstants.LEFT);
						labelCap.setLabelFor(textFieldCap);
						
								textFieldCap = new JTextField();
								GridBagConstraints gbc_textFieldCap = new GridBagConstraints();
								gbc_textFieldCap.fill = GridBagConstraints.BOTH;
								gbc_textFieldCap.insets = new Insets(0, 0, 5, 5);
								gbc_textFieldCap.gridx = 3;
								gbc_textFieldCap.gridy = 4;
								anagraficaPanel.add(textFieldCap, gbc_textFieldCap);
								textFieldCap.setColumns(10);
						
								JLabel labelTelefono = new JLabel("Telefono");
								GridBagConstraints gbc_labelTelefono = new GridBagConstraints();
								gbc_labelTelefono.fill = GridBagConstraints.BOTH;
								gbc_labelTelefono.insets = new Insets(0, 0, 5, 5);
								gbc_labelTelefono.gridx = 0;
								gbc_labelTelefono.gridy = 5;
								anagraficaPanel.add(labelTelefono, gbc_labelTelefono);
								labelTelefono.setHorizontalAlignment(SwingConstants.LEFT);
				
						textFieldTelefono = new JTextField();
						GridBagConstraints gbc_textFieldTelefono = new GridBagConstraints();
						gbc_textFieldTelefono.fill = GridBagConstraints.BOTH;
						gbc_textFieldTelefono.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldTelefono.gridx = 1;
						gbc_textFieldTelefono.gridy = 5;
						anagraficaPanel.add(textFieldTelefono, gbc_textFieldTelefono);
						textFieldTelefono.setColumns(10);
						labelTelefono.setLabelFor(textFieldTelefono);

				
				JLabel labelEmail = new JLabel("E-mail");
				GridBagConstraints gbc_labelEmail = new GridBagConstraints();
				gbc_labelEmail.fill = GridBagConstraints.BOTH;
				gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
				gbc_labelEmail.gridx = 2;
				gbc_labelEmail.gridy = 5;
				anagraficaPanel.add(labelEmail, gbc_labelEmail);
				labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		
				textFieldEmail = new JTextField();
				GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
				gbc_textFieldEmail.gridwidth = 2;
				gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
				gbc_textFieldEmail.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldEmail.gridx = 3;
				gbc_textFieldEmail.gridy = 5;
				anagraficaPanel.add(textFieldEmail, gbc_textFieldEmail);
				textFieldEmail.setColumns(10);
				labelEmail.setLabelFor(textFieldEmail);

		JLabel lblAnniServizio = new JLabel("Anni di Servizio");
		GridBagConstraints gbc_lblAnniServizio = new GridBagConstraints();
		gbc_lblAnniServizio.fill = GridBagConstraints.BOTH;
		gbc_lblAnniServizio.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnniServizio.gridx = 0;
		gbc_lblAnniServizio.gridy = 6;
		anagraficaPanel.add(lblAnniServizio, gbc_lblAnniServizio);
		
		textFieldAnniServizio = new JTextField();
		GridBagConstraints gbc_textFieldAnniServizio = new GridBagConstraints();
		gbc_textFieldAnniServizio.fill = GridBagConstraints.BOTH;
		gbc_textFieldAnniServizio.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAnniServizio.gridx = 1;
		gbc_textFieldAnniServizio.gridy = 6;
		anagraficaPanel.add(textFieldAnniServizio, gbc_textFieldAnniServizio);
		textFieldAnniServizio.setColumns(10);
		
		JLabel lblSpecializzazioni = new JLabel("Specializzazioni");
		GridBagConstraints gbc_lblSpecializzazioni = new GridBagConstraints();
		gbc_lblSpecializzazioni.fill = GridBagConstraints.BOTH;
		gbc_lblSpecializzazioni.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpecializzazioni.gridx = 2;
		gbc_lblSpecializzazioni.gridy = 6;
		anagraficaPanel.add(lblSpecializzazioni, gbc_lblSpecializzazioni);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 6;
		anagraficaPanel.add(textField, gbc_textField);
		textField.setColumns(10);
 * */
}
