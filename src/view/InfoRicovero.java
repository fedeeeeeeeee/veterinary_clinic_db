package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.AnimaleController;
import controller.FarmaciController;
import controller.FarmaciControllerImpl;
import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import controller.RicoveroController;
import controller.RicoveroControllerImpl;
import controller.UserController;
import model.Appuntamento;
import model.AppuntamentoImpl;
import model.Farmaco;
import model.FarmacoInPrestazione;
import model.Patologia;
import model.Ricovero;
import model.User;

public class InfoRicovero extends CommonView {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private DefaultTableModel model;
	private JTable table;
	private List<Appuntamento> listAppuntamenti;
	private PrestazioneController prestazioneController;
	private RicoveroController ricoveroController ;
	private AnimaleController animaleController;
	private UserController userController;
	private FarmaciController farmaciController;
	private JTextField textField;
	
	public InfoRicovero(JFrame frame, User user, Ricovero ricovero) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.prestazioneController = new PrestazioneControllerImpl(); // per aggiungere appuntamneto rilascio ricovero 
		this.ricoveroController = new RicoveroControllerImpl();
		this.farmaciController = new FarmaciControllerImpl();
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();

GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
					.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
					.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(20)
					.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addGap(84)
					.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(13))
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
		
	//	JButton btnAddFarmaco = new JButton("+");
		//scrollPanelRight.add(btnAddFarmaco, "4, 2");
		
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
		tableFarmaci.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		DefaultTableModel modelFarmaci = (DefaultTableModel) tableFarmaci.getModel();
		List<FarmacoInPrestazione> farmaci =  this.farmaciController.getFarmaciDiAnimale(ricovero.getAnimale().getCodAnimale());		
		tableFarmaci.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				   int rowIndex = tableFarmaci.getSelectedRow();
				   int reply = JOptionPane.showConfirmDialog(null, "Vuole somministare il seguent farmaco: " + farmaci.get(rowIndex).getFarmaco().getNomeFarmaco() + " ?", "Guarigione", JOptionPane.YES_NO_OPTION);
				   if (reply == JOptionPane.YES_OPTION) {
					   String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					   farmaciController.updateQuantita( farmaci.get(rowIndex).getFarmaco(), -1);
					   } 
			}
		});
		modelFarmaci.addColumn("Nome");
		modelFarmaci.addColumn("Indicazioni");
		
		farmaci.forEach(x -> {
			modelFarmaci.addRow(new Object[] { 
					x.getFarmaco().getNomeFarmaco(),
					x.getIndicazioni()
			});
		});
		scrollPaneFarmaco.setViewportView(tableFarmaci);

		
		
		JTable tablePatologie = new JTable();
		tablePatologie.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		DefaultTableModel modelPatologie = (DefaultTableModel) tablePatologie.getModel();
		Appuntamento app = new AppuntamentoImpl();
		app.setAnimale(ricovero.getAnimale());
		List<Patologia> patologie = prestazioneController.getPatologieAttiveDiAnimale(app); //controlla TODO 
		
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

		
		/* FINE search bar */
		GridBagLayout gbl_scrollPanelLeft = new GridBagLayout();
		gbl_scrollPanelLeft.columnWidths = new int[] { 0, 0, 0, -23, -23, 0, 0, 0, 0, 0, 0, 0 };
		gbl_scrollPanelLeft.rowHeights = new int[] { 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0 };
		gbl_scrollPanelLeft.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_scrollPanelLeft.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		scrollPanelLeft.setLayout(gbl_scrollPanelLeft);
		
		JLabel lblNewLabel = new JLabel("Dati Paziente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 1;
		scrollPanelLeft.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblAnimale = new JLabel("Nome Animale");
		GridBagConstraints gbc_lblAnimale = new GridBagConstraints();
		gbc_lblAnimale.anchor = GridBagConstraints.WEST;
		gbc_lblAnimale.gridwidth = 4;
		gbc_lblAnimale.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnimale.gridx = 1;
		gbc_lblAnimale.gridy = 3;
		scrollPanelLeft.add(lblAnimale, gbc_lblAnimale);
	
		JTextField textFieldNomA = new JTextField();
		textFieldNomA.setEditable(false);
		GridBagConstraints gbc_textFieldNomA = new GridBagConstraints();
		gbc_textFieldNomA.gridwidth = 5;
		gbc_textFieldNomA.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNomA.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNomA.gridx = 5;
		gbc_textFieldNomA.gridy = 3;
		scrollPanelLeft.add(textFieldNomA, gbc_textFieldNomA);
		textFieldNomA.setColumns(10);

		JTextField textFieldNomeProprietario = new JTextField();
		textFieldNomeProprietario.setEditable(false);
		
		JLabel lblAltezza = new JLabel("Altezza");
		GridBagConstraints gbc_lblAltezza = new GridBagConstraints();
		gbc_lblAltezza.gridwidth = 3;
		gbc_lblAltezza.anchor = GridBagConstraints.WEST;
		gbc_lblAltezza.insets = new Insets(0, 0, 5, 5);
		gbc_lblAltezza.gridx = 1;
		gbc_lblAltezza.gridy = 4;
		scrollPanelLeft.add(lblAltezza, gbc_lblAltezza);
		
		JTextField textFieldAltezza = new JTextField();
		textFieldAltezza.setText(String.valueOf(ricovero.getAnimale().getAltezza())+" cm");
		textFieldAltezza.setEditable(false);
		GridBagConstraints gbc_textFieldAltezza = new GridBagConstraints();
		gbc_textFieldAltezza.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAltezza.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAltezza.gridx = 4;
		gbc_textFieldAltezza.gridy = 4;
		scrollPanelLeft.add(textFieldAltezza, gbc_textFieldAltezza);
		textFieldAltezza.setColumns(10);
		
		JLabel lblPeso = new JLabel("Peso");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.gridwidth = 2;
		gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeso.gridx = 6;
		gbc_lblPeso.gridy = 4;
		scrollPanelLeft.add(lblPeso, gbc_lblPeso);
		
		JTextField textFieldPeso = new JTextField();
		textFieldPeso.setText(String.valueOf(ricovero.getAnimale().getPeso())+ " kg");
		textFieldPeso.setEditable(false);
		GridBagConstraints gbc_textFieldPeso = new GridBagConstraints();
		gbc_textFieldPeso.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPeso.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPeso.gridx = 8;
		gbc_textFieldPeso.gridy = 4;
		scrollPanelLeft.add(textFieldPeso, gbc_textFieldPeso);
		textFieldPeso.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Specie");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		scrollPanelLeft.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.setText(ricovero.getAnimale().getSpecie());
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 5;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 5;
		scrollPanelLeft.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Dati Ricovero");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 7;
		scrollPanelLeft.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 9;
		scrollPanelLeft.add(separator, gbc_separator);
		
				JLabel lblData = new JLabel("Data Inizio");
				GridBagConstraints gbc_lblData = new GridBagConstraints();
				gbc_lblData.gridwidth = 3;
				gbc_lblData.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblData.insets = new Insets(0, 0, 5, 5);
				gbc_lblData.gridx = 1;
				gbc_lblData.gridy = 10;
				scrollPanelLeft.add(lblData, gbc_lblData);
		
				JTextField textFieldData = new JTextField();
		textFieldData.setEditable(false);
		GridBagConstraints gbc_textFieldData = new GridBagConstraints();
		gbc_textFieldData.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldData.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldData.gridx = 4;
		gbc_textFieldData.gridy = 10;
		scrollPanelLeft.add(textFieldData, gbc_textFieldData);
		textFieldData.setColumns(10);

		JLabel lblOra = new JLabel("Ora Inizio");
		GridBagConstraints gbc_lblOra = new GridBagConstraints();
		gbc_lblOra.anchor = GridBagConstraints.EAST;
		gbc_lblOra.insets = new Insets(0, 0, 5, 5);
		gbc_lblOra.gridx = 7;
		gbc_lblOra.gridy = 10;
		scrollPanelLeft.add(lblOra, gbc_lblOra);
		LocalTime local = LocalTime.now();
		
		JTextField textFieldOra = new JTextField();
		textFieldOra.setEditable(false);
		GridBagConstraints gbc_textFieldOra = new GridBagConstraints();
		gbc_textFieldOra.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldOra.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldOra.gridx = 8;
		gbc_textFieldOra.gridy = 10;
		scrollPanelLeft.add(textFieldOra, gbc_textFieldOra);
		textFieldOra.setColumns(10);
        
		JLabel lblTipoPrestazione = new JLabel("Motivo Ricovero");
		GridBagConstraints gbc_lblTipoPrestazione = new GridBagConstraints();
		gbc_lblTipoPrestazione.anchor = GridBagConstraints.WEST;
		gbc_lblTipoPrestazione.gridwidth = 4;
		gbc_lblTipoPrestazione.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoPrestazione.gridx = 1;
		gbc_lblTipoPrestazione.gridy = 11;
		scrollPanelLeft.add(lblTipoPrestazione, gbc_lblTipoPrestazione);
		
		JTextField textFieldTipo = new JTextField();
		textFieldTipo.setEditable(false);
		GridBagConstraints gbc_textFieldTipo = new GridBagConstraints();
		gbc_textFieldTipo.gridwidth = 5;
		gbc_textFieldTipo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTipo.gridx = 5;
		gbc_textFieldTipo.gridy = 11;
		scrollPanelLeft.add(textFieldTipo, gbc_textFieldTipo);
		textFieldTipo.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 10;
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 13;
		scrollPanelLeft.add(separator_1, gbc_separator_1);

		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.5);
		middlePanel.add(splitPane, BorderLayout.CENTER);
		

		/* UPPER PANEL AND TITLE */
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel = new JLabel("Info Ricovero");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		/* LOWER PANEL */
		Icon icon = IconImage.BACK.getImageIcon();
		JButton sendButton = new JButton(icon);
		sendButton.setBorder(BorderFactory.createEmptyBorder());
		sendButton.setContentAreaFilled(false);
		sendButton.setHorizontalAlignment(SwingConstants.LEFT);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new GestioneRicoveri(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(sendButton, BorderLayout.WEST);
		
		
		List<Farmaco> nuoviFarmaci = new ArrayList<>();
		OptionFarmaco optionPanelFarmaco = new OptionFarmaco(ricovero.getAnimale());
		// TODO decremento farmaco
		
		List<Patologia> nuovePatologie = new ArrayList<>();
		
		
		JPanel savePanel = new JPanel();
		middlePanel.add(savePanel, BorderLayout.SOUTH);
	
		textFieldNomA.setText(ricovero.getAnimale().getNome());
		textFieldData.setText(ricovero.getDataInizio());
		textFieldOra.setText(ricovero.getOraInizio());
		textFieldTipo.setText(ricovero.getMotivoRicovero());

		//setLayout(groupLayout);
	    //new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}
}