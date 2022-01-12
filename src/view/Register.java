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

		JPanel centerPanel = new JPanel();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		   int w = frame.getSize().width;
           int h = frame.getSize().height;
           int x = (dim.width-w)/2;
           int y = (dim.height-h)/2;
           GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
           
		// utile per il design
          // GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, (int)(w*0.99), GroupLayout.PREFERRED_SIZE)
								.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, (int)(w*0.99),  GroupLayout.PREFERRED_SIZE)
								.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, (int)(w*0.99),  GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, (int)(h*0.2), GroupLayout.PREFERRED_SIZE)
						.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, (int)(h*0.65), GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, (int)(h*0.1), GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		
		JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Anagrafica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		anagraficaPanel.setLayout(new GridLayout(5, 4, 0, 0));
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setHorizontalAlignment(SwingConstants.RIGHT);
		anagraficaPanel.add(labelNome);

		textFieldNome = new JTextField();
		labelNome.setLabelFor(textFieldNome);
		anagraficaPanel.add(textFieldNome);

		textFieldNome.setColumns(10);
		JLabel labelCognome = new JLabel("Cognome");
		labelCognome.setHorizontalAlignment(SwingConstants.RIGHT);
		anagraficaPanel.add(labelCognome);

		textFieldCognome = new JTextField();
		labelCognome.setLabelFor(textFieldCognome);
		anagraficaPanel.add(textFieldCognome);

		textFieldCognome.setColumns(10);
		JLabel labelCf = new JLabel("CF");
		labelCf.setHorizontalAlignment(SwingConstants.RIGHT);
		anagraficaPanel.add(labelCf);

		textFieldCodiceFiscale = new JTextField();
		labelCf.setLabelFor(textFieldCodiceFiscale);
		anagraficaPanel.add(textFieldCodiceFiscale);
		textFieldCodiceFiscale.setColumns(10);

		JLabel labelDataNascita = new JLabel("Data di nascita");
		anagraficaPanel.add(labelDataNascita);

		JDateChooser dateChooser = new JDateChooser();
		labelDataNascita.setLabelFor(dateChooser);
		labelDataNascita.setHorizontalAlignment(SwingConstants.RIGHT);
		anagraficaPanel.add(dateChooser);

		JLabel labelVia = new JLabel("Via");
		anagraficaPanel.add(labelVia);

		textFieldVia = new JTextField();
		labelVia.setLabelFor(textFieldVia);
		labelVia.setHorizontalAlignment(SwingConstants.RIGHT);
		anagraficaPanel.add(textFieldVia);
		textFieldVia.setColumns(10);

		JLabel labelNumeroCivico = new JLabel("Nr.");
		anagraficaPanel.add(labelNumeroCivico);

		textFieldNumeroCivico = new JTextField();
		labelNumeroCivico.setLabelFor(textFieldNumeroCivico);
		labelNumeroCivico.setHorizontalAlignment(SwingConstants.RIGHT);
		anagraficaPanel.add(textFieldNumeroCivico);
		textFieldNumeroCivico.setColumns(10);

		JLabel labelCitta = new JLabel("Citta");
		anagraficaPanel.add(labelCitta);

		textFieldCitta = new JTextField();
		labelCitta.setLabelFor(textFieldCitta);
		labelCitta.setHorizontalAlignment(SwingConstants.RIGHT);
		anagraficaPanel.add(textFieldCitta);
		textFieldCitta.setColumns(10);

		JLabel labelCap = new JLabel("CAP");
		anagraficaPanel.add(labelCap);

		textFieldCap = new JTextField();
		labelCap.setLabelFor(textFieldCap);
		labelCap.setHorizontalAlignment(SwingConstants.RIGHT);
		anagraficaPanel.add(textFieldCap);
		textFieldCap.setColumns(10);

		JLabel labelTelefono = new JLabel("Telefono");
		anagraficaPanel.add(labelTelefono);

		textFieldTelefono = new JTextField();
		labelTelefono.setLabelFor(textFieldTelefono);
		labelTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		anagraficaPanel.add(textFieldTelefono);
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
						frame.getContentPane().add(new HomeVeterinario(frame, user));
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
