package view;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.Person;
import model.Proprietario;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AnagraficaProprietarioPanel extends JPanel {

	private static final long serialVersionUID = -8914768329401485459L;
	private Person proprietario;

	public AnagraficaProprietarioPanel(Person proprietario) {
		this.proprietario = proprietario;
		setLayout(new BorderLayout(0, 0));

		JPanel anagraficaPanel = new JPanel();
		GridBagLayout gbl_anagraficaPanel = new GridBagLayout();
		gbl_anagraficaPanel.columnWidths = new int[] { 0, 109, 109, 43, 7, 109, 0, 0, 0 };
		gbl_anagraficaPanel.rowHeights = new int[] { 55, 55, 55, 55, 55, 0 };
		gbl_anagraficaPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_anagraficaPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		anagraficaPanel.setLayout(gbl_anagraficaPanel);

		JLabel labelNome = new JLabel("Nome");
		labelNome.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelNome = new GridBagConstraints();
		gbc_labelNome.fill = GridBagConstraints.BOTH;
		gbc_labelNome.insets = new Insets(0, 0, 5, 5);
		gbc_labelNome.gridx = 1;
		gbc_labelNome.gridy = 0;
		anagraficaPanel.add(labelNome, gbc_labelNome);

		JTextField jTextFieldNomeProprietario = new JTextField();
		jTextFieldNomeProprietario.setText(this.proprietario.getNome());
		jTextFieldNomeProprietario.setEditable(false);
		GridBagConstraints gbc_labelNomeProprietario = new GridBagConstraints();
		gbc_labelNomeProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelNomeProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelNomeProprietario.gridx = 2;
		gbc_labelNomeProprietario.gridy = 0;
		anagraficaPanel.add(jTextFieldNomeProprietario, gbc_labelNomeProprietario);
		labelNome.setLabelFor(jTextFieldNomeProprietario);

		JLabel labelCognome = new JLabel("Cognome");
		labelCognome.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelCognome = new GridBagConstraints();
		gbc_labelCognome.fill = GridBagConstraints.BOTH;
		gbc_labelCognome.insets = new Insets(0, 0, 5, 5);
		gbc_labelCognome.gridx = 4;
		gbc_labelCognome.gridy = 0;
		anagraficaPanel.add(labelCognome, gbc_labelCognome);

		JTextField jTextFieldCognomeProprietario = new JTextField();
		jTextFieldCognomeProprietario.setText(this.proprietario.getCognome());
		jTextFieldCognomeProprietario.setEditable(false);
		GridBagConstraints gbc_labelCognomeProprietario = new GridBagConstraints();
		gbc_labelCognomeProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelCognomeProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelCognomeProprietario.gridx = 5;
		gbc_labelCognomeProprietario.gridy = 0;
		anagraficaPanel.add(jTextFieldCognomeProprietario, gbc_labelCognomeProprietario);
		labelCognome.setLabelFor(jTextFieldCognomeProprietario);

		JLabel labelCf = new JLabel("CF");
		labelCf.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelCf = new GridBagConstraints();
		gbc_labelCf.fill = GridBagConstraints.BOTH;
		gbc_labelCf.insets = new Insets(0, 0, 5, 5);
		gbc_labelCf.gridx = 1;
		gbc_labelCf.gridy = 1;
		anagraficaPanel.add(labelCf, gbc_labelCf);

		JTextField jTextFieldCF = new JTextField();
		jTextFieldCF.setText(this.proprietario.getCF());
		jTextFieldCF.setEditable(false);
		GridBagConstraints gbc_labelCfProprietario = new GridBagConstraints();
		gbc_labelCfProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelCfProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelCfProprietario.gridx = 2;
		gbc_labelCfProprietario.gridy = 1;
		anagraficaPanel.add(jTextFieldCF, gbc_labelCfProprietario);
		labelCf.setLabelFor(jTextFieldCF);

		JLabel labelDataNascita = new JLabel("Data di nascita");
		GridBagConstraints gbc_labelDataNascita = new GridBagConstraints();
		gbc_labelDataNascita.fill = GridBagConstraints.BOTH;
		gbc_labelDataNascita.insets = new Insets(0, 0, 5, 5);
		gbc_labelDataNascita.gridx = 4;
		gbc_labelDataNascita.gridy = 1;
		anagraficaPanel.add(labelDataNascita, gbc_labelDataNascita);
		labelDataNascita.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField jTextFieldData = new JTextField();
		jTextFieldData.setText(this.proprietario.getDataNascita());
		jTextFieldData.setEditable(false);
		GridBagConstraints gbc_lblNewLabelDataNascita = new GridBagConstraints();
		gbc_lblNewLabelDataNascita.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabelDataNascita.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelDataNascita.gridx = 5;
		gbc_lblNewLabelDataNascita.gridy = 1;
		anagraficaPanel.add(jTextFieldData, gbc_lblNewLabelDataNascita);
		labelDataNascita.setLabelFor(jTextFieldData);

		JLabel labelVia = new JLabel("Via");
		GridBagConstraints gbc_labelVia = new GridBagConstraints();
		gbc_labelVia.fill = GridBagConstraints.BOTH;
		gbc_labelVia.insets = new Insets(0, 0, 5, 5);
		gbc_labelVia.gridx = 1;
		gbc_labelVia.gridy = 2;
		anagraficaPanel.add(labelVia, gbc_labelVia);
		labelVia.setHorizontalAlignment(SwingConstants.LEFT);


		JTextField jTextFieldVia = new JTextField();
		jTextFieldVia.setText(this.proprietario.getIndirizzoVia());
		jTextFieldVia.setEditable(false);
		GridBagConstraints gbc_labelViaProprietario = new GridBagConstraints();
		gbc_labelViaProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelViaProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelViaProprietario.gridx = 2;
		gbc_labelViaProprietario.gridy = 2;
		anagraficaPanel.add(jTextFieldVia, gbc_labelViaProprietario);
		labelVia.setLabelFor(jTextFieldVia);

		JLabel labelNumeroCivico = new JLabel("Nr. ");
		GridBagConstraints gbc_labelNumeroCivico = new GridBagConstraints();
		gbc_labelNumeroCivico.fill = GridBagConstraints.BOTH;
		gbc_labelNumeroCivico.insets = new Insets(0, 0, 5, 5);
		gbc_labelNumeroCivico.gridx = 4;
		gbc_labelNumeroCivico.gridy = 2;
		anagraficaPanel.add(labelNumeroCivico, gbc_labelNumeroCivico);
		labelNumeroCivico.setHorizontalAlignment(SwingConstants.LEFT);
		

		JTextField jTextFieldNumeroInd = new JTextField();
		jTextFieldNumeroInd.setText(String.valueOf(this.proprietario.getIndirizzoNumero()));
		jTextFieldNumeroInd.setEditable(false);
		GridBagConstraints gbc_labelNumeroCivicoProprietario = new GridBagConstraints();
		gbc_labelNumeroCivicoProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelNumeroCivicoProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelNumeroCivicoProprietario.gridx = 5;
		gbc_labelNumeroCivicoProprietario.gridy = 2;
		anagraficaPanel.add(jTextFieldNumeroInd, gbc_labelNumeroCivicoProprietario);
		labelNumeroCivico.setLabelFor(jTextFieldNumeroInd);

		JLabel labelCitta = new JLabel("Città");
		GridBagConstraints gbc_labelCitta = new GridBagConstraints();
		gbc_labelCitta.fill = GridBagConstraints.BOTH;
		gbc_labelCitta.insets = new Insets(0, 0, 5, 5);
		gbc_labelCitta.gridx = 1;
		gbc_labelCitta.gridy = 3;
		anagraficaPanel.add(labelCitta, gbc_labelCitta);
		labelCitta.setHorizontalAlignment(SwingConstants.LEFT);

		JTextField jTextFieldCitta = new JTextField();
		jTextFieldCitta.setText(String.valueOf(this.proprietario.getIndirizzoNumero()));
		jTextFieldCitta.setEditable(false);
		GridBagConstraints gbc_labelCittaProprietario = new GridBagConstraints();
		gbc_labelCittaProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelCittaProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelCittaProprietario.gridx = 2;
		gbc_labelCittaProprietario.gridy = 3;
		anagraficaPanel.add(jTextFieldCitta, gbc_labelCittaProprietario);
		labelCitta.setLabelFor(jTextFieldCitta);

		JLabel labelCap = new JLabel("CAP");
		GridBagConstraints gbc_labelCap = new GridBagConstraints();
		gbc_labelCap.fill = GridBagConstraints.BOTH;
		gbc_labelCap.insets = new Insets(0, 0, 5, 5);
		gbc_labelCap.gridx = 4;
		gbc_labelCap.gridy = 3;
		anagraficaPanel.add(labelCap, gbc_labelCap);
		labelCap.setHorizontalAlignment(SwingConstants.LEFT);

		JTextField jTextFieldCap = new JTextField();
		jTextFieldCap.setText(String.valueOf(this.proprietario.getIndirizzoCap()));
		jTextFieldCap.setEditable(false);
		GridBagConstraints gbc_labelCapProprietario = new GridBagConstraints();
		gbc_labelCapProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelCapProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelCapProprietario.gridx = 5;
		gbc_labelCapProprietario.gridy = 3;
		anagraficaPanel.add(jTextFieldCap, gbc_labelCapProprietario);
		labelCap.setLabelFor(jTextFieldCap);

		JLabel labelTelefono = new JLabel("Telefono");
		GridBagConstraints gbc_labelTelefono = new GridBagConstraints();
		gbc_labelTelefono.fill = GridBagConstraints.BOTH;
		gbc_labelTelefono.insets = new Insets(0, 0, 0, 5);
		gbc_labelTelefono.gridx = 1;
		gbc_labelTelefono.gridy = 4;
		anagraficaPanel.add(labelTelefono, gbc_labelTelefono);
		labelTelefono.setHorizontalAlignment(SwingConstants.LEFT);


		JTextField jTextFieldTelefono = new JTextField();
		jTextFieldTelefono.setText(String.valueOf(this.proprietario.getTelefono()));
		jTextFieldTelefono.setEditable(false);
		GridBagConstraints gbc_labelTelefonoProprietario = new GridBagConstraints();
		gbc_labelTelefonoProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelTelefonoProprietario.insets = new Insets(0, 0, 0, 5);
		gbc_labelTelefonoProprietario.gridx = 2;
		gbc_labelTelefonoProprietario.gridy = 4;
		anagraficaPanel.add(jTextFieldTelefono, gbc_labelTelefonoProprietario);
		labelTelefono.setLabelFor(jTextFieldTelefono);

		JLabel labelEmail = new JLabel("E-mail");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.fill = GridBagConstraints.BOTH;
		gbc_labelEmail.insets = new Insets(0, 0, 0, 5);
		gbc_labelEmail.gridx = 4;
		gbc_labelEmail.gridy = 4;
		anagraficaPanel.add(labelEmail, gbc_labelEmail);
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);

		JTextField jTextFieldEmail = new JTextField();
		jTextFieldEmail.setText(String.valueOf(this.proprietario.getEmail()));
		jTextFieldEmail.setEditable(false);
		GridBagConstraints gbc_labelEmailProprietario = new GridBagConstraints();
		gbc_labelEmailProprietario.insets = new Insets(0, 0, 0, 5);
		gbc_labelEmailProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelEmailProprietario.gridx = 5;
		gbc_labelEmailProprietario.gridy = 4;
		anagraficaPanel.add(jTextFieldEmail, gbc_labelEmailProprietario);
		labelEmail.setLabelFor(jTextFieldEmail);
		
		this.add(anagraficaPanel);
	}

}
