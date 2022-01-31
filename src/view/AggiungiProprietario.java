package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import com.toedter.calendar.JDateChooser;

import controller.ProprietarioController;
import controller.ProprietarioControllerImpl;
import model.Person;
import model.Proprietario;
import model.User;
import utility.ValidityFieldsChecker;

public class AggiungiProprietario extends CommonView {

	private static final long serialVersionUID = 1L;
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
	private ValidityFieldsChecker check;
	private Person proprietario;
	private ProprietarioController controller;
	private User user;
	
	public AggiungiProprietario(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.check = new ValidityFieldsChecker();
		this.proprietario = new Proprietario();
		this.controller = new ProprietarioControllerImpl();
			
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());           
		// utile per il design
        // GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
								.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
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
		labelNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelNome.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(labelNome);

		textFieldNome = new JTextField();
		labelNome.setLabelFor(textFieldNome);
		anagraficaPanel.add(textFieldNome);

		textFieldNome.setColumns(10);
		JLabel labelCognome = new JLabel("Cognome");
		labelCognome.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelCognome.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(labelCognome);

		textFieldCognome = new JTextField();
		labelCognome.setLabelFor(textFieldCognome);
		anagraficaPanel.add(textFieldCognome);

		textFieldCognome.setColumns(10);
		JLabel labelCf = new JLabel("CF");
		labelCf.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelCf.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(labelCf);

		textFieldCodiceFiscale = new JTextField();
		labelCf.setLabelFor(textFieldCodiceFiscale);
		anagraficaPanel.add(textFieldCodiceFiscale);
		textFieldCodiceFiscale.setColumns(10);

		JLabel labelDataNascita = new JLabel("Data di nascita");
		labelDataNascita.setFont(new Font("Tahoma", Font.BOLD, 13));
		anagraficaPanel.add(labelDataNascita);

		JDateChooser dateChooser = new JDateChooser();
		labelDataNascita.setLabelFor(dateChooser);
		labelDataNascita.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(dateChooser);

		JLabel labelVia = new JLabel("Via");
		labelVia.setFont(new Font("Tahoma", Font.BOLD, 13));
		anagraficaPanel.add(labelVia);

		textFieldVia = new JTextField();
		labelVia.setLabelFor(textFieldVia);
		labelVia.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldVia);
		textFieldVia.setColumns(10);

		JLabel labelNumeroCivico = new JLabel("Nr.");
		labelNumeroCivico.setFont(new Font("Tahoma", Font.BOLD, 13));
		anagraficaPanel.add(labelNumeroCivico);

		textFieldNumeroCivico = new JTextField();
		labelNumeroCivico.setLabelFor(textFieldNumeroCivico);
		labelNumeroCivico.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldNumeroCivico);
		textFieldNumeroCivico.setColumns(10);

		JLabel labelCitta = new JLabel("Citta");
		labelCitta.setFont(new Font("Tahoma", Font.BOLD, 13));
		anagraficaPanel.add(labelCitta);

		textFieldCitta = new JTextField();
		labelCitta.setLabelFor(textFieldCitta);
		labelCitta.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldCitta);
		textFieldCitta.setColumns(10);

		JLabel labelCap = new JLabel("CAP");
		labelCap.setFont(new Font("Tahoma", Font.BOLD, 13));
		anagraficaPanel.add(labelCap);

		textFieldCap = new JTextField();
		labelCap.setLabelFor(textFieldCap);
		labelCap.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldCap);
		textFieldCap.setColumns(10);

		JLabel labelTelefono = new JLabel("Telefono");
		labelTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
		anagraficaPanel.add(labelTelefono);

		textFieldTelefono = new JTextField();
		labelTelefono.setLabelFor(textFieldTelefono);
		labelTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JLabel labelEmail = new JLabel("E-mail");
		labelEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		anagraficaPanel.add(labelEmail);

		textFieldEmail = new JTextField();
		labelEmail.setLabelFor(textFieldEmail);
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

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
		
		
		JLabel titleLabel = new JLabel("Aggiungi proprietario");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		upperPanel.add(titleLabel);
		JPanel panelSx = new JPanel();
		centerPanel.add(panelSx, "2, 2, 1, 3, fill, fill");
		panelSx.setMinimumSize(new Dimension(20,6));
		centerPanel.add(anagraficaPanel, "4, 2, 3, 1, fill, fill");
		JPanel panelDx = new JPanel();
		centerPanel.add(panelDx, "8, 2, 1, 3, fill, fill");
		
				
		JButton sendButton = new JButton("SEND");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check.checkCF(textFieldCodiceFiscale.getText()) 
						&& check.checkEmail(textFieldEmail.getText())
						&& check.checkPhoneNumber(textFieldTelefono.getText())
						&& check.checkNotEmpty(textFieldNome.getText())
						&& check.checkNotEmpty(textFieldCognome.getText())
						&& check.checkNotEmpty(textFieldVia.getText())
						&& check.checkNumber(textFieldNumeroCivico.getText())
						&& check.checkNotEmpty(textFieldNumeroCivico.getText())
						&& check.checkCap(textFieldCap.getText()) 
						&& check.checkNotEmpty(textFieldCap.getText())
						&& check.checkNotEmpty(textFieldCitta.getText())
						&& dateChooser.getDate() != null) {
					System.out.println("tutto ok");
					
					proprietario = new Proprietario();

					proprietario.setNome(textFieldNome.getText());
					proprietario.setCognome(textFieldCognome.getText());
					proprietario.setCF(textFieldCodiceFiscale.getText());
					proprietario.setIndirizzoVia(textFieldVia.getText());
					proprietario.setIndirizzoNumero(Integer.valueOf(textFieldNumeroCivico.getText()));
					proprietario.setIndirizzoCitta(textFieldCitta.getText());
					proprietario.setIndirizzoCap(Integer.valueOf(textFieldCap.getText()));
					
					dateChooser.setDateFormatString("yyyy-MM-dd");
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date = format.format(dateChooser.getDate());
					proprietario.setDataNascita(date);
					proprietario.setIndirizzoCitta(textFieldCitta.getText());
					proprietario.setTelefono(Long.valueOf(textFieldTelefono.getText()));
					proprietario.setEmail(textFieldEmail.getText());

					controller.addNewProprietario(proprietario);
					// paziente inserito correttamente
					frame.getContentPane().add(new RegistraPaziente(frame, user));
				} else {
					// errore di compilazione della form
					System.out.println("errore");
				}
			}
		});

		centerPanel.add(sendButton, "5, 3, 3, 2, center, center");
		
		// importante per il design
		//setLayout(groupLayout);

		// importante per la visualizzazione 
		this.initCommonView(frame, user);

		frame.getContentPane().setLayout(groupLayout);
	}

}
