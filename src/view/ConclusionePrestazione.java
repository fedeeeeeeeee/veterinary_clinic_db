package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controller.AnimaleController;
import controller.AnimaleControllerImpl;
import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import controller.UserController;
import controller.UserControllerImpl;
import model.Animale;
import model.Appuntamento;
import model.AppuntamentoImpl;
import model.Farmaco;
import model.Patologia;
import model.Person;
import model.Prestazione;
import model.User;
import utility.Pair;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class ConclusionePrestazione extends CommonView {

	private static final long serialVersionUID = 693737436773825328L;
	private JFrame frame;
	private User user;
	private PrestazioneController prestazioneController;
	private JTextField textFieldNomeProprietario;
	private DefaultTableModel model;
	private List<Appuntamento> listAppuntamenti;
	private AnimaleController animaleController;
	private UserController userController;
	private JTextField textFieldTelefono;
	private JTextField textFieldNomA;
	private JTextField textFieldData;
	private JTextField textFieldOra;
	private JTextField textFieldTipo;
	private JTextField textFieldNomePrestazione;
	private JTextField textFieldAltezza;
	private JTextField textFieldPeso;
	private Appuntamento appuntamento;
	private JTextField textFieldProprietario;

	private void createTable(DefaultTableModel model, List<Appuntamento> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { 
					x.getAnimale().getNome(),
					x.getData(), 
					x.getOra(), 
					x.getDurata(),
					x.getVeterinario().getNome(),
					x.getVeterinario().getCognome(),
			});
		});
	}

	private void initTable() {
		model.addColumn("Animale");
		model.addColumn("Data");
		model.addColumn("Ora");
		model.addColumn("Durata");
		model.addColumn("Nome ");
		model.addColumn("Cognome");
	}
	
	private void resetAndDraw(List<Appuntamento> allAppuntamento) {
		this.listAppuntamenti.clear();
		this.listAppuntamenti = allAppuntamento;
		model.setRowCount(0);
		this.createTable(model, listAppuntamenti);
		// scrollPane.setViewportView(table);
	}
	

	
	public ConclusionePrestazione(JFrame frame, User user, Appuntamento appuntamento ) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.appuntamento = appuntamento;
		this.animaleController = new AnimaleControllerImpl();
		this.prestazioneController = new PrestazioneControllerImpl();
		this.userController = new UserControllerImpl();
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	//	GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
					.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
					.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
					.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(44)
					.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addGap(84)
					.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(0))
		);
		middlePanel.setLayout(new BorderLayout());

		JPanel scrollPanelLeft = new JPanel();
		JPanel scrollPanelRight = new JPanel();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPanelLeft, scrollPanelRight);
		scrollPanelRight.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblFarmaci = new JLabel("Farmaci Prescritti");
		scrollPanelRight.add(lblFarmaci, "2, 2");
		
		JButton btnAddFarmaco = new JButton("+");
		scrollPanelRight.add(btnAddFarmaco, "4, 2");
		
		JPanel panelFarmaco = new JPanel();
		scrollPanelRight.add(panelFarmaco, "2, 4, 3, 1, fill, fill");
		GridBagLayout gbl_panelFarmaco = new GridBagLayout();
		gbl_panelFarmaco.columnWidths = new int[]{142, 2, 0};
		gbl_panelFarmaco.rowHeights = new int[]{2, 0};
		gbl_panelFarmaco.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelFarmaco.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelFarmaco.setLayout(gbl_panelFarmaco);
		
		JScrollPane scrollPaneFarmaco = new JScrollPane();
		GridBagConstraints gbc_scrollPaneFarmaco = new GridBagConstraints();
		gbc_scrollPaneFarmaco.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPaneFarmaco.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneFarmaco.gridx = 0;
		gbc_scrollPaneFarmaco.gridy = 0;
		panelFarmaco.add(scrollPaneFarmaco, gbc_scrollPaneFarmaco);
		
		JLabel lblPatologie = new JLabel("Patologie Diagnosticate");
		scrollPanelRight.add(lblPatologie, "2, 6");
		
		JButton btnAddPatologia = new JButton("+");
		scrollPanelRight.add(btnAddPatologia, "4, 6");
		
		JPanel panelPatologia = new JPanel();
		scrollPanelRight.add(panelPatologia, "2, 8, 3, 1, fill, fill");
		GridBagLayout gbl_panelPatologia = new GridBagLayout();
		gbl_panelPatologia.columnWidths = new int[]{175, 2, 0};
		gbl_panelPatologia.rowHeights = new int[]{2, 0};
		gbl_panelPatologia.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelPatologia.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelPatologia.setLayout(gbl_panelPatologia);
		
		JScrollPane scrollPanePatologia = new JScrollPane();
		GridBagConstraints gbc_scrollPanePatologia = new GridBagConstraints();
		gbc_scrollPanePatologia.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPanePatologia.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePatologia.gridx = 0;
		gbc_scrollPanePatologia.gridy = 0;
		panelPatologia.add(scrollPanePatologia, gbc_scrollPanePatologia);
		

		JTable tableFarmaci = new JTable();
		DefaultTableModel modelFarmaci = (DefaultTableModel) tableFarmaci.getModel();
		List<Farmaco> farmaci = prestazioneController.getFarmaciInPrestazione(appuntamento);
				
		modelFarmaci.addColumn("Nome");
		modelFarmaci.addColumn("Descrizione");
		modelFarmaci.addColumn("Indicazioni");
		
		farmaci.forEach(x -> {
			modelFarmaci.addRow(new Object[] { 
					x.getNomeFarmaco(),
					x.getDescrizione(), 
					x.getIndicazioni()
			});
		});
		scrollPaneFarmaco.setViewportView(tableFarmaci);

		
		
		JTable tablePatologie = new JTable();
		DefaultTableModel modelPatologie = (DefaultTableModel) tablePatologie.getModel();
		List<Patologia> patologie = prestazioneController.getPatologieAttiveDiAnimale(appuntamento);

		modelPatologie.addColumn("Nome");
		modelPatologie.addColumn("Descrizione");
		modelPatologie.addColumn("Data inizio");

		patologie.forEach(x -> {
			modelPatologie.addRow(new Object[] { 
					x.getNomePatologia(),
					x.getDescrizione(),
					x.getDataInizio()
			});
		});
		scrollPanePatologia.setViewportView(tablePatologie);

		List<Patologia> patologieDaGuarire = new ArrayList<>();
		tablePatologie.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				   int rowIndex = tablePatologie.getSelectedRow();
				   int reply = JOptionPane.showConfirmDialog(null, "Il paziente è guarito dalla seguente patologia: " + patologie.get(rowIndex).getNomePatologia() + " ?", "Guarigione", JOptionPane.YES_NO_OPTION);
				   if (reply == JOptionPane.YES_OPTION) {
					   String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					   patologie.get(rowIndex).setDataFine(date);
					   patologieDaGuarire.add(patologie.get(rowIndex));
				   } 
			}
		});
		
		/* FINE search bar */
		GridBagLayout gbl_scrollPanelLeft = new GridBagLayout();
		gbl_scrollPanelLeft.columnWidths = new int[] { 0, 0, 0, -23, -23, 0, 0, 0, 0, 0, 0, 0 };
		gbl_scrollPanelLeft.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_scrollPanelLeft.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_scrollPanelLeft.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0,
				1.0, 0.0, Double.MIN_VALUE };
		scrollPanelLeft.setLayout(gbl_scrollPanelLeft);

		JLabel lblAnimale = new JLabel("Nome Animale");
		GridBagConstraints gbc_lblAnimale = new GridBagConstraints();
		gbc_lblAnimale.anchor = GridBagConstraints.WEST;
		gbc_lblAnimale.gridwidth = 4;
		gbc_lblAnimale.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnimale.gridx = 1;
		gbc_lblAnimale.gridy = 1;
		scrollPanelLeft.add(lblAnimale, gbc_lblAnimale);
	
		textFieldNomA = new JTextField();
		textFieldNomA.setEditable(false);
		GridBagConstraints gbc_textFieldNomA = new GridBagConstraints();
		gbc_textFieldNomA.gridwidth = 5;
		gbc_textFieldNomA.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNomA.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNomA.gridx = 5;
		gbc_textFieldNomA.gridy = 1;
		scrollPanelLeft.add(textFieldNomA, gbc_textFieldNomA);
		textFieldNomA.setColumns(10);
		
		
		JLabel lblProprietario = new JLabel("Proprietario");
		GridBagConstraints gbc_lblProprietario = new GridBagConstraints();
		gbc_lblProprietario.anchor = GridBagConstraints.WEST;
		gbc_lblProprietario.gridwidth = 4;
		gbc_lblProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_lblProprietario.gridx = 1;
		gbc_lblProprietario.gridy = 2;
		scrollPanelLeft.add(lblProprietario, gbc_lblProprietario);

		textFieldNomeProprietario = new JTextField();
		textFieldNomeProprietario.setEditable(false);
		
		textFieldProprietario = new JTextField();
		textFieldProprietario.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 5;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 2;
		scrollPanelLeft.add(textFieldProprietario, gbc_textField);
		textFieldProprietario.setColumns(10);
		
	
		JLabel lblTelefono = new JLabel("Telefono");
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.anchor = GridBagConstraints.WEST;
		gbc_lblTelefono.gridwidth = 4;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 1;
		gbc_lblTelefono.gridy = 3;
		scrollPanelLeft.add(lblTelefono, gbc_lblTelefono);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 5;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 3;
		scrollPanelLeft.add(textFieldTelefono, gbc_textField_1);
		textFieldTelefono.setColumns(10);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 4;
		scrollPanelLeft.add(separator, gbc_separator);
		
				JLabel lblData = new JLabel("Data");
				GridBagConstraints gbc_lblData = new GridBagConstraints();
				gbc_lblData.gridwidth = 3;
				gbc_lblData.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblData.insets = new Insets(0, 0, 5, 5);
				gbc_lblData.gridx = 1;
				gbc_lblData.gridy = 5;
				scrollPanelLeft.add(lblData, gbc_lblData);
		
		textFieldData = new JTextField();
		textFieldData.setEditable(false);
		GridBagConstraints gbc_textFieldData = new GridBagConstraints();
		gbc_textFieldData.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldData.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldData.gridx = 4;
		gbc_textFieldData.gridy = 5;
		scrollPanelLeft.add(textFieldData, gbc_textFieldData);
		textFieldData.setColumns(10);

		JLabel lblOra = new JLabel("Ora");
		GridBagConstraints gbc_lblOra = new GridBagConstraints();
		gbc_lblOra.anchor = GridBagConstraints.EAST;
		gbc_lblOra.insets = new Insets(0, 0, 5, 5);
		gbc_lblOra.gridx = 7;
		gbc_lblOra.gridy = 5;
		scrollPanelLeft.add(lblOra, gbc_lblOra);
		//timePicker.setText("Meeting time");
		LocalTime local = LocalTime.now();
		
		textFieldOra = new JTextField();
		textFieldOra.setEditable(false);
		GridBagConstraints gbc_textFieldOra = new GridBagConstraints();
		gbc_textFieldOra.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldOra.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldOra.gridx = 8;
		gbc_textFieldOra.gridy = 5;
		scrollPanelLeft.add(textFieldOra, gbc_textFieldOra);
		textFieldOra.setColumns(10);
        
		JLabel lblTipoPrestazione = new JLabel("Tipo Prestazione");
		GridBagConstraints gbc_lblTipoPrestazione = new GridBagConstraints();
		gbc_lblTipoPrestazione.anchor = GridBagConstraints.WEST;
		gbc_lblTipoPrestazione.gridwidth = 4;
		gbc_lblTipoPrestazione.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoPrestazione.gridx = 1;
		gbc_lblTipoPrestazione.gridy = 6;
		scrollPanelLeft.add(lblTipoPrestazione, gbc_lblTipoPrestazione);
		
		textFieldTipo = new JTextField();
		textFieldTipo.setEditable(false);
		GridBagConstraints gbc_textFieldTipo = new GridBagConstraints();
		gbc_textFieldTipo.gridwidth = 5;
		gbc_textFieldTipo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTipo.gridx = 5;
		gbc_textFieldTipo.gridy = 6;
		scrollPanelLeft.add(textFieldTipo, gbc_textFieldTipo);
		textFieldTipo.setColumns(10);
		
		JLabel lblNomPrestazione = new JLabel("Nome Prestazione");
		GridBagConstraints gbc_lblNomPrestazione = new GridBagConstraints();
		gbc_lblNomPrestazione.anchor = GridBagConstraints.WEST;
		gbc_lblNomPrestazione.gridwidth = 4;
		gbc_lblNomPrestazione.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomPrestazione.gridx = 1;
		gbc_lblNomPrestazione.gridy = 7;
		scrollPanelLeft.add(lblNomPrestazione, gbc_lblNomPrestazione);
		
		
		textFieldNomePrestazione = new JTextField();
		textFieldNomePrestazione.setEditable(false);
		GridBagConstraints gbc_textFieldNomePrestazione = new GridBagConstraints();
		gbc_textFieldNomePrestazione.gridwidth = 5;
		gbc_textFieldNomePrestazione.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNomePrestazione.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNomePrestazione.gridx = 5;
		gbc_textFieldNomePrestazione.gridy = 7;
		scrollPanelLeft.add(textFieldNomePrestazione, gbc_textFieldNomePrestazione);
		textFieldNomePrestazione.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridwidth = 10;
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 9;
		scrollPanelLeft.add(separator_1, gbc_separator_1);

		JLabel lblNewLabel = new JLabel("Dati rilevati");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 10;
		scrollPanelLeft.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblAltezza = new JLabel("Altezza");
		GridBagConstraints gbc_lblAltezza = new GridBagConstraints();
		gbc_lblAltezza.anchor = GridBagConstraints.WEST;
		gbc_lblAltezza.insets = new Insets(0, 0, 5, 5);
		gbc_lblAltezza.gridx = 3;
		gbc_lblAltezza.gridy = 12;
		scrollPanelLeft.add(lblAltezza, gbc_lblAltezza);
		
		textFieldAltezza = new JTextField();
		GridBagConstraints gbc_textFieldAltezza = new GridBagConstraints();
		gbc_textFieldAltezza.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAltezza.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAltezza.gridx = 4;
		gbc_textFieldAltezza.gridy = 12;
		scrollPanelLeft.add(textFieldAltezza, gbc_textFieldAltezza);
		textFieldAltezza.setColumns(10);
		
		JLabel lblPeso = new JLabel("Peso");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.gridwidth = 2;
		gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeso.gridx = 6;
		gbc_lblPeso.gridy = 12;
		scrollPanelLeft.add(lblPeso, gbc_lblPeso);
		
		textFieldPeso = new JTextField();
		GridBagConstraints gbc_textFieldPeso = new GridBagConstraints();
		gbc_textFieldPeso.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPeso.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPeso.gridx = 8;
		gbc_textFieldPeso.gridy = 12;
		scrollPanelLeft.add(textFieldPeso, gbc_textFieldPeso);
		textFieldPeso.setColumns(10);
		
		JLabel lblDescrizione = new JLabel("Commento");
		GridBagConstraints gbc_lblDescrizione = new GridBagConstraints();
		gbc_lblDescrizione.anchor = GridBagConstraints.WEST;
		gbc_lblDescrizione.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrizione.gridx = 3;
		gbc_lblDescrizione.gridy = 14;
		scrollPanelLeft.add(lblDescrizione, gbc_lblDescrizione);
		
		JScrollPane scrollPaneCommento = new JScrollPane();
		GridBagConstraints gbc_scrollPaneCommento = new GridBagConstraints();
		gbc_scrollPaneCommento.gridheight = 2;
		gbc_scrollPaneCommento.gridwidth = 6;
		gbc_scrollPaneCommento.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneCommento.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneCommento.gridx = 4;
		gbc_scrollPaneCommento.gridy = 14;
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPaneCommento.setViewportView(textArea);
		scrollPanelLeft.add(scrollPaneCommento, gbc_scrollPaneCommento);

		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.5);
		middlePanel.add(splitPane, BorderLayout.CENTER);
		

		/* UPPER PANEL AND TITLE */
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel = new JLabel("Conclusione Prestazione");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		/* LOWER PANEL */
		JButton sendButton = new JButton("BACK");
		sendButton.setHorizontalAlignment(SwingConstants.LEFT);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new GestionePrestazione(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(sendButton, BorderLayout.WEST);
		
		
		
		btnAddFarmaco.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OptionFarmaco panelFarmaco = new OptionFarmaco();
				int result = JOptionPane.showConfirmDialog(null,
						panelFarmaco,
						"Farmaco",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
						if (result == JOptionPane.OK_OPTION) {
							//resetAndDraw(userController.getAllSpecializzazioni(user.getCF()));
						}
			}
		});
		
		List<Patologia> nuovePatologie = new ArrayList<>();
		
		btnAddPatologia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OptionPatologia panelPatologia = new OptionPatologia();
				int result = JOptionPane.showConfirmDialog(null,
						panelPatologia,
						"Patologia",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
						if (result == JOptionPane.OK_OPTION) {
							patologie.add(panelPatologia.getSelectedPatologia());
							nuovePatologie.add(panelPatologia.getSelectedPatologia());
							
							List<Patologia> list = new ArrayList<>();
							list.addAll(patologie);							
							modelPatologie.setRowCount(0);
							list.forEach(x -> {
								modelPatologie.addRow(new Object[] { 
										x.getNomePatologia(),
										x.getDescrizione(),
										x.getDataInizio()
								});
							});
						}
			}
	    });
		
		
		JPanel savePanel = new JPanel();
		middlePanel.add(savePanel, BorderLayout.SOUTH);
		
		JButton saveButton = new JButton("SAVE");
		
		savePanel.add(saveButton);
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				   // aggiungi nel db la data di guarigione --> prestazioniDaGuarire
				patologieDaGuarire.forEach(p-> {
	
					prestazioneController.addDataFinePatologia(p);
				});
				
				nuovePatologie.forEach(np-> {
					prestazioneController.addPatologiaInPrestazione(np);
				});
				
			}
	    });
		
		textFieldTelefono.setText(String.valueOf(appuntamento.getAnimale().getProprietario().getTelefono()));
		textFieldNomA.setText(appuntamento.getAnimale().getNome());
		textFieldData.setText(appuntamento.getData());
		textFieldOra.setText(appuntamento.getOra());
		textFieldTipo.setText(appuntamento.getTipoPrestazione());
		textFieldNomePrestazione.setText(appuntamento.getPrestazione());
		textFieldProprietario.setText(appuntamento.getAnimale().getProprietario().getNome()+ " " + appuntamento.getAnimale().getProprietario().getCognome());
		
		//setLayout(groupLayout);
	    //new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}
}