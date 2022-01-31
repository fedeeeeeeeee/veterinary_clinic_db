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

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
		
		
		JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Anagrafica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_anagraficaPanel = new GridBagLayout();
		gbl_anagraficaPanel.columnWidths = new int[]{0, 40, 67, 60, 67, 114, 0, 0};
		gbl_anagraficaPanel.rowHeights = new int[]{0, 30, 5, 30, 5, 30, 5, 30, 5, 30, 0, 0, 0};
		gbl_anagraficaPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_anagraficaPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		anagraficaPanel.setLayout(gbl_anagraficaPanel);
		JLabel labelNome = new JLabel("Nome");
				labelNome.setLabelFor(textFieldNome);
				labelNome.setFont(new Font("Tahoma", Font.BOLD, 13));
				labelNome.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_labelNome = new GridBagConstraints();
				gbc_labelNome.fill = GridBagConstraints.BOTH;
				gbc_labelNome.insets = new Insets(0, 0, 5, 5);
				gbc_labelNome.gridx = 1;
				gbc_labelNome.gridy = 1;
				anagraficaPanel.add(labelNome, gbc_labelNome);
		
				textFieldNome = new JTextField();
				GridBagConstraints gbc_textFieldNome = new GridBagConstraints();
				gbc_textFieldNome.fill = GridBagConstraints.BOTH;
				gbc_textFieldNome.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldNome.gridx = 2;
				gbc_textFieldNome.gridy = 1;
				anagraficaPanel.add(textFieldNome, gbc_textFieldNome);
				
						textFieldNome.setColumns(10);
						JLabel labelCognome = new JLabel("Cognome");
				labelCognome.setLabelFor(textFieldCognome);
			
				labelCognome.setFont(new Font("Tahoma", Font.BOLD, 13));
				labelCognome.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_labelCognome = new GridBagConstraints();
				gbc_labelCognome.fill = GridBagConstraints.BOTH;
				gbc_labelCognome.insets = new Insets(0, 0, 5, 5);
				gbc_labelCognome.gridx = 4;
				gbc_labelCognome.gridy = 1;
				anagraficaPanel.add(labelCognome, gbc_labelCognome);
		
				textFieldCognome = new JTextField();
				GridBagConstraints gbc_textFieldCognome = new GridBagConstraints();
				gbc_textFieldCognome.fill = GridBagConstraints.BOTH;
				gbc_textFieldCognome.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldCognome.gridx = 5;
				gbc_textFieldCognome.gridy = 1;
				anagraficaPanel.add(textFieldCognome, gbc_textFieldCognome);
				
						textFieldCognome.setColumns(10);
		JLabel labelCf = new JLabel("CF");
		labelCf.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelCf.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelCf = new GridBagConstraints();
		gbc_labelCf.fill = GridBagConstraints.BOTH;
		gbc_labelCf.insets = new Insets(0, 0, 5, 5);
		gbc_labelCf.gridx = 1;
		gbc_labelCf.gridy = 3;
		anagraficaPanel.add(labelCf, gbc_labelCf);
		labelCf.setLabelFor(textFieldCodiceFiscale);
		
				textFieldCodiceFiscale = new JTextField();
				GridBagConstraints gbc_textFieldCodiceFiscale = new GridBagConstraints();
				gbc_textFieldCodiceFiscale.fill = GridBagConstraints.BOTH;
				gbc_textFieldCodiceFiscale.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldCodiceFiscale.gridx = 2;
				gbc_textFieldCodiceFiscale.gridy = 3;
				anagraficaPanel.add(textFieldCodiceFiscale, gbc_textFieldCodiceFiscale);
				textFieldCodiceFiscale.setColumns(10);
				
						
						
								JDateChooser dateChooser = new JDateChooser();
								GridBagConstraints gbc_dateChooser = new GridBagConstraints();
								gbc_dateChooser.fill = GridBagConstraints.BOTH;
								gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
								gbc_dateChooser.gridx = 5;
								gbc_dateChooser.gridy = 3;
								anagraficaPanel.add(dateChooser, gbc_dateChooser);
								JLabel labelVia = new JLabel("Via");
								labelVia.setLabelFor(textFieldVia);
								
								JLabel labelDataNascita = new JLabel("Data di nascita");
								labelDataNascita.setLabelFor(dateChooser);	
										labelDataNascita.setFont(new Font("Tahoma", Font.BOLD, 13));
										GridBagConstraints gbc_labelDataNascita = new GridBagConstraints();
										gbc_labelDataNascita.fill = GridBagConstraints.BOTH;
										gbc_labelDataNascita.insets = new Insets(0, 0, 5, 5);
										gbc_labelDataNascita.gridx = 4;
										gbc_labelDataNascita.gridy = 3;
										anagraficaPanel.add(labelDataNascita, gbc_labelDataNascita);
										labelDataNascita.setHorizontalAlignment(SwingConstants.LEFT);
										
										labelVia.setFont(new Font("Tahoma", Font.BOLD, 13));
										GridBagConstraints gbc_labelVia = new GridBagConstraints();
										gbc_labelVia.fill = GridBagConstraints.BOTH;
										gbc_labelVia.insets = new Insets(0, 0, 5, 5);
										gbc_labelVia.gridx = 1;
										gbc_labelVia.gridy = 5;
										anagraficaPanel.add(labelVia, gbc_labelVia);
										labelVia.setHorizontalAlignment(SwingConstants.LEFT);
						
								textFieldVia = new JTextField();
								GridBagConstraints gbc_textFieldVia = new GridBagConstraints();
								gbc_textFieldVia.fill = GridBagConstraints.BOTH;
								gbc_textFieldVia.insets = new Insets(0, 0, 5, 5);
								gbc_textFieldVia.gridx = 2;
								gbc_textFieldVia.gridy = 5;
								anagraficaPanel.add(textFieldVia, gbc_textFieldVia);
								textFieldVia.setColumns(10);
								
								JLabel labelNumeroCivico = new JLabel("Nr.");
								labelNumeroCivico.setLabelFor(textFieldNumeroCivico);
								labelNumeroCivico.setFont(new Font("Tahoma", Font.BOLD, 13));
								GridBagConstraints gbc_labelNumeroCivico = new GridBagConstraints();
								gbc_labelNumeroCivico.fill = GridBagConstraints.BOTH;
								gbc_labelNumeroCivico.insets = new Insets(0, 0, 5, 5);
								gbc_labelNumeroCivico.gridx = 4;
								gbc_labelNumeroCivico.gridy = 5;
								anagraficaPanel.add(labelNumeroCivico, gbc_labelNumeroCivico);
								labelNumeroCivico.setHorizontalAlignment(SwingConstants.LEFT);
				
						textFieldNumeroCivico = new JTextField();
						GridBagConstraints gbc_textFieldNumeroCivico = new GridBagConstraints();
						gbc_textFieldNumeroCivico.fill = GridBagConstraints.BOTH;
						gbc_textFieldNumeroCivico.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldNumeroCivico.gridx = 5;
						gbc_textFieldNumeroCivico.gridy = 5;
						anagraficaPanel.add(textFieldNumeroCivico, gbc_textFieldNumeroCivico);
						textFieldNumeroCivico.setColumns(10);
				
						JLabel labelCitta = new JLabel("Citta");
						labelCitta.setFont(new Font("Tahoma", Font.BOLD, 13));
						GridBagConstraints gbc_labelCitta = new GridBagConstraints();
						gbc_labelCitta.fill = GridBagConstraints.BOTH;
						gbc_labelCitta.insets = new Insets(0, 0, 5, 5);
						gbc_labelCitta.gridx = 1;
						gbc_labelCitta.gridy = 7;
						anagraficaPanel.add(labelCitta, gbc_labelCitta);
						labelCitta.setHorizontalAlignment(SwingConstants.LEFT);
				labelCitta.setLabelFor(textFieldCitta);
				
						textFieldCitta = new JTextField();
						GridBagConstraints gbc_textFieldCitta = new GridBagConstraints();
						gbc_textFieldCitta.fill = GridBagConstraints.BOTH;
						gbc_textFieldCitta.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldCitta.gridx = 2;
						gbc_textFieldCitta.gridy = 7;
						anagraficaPanel.add(textFieldCitta, gbc_textFieldCitta);
						textFieldCitta.setColumns(10);
						
						JLabel labelCap = new JLabel("CAP");
						labelCap.setLabelFor(textFieldCap);	
						labelCap.setFont(new Font("Tahoma", Font.BOLD, 13));
						GridBagConstraints gbc_labelCap = new GridBagConstraints();
						gbc_labelCap.fill = GridBagConstraints.BOTH;
						gbc_labelCap.insets = new Insets(0, 0, 5, 5);
						gbc_labelCap.gridx = 4;
						gbc_labelCap.gridy = 7;
						anagraficaPanel.add(labelCap, gbc_labelCap);
						labelCap.setHorizontalAlignment(SwingConstants.LEFT);
		
				textFieldCap = new JTextField();
				GridBagConstraints gbc_textFieldCap = new GridBagConstraints();
				gbc_textFieldCap.fill = GridBagConstraints.BOTH;
				gbc_textFieldCap.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldCap.gridx = 5;
				gbc_textFieldCap.gridy = 7;
				anagraficaPanel.add(textFieldCap, gbc_textFieldCap);
				textFieldCap.setColumns(10);
				
				JLabel labelTelefono = new JLabel("Telefono");
				labelTelefono.setLabelFor(textFieldTelefono);
						labelTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
						GridBagConstraints gbc_labelTelefono = new GridBagConstraints();
						gbc_labelTelefono.fill = GridBagConstraints.BOTH;
						gbc_labelTelefono.insets = new Insets(0, 0, 5, 5);
						gbc_labelTelefono.gridx = 1;
						gbc_labelTelefono.gridy = 9;
						anagraficaPanel.add(labelTelefono, gbc_labelTelefono);
						labelTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		
				textFieldTelefono = new JTextField();
				GridBagConstraints gbc_textFieldTelefono = new GridBagConstraints();
				gbc_textFieldTelefono.fill = GridBagConstraints.BOTH;
				gbc_textFieldTelefono.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldTelefono.gridx = 2;
				gbc_textFieldTelefono.gridy = 9;
				anagraficaPanel.add(textFieldTelefono, gbc_textFieldTelefono);
				textFieldTelefono.setColumns(10);
		
		JLabel labelEmail = new JLabel("E-mail");
		labelEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.fill = GridBagConstraints.BOTH;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 4;
		gbc_labelEmail.gridy = 9;
		anagraficaPanel.add(labelEmail, gbc_labelEmail);
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		labelEmail.setLabelFor(textFieldEmail);
		
				textFieldEmail = new JTextField();
				GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
				gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
				gbc_textFieldEmail.gridx = 5;
				gbc_textFieldEmail.gridy = 9;
				anagraficaPanel.add(textFieldEmail, gbc_textFieldEmail);
				textFieldEmail.setColumns(10);

		  Icon icon = IconImage.BACK.getImageIcon();
			JButton backButton = new JButton(icon);
			backButton.setBorder(BorderFactory.createEmptyBorder());
			backButton.setContentAreaFilled(false);
			backButton.setHorizontalAlignment(SwingConstants.LEFT);
			backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new RegistraPaziente(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);
		
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel =new JLabel("<html><font color='#56728f '>Aggiungi proprietario</font></html>");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		upperPanel.add(titleLabel, BorderLayout.CENTER);
		
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		centerPanel.add(anagraficaPanel, BorderLayout.CENTER);
		JPanel middlelowerPanel = new JPanel();
		centerPanel.add(middlelowerPanel, BorderLayout.SOUTH);
		
		JButton sendButton = new JButton("Salva");
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
                    frame.getContentPane().add(new RegistraPaziente(frame, user));
                } else {
                    System.out.println("errore");
                }
            }
        });
		middlelowerPanel.add(sendButton);
		//setLayout(groupLayout);
		//this.initCommonView(frame, user);

		frame.getContentPane().setLayout(groupLayout);
	}
}
