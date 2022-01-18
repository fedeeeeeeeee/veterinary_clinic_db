package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import model.User;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UserInfo extends JPanel {

	private static final long serialVersionUID = 8425214808736844760L;
	private JFrame frame;
	private JTextField textFieldEmail;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldCodiceFiscale;
	private JTextField textFieldVia;
	private JTextField textFieldNumeroCivico;
	private JTextField textFieldCitta;
	private JTextField textFieldCap;
	private JTextField textFieldTelefono;
	private User user;
	private JTextField textFieldDataNascita;
	private JTextField textFieldAnniServizio;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public UserInfo(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
			
		JPanel upperPanel = new JPanel();
		upperPanel.setBounds(6, 0, 985, 121);
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBounds(6, 552, 985, 50);
		JPanel centerPanel = new JPanel();
		centerPanel.setBounds(6, 127, 985, 422);
		
		centerPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(184dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

	//	GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	   GroupLayout groupLayout = new GroupLayout(this);
	       groupLayout.setHorizontalGroup(
	       	groupLayout.createParallelGroup(Alignment.LEADING)
	       		.addGroup(groupLayout.createSequentialGroup()
	       			.addContainerGap()
	       			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	       				.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
	       				.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
	       				.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE))
	       			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	       );
	       groupLayout.setVerticalGroup(
	       	groupLayout.createParallelGroup(Alignment.LEADING)
	       		.addGroup(groupLayout.createSequentialGroup()
	       			.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
	       			.addPreferredGap(ComponentPlacement.RELATED)
	       			.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
	       			.addPreferredGap(ComponentPlacement.RELATED)
	       			.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
	       			.addContainerGap()));
	      centerPanel.setLayout(new BorderLayout(0, 0));
	       
		
		JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Anagrafica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		anagraficaPanel.setLayout(new GridLayout(5, 4, 0, 0));
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(labelNome);

		textFieldNome = new JTextField();
		labelNome.setLabelFor(textFieldNome);
		anagraficaPanel.add(textFieldNome);

		textFieldNome.setColumns(10);
		JLabel labelCognome = new JLabel("Cognome");
		labelCognome.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(labelCognome);
		textFieldNome.setEditable(false);
		
		textFieldCognome = new JTextField();
		labelCognome.setLabelFor(textFieldCognome);
		anagraficaPanel.add(textFieldCognome);

		textFieldCognome.setColumns(10);
		JLabel labelCf = new JLabel("CF");
		labelCf.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(labelCf);
		textFieldCognome.setEditable(false);

		textFieldCodiceFiscale = new JTextField();
		labelCf.setLabelFor(textFieldCodiceFiscale);
		anagraficaPanel.add(textFieldCodiceFiscale);
		textFieldCodiceFiscale.setColumns(10);
		textFieldCodiceFiscale.setEditable(false);

		JLabel labelDataNascita = new JLabel("Data di nascita");
		anagraficaPanel.add(labelDataNascita);

		JTextField textFieldData = new JTextField();

		labelDataNascita.setLabelFor(textFieldData);
		labelDataNascita.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldData);
		textFieldData.setColumns(10);
		anagraficaPanel.add(textFieldData);
		textFieldData.setEditable(false);

		JLabel labelVia = new JLabel("Via");
		anagraficaPanel.add(labelVia);

		textFieldVia = new JTextField();
		labelVia.setLabelFor(textFieldVia);
		labelVia.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldVia);
		textFieldVia.setColumns(10);
		textFieldVia.setEditable(false);

		JLabel labelNumeroCivico = new JLabel("Nr.");
		anagraficaPanel.add(labelNumeroCivico);

		textFieldNumeroCivico = new JTextField();
		labelNumeroCivico.setLabelFor(textFieldNumeroCivico);
		labelNumeroCivico.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldNumeroCivico);
		textFieldNumeroCivico.setColumns(10);
		textFieldNumeroCivico.setEditable(false);

		JLabel labelCitta = new JLabel("Citta");
		anagraficaPanel.add(labelCitta);

		textFieldCitta = new JTextField();
		labelCitta.setLabelFor(textFieldCitta);
		labelCitta.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldCitta);
		textFieldCitta.setColumns(10);
		textFieldCitta.setEditable(false);

		JLabel labelCap = new JLabel("CAP");
		anagraficaPanel.add(labelCap);

		textFieldCap = new JTextField();
		labelCap.setLabelFor(textFieldCap);
		labelCap.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldCap);
		textFieldCap.setColumns(10);
		textFieldCap.setEditable(false);

		JLabel labelTelefono = new JLabel("Telefono");
		anagraficaPanel.add(labelTelefono);

		textFieldTelefono = new JTextField();
		labelTelefono.setLabelFor(textFieldTelefono);
		labelTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setEditable(false);

		JLabel labelEmail = new JLabel("E-mail");
		anagraficaPanel.add(labelEmail);

		textFieldEmail = new JTextField();
		labelEmail.setLabelFor(textFieldEmail);
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		textFieldEmail.setEditable(false);

		JButton backButton = new JButton("BACK");
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new RegistraPaziente(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);
		
		
		JLabel titleLabel = new JLabel("Profilo utente");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel);
		setLayout(null);
		
		
		centerPanel.add(anagraficaPanel, "4, 2, 5, 1, fill, fill");
		
		
		add(centerPanel);
		
		JPanel datiPanel = new JPanel();
		datiPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Lavoro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		centerPanel.add(datiPanel, "5, 4, 2, 4, fill, fill");
		GridBagLayout gbl_datiPanel = new GridBagLayout();
		gbl_datiPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_datiPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_datiPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_datiPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		datiPanel.setLayout(gbl_datiPanel);
		
		JLabel lblAnniServizio = new JLabel("Anni di Servizio");
		GridBagConstraints gbc_lblAnniServizio = new GridBagConstraints();
		gbc_lblAnniServizio.gridwidth = 6;
		gbc_lblAnniServizio.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnniServizio.gridx = 3;
		gbc_lblAnniServizio.gridy = 1;
		datiPanel.add(lblAnniServizio, gbc_lblAnniServizio);
		
		JLabel lblSpecializzazione = new JLabel("Specializzazioni");
		GridBagConstraints gbc_lblSpecializzazione = new GridBagConstraints();
		gbc_lblSpecializzazione.gridwidth = 7;
		gbc_lblSpecializzazione.anchor = GridBagConstraints.WEST;
		gbc_lblSpecializzazione.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpecializzazione.gridx = 11;
		gbc_lblSpecializzazione.gridy = 1;
		datiPanel.add(lblSpecializzazione, gbc_lblSpecializzazione);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 4;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 2;
		datiPanel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 7;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 11;
		gbc_textField_2.gridy = 2;
		datiPanel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		add(upperPanel);
		add(lowerPanel);
		
	    setLayout(groupLayout);

		//new GroupLayout(this);
		// this.initCommonView(frame);
		// importante per la visualizzazione 
		//frame.getContentPane().setLayout(groupLayout);
	}
}
