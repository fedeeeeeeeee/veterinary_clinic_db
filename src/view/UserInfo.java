package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.AnimaleController;
import controller.UserController;
import controller.UserControllerImpl;
import model.Paziente;
import model.Specializzazione;
import model.User;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UserInfo  extends CommonView {
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
	private JTextField textFieldAnniDiServizio;
	private JTextField textFieldSpecializzazioni;
	private JTable table;
	private JScrollPane scrollPane;
	private List<Specializzazione> listSpecializzazioni;
	private DefaultTableModel model;
	private UserController userController;
	
	
	public UserInfo(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.userController = new UserControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	  //GroupLayout groupLayout = new GroupLayout(this);
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
	       
	  	centerPanel.setLayout(new FormLayout(new ColumnSpec[] {
	  			FormSpecs.RELATED_GAP_COLSPEC,
	  			ColumnSpec.decode("max(50dlu;default):grow"),
	  			FormSpecs.RELATED_GAP_COLSPEC,
	  			ColumnSpec.decode("default:grow"),
	  			FormSpecs.RELATED_GAP_COLSPEC,
	  			ColumnSpec.decode("max(184dlu;default)"),
	  			FormSpecs.RELATED_GAP_COLSPEC,
	  			ColumnSpec.decode("default:grow"),
	  			FormSpecs.RELATED_GAP_COLSPEC,
	  			ColumnSpec.decode("max(50dlu;default):grow"),},
	  		new RowSpec[] {
	  			FormSpecs.RELATED_GAP_ROWSPEC,
	  			RowSpec.decode("default:grow"),
	  			FormSpecs.RELATED_GAP_ROWSPEC,
	  			RowSpec.decode("default:grow"),
	  			FormSpecs.RELATED_GAP_ROWSPEC,
	  			FormSpecs.DEFAULT_ROWSPEC,
	  			FormSpecs.RELATED_GAP_ROWSPEC,
	  			FormSpecs.DEFAULT_ROWSPEC,}));
	  	
		JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Anagrafica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		anagraficaPanel.setLayout(new GridLayout(5, 4, 0, 0));
		
		JLabel labelNome = new JLabel("Nome");
		labelNome.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(labelNome);

		textFieldNome = new JTextField();
		textFieldNome.setText(user.getNome());
		labelNome.setLabelFor(textFieldNome);
		anagraficaPanel.add(textFieldNome);

		textFieldNome.setColumns(10);
		JLabel labelCognome = new JLabel("Cognome");
		labelCognome.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(labelCognome);
		textFieldNome.setEditable(false);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setText(user.getCognome());
		
		labelCognome.setLabelFor(textFieldCognome);
		anagraficaPanel.add(textFieldCognome);

		textFieldCognome.setColumns(10);
		JLabel labelCf = new JLabel("CF");
		labelCf.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(labelCf);
		textFieldCognome.setEditable(false);

		textFieldCodiceFiscale = new JTextField();
		textFieldCodiceFiscale.setText(user.getCF());
		labelCf.setLabelFor(textFieldCodiceFiscale);
		anagraficaPanel.add(textFieldCodiceFiscale);
		textFieldCodiceFiscale.setColumns(10);
		textFieldCodiceFiscale.setEditable(false);

		JLabel labelDataNascita = new JLabel("Data di nascita");
		anagraficaPanel.add(labelDataNascita);

		JTextField textFieldData = new JTextField();
		textFieldData.setText(user.getDataNascita());
		labelDataNascita.setLabelFor(textFieldData);
		labelDataNascita.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldData);
		textFieldData.setColumns(10);
		anagraficaPanel.add(textFieldData);
		textFieldData.setEditable(false);

		JLabel labelVia = new JLabel("Via");
		anagraficaPanel.add(labelVia);

		textFieldVia = new JTextField();
		textFieldVia.setText(user.getIndirizzoVia());
		labelVia.setLabelFor(textFieldVia);
		labelVia.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldVia);
		textFieldVia.setColumns(10);
		textFieldVia.setEditable(false);

		JLabel labelNumeroCivico = new JLabel("Nr.");
		anagraficaPanel.add(labelNumeroCivico);

		textFieldNumeroCivico = new JTextField();
		textFieldNumeroCivico.setText(String.valueOf(user.getIndirizzoNumero()));
		labelNumeroCivico.setLabelFor(textFieldNumeroCivico);
		labelNumeroCivico.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldNumeroCivico);
		textFieldNumeroCivico.setColumns(10);
		textFieldNumeroCivico.setEditable(false);

		JLabel labelCitta = new JLabel("Citta");
		anagraficaPanel.add(labelCitta);

		textFieldCitta = new JTextField();
		textFieldCitta.setText(user.getIndirizzoCitta());
		labelCitta.setLabelFor(textFieldCitta);
		labelCitta.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldCitta);
		textFieldCitta.setColumns(10);
		textFieldCitta.setEditable(false);

		JLabel labelCap = new JLabel("CAP");
		anagraficaPanel.add(labelCap);

		textFieldCap = new JTextField();
		textFieldCap.setText(String.valueOf(user.getIndirizzoCap()));
		labelCap.setLabelFor(textFieldCap);
		labelCap.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldCap);
		textFieldCap.setColumns(10);
		textFieldCap.setEditable(false);

		JLabel labelTelefono = new JLabel("Telefono");
		anagraficaPanel.add(labelTelefono);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setText(String.valueOf(user.getTelefono()));
		labelTelefono.setLabelFor(textFieldTelefono);
		labelTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setEditable(false);

		JLabel labelEmail = new JLabel("E-mail");
		anagraficaPanel.add(labelEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setText(user.getEmail());
		labelEmail.setLabelFor(textFieldEmail);
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		anagraficaPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		textFieldEmail.setEditable(false);

		JButton backButton = new JButton("Home");
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getRuolo().equals("Veterinario") || user.getRuolo().equals("Inferemiere")  ) {
					frame.getContentPane().add(new HomeVeterinario(frame, user));
				}else { 
					frame.getContentPane().add(new HomeSegretaria(frame, user));
				}
				
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);
		
		
		JLabel titleLabel = new JLabel("Profilo utente");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel);
		setLayout(null);
			
		centerPanel.add(anagraficaPanel, "4, 2, 5, 1, fill, fill");
		JPanel datiPanel = new JPanel();
		datiPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Lavoro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		centerPanel.add(datiPanel, "3, 4, 7, 4, fill, fill");
		GridBagLayout gbl_datiPanel = new GridBagLayout();
		gbl_datiPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_datiPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_datiPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_datiPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		datiPanel.setLayout(gbl_datiPanel);
		
		JLabel lblAnniServizio = new JLabel("Anni di Servizio");
		GridBagConstraints gbc_lblAnniServizio = new GridBagConstraints();
		gbc_lblAnniServizio.gridwidth = 6;
		gbc_lblAnniServizio.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnniServizio.gridx = 2;
		gbc_lblAnniServizio.gridy = 1;
		datiPanel.add(lblAnniServizio, gbc_lblAnniServizio);
		
		
		if (user.getRuolo().equals("Veterinario")) { 


			this.initTable();
			this.listSpecializzazioni = userController.getAllSpecializzazioni(user.getCF());
			this.createTable(model, listSpecializzazioni);
			this.model = (DefaultTableModel) table.getModel();
		
		
			textFieldAnniDiServizio = new JTextField();
			textFieldAnniDiServizio.setText(String.valueOf(user.getAnniDiServizio()));
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.gridwidth = 4;
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 2;
			gbc_textField_1.gridy = 2;
			datiPanel.add(textFieldAnniDiServizio, gbc_textField_1);
			textFieldAnniDiServizio.setColumns(10);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
			gbc_scrollPane_1.gridheight = 2;
			gbc_scrollPane_1.gridwidth = 12;
			gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
			gbc_scrollPane_1.gridx = 8;
			gbc_scrollPane_1.gridy = 1;
			datiPanel.add(scrollPane_1, gbc_scrollPane_1);
			scrollPane_1.setViewportView(table);
	
		}
	   // setLayout(groupLayout);
		//new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);
	}
	
	private void resetAndDraw(List<Specializzazione> allAnimali) {
		this.listSpecializzazioni.clear();
		this.listSpecializzazioni = allAnimali;
		//table.clearSelection();
		model.setRowCount(0);
		this.createTable(model, listSpecializzazioni);
	}

	private void createTable(DefaultTableModel model, List<Specializzazione> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { x.getTitolo(), x.getAnnoConseguimento(), });
		});
	}

	private void initTable() {
		this.table = new JTable(); 
		this.model =  (DefaultTableModel) table.getModel();
		model.addColumn("Titolo");
		model.addColumn("Anno");
		
	}
}
