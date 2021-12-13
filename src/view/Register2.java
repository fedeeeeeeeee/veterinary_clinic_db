package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;
import controller.UserController;
import controller.UserControllerImpl;
import model.User;
import model.UserImpl;
import utility.ValidityFieldsChecker;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Register2 extends JPanel {

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

	public Register2(JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.check = new ValidityFieldsChecker();
		this.controller = new UserControllerImpl();

		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();

		JPanel centerPanel = new JPanel();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		   int w = frame.getSize().width;
           int h = frame.getSize().height;
           int x = (dim.width-w)/2;
           int y = (dim.height-h)/2;
          GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
           
		// utile per il design
        //GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								//748
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, (int)(w*0.99), GroupLayout.PREFERRED_SIZE)
								.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, (int)(w*0.99),  GroupLayout.PREFERRED_SIZE)
								.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, (int)(w*0.99),  GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
							/*addComponent(upperPanel, GroupLayout.PREFERRED_SIZE,748, GroupLayout.PREFERRED_SIZE)
								.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 748,  GroupLayout.PREFERRED_SIZE)
								.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 748,  GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));*/
		
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						// 58 250 67 
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, (int)(h*0.2), GroupLayout.PREFERRED_SIZE)
						.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, (int)(h*0.65), GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, (int)(h*0.1), GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
						/*
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		*/
		centerPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(113dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		
		JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Anagrafica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_anagraficaPanel = new GridBagLayout();
		gbl_anagraficaPanel.columnWidths = new int[]{47, 142, 142, 142, 142, 0, 0};
		gbl_anagraficaPanel.rowHeights = new int[]{29, 29, 29, 29, 29, 0};
		gbl_anagraficaPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_anagraficaPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		anagraficaPanel.setLayout(gbl_anagraficaPanel);
					
						
						JLabel labelNome = new JLabel("Nome");
						labelNome.setLabelFor(textFieldNome);
						labelNome.setHorizontalAlignment(SwingConstants.RIGHT);
						GridBagConstraints gbc_labelNome = new GridBagConstraints();
						gbc_labelNome.fill = GridBagConstraints.VERTICAL;
						gbc_labelNome.insets = new Insets(0, 0, 5, 5);
						gbc_labelNome.gridx = 1;
						gbc_labelNome.gridy = 0;
						anagraficaPanel.add(labelNome, gbc_labelNome);
				
						textFieldNome = new JTextField();
						GridBagConstraints gbc_textFieldNome = new GridBagConstraints();
						gbc_textFieldNome.fill = GridBagConstraints.BOTH;
						gbc_textFieldNome.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldNome.gridx = 2;
						gbc_textFieldNome.gridy = 0;
						anagraficaPanel.add(textFieldNome, gbc_textFieldNome);
						
								textFieldNome.setColumns(10);
				JLabel labelCognome = new JLabel("Cognome");
				labelCognome.setHorizontalAlignment(SwingConstants.RIGHT);
				GridBagConstraints gbc_labelCognome = new GridBagConstraints();
				gbc_labelCognome.fill = GridBagConstraints.VERTICAL;
				gbc_labelCognome.insets = new Insets(0, 0, 5, 5);
				gbc_labelCognome.gridx = 3;
				gbc_labelCognome.gridy = 0;
				anagraficaPanel.add(labelCognome, gbc_labelCognome);
				labelCognome.setLabelFor(textFieldCognome);
				
						textFieldCognome = new JTextField();
						GridBagConstraints gbc_textFieldCognome = new GridBagConstraints();
						gbc_textFieldCognome.fill = GridBagConstraints.BOTH;
						gbc_textFieldCognome.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldCognome.gridx = 4;
						gbc_textFieldCognome.gridy = 0;
						anagraficaPanel.add(textFieldCognome, gbc_textFieldCognome);
						
								textFieldCognome.setColumns(10);
				JLabel labelCf = new JLabel("CF");
				labelCf.setHorizontalAlignment(SwingConstants.RIGHT);
				GridBagConstraints gbc_labelCf = new GridBagConstraints();
				gbc_labelCf.fill = GridBagConstraints.VERTICAL;
				gbc_labelCf.insets = new Insets(0, 0, 5, 5);
				gbc_labelCf.gridx = 1;
				gbc_labelCf.gridy = 1;
				anagraficaPanel.add(labelCf, gbc_labelCf);
				labelCf.setLabelFor(textFieldCodiceFiscale);
				
						textFieldCodiceFiscale = new JTextField();
						GridBagConstraints gbc_textFieldCodiceFiscale = new GridBagConstraints();
						gbc_textFieldCodiceFiscale.fill = GridBagConstraints.BOTH;
						gbc_textFieldCodiceFiscale.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldCodiceFiscale.gridx = 2;
						gbc_textFieldCodiceFiscale.gridy = 1;
						anagraficaPanel.add(textFieldCodiceFiscale, gbc_textFieldCodiceFiscale);
						textFieldCodiceFiscale.setColumns(10);
			
				
						JDateChooser dateChooser = new JDateChooser();
						JLabel labelDataNascita = new JLabel("Data di nascita");
						labelDataNascita.setLabelFor(dateChooser);
						GridBagConstraints gbc_labelDataNascita = new GridBagConstraints();
						gbc_labelDataNascita.fill = GridBagConstraints.VERTICAL;
						gbc_labelDataNascita.insets = new Insets(0, 0, 5, 5);
						gbc_labelDataNascita.gridx = 3;
						gbc_labelDataNascita.gridy = 1;
						anagraficaPanel.add(labelDataNascita, gbc_labelDataNascita);
						labelDataNascita.setHorizontalAlignment(SwingConstants.RIGHT);
		
			
				GridBagConstraints gbc_dateChooser = new GridBagConstraints();
				gbc_dateChooser.fill = GridBagConstraints.BOTH;
				gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
				gbc_dateChooser.gridx = 4;
				gbc_dateChooser.gridy = 1;
				anagraficaPanel.add(dateChooser, gbc_dateChooser);
								
								
										JLabel labelVia = new JLabel("Via");
										labelVia.setLabelFor(textFieldVia);
										GridBagConstraints gbc_labelVia = new GridBagConstraints();
										gbc_labelVia.fill = GridBagConstraints.VERTICAL;
										gbc_labelVia.insets = new Insets(0, 0, 5, 5);
										gbc_labelVia.gridx = 1;
										gbc_labelVia.gridy = 2;
										anagraficaPanel.add(labelVia, gbc_labelVia);
										labelVia.setHorizontalAlignment(SwingConstants.RIGHT);
						
								textFieldVia = new JTextField();
								GridBagConstraints gbc_textFieldVia = new GridBagConstraints();
								gbc_textFieldVia.fill = GridBagConstraints.BOTH;
								gbc_textFieldVia.insets = new Insets(0, 0, 5, 5);
								gbc_textFieldVia.gridx = 2;
								gbc_textFieldVia.gridy = 2;
								anagraficaPanel.add(textFieldVia, gbc_textFieldVia);
								textFieldVia.setColumns(10);
					
						
								JLabel labelNumeroCivico = new JLabel("Nr.");
								labelNumeroCivico.setLabelFor(textFieldNumeroCivico);
								GridBagConstraints gbc_labelNumeroCivico = new GridBagConstraints();
								gbc_labelNumeroCivico.fill = GridBagConstraints.VERTICAL;
								gbc_labelNumeroCivico.insets = new Insets(0, 0, 5, 5);
								gbc_labelNumeroCivico.gridx = 3;
								gbc_labelNumeroCivico.gridy = 2;
								anagraficaPanel.add(labelNumeroCivico, gbc_labelNumeroCivico);
								labelNumeroCivico.setHorizontalAlignment(SwingConstants.RIGHT);
				
						textFieldNumeroCivico = new JTextField();
						GridBagConstraints gbc_textFieldNumeroCivico = new GridBagConstraints();
						gbc_textFieldNumeroCivico.fill = GridBagConstraints.BOTH;
						gbc_textFieldNumeroCivico.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldNumeroCivico.gridx = 4;
						gbc_textFieldNumeroCivico.gridy = 2;
						anagraficaPanel.add(textFieldNumeroCivico, gbc_textFieldNumeroCivico);
						textFieldNumeroCivico.setColumns(10);
		
				JLabel labelCitta = new JLabel("Citta");
				GridBagConstraints gbc_labelCitta = new GridBagConstraints();
				gbc_labelCitta.fill = GridBagConstraints.VERTICAL;
				gbc_labelCitta.insets = new Insets(0, 0, 5, 5);
				gbc_labelCitta.gridx = 1;
				gbc_labelCitta.gridy = 3;
				anagraficaPanel.add(labelCitta, gbc_labelCitta);
				labelCitta.setHorizontalAlignment(SwingConstants.RIGHT);
						labelCitta.setLabelFor(textFieldCitta);
						
								textFieldCitta = new JTextField();
								GridBagConstraints gbc_textFieldCitta = new GridBagConstraints();
								gbc_textFieldCitta.fill = GridBagConstraints.BOTH;
								gbc_textFieldCitta.insets = new Insets(0, 0, 5, 5);
								gbc_textFieldCitta.gridx = 2;
								gbc_textFieldCitta.gridy = 3;
								anagraficaPanel.add(textFieldCitta, gbc_textFieldCitta);
								textFieldCitta.setColumns(10);
				
						JLabel labelCap = new JLabel("CAP");
						GridBagConstraints gbc_labelCap = new GridBagConstraints();
						gbc_labelCap.fill = GridBagConstraints.VERTICAL;
						gbc_labelCap.insets = new Insets(0, 0, 5, 5);
						gbc_labelCap.gridx = 3;
						gbc_labelCap.gridy = 3;
						anagraficaPanel.add(labelCap, gbc_labelCap);
						labelCap.setHorizontalAlignment(SwingConstants.RIGHT);
				labelCap.setLabelFor(textFieldCap);
				
						textFieldCap = new JTextField();
						GridBagConstraints gbc_textFieldCap = new GridBagConstraints();
						gbc_textFieldCap.fill = GridBagConstraints.BOTH;
						gbc_textFieldCap.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldCap.gridx = 4;
						gbc_textFieldCap.gridy = 3;
						anagraficaPanel.add(textFieldCap, gbc_textFieldCap);
						textFieldCap.setColumns(10);
		
				JLabel labelTelefono = new JLabel("Telefono");
				GridBagConstraints gbc_labelTelefono = new GridBagConstraints();
				gbc_labelTelefono.fill = GridBagConstraints.VERTICAL;
				gbc_labelTelefono.insets = new Insets(0, 0, 0, 5);
				gbc_labelTelefono.gridx = 1;
				gbc_labelTelefono.gridy = 4;
				anagraficaPanel.add(labelTelefono, gbc_labelTelefono);
				labelTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTelefono.setLabelFor(textFieldTelefono);
		
				textFieldTelefono = new JTextField();
				GridBagConstraints gbc_textFieldTelefono = new GridBagConstraints();
				gbc_textFieldTelefono.fill = GridBagConstraints.BOTH;
				gbc_textFieldTelefono.insets = new Insets(0, 0, 0, 5);
				gbc_textFieldTelefono.gridx = 2;
				gbc_textFieldTelefono.gridy = 4;
				anagraficaPanel.add(textFieldTelefono, gbc_textFieldTelefono);
				textFieldTelefono.setColumns(10);

		JPanel datiAccessoPanel = new JPanel();
		datiAccessoPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Dati di accesso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		datiAccessoPanel.setLayout(new GridLayout(2, 2, 5, 1));

		JLabel labelEmail = new JLabel("E-mail");
		datiAccessoPanel.add(labelEmail);
		labelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel labelPassword = new JLabel("Password");
		textFieldEmail = new JTextField();
		datiAccessoPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		datiAccessoPanel.add(labelPassword);
		labelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordField = new JPasswordField();
		datiAccessoPanel.add(passwordField);

		JPanel ruoloPanel = new JPanel();
		ruoloPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ruolo",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ruoloPanel.setLayout(new GridLayout(2, 2, 5, 1));

		JLabel labelAnniServizio = new JLabel("Anni di servizio");
		ruoloPanel.add(labelAnniServizio);
		labelAnniServizio.setHorizontalAlignment(SwingConstants.RIGHT);


		JSpinner spinner = new JSpinner();
		labelAnniServizio.setLabelFor(spinner);
		ruoloPanel.add(spinner);

		JLabel labelRuolo = new JLabel("Ruolo");
		ruoloPanel.add(labelRuolo);
		JComboBox<String> comboBox = new JComboBox<>();
		labelRuolo.setLabelFor(comboBox);
		labelRuolo.setHorizontalAlignment(SwingConstants.RIGHT);
		ruoloPanel.add(comboBox);
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

		String[] ruoli = controller.getAllRuoli().toArray(new String[0]);
		for (int i = 0; i < ruoli.length; i++) {
			comboBox.addItem(ruoli[i]);
		}

		JPanel panelSx = new JPanel();
		centerPanel.add(panelSx, "2, 2, 1, 3, fill, fill");
		panelSx.setMinimumSize(new Dimension(20,6));
		
		centerPanel.add(anagraficaPanel, "4, 2, 3, 1, fill, fill");
		
		JPanel panelDx = new JPanel();
		centerPanel.add(panelDx, "8, 2, 1, 3, fill, fill");
		
		centerPanel.add(datiAccessoPanel, "4, 4, fill, fill");
		
		centerPanel.add(ruoloPanel, "6, 4, fill, fill");

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
						&& dateChooser.getDate() != null && comboBox.getSelectedItem() != null) {
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

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout =
		new GroupLayout(this);
	frame.getContentPane().setLayout(groupLayout);
	}
}
