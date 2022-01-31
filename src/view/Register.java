package view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;

import java.awt.Insets;
import javax.swing.SwingConstants;
import controller.UserController;
import controller.UserControllerImpl;
import model.User;
import model.UserImpl;
import utility.ValidityFieldsChecker;
public class Register extends JPanel {

    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private UserController controller;
    private User user;
	private JTextField textFieldEmail;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldCodiceFiscale;
	private JTextField textFieldVia;
	private JTextField textFieldNumeroCivico;
	private JTextField textFieldCitta;
	private JTextField textFieldCap;
	private JTextField textFieldTelefono;
	private ValidityFieldsChecker check;
	
    public Register(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    	this.check = new ValidityFieldsChecker();
        this.frame = frame;
        initialize();
    }

    private void initialize() {
    	controller = new UserControllerImpl();        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        
        JLabel loginLabel = new JLabel("<html><font color='#56728f '>Registrazione</font></html>");
        loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 40)); 

        JButton loginButton = new JButton("Accesso");
        loginButton.addActionListener(new ActionListener() {
           	public void actionPerformed(ActionEvent e) {
           		frame.getContentPane().add(new Login(frame));
           	}
           });
        JButton btnRegister = new JButton("Registrazione");
        btnRegister.setEnabled(false);
    	JButton sendButton = new JButton("Invia");
    	sendButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JPanel centerPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout(0, 0));
         
	
       // GROUP LAYOUT
        //TODO
  GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
     // GroupLayout groupLayout = new GroupLayout(this);
      groupLayout.setHorizontalGroup(
      	groupLayout.createParallelGroup(Alignment.LEADING)
      		.addGroup(groupLayout.createSequentialGroup()
      			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
      			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
      				.addGroup(groupLayout.createSequentialGroup()
      					.addGap(60)
      					.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE))
      				.addGroup(groupLayout.createSequentialGroup()
      					.addGap(300)
      					.addComponent(loginLabel))
      				.addGroup(groupLayout.createSequentialGroup()
      					.addGap(370)
      					.addComponent(sendButton))))
      );
      groupLayout.setVerticalGroup(
      	groupLayout.createParallelGroup(Alignment.LEADING)
      		.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      		.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
      			.addGap(50)
      			.addComponent(loginLabel)
      			.addGap(50)
      			.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
      			.addGap(2)
      			.addComponent(sendButton))
      			//.addGap(270))
      );

       
   	centerPanel.setLayout(new FormLayout(new ColumnSpec[] {
   			FormSpecs.RELATED_GAP_COLSPEC,
   			FormSpecs.DEFAULT_COLSPEC,
   			FormSpecs.RELATED_GAP_COLSPEC,
   			ColumnSpec.decode("default:grow"),},
   		new RowSpec[] {
   			FormSpecs.RELATED_GAP_ROWSPEC,
   			RowSpec.decode("max(113dlu;default)"),
   			FormSpecs.RELATED_GAP_ROWSPEC,
   			FormSpecs.DEFAULT_ROWSPEC,}));

	JPanel anagraficaPanel = new JPanel();
	anagraficaPanel.setBorder(new TitledBorder(
			new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
			"Anagrafica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagLayout gbl_anagraficaPanel = new GridBagLayout();
	gbl_anagraficaPanel.columnWidths = new int[] { 40, 142, 142, 65, 142, 142, 40, 0 };
	gbl_anagraficaPanel.rowHeights = new int[] { 29, 29, 29, 29, 29, 0 };
	gbl_anagraficaPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	gbl_anagraficaPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	anagraficaPanel.setLayout(gbl_anagraficaPanel);

	JLabel labelNome = new JLabel("Nome");
	labelNome.setLabelFor(textFieldNome);
	labelNome.setHorizontalAlignment(SwingConstants.RIGHT);
	GridBagConstraints gbc_labelNome = new GridBagConstraints();
	gbc_labelNome.anchor = GridBagConstraints.WEST;
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
	gbc_labelCognome.anchor = GridBagConstraints.WEST;
	gbc_labelCognome.fill = GridBagConstraints.VERTICAL;
	gbc_labelCognome.insets = new Insets(0, 0, 5, 5);
	gbc_labelCognome.gridx = 4;
	gbc_labelCognome.gridy = 0;
	anagraficaPanel.add(labelCognome, gbc_labelCognome);
	labelCognome.setLabelFor(textFieldCognome);

	textFieldCognome = new JTextField();
	GridBagConstraints gbc_textFieldCognome = new GridBagConstraints();
	gbc_textFieldCognome.fill = GridBagConstraints.BOTH;
	gbc_textFieldCognome.insets = new Insets(0, 0, 5, 5);
	gbc_textFieldCognome.gridx = 5;
	gbc_textFieldCognome.gridy = 0;
	anagraficaPanel.add(textFieldCognome, gbc_textFieldCognome);

	textFieldCognome.setColumns(10);
	JLabel labelCf = new JLabel("CF");
	labelCf.setHorizontalAlignment(SwingConstants.RIGHT);
	GridBagConstraints gbc_labelCf = new GridBagConstraints();
	gbc_labelCf.anchor = GridBagConstraints.WEST;
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
	gbc_labelDataNascita.anchor = GridBagConstraints.WEST;
	gbc_labelDataNascita.fill = GridBagConstraints.VERTICAL;
	gbc_labelDataNascita.insets = new Insets(0, 0, 5, 5);
	gbc_labelDataNascita.gridx = 4;
	gbc_labelDataNascita.gridy = 1;
	anagraficaPanel.add(labelDataNascita, gbc_labelDataNascita);
	labelDataNascita.setHorizontalAlignment(SwingConstants.RIGHT);

	GridBagConstraints gbc_dateChooser = new GridBagConstraints();
	gbc_dateChooser.fill = GridBagConstraints.BOTH;
	gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
	gbc_dateChooser.gridx = 5;
	gbc_dateChooser.gridy = 1;
	anagraficaPanel.add(dateChooser, gbc_dateChooser);

	JLabel labelVia = new JLabel("Via");
	labelVia.setLabelFor(textFieldVia);
	GridBagConstraints gbc_labelVia = new GridBagConstraints();
	gbc_labelVia.anchor = GridBagConstraints.WEST;
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
	gbc_labelNumeroCivico.anchor = GridBagConstraints.WEST;
	gbc_labelNumeroCivico.fill = GridBagConstraints.VERTICAL;
	gbc_labelNumeroCivico.insets = new Insets(0, 0, 5, 5);
	gbc_labelNumeroCivico.gridx = 4;
	gbc_labelNumeroCivico.gridy = 2;
	anagraficaPanel.add(labelNumeroCivico, gbc_labelNumeroCivico);
	labelNumeroCivico.setHorizontalAlignment(SwingConstants.RIGHT);

	textFieldNumeroCivico = new JTextField();
	GridBagConstraints gbc_textFieldNumeroCivico = new GridBagConstraints();
	gbc_textFieldNumeroCivico.fill = GridBagConstraints.BOTH;
	gbc_textFieldNumeroCivico.insets = new Insets(0, 0, 5, 5);
	gbc_textFieldNumeroCivico.gridx = 5;
	gbc_textFieldNumeroCivico.gridy = 2;
	anagraficaPanel.add(textFieldNumeroCivico, gbc_textFieldNumeroCivico);
	textFieldNumeroCivico.setColumns(10);

	JLabel labelCitta = new JLabel("Citta");
	GridBagConstraints gbc_labelCitta = new GridBagConstraints();
	gbc_labelCitta.anchor = GridBagConstraints.WEST;
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
	gbc_labelCap.anchor = GridBagConstraints.WEST;
	gbc_labelCap.fill = GridBagConstraints.VERTICAL;
	gbc_labelCap.insets = new Insets(0, 0, 5, 5);
	gbc_labelCap.gridx = 4;
	gbc_labelCap.gridy = 3;
	anagraficaPanel.add(labelCap, gbc_labelCap);
	labelCap.setHorizontalAlignment(SwingConstants.RIGHT);
	labelCap.setLabelFor(textFieldCap);

	textFieldCap = new JTextField();
	GridBagConstraints gbc_textFieldCap = new GridBagConstraints();
	gbc_textFieldCap.fill = GridBagConstraints.BOTH;
	gbc_textFieldCap.insets = new Insets(0, 0, 5, 5);
	gbc_textFieldCap.gridx = 5;
	gbc_textFieldCap.gridy = 3;
	anagraficaPanel.add(textFieldCap, gbc_textFieldCap);
	textFieldCap.setColumns(10);

	JLabel labelTelefono = new JLabel("Telefono");
	GridBagConstraints gbc_labelTelefono = new GridBagConstraints();
	gbc_labelTelefono.anchor = GridBagConstraints.WEST;
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
	GridBagLayout gbl_datiAccessoPanel = new GridBagLayout();
	gbl_datiAccessoPanel.columnWidths = new int[]{40, 140, 147, 15, 0};
	gbl_datiAccessoPanel.rowHeights = new int[]{22, 22, 0};
	gbl_datiAccessoPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_datiAccessoPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	datiAccessoPanel.setLayout(gbl_datiAccessoPanel);
	
		JLabel labelEmail = new JLabel("E-mail");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.anchor = GridBagConstraints.WEST;
		gbc_labelEmail.fill = GridBagConstraints.VERTICAL;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 0;
		datiAccessoPanel.add(labelEmail, gbc_labelEmail);
		labelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
	textFieldEmail = new JTextField();
	GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
	gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
	gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
	gbc_textFieldEmail.gridx = 2;
	gbc_textFieldEmail.gridy = 0;
	datiAccessoPanel.add(textFieldEmail, gbc_textFieldEmail);
	textFieldEmail.setColumns(10);
	JLabel labelPassword = new JLabel("Password");
	GridBagConstraints gbc_labelPassword = new GridBagConstraints();
	gbc_labelPassword.anchor = GridBagConstraints.WEST;
	gbc_labelPassword.fill = GridBagConstraints.VERTICAL;
	gbc_labelPassword.insets = new Insets(0, 0, 0, 5);
	gbc_labelPassword.gridx = 1;
	gbc_labelPassword.gridy = 1;
	datiAccessoPanel.add(labelPassword, gbc_labelPassword);
	labelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
	
	passwordField = new JPasswordField();
	GridBagConstraints gbc_passwordField = new GridBagConstraints();
	gbc_passwordField.insets = new Insets(0, 0, 0, 5);
	gbc_passwordField.fill = GridBagConstraints.BOTH;
	gbc_passwordField.gridx = 2;
	gbc_passwordField.gridy = 1;
	datiAccessoPanel.add(passwordField, gbc_passwordField);

		JPanel ruoloPanel = new JPanel();
		ruoloPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ruolo",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_ruoloPanel = new GridBagLayout();
		gbl_ruoloPanel.columnWidths = new int[] { 35, 129, 137, 40, 0 };
		gbl_ruoloPanel.rowHeights = new int[] { 23, 23, 0 };
		gbl_ruoloPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_ruoloPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		ruoloPanel.setLayout(gbl_ruoloPanel);

		JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));

		JLabel labelAnniServizio = new JLabel("Anni di servizio");
		labelAnniServizio.setLabelFor(spinner);
		GridBagConstraints gbc_labelAnniServizio = new GridBagConstraints();
		gbc_labelAnniServizio.anchor = GridBagConstraints.WEST;
		gbc_labelAnniServizio.fill = GridBagConstraints.VERTICAL;
		gbc_labelAnniServizio.insets = new Insets(0, 0, 5, 5);
		gbc_labelAnniServizio.gridx = 1;
		gbc_labelAnniServizio.gridy = 0;
		ruoloPanel.add(labelAnniServizio, gbc_labelAnniServizio);
		labelAnniServizio.setHorizontalAlignment(SwingConstants.RIGHT);
		JFormattedTextField txt2 = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
		((NumberFormatter) txt2.getFormatter()).setAllowsInvalid(false);

		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.BOTH;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 0;
		ruoloPanel.add(spinner, gbc_spinner);
		JLabel labelRuolo = new JLabel("Ruolo");
		JComboBox<String> comboBox = new JComboBox<>();
		labelRuolo.setLabelFor(comboBox);

		GridBagConstraints gbc_labelRuolo = new GridBagConstraints();
		gbc_labelRuolo.anchor = GridBagConstraints.WEST;
		gbc_labelRuolo.fill = GridBagConstraints.VERTICAL;
		gbc_labelRuolo.insets = new Insets(0, 0, 0, 5);
		gbc_labelRuolo.gridx = 1;
		gbc_labelRuolo.gridy = 1;
		ruoloPanel.add(labelRuolo, gbc_labelRuolo);
		labelRuolo.setHorizontalAlignment(SwingConstants.RIGHT);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		ruoloPanel.add(comboBox, gbc_comboBox);

	String[] ruoli = controller.getAllRuoli().toArray(new String[0]);
	for (int i = 0; i < ruoli.length; i++) {
		comboBox.addItem(ruoli[i]);
	}

	centerPanel.add(anagraficaPanel, "2, 2, 3, 1, fill, fill");

	centerPanel.add(datiAccessoPanel, "2, 4, fill, fill");

	centerPanel.add(ruoloPanel, "4, 4, fill, fill");


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

       
       //BARRA LATERALE
       GroupLayout gl_panel = new GroupLayout(panel);
       gl_panel.setHorizontalGroup(
       	gl_panel.createParallelGroup(Alignment.LEADING)
       		.addGroup(gl_panel.createSequentialGroup()
       			.addContainerGap()
       			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
       				.addComponent(btnRegister, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
       				.addComponent(loginButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
       			.addContainerGap())
       );
       gl_panel.setVerticalGroup(
       	gl_panel.createParallelGroup(Alignment.LEADING)
       		.addGroup(gl_panel.createSequentialGroup()
       			.addGap(77)
       			.addComponent(loginButton)
       			.addGap(18)
       			.addComponent(btnRegister)
       			.addContainerGap(271, Short.MAX_VALUE))
       );
       panel.setLayout(gl_panel);
  frame.getContentPane().setLayout(groupLayout);
    }
}
