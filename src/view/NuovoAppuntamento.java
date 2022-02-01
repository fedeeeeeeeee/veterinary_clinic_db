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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import controller.AnimaleController;
import controller.AnimaleControllerImpl;
import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import controller.StrumentiController;
import controller.StrumentiControllerImpl;
import controller.UserController;
import controller.UserControllerImpl;
import model.Animale;
import model.Appuntamento;
import model.AppuntamentoImpl;
import model.Prestazione;
import model.User;
import utility.Pair;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;

public class NuovoAppuntamento extends CommonView {

	private static final long serialVersionUID = 693737436773825328L;
	private JFrame frame;
	private User user;
	private PrestazioneController prestazioneController;
	private JTextField textFieldNomeProprietario;
	private JTable table;
	private DefaultTableModel model;
	private List<Appuntamento> listAppuntamenti;
	private List<Animale> listAnimali;
	private AnimaleController animaleController;
	private List<User> listVeterinari;
	private List<User> listInfermieri;
	private UserController userController;
	private String selectedInfermiere;
	private List<Prestazione> listTipiPrestazione;
	private JTextField textFieldTelefono;
	private int codTipoPrestazione;
	private JComboBox<String> comboBoxVeterinario;
	private List<String> vetNonDisponibili;
	private StrumentiController strumentiController; 
	private boolean isFirst = true;
	private boolean erroreStrumento = false;
	
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
		this.table = new JTable();
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		this.model = (DefaultTableModel) table.getModel();
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
	
	private boolean checkDisponibilitaVeterinario(String date, String time) {
		String durata = listTipiPrestazione.stream()
				.filter(p-> p.getCodPrestazione() == this.codTipoPrestazione)
				.map(p-> p.getDurataMedia())
				.collect(Collectors.toList()).get(0);		
		vetNonDisponibili = prestazioneController.getVeterinariNonDisponibili(date, time, durata);
		return !vetNonDisponibili.contains(this.comboBoxVeterinario.getSelectedItem().toString());
	}
	
	public NuovoAppuntamento(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.animaleController = new AnimaleControllerImpl();
		this.prestazioneController = new PrestazioneControllerImpl();
		this.userController = new UserControllerImpl();
		this.strumentiController = new StrumentiControllerImpl();
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	//	GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
					.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
					.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
		scrollPanelRight.setLayout(new BorderLayout(0, 0));

		JPanel menuPanel = new JPanel();
		scrollPanelRight.add(menuPanel, BorderLayout.NORTH);
		GridBagLayout gbl_menuPanel = new GridBagLayout();
		gbl_menuPanel.columnWidths = new int[]{0, 44, 93, 78, 54, 0, 0, 0, 137, 0};
		gbl_menuPanel.rowHeights = new int[]{26, 0};
		gbl_menuPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_menuPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		menuPanel.setLayout(gbl_menuPanel);
		
				JDateChooser dateChooserRicerca = new JDateChooser();
				dateChooserRicerca.setDateFormatString("yyyy-MM-dd");
				dateChooserRicerca.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (dateChooserRicerca.getDate() != null) {
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							String date = df.format(dateChooserRicerca.getDate());
							resetAndDraw(prestazioneController.getPrestazioniInData(date));
						}
					}
				});
				
				GridBagConstraints gbc_dateChooserRicerca = new GridBagConstraints();
				gbc_dateChooserRicerca.gridwidth = 5;
				gbc_dateChooserRicerca.insets = new Insets(0, 0, 0, 5);
				gbc_dateChooserRicerca.fill = GridBagConstraints.BOTH;
				gbc_dateChooserRicerca.gridx = 3;
				gbc_dateChooserRicerca.gridy = 0;
				menuPanel.add(dateChooserRicerca, gbc_dateChooserRicerca);
		
		JPanel panelAppuntamenti = new JPanel();
		scrollPanelRight.add(panelAppuntamenti, BorderLayout.CENTER);

		/* TABLE */
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_centerPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panelAppuntamenti.setLayout(gbl_centerPanel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		panelAppuntamenti.add(scrollPane, gbc_scrollPane);

		this.initTable();
		this.listAppuntamenti = this.prestazioneController.getFuturePrestazioni();
		this.createTable(model, listAppuntamenti);
		scrollPane.setViewportView(table);

		/* FINE search bar */

		GridBagLayout gbl_scrollPanelLeft = new GridBagLayout();
		gbl_scrollPanelLeft.columnWidths = new int[] { 0, 0, -23, 0, 0, 0, 0, 0, 0, 0 };
		gbl_scrollPanelLeft.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_scrollPanelLeft.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_scrollPanelLeft.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE };
		scrollPanelLeft.setLayout(gbl_scrollPanelLeft);

		JLabel lblAnimale = new JLabel("Nome Animale");
		lblAnimale.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GridBagConstraints gbc_lblAnimale = new GridBagConstraints();
		gbc_lblAnimale.anchor = GridBagConstraints.WEST;
		gbc_lblAnimale.gridwidth = 3;
		gbc_lblAnimale.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnimale.gridx = 0;
		gbc_lblAnimale.gridy = 1;
		scrollPanelLeft.add(lblAnimale, gbc_lblAnimale);

		JComboBox<String> comboBoxAnimale = new JComboBox<>();
		GridBagConstraints gbc_comboBoxAnimale = new GridBagConstraints();
		gbc_comboBoxAnimale.gridwidth = 3;
		gbc_comboBoxAnimale.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAnimale.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAnimale.gridx = 4;
		gbc_comboBoxAnimale.gridy = 1;
		scrollPanelLeft.add(comboBoxAnimale, gbc_comboBoxAnimale);	
		this.listAnimali = new ArrayList<>();
		this.listAnimali = this.animaleController.getAllAnimali();
	
		for (Animale animale : listAnimali) {
			comboBoxAnimale.addItem(animale.getNome());
		}
		comboBoxAnimale.setSelectedIndex(-1);
		comboBoxAnimale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animale animaleSelected = listAnimali.get(comboBoxAnimale.getSelectedIndex());
				textFieldNomeProprietario.setText(animaleSelected.getProprietario().getNome()+ " " + animaleSelected.getProprietario().getCognome());
				textFieldTelefono.setText(String.valueOf(animaleSelected.getProprietario().getTelefono()));
			}
		});

		
		JLabel lblNewLabel_1 = new JLabel("Proprietario");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		scrollPanelLeft.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textFieldNomeProprietario = new JTextField();
		textFieldNomeProprietario.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 2;
		scrollPanelLeft.add(textFieldNomeProprietario, gbc_textField);
		textFieldNomeProprietario.setColumns(10);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.anchor = GridBagConstraints.WEST;
		gbc_lblTelefono.gridwidth = 3;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 0;
		gbc_lblTelefono.gridy = 3;
		scrollPanelLeft.add(lblTelefono, gbc_lblTelefono);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 3;
		scrollPanelLeft.add(textFieldTelefono, gbc_textField_1);
		textFieldTelefono.setColumns(10);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 3;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 4;
		scrollPanelLeft.add(separator, gbc_separator);

		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GridBagConstraints gbc_lblData = new GridBagConstraints();
		gbc_lblData.gridwidth = 2;
		gbc_lblData.anchor = GridBagConstraints.NORTH;
		gbc_lblData.insets = new Insets(0, 0, 5, 5);
		gbc_lblData.gridx = 0;
		gbc_lblData.gridy = 5;
		scrollPanelLeft.add(lblData, gbc_lblData);

		JDateChooser dateChooser = new JDateChooser();
		
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.anchor = GridBagConstraints.NORTH;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 5;
		scrollPanelLeft.add(dateChooser, gbc_dateChooser);

		JLabel lblOra = new JLabel("Ora");
		GridBagConstraints gbc_lblOra = new GridBagConstraints();
		gbc_lblOra.insets = new Insets(0, 0, 5, 5);
		gbc_lblOra.gridx = 5;
		gbc_lblOra.gridy = 5;
		scrollPanelLeft.add(lblOra, gbc_lblOra);

		TimePicker timePicker = new TimePicker();
		//timePicker.setText("Meeting time");
		LocalTime local = LocalTime.now();
		timePicker.setTime(local);
		GridBagConstraints gbc_timePicker = new GridBagConstraints();
		gbc_timePicker.anchor = GridBagConstraints.NORTHWEST;
		gbc_timePicker.insets = new Insets(0, 0, 5, 5);
		gbc_timePicker.gridx = 6;
		gbc_timePicker.gridy = 5;
		scrollPanelLeft.add(timePicker, gbc_timePicker);
        
		JLabel lblTipoPrestazione = new JLabel("Tipo Prestazione");
		lblTipoPrestazione.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GridBagConstraints gbc_lblTipoPrestazione = new GridBagConstraints();
		gbc_lblTipoPrestazione.anchor = GridBagConstraints.WEST;
		gbc_lblTipoPrestazione.gridwidth = 3;
		gbc_lblTipoPrestazione.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoPrestazione.gridx = 0;
		gbc_lblTipoPrestazione.gridy = 6;
		scrollPanelLeft.add(lblTipoPrestazione, gbc_lblTipoPrestazione);

		JComboBox<String> comboBoxTipoPrestazione = new JComboBox<>();
		GridBagConstraints gbc_comboBoxPrestazione = new GridBagConstraints();
		gbc_comboBoxPrestazione.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPrestazione.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPrestazione.gridx = 6;
		gbc_comboBoxPrestazione.gridy = 6;
		scrollPanelLeft.add(comboBoxTipoPrestazione, gbc_comboBoxPrestazione);
				
		JLabel lblNomPrestazione = new JLabel("Nome Prestazione");
		lblNomPrestazione.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GridBagConstraints gbc_lblNomPrestazione = new GridBagConstraints();
		gbc_lblNomPrestazione.anchor = GridBagConstraints.WEST;
		gbc_lblNomPrestazione.gridwidth = 3;
		gbc_lblNomPrestazione.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomPrestazione.gridx = 0;
		gbc_lblNomPrestazione.gridy = 7;
		scrollPanelLeft.add(lblNomPrestazione, gbc_lblNomPrestazione);

		JComboBox<String> comboBoxNomePrestazione = new JComboBox<>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 6;
		gbc_comboBox.gridy = 7;
		scrollPanelLeft.add(comboBoxNomePrestazione, gbc_comboBox);
		comboBoxNomePrestazione.setEnabled(false);
		
	
		
		this.listTipiPrestazione = new ArrayList<>();
		this.listTipiPrestazione = this.prestazioneController.getAllTipiDiPrestazione();

		Map<Integer, Pair<String, String>> map = new HashMap<>();
		listTipiPrestazione.forEach(elem-> {
			map.put(elem.getCodPrestazione(), new Pair<>(elem.getTipoPrestazione(), elem.getNomePrestazione()));
		});
		
		listTipiPrestazione.stream()
				.map(e-> e.getTipoPrestazione())
				.distinct()
				.forEach(x->{
					comboBoxTipoPrestazione.addItem(x);
		});
		
		comboBoxNomePrestazione.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!isFirst && comboBoxNomePrestazione.getSelectedIndex() != -1) {
					int codPrestazione = map.entrySet().stream()
							.filter(en -> en.getValue().getY().equals(comboBoxNomePrestazione.getSelectedItem().toString()))
							.map(en -> en.getKey()).findAny().get();
					if (strumentiController.isStrumentiAvailable(codPrestazione)) {
						erroreStrumento = false;
					} else {
						JOptionPane.showMessageDialog(frame, "Gli strumenti necessari per la prestazione selezionata non sono disponibili.",
								"Attenzione!", JOptionPane.ERROR_MESSAGE);
						erroreStrumento = true;
					}
				}
				isFirst = false;
			}
		});

		comboBoxTipoPrestazione.setSelectedIndex(-1);		
		comboBoxTipoPrestazione.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxNomePrestazione.setEnabled(true);
				comboBoxNomePrestazione.removeAllItems();
				for (Map.Entry<Integer, Pair<String, String>> pair : map.entrySet()) {
					if(pair.getValue().getX().equals(comboBoxTipoPrestazione.getSelectedItem().toString())) {
						comboBoxNomePrestazione.addItem(pair.getValue().getY());
					}
				}
				comboBoxNomePrestazione.setSelectedIndex(-1);
			}	
		});
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridwidth = 9;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 8;
		scrollPanelLeft.add(separator_1, gbc_separator_1);

		JLabel lblNewLabel = new JLabel("Personale");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 9;
		scrollPanelLeft.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblVeterinario = new JLabel("Seleziona Veterinario");
		GridBagConstraints gbc_lblVeterinario = new GridBagConstraints();
		gbc_lblVeterinario.gridwidth = 2;
		gbc_lblVeterinario.insets = new Insets(0, 0, 5, 5);
		gbc_lblVeterinario.gridx = 1;
		gbc_lblVeterinario.gridy = 10;
		scrollPanelLeft.add(lblVeterinario, gbc_lblVeterinario);

		JLabel lblInfermiere = new JLabel("Seleziona Infermiere");
		GridBagConstraints gbc_lblInfermiere = new GridBagConstraints();
		gbc_lblInfermiere.insets = new Insets(0, 0, 5, 5);
		gbc_lblInfermiere.gridx = 6;
		gbc_lblInfermiere.gridy = 10;
		scrollPanelLeft.add(lblInfermiere, gbc_lblInfermiere);

		comboBoxVeterinario = new JComboBox<>();
		GridBagConstraints gbc_comboBoxVeterinario = new GridBagConstraints();
		gbc_comboBoxVeterinario.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxVeterinario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVeterinario.gridx = 2;
		gbc_comboBoxVeterinario.gridy = 11;
		scrollPanelLeft.add(comboBoxVeterinario, gbc_comboBoxVeterinario);
		
		this.listVeterinari = new ArrayList<>();
		this.listVeterinari = this.userController.getAllPersonaleByRuolo("Veterinario");
		listVeterinari.forEach(u-> {
			comboBoxVeterinario.addItem(u.getNome() + " " + u.getCognome());
		});	
		comboBoxVeterinario.setSelectedIndex(-1);

		JComboBox<String> comboBoxInfermiere = new JComboBox<>();
		GridBagConstraints gbc_comboBoxInfermiere = new GridBagConstraints();
		gbc_comboBoxInfermiere.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInfermiere.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInfermiere.gridx = 6;
		gbc_comboBoxInfermiere.gridy = 11;
		scrollPanelLeft.add(comboBoxInfermiere, gbc_comboBoxInfermiere);
		
		this.listInfermieri = new ArrayList<>();
		this.listInfermieri = this.userController.getAllPersonaleByRuolo("Infermiere");
		listInfermieri.forEach(u-> {
			comboBoxInfermiere.addItem(u.getNome() + " " + u.getCognome());
		});
		comboBoxInfermiere.setSelectedIndex(-1);
		
		JButton addInfermiereButton = new JButton("+");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 11;
		scrollPanelLeft.add(addInfermiereButton, gbc_btnNewButton);
		
		comboBoxInfermiere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addInfermiereButton.setEnabled(true);
			};
		
		});
		
		JComboBox<String> comboBoxInfermiere2 = new JComboBox<>();
		GridBagConstraints gbc_comboBoxInfermiere_1 = new GridBagConstraints();
		gbc_comboBoxInfermiere_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInfermiere_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInfermiere_1.gridx = 6;
		gbc_comboBoxInfermiere_1.gridy = 12;
		scrollPanelLeft.add(comboBoxInfermiere2, gbc_comboBoxInfermiere_1);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.5);
		middlePanel.add(splitPane, BorderLayout.CENTER);
		comboBoxInfermiere2.setVisible(false);	 
		addInfermiereButton.setEnabled(false);
				
		addInfermiereButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(addInfermiereButton.getText() == "+") {
					addInfermiereButton.setText("-");
					if (comboBoxInfermiere.getSelectedItem() != null) {
						comboBoxInfermiere2.setVisible(true);
						selectedInfermiere = comboBoxInfermiere.getSelectedItem().toString();
						comboBoxInfermiere.setEnabled(false);
						if (comboBoxInfermiere2.getItemCount() == 0) {
							listInfermieri.stream()
									.filter(x -> !(x.getNome() + " " + x.getCognome()).equals(selectedInfermiere))
									.forEach(x -> {
										comboBoxInfermiere2.addItem(x.getNome() + " " + x.getCognome());
									});
						}
					}
				} else {
					addInfermiereButton.setText("+");
					comboBoxInfermiere.setEnabled(true);
					comboBoxInfermiere2.removeAllItems();
					comboBoxInfermiere2.setVisible(false);
				}				
			}
		});
		

		/* UPPER PANEL AND TITLE */
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel =new JLabel("<html><font color='#56728f '>Nuovo Appuntamento</font></html>");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		/* LOWER PANEL */
		  Icon icon = IconImage.BACK.getImageIcon();
			JButton backButton = new JButton(icon);
			backButton.setBorder(BorderFactory.createEmptyBorder());
			backButton.setContentAreaFilled(false);
			backButton.setHorizontalAlignment(SwingConstants.LEFT);
			backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new GestioneAppuntamento(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);
		
		JPanel savePanel = new JPanel();
		middlePanel.add(savePanel, BorderLayout.SOUTH);
		
		JButton saveButton = new JButton("Salva");
		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(comboBoxAnimale.getSelectedIndex() != -1
						&& comboBoxTipoPrestazione.getSelectedIndex() != -1
						&& comboBoxNomePrestazione.getSelectedIndex() != -1
						&& comboBoxVeterinario.getSelectedIndex() != -1
						&& comboBoxInfermiere.getSelectedIndex() != -1
						&& dateChooser.getDate() != null
						&& timePicker.getTime() != null) {
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String date = df.format(dateChooser.getDate());
					Pair<String, String> p = new Pair<>(comboBoxTipoPrestazione.getSelectedItem().toString(), comboBoxNomePrestazione.getSelectedItem().toString());	
					codTipoPrestazione = map.entrySet().stream().filter(en-> en.getValue().equals(p)).findFirst().get().getKey();
					
					Appuntamento app = new AppuntamentoImpl();

					if(checkDisponibilitaVeterinario(date, timePicker.getTime().toString())) {
						app.setAnimale(listAnimali.get(comboBoxAnimale.getSelectedIndex()));						
						app.setCodPrestazione(codTipoPrestazione);
						app.setData(date);
						app.setOra(timePicker.getTime().toString());					
						
						List<User> list = new ArrayList<>();
						list.add(listInfermieri.get(comboBoxInfermiere.getSelectedIndex()));
						if(comboBoxInfermiere2.getSelectedIndex() != -1) {
							list.add(listInfermieri.get(comboBoxInfermiere2.getSelectedIndex()));
						}
						list.add(listVeterinari.get(comboBoxVeterinario.getSelectedIndex()));
						app.setAllMediciEInfermieri(list);
						
						if(erroreStrumento) {
							JOptionPane.showMessageDialog(frame, "Gli strumenti necessari per la prestazione selezionata non sono disponibili, non è possibile inserire l'appuntamento.",
									"Attenzione!", JOptionPane.ERROR_MESSAGE);
						} else {
							if(prestazioneController.addNewAppuntamento(app) && prestazioneController.addEffettua(app)) {
								strumentiController.updateStrumentiDiPrestazione(app.getCodPrestazione());	
								frame.getContentPane().add(new GestioneAppuntamento(frame, user));
							} else {
								JOptionPane.showMessageDialog(frame, "Si è verificato un errore nell'inserimento dell'appuntamaneto.", 
										"Attenzione!", JOptionPane.ERROR_MESSAGE);
							}
						}
								
						
					} else {
						List<String> list = listVeterinari.stream()
								.filter(vet -> !vetNonDisponibili.contains(vet.getNome() + " " + vet.getCognome()))
								.map(v -> v.getNome() + " " + v.getCognome())
								.collect(Collectors.toList());

						String[] options = new String[list.size()];
						list.toArray(options);
						
						if(list.isEmpty()) {
							JOptionPane.showMessageDialog(frame, "Nessun veterinario disponibile per quella data e ora. Controlla nella riquadro a destra le disponibilità", 
									"Attenzione!", JOptionPane.WARNING_MESSAGE);
						} else {
							String result = (String) JOptionPane.showInputDialog(null,
									"Il Veterinario selezionato non è disponibile. Selezionane uno dei seguenti dalla lista",
									"Disponibilità Veterinari", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
							comboBoxVeterinario.setSelectedItem(result);
						}
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Si è verificato un errore nella compilazione del form. Ricontrollare i campi!", 
							"Attenzione!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		savePanel.add(saveButton);
		
		// setLayout(groupLayout);
		// new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}
	
}