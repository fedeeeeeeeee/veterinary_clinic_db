package view;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		gbl_anagraficaPanel.columnWidths = new int[] { 109, 109, 74, 109, 109, 0, 0 };
		gbl_anagraficaPanel.rowHeights = new int[] { 55, 55, 55, 55, 55, 0 };
		gbl_anagraficaPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_anagraficaPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		anagraficaPanel.setLayout(gbl_anagraficaPanel);

		JLabel labelNome = new JLabel("Nome: ");
		labelNome.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelNome = new GridBagConstraints();
		gbc_labelNome.fill = GridBagConstraints.BOTH;
		gbc_labelNome.insets = new Insets(0, 0, 5, 5);
		gbc_labelNome.gridx = 0;
		gbc_labelNome.gridy = 0;
		anagraficaPanel.add(labelNome, gbc_labelNome);

		JLabel labelNomeProprietario = new JLabel(this.proprietario.getNome());
		GridBagConstraints gbc_labelNomeProprietario = new GridBagConstraints();
		gbc_labelNomeProprietario.fill = GridBagConstraints.BOTH;
		gbc_labelNomeProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelNomeProprietario.gridx = 1;
		gbc_labelNomeProprietario.gridy = 0;
		anagraficaPanel.add(labelNomeProprietario, gbc_labelNomeProprietario);
		labelNome.setLabelFor(labelNomeProprietario);

		JLabel labelCognome = new JLabel("Cognome: ");
		labelCognome.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelCognome = new GridBagConstraints();
		gbc_labelCognome.fill = GridBagConstraints.BOTH;
		gbc_labelCognome.insets = new Insets(0, 0, 5, 5);
		gbc_labelCognome.gridx = 3;
		gbc_labelCognome.gridy = 0;
		anagraficaPanel.add(labelCognome, gbc_labelCognome);

		JLabel labelCognomeProprietario = new JLabel(this.proprietario.getCognome());
		GridBagConstraints gbc_labelCognomeProprietario = new GridBagConstraints();
		gbc_labelCognomeProprietario.fill = GridBagConstraints.BOTH;
		gbc_labelCognomeProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelCognomeProprietario.gridx = 4;
		gbc_labelCognomeProprietario.gridy = 0;
		anagraficaPanel.add(labelCognomeProprietario, gbc_labelCognomeProprietario);
		labelCognome.setLabelFor(labelCognomeProprietario);

		JLabel labelCf = new JLabel("CF: ");
		labelCf.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelCf = new GridBagConstraints();
		gbc_labelCf.fill = GridBagConstraints.BOTH;
		gbc_labelCf.insets = new Insets(0, 0, 5, 5);
		gbc_labelCf.gridx = 0;
		gbc_labelCf.gridy = 1;
		anagraficaPanel.add(labelCf, gbc_labelCf);

		JLabel labelCfProprietario = new JLabel(this.proprietario.getCF());
		GridBagConstraints gbc_labelCfProprietario = new GridBagConstraints();
		gbc_labelCfProprietario.fill = GridBagConstraints.BOTH;
		gbc_labelCfProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelCfProprietario.gridx = 1;
		gbc_labelCfProprietario.gridy = 1;
		anagraficaPanel.add(labelCfProprietario, gbc_labelCfProprietario);
		labelCf.setLabelFor(labelCfProprietario);

		JLabel labelDataNascita = new JLabel("Data di nascita: ");
		GridBagConstraints gbc_labelDataNascita = new GridBagConstraints();
		gbc_labelDataNascita.fill = GridBagConstraints.BOTH;
		gbc_labelDataNascita.insets = new Insets(0, 0, 5, 5);
		gbc_labelDataNascita.gridx = 3;
		gbc_labelDataNascita.gridy = 1;
		anagraficaPanel.add(labelDataNascita, gbc_labelDataNascita);
		labelDataNascita.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNewLabelDataNascita = new JLabel(this.proprietario.getDataNascita());
		GridBagConstraints gbc_lblNewLabelDataNascita = new GridBagConstraints();
		gbc_lblNewLabelDataNascita.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabelDataNascita.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelDataNascita.gridx = 4;
		gbc_lblNewLabelDataNascita.gridy = 1;
		anagraficaPanel.add(lblNewLabelDataNascita, gbc_lblNewLabelDataNascita);

		JLabel labelVia = new JLabel("Via: ");
		GridBagConstraints gbc_labelVia = new GridBagConstraints();
		gbc_labelVia.fill = GridBagConstraints.BOTH;
		gbc_labelVia.insets = new Insets(0, 0, 5, 5);
		gbc_labelVia.gridx = 0;
		gbc_labelVia.gridy = 2;
		anagraficaPanel.add(labelVia, gbc_labelVia);
		labelVia.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel labelViaProprietario = new JLabel(this.proprietario.getIndirizzoVia());
		GridBagConstraints gbc_labelViaProprietario = new GridBagConstraints();
		gbc_labelViaProprietario.fill = GridBagConstraints.BOTH;
		gbc_labelViaProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelViaProprietario.gridx = 1;
		gbc_labelViaProprietario.gridy = 2;
		anagraficaPanel.add(labelViaProprietario, gbc_labelViaProprietario);
		labelVia.setLabelFor(labelViaProprietario);

		JLabel labelNumeroCivico = new JLabel("Nr. ");
		GridBagConstraints gbc_labelNumeroCivico = new GridBagConstraints();
		gbc_labelNumeroCivico.fill = GridBagConstraints.BOTH;
		gbc_labelNumeroCivico.insets = new Insets(0, 0, 5, 5);
		gbc_labelNumeroCivico.gridx = 3;
		gbc_labelNumeroCivico.gridy = 2;
		anagraficaPanel.add(labelNumeroCivico, gbc_labelNumeroCivico);
		labelNumeroCivico.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel labelNumeroCivicoProprietario = new JLabel(String.valueOf(this.proprietario.getIndirizzoNumero()));
		GridBagConstraints gbc_labelNumeroCivicoProprietario = new GridBagConstraints();
		gbc_labelNumeroCivicoProprietario.fill = GridBagConstraints.BOTH;
		gbc_labelNumeroCivicoProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelNumeroCivicoProprietario.gridx = 4;
		gbc_labelNumeroCivicoProprietario.gridy = 2;
		anagraficaPanel.add(labelNumeroCivicoProprietario, gbc_labelNumeroCivicoProprietario);
		labelNumeroCivico.setLabelFor(labelNumeroCivicoProprietario);

		JLabel labelCitta = new JLabel("Città: ");
		GridBagConstraints gbc_labelCitta = new GridBagConstraints();
		gbc_labelCitta.fill = GridBagConstraints.BOTH;
		gbc_labelCitta.insets = new Insets(0, 0, 5, 5);
		gbc_labelCitta.gridx = 0;
		gbc_labelCitta.gridy = 3;
		anagraficaPanel.add(labelCitta, gbc_labelCitta);
		labelCitta.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel labelCittaProprietario = new JLabel(this.proprietario.getIndirizzoCitta());
		GridBagConstraints gbc_labelCittaProprietario = new GridBagConstraints();
		gbc_labelCittaProprietario.fill = GridBagConstraints.BOTH;
		gbc_labelCittaProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelCittaProprietario.gridx = 1;
		gbc_labelCittaProprietario.gridy = 3;
		anagraficaPanel.add(labelCittaProprietario, gbc_labelCittaProprietario);
		labelCitta.setLabelFor(labelCittaProprietario);

		JLabel labelCap = new JLabel("CAP");
		GridBagConstraints gbc_labelCap = new GridBagConstraints();
		gbc_labelCap.fill = GridBagConstraints.BOTH;
		gbc_labelCap.insets = new Insets(0, 0, 5, 5);
		gbc_labelCap.gridx = 3;
		gbc_labelCap.gridy = 3;
		anagraficaPanel.add(labelCap, gbc_labelCap);
		labelCap.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel labelCapProprietario = new JLabel(String.valueOf(this.proprietario.getIndirizzoCap()));
		GridBagConstraints gbc_labelCapProprietario = new GridBagConstraints();
		gbc_labelCapProprietario.fill = GridBagConstraints.BOTH;
		gbc_labelCapProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelCapProprietario.gridx = 4;
		gbc_labelCapProprietario.gridy = 3;
		anagraficaPanel.add(labelCapProprietario, gbc_labelCapProprietario);
		labelCap.setLabelFor(labelCapProprietario);

		JLabel labelTelefono = new JLabel("Telefono: ");
		GridBagConstraints gbc_labelTelefono = new GridBagConstraints();
		gbc_labelTelefono.fill = GridBagConstraints.BOTH;
		gbc_labelTelefono.insets = new Insets(0, 0, 0, 5);
		gbc_labelTelefono.gridx = 0;
		gbc_labelTelefono.gridy = 4;
		anagraficaPanel.add(labelTelefono, gbc_labelTelefono);
		labelTelefono.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel labelTelefonoProprietario = new JLabel(String.valueOf(this.proprietario.getTelefono()));
		GridBagConstraints gbc_labelTelefonoProprietario = new GridBagConstraints();
		gbc_labelTelefonoProprietario.fill = GridBagConstraints.BOTH;
		gbc_labelTelefonoProprietario.insets = new Insets(0, 0, 0, 5);
		gbc_labelTelefonoProprietario.gridx = 1;
		gbc_labelTelefonoProprietario.gridy = 4;
		anagraficaPanel.add(labelTelefonoProprietario, gbc_labelTelefonoProprietario);
		labelTelefono.setLabelFor(labelTelefonoProprietario);

		JLabel labelEmail = new JLabel("E-mail: ");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.fill = GridBagConstraints.BOTH;
		gbc_labelEmail.insets = new Insets(0, 0, 0, 5);
		gbc_labelEmail.gridx = 3;
		gbc_labelEmail.gridy = 4;
		anagraficaPanel.add(labelEmail, gbc_labelEmail);
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel labelEmailProprietario = new JLabel(this.proprietario.getEmail());
		GridBagConstraints gbc_labelEmailProprietario = new GridBagConstraints();
		gbc_labelEmailProprietario.insets = new Insets(0, 0, 0, 5);
		gbc_labelEmailProprietario.fill = GridBagConstraints.BOTH;
		gbc_labelEmailProprietario.gridx = 4;
		gbc_labelEmailProprietario.gridy = 4;
		anagraficaPanel.add(labelEmailProprietario, gbc_labelEmailProprietario);
		labelEmail.setLabelFor(labelEmailProprietario);
		
		this.add(anagraficaPanel);
	}

}
