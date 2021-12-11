package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import controller.UserController;
import controller.UserControllerImpl;
import model.User;
import model.UserImpl;
import utility.ValidityFieldsChecker;

import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Register extends JPanel {

	private static final long serialVersionUID = -6907259299289467414L;
	private JFrame frame;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldCodiceFiscale;
	private JTextField textFieldVia;
	private JTextField textFieldNumeroCivico;
	private JTextField textFieldCitta;
	private JTextField textFieldCap;
	private JTextField textFieldTelefono;
	private ValidityFieldsChecker check;
	private User user;
	private UserController controller;

	public Register(JFrame frame) {		
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.check = new ValidityFieldsChecker();
		this.controller = new UserControllerImpl();

		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Anagrafica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel datiAccessoPanel = new JPanel();
		datiAccessoPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Dati di accesso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel ruoloPanel = new JPanel();
		ruoloPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ruolo",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		

		// utile per il design
		//GroupLayout groupLayout = new GroupLayout(this);
		
		
		// per la visualizzazione
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(upperPanel,
								GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lowerPanel,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(27)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(anagraficaPanel, GroupLayout.PREFERRED_SIZE, 701,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(datiAccessoPanel, GroupLayout.PREFERRED_SIZE, 434,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(ruoloPanel,
														GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)))
				.addContainerGap()));
		
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(5)
				.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE).addGap(3)
				.addComponent(anagraficaPanel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(datiAccessoPanel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addComponent(ruoloPanel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                frame.getContentPane().add(new MainPage(frame));
			}
		});
		lowerPanel.add(backButton, BorderLayout.WEST);

		JLabel titleLabel = new JLabel("Registrazione");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel);
		ruoloPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel labelAnniServizio = new JLabel("Anni di servizio");
		ruoloPanel.add(labelAnniServizio);
		
		JSpinner spinner = new JSpinner();
		labelAnniServizio.setLabelFor(spinner);
		ruoloPanel.add(spinner);
		
		JLabel labelRuolo = new JLabel("Ruolo");
		ruoloPanel.add(labelRuolo);
		
		String[] ruoli = controller.getAllRuoli().toArray(new String[0]);		
		JComboBox<String> comboBox = new JComboBox<String>(ruoli);
		labelRuolo.setLabelFor(comboBox);
		ruoloPanel.add(comboBox);

		
		datiAccessoPanel.setLayout(new GridLayout(2, 2, 0, 0));
		anagraficaPanel.setLayout(new GridLayout(5, 4, 0, 0));
		
		
		JLabel labelEmail = new JLabel("E-mail");
		datiAccessoPanel.add(labelEmail);
		JLabel labelPassword = new JLabel("Password");
		textFieldEmail = new JTextField();
		datiAccessoPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		datiAccessoPanel.add(labelPassword);
		passwordField = new JPasswordField();
		datiAccessoPanel.add(passwordField);
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(labelNome);

		textFieldNome = new JTextField();
		labelNome.setLabelFor(textFieldNome);
		anagraficaPanel.add(textFieldNome);
		
		textFieldNome.setColumns(10);
		JLabel labelCognome = new JLabel("Cognome");
		anagraficaPanel.add(labelCognome);

		textFieldCognome = new JTextField();
		labelCognome.setLabelFor(textFieldCognome);
		anagraficaPanel.add(textFieldCognome);
		
		textFieldCognome.setColumns(10);
		JLabel labelCf = new JLabel("CF");
		anagraficaPanel.add(labelCf);
		
		textFieldCodiceFiscale = new JTextField();
		labelCf.setLabelFor(textFieldCodiceFiscale);
		anagraficaPanel.add(textFieldCodiceFiscale);
		textFieldCodiceFiscale.setColumns(10);
		
		JLabel labelDataNascita = new JLabel("Data di nascita");
		anagraficaPanel.add(labelDataNascita);
		
		JDateChooser dateChooser = new JDateChooser();
		labelDataNascita.setLabelFor(dateChooser);
		anagraficaPanel.add(dateChooser);

		JLabel labelVia = new JLabel("Via");
		anagraficaPanel.add(labelVia);
		
		textFieldVia = new JTextField();
		labelVia.setLabelFor(textFieldVia);
		anagraficaPanel.add(textFieldVia);
		textFieldVia.setColumns(10);

		JLabel labelNumeroCivico = new JLabel("Nr.");
		anagraficaPanel.add(labelNumeroCivico);
		
		textFieldNumeroCivico = new JTextField();
		labelNumeroCivico.setLabelFor(textFieldNumeroCivico);
		anagraficaPanel.add(textFieldNumeroCivico);
		textFieldNumeroCivico.setColumns(10);

		JLabel labelCitta = new JLabel("Citta");
		anagraficaPanel.add(labelCitta);
		
		textFieldCitta = new JTextField();
		labelCitta.setLabelFor(textFieldCitta);
		anagraficaPanel.add(textFieldCitta);
		textFieldCitta.setColumns(10);

		JLabel lblNewLabel = new JLabel("CAP");
		anagraficaPanel.add(lblNewLabel);
		
		textFieldCap = new JTextField();
		lblNewLabel.setLabelFor(textFieldCap);
		anagraficaPanel.add(textFieldCap);
		textFieldCap.setColumns(10);
		
		JLabel labelTelefono = new JLabel("Telefono");
		anagraficaPanel.add(labelTelefono);
		
		textFieldTelefono = new JTextField();
		labelTelefono.setLabelFor(textFieldTelefono);
		anagraficaPanel.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		JButton sendButton = new JButton("SEND");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check.checkCF(textFieldCodiceFiscale.getText()) && check.checkEmail(textFieldEmail.getText())
						&& check.checkPhoneNumber(textFieldTelefono.getText())
						&& check.checkNotEmpty(textFieldNome.getText())
						&& check.checkNotEmpty(textFieldCognome.getText())
						&& check.checkNotEmpty(textFieldVia.getText())
						&& check.checkNumber(textFieldNumeroCivico.getText())
						&& check.checkNotEmpty(textFieldNumeroCivico.getText())
						&& check.checkCap(textFieldCap.getText()) && check.checkNotEmpty(textFieldCap.getText())
						&& check.checkNotEmpty(textFieldCitta.getText()) && spinner.getValue() != null
						&& dateChooser.getDate() != null
				&& comboBox.getSelectedItem() != null
				) {
					System.out.println("tutto ok");
					user = new UserImpl();

					user.setNome(textFieldNome.getText());
					user.setCognome(textFieldCognome.getText());
					user.setCF(textFieldCodiceFiscale.getText());
					user.setIndirizzoVia(textFieldVia.getText());
					user.setIndirizzoNumero(Integer.valueOf(textFieldNumeroCivico.getText()));
					user.setIndirizzoCitta(textFieldCitta.getText());
					user.setIndirizzoCap(Integer.valueOf(textFieldCap.getText()));
					user.setRuolo(comboBox.getSelectedItem().toString());
					dateChooser.setDateFormatString("yyyy-MM-dd");

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date = format.format(dateChooser.getDate());
					user.setDataNascita(date);
					user.setIndirizzoCitta(textFieldCitta.getText());
					user.setTelefono(Long.valueOf(textFieldTelefono.getText()));
					user.setEmail(textFieldEmail.getText());
					user.setHashPassword(String.valueOf(passwordField.getPassword()));

					controller.addNewUser(user);
					
					switch (user.getRuolo()) {
					case "Veterinario":
					case "Infermiere":
						// TODO
						// frame.getContentPane().add(new Home(frame, user));
						break;
					case "Segretaria":
						frame.getContentPane().add(new HomeSegretaria(frame, user));
						break;
					default:
					}
				} else {
					// errore di compilazione della form
					System.out.println("errore");
				}
			}
		});
		lowerPanel.add(sendButton, BorderLayout.EAST);

		// importante per il design
		//setLayout(groupLayout);

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout = new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
	}

}
