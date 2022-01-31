package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import controller.RicoveroController;
import controller.RicoveroControllerImpl;
import model.Appuntamento;
import model.Ricovero;
import model.User;
import utility.ValidityFieldsChecker;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class AggiungiRicovero extends CommonView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private DefaultTableModel model;
	private JTable table;
	private List<Appuntamento> listAppuntamenti;
	private PrestazioneController prestazioneController = new PrestazioneControllerImpl();
	private RicoveroController ricoveroController = new RicoveroControllerImpl();
	
	private JTextField textFieldDurata;
	private JTextField textFieldPrezzo;
	private JTextField textFieldAnimale;
	private JTextField textFieldSpecie;
	private ValidityFieldsChecker checker = new ValidityFieldsChecker() ;
	
	public AggiungiRicovero(JFrame frame, User user, Ricovero ricovero) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.ricoveroController = new RicoveroControllerImpl();
		this.prestazioneController = new PrestazioneControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel savePanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		// GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
								.addComponent(savePanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
								.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(savePanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 0, 0 };
		gbl_centerPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		centerPanel.setLayout(gbl_centerPanel);

		JLabel lblNewLabel = new JLabel("Seleziona l'animale da ricoverare e completa la form");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		centerPanel.add(lblNewLabel, gbc_lblNewLabel);

		JPanel panelToSplit = new JPanel();
		panelToSplit.setLayout(new BorderLayout());
		GridBagConstraints gbc_panelToSplit = new GridBagConstraints();
		gbc_panelToSplit.fill = GridBagConstraints.BOTH;
		gbc_panelToSplit.gridx = 0;
		gbc_panelToSplit.gridy = 1;
		centerPanel.add(panelToSplit, gbc_panelToSplit);

		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
		GridBagLayout gbl_panelRight = new GridBagLayout();
		gbl_panelRight.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelRight.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelRight.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelRight.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelRight.setLayout(gbl_panelRight);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 6;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panelRight.add(panel, gbc_panel);

		JLabel lblPosto = new JLabel("Posto");
		lblPosto.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblPosto);

		JLabel fieldPosto = new JLabel("3");
		fieldPosto.setText(String.valueOf(ricovero.getPosto().getNumero()));
		fieldPosto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(fieldPosto);

		JLabel lblTaglia = new JLabel("- Taglia");
		lblTaglia.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblTaglia);

		JLabel fieldTaglia = new JLabel("media");
		fieldTaglia.setText(String.valueOf(ricovero.getPosto().getDescrizione()));
		fieldTaglia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(fieldTaglia);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 6;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		panelRight.add(panel_1, gbc_panel_1);

		JLabel lblNewLabel_3 = new JLabel("Ricoverato per:");
		panel_1.add(lblNewLabel_3);

		JLabel fieldPrestazioneNome = new JLabel("Tumore");
		panel_1.add(fieldPrestazioneNome);

		JLabel lblNewLabel_1 = new JLabel("Animale");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 3;
		panelRight.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textFieldAnimale = new JTextField();
		textFieldAnimale.setEditable(false);
		GridBagConstraints gbc_textFieldAnimale = new GridBagConstraints();
		gbc_textFieldAnimale.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAnimale.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAnimale.gridx = 3;
		gbc_textFieldAnimale.gridy = 3;
		panelRight.add(textFieldAnimale, gbc_textFieldAnimale);
		textFieldAnimale.setColumns(10);

		JLabel lblSpecie = new JLabel("Specie");
		lblSpecie.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblSpecie = new GridBagConstraints();
		gbc_lblSpecie.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpecie.gridx = 2;
		gbc_lblSpecie.gridy = 4;
		panelRight.add(lblSpecie, gbc_lblSpecie);

		textFieldSpecie = new JTextField();
		textFieldSpecie.setEditable(false);
		GridBagConstraints gbc_textFieldSpecie = new GridBagConstraints();
		gbc_textFieldSpecie.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSpecie.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSpecie.gridx = 3;
		gbc_textFieldSpecie.gridy = 4;
		panelRight.add(textFieldSpecie, gbc_textFieldSpecie);
		textFieldSpecie.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Durata in giorni");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 6;
		panelRight.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textFieldDurata = new JTextField();
		GridBagConstraints gbc_textFieldDurata = new GridBagConstraints();
		gbc_textFieldDurata.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDurata.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDurata.gridx = 3;
		gbc_textFieldDurata.gridy = 6;
		panelRight.add(textFieldDurata, gbc_textFieldDurata);
		textFieldDurata.setColumns(10);

		JLabel lblPrezzo = new JLabel("Prezzo giornaliero");
		GridBagConstraints gbc_lblPrezzo = new GridBagConstraints();
		gbc_lblPrezzo.anchor = GridBagConstraints.WEST;
		gbc_lblPrezzo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrezzo.gridx = 2;
		gbc_lblPrezzo.gridy = 7;
		panelRight.add(lblPrezzo, gbc_lblPrezzo);

		textFieldPrezzo = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 7;
		panelRight.add(textFieldPrezzo, gbc_textField_1);
		textFieldPrezzo.setColumns(10);
		panelToSplit.add(splitPane);

		JScrollPane scrollPaneLeft = new JScrollPane();
		panelLeft.add(scrollPaneLeft, BorderLayout.CENTER);

		this.initTable();
		this.listAppuntamenti = this.prestazioneController.getAllPrestazioniPerGestioneRicovero(user.getCF());
		this.createTable(model, listAppuntamenti);

		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int rowIndex = table.getSelectedRow();
				fieldPrestazioneNome.setText(listAppuntamenti.get(rowIndex).getPrestazione());
				textFieldAnimale.setText(listAppuntamenti.get(rowIndex).getAnimale().getNome());
				textFieldSpecie.setText(listAppuntamenti.get(rowIndex).getAnimale().getSpecie());
			}
		});
		scrollPaneLeft.setViewportView(table);

		JButton btnSave = new JButton("Salva");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checker.checkNotEmpty(textFieldDurata.getText()) && checker.checkNotEmpty(textFieldPrezzo.getText())
						&& checker.checkNumber(textFieldDurata.getText())
						&& checker.checkFloatFieldsValue(textFieldPrezzo.getText())) {
					ricovero.setDurataInGiorni(Integer.valueOf(textFieldDurata.getText()));
					ricovero.setPrezzoGiornaliero(Float.valueOf(textFieldPrezzo.getText()));
					
					if(!ricoveroController.isRicoveratoOggi(listAppuntamenti.get(table.getSelectedRow()).getAnimale().getCodAnimale())) {
						ricoveroController.addRicovero(ricovero, listAppuntamenti.get(table.getSelectedRow()));
						frame.getContentPane().add(new GestioneRicoveri(frame, user));
					} else {
						JOptionPane.showMessageDialog(frame, "L'animale è già stato ricoverato e rilasciato",
						"Attenzione!", JOptionPane.ERROR_MESSAGE);
					}		
				}
			}
		});
		savePanel.add(btnSave);

		/* LOWER PANEL */
		Icon icon = IconImage.BACK.getImageIcon();
		JButton backButton = new JButton(icon);
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new GestioneRicoveri(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);
		upperPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel =new JLabel("<html><font color='#56728f '>Nuovo Ricovero</font></html>");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		upperPanel.add(titleLabel, BorderLayout.CENTER);
		upperPanel.add(titleLabel);

		// setLayout(groupLayout);
		// new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);
	}

	private void createTable(DefaultTableModel model, List<Appuntamento> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { 
					x.getAnimale().getNome(),
					x.getAnimale().getSpecie(),
					x.getAnimale().getAltezza(),
					x.getPrestazione()
			});
		});
	}

	private void initTable() {
		this.table = new JTable();
		this.table.getTableHeader().setForeground(Color.decode("#56728f"));
		this.table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Nome");
		model.addColumn("Specie");
		model.addColumn("Altezza");
		model.addColumn("Prestazione");
	}
}
