package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import controller.ProprietarioController;
import controller.ProprietarioControllerImpl;
import model.Appuntamento;
import model.Person;
import model.Proprietario;
import model.User;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JCalendar;

public class GestioneAppuntamento extends CommonView {

	private static final long serialVersionUID = -83378890947911750L;
	private JFrame frame;
	private User user;
	private JTextField searchBar;
	private JTable table;	
	private JScrollPane scrollPane;
	private PrestazioneController prestazioneController;
	private ProprietarioController proprietarioController;
	private List<Appuntamento> listAppuntamenti;
	private DefaultTableModel model;
	private boolean first; 
	private String saved;
	private int selectedAppuntamento;
	
	public GestioneAppuntamento(final JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.proprietarioController = new ProprietarioControllerImpl();
		this.prestazioneController = new PrestazioneControllerImpl();
		this.selectedAppuntamento = -1;
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();
				
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE).addGap(44)
						.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE).addGap(84)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE).addGap(12)));
		

		//------MENU BAR------
				middlePanel.setLayout(new BorderLayout(0, 0));
				JPanel upperMiddlePanel = new JPanel();
				upperMiddlePanel.setBorder(new EmptyBorder(0, 10, 25, 10));
				middlePanel.add(upperMiddlePanel, BorderLayout.NORTH);
				
				upperMiddlePanel.setLayout(new FormLayout(new ColumnSpec[] {
						ColumnSpec.decode("900px:grow"),},
					new RowSpec[] {
						RowSpec.decode("25px"),}));
				
				JMenuBar menuBar = new JMenuBar();
				upperMiddlePanel.add(menuBar, "1, 1, fill, top");
				JMenu menuMostra = new JMenu("Mostra");
				JMenu menuAggiungi = new JMenu("Azioni");

				searchBar = new JTextField();
				searchBar.setFont(new Font("Tahoma", Font.ITALIC, 13));
				searchBar.setText("Ricerca Animale");
				searchBar.addFocusListener((FocusListener) new FocusListener() {
					
					@Override
					public void focusGained(FocusEvent e) {
						if (searchBar.getText().equals("Ricerca Animale")) {
							searchBar.setText("");
						}
					}

					@Override
					public void focusLost(FocusEvent e) {
						if (searchBar.getText().equals("")) {
							searchBar.setText("Ricerca Animale");
						}
					}
				});
				searchBar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						searchBar.setText("");
					}
				});
				
				Icon iconSearch = IconImage.SEARCH.getImageIcon();
				JButton buttonVai = new JButton(iconSearch);
				buttonVai.setBorderPainted(false);
				buttonVai.setContentAreaFilled(false);
				buttonVai.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						search(e);
					}
				});
				menuBar.add(menuMostra);
				menuBar.add(menuAggiungi);
				//menuBar.add(dateChooser);
				menuBar.add(searchBar);
				menuBar.add(buttonVai);
				
				JButton orderAsc = new JButton(IconImage.ARROW.getImageIcon());
				orderAsc.setBackground(Color.LIGHT_GRAY);
				orderAsc.setForeground(Color.BLACK);
				//menuBar.add(orderAsc);
				
				JPanel centerPanel = new JPanel();
				centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
				middlePanel.add(centerPanel, BorderLayout.CENTER);
				
				upperPanel.setLayout(new BorderLayout(0, 0));
				JLabel titleLabel = new JLabel("Appuntamenti");
				titleLabel.setBounds(409, 58, 194, 52);
				titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
				titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
				upperPanel.add(titleLabel, BorderLayout.CENTER);
		
		
		
		
		/*------TABLE */
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		centerPanel.add(scrollPane, gbc_scrollPane);
		
		this.initTable();
		this.listAppuntamenti = this.prestazioneController.getAllPrestazioni();
		this.createTable(model, listAppuntamenti);
		scrollPane.setViewportView(table);
	

		/*------fine-------- table */
		// -----inizio ----- MENUU--- listener
				JMenuItem itemOggi = new JMenuItem("Oggi"); // , new ImageIcon("cut-java-image.gif"));
				JMenuItem itemSettimana = new JMenuItem("Settimana Corrente");
				JMenuItem itemMese = new JMenuItem("Mese Corrente");
				JMenuItem itemTutti = new JMenuItem("Tutti");
				JMenuItem itemNuovoAppuntamento = new JMenuItem("Aggiungi Nuovo Appuntamento");
				JMenuItem itemDettagliAppuntamento = new JMenuItem("Gestisci Appuntamento");
				JMenuItem itemGiorno = new JMenuItem("Seleziona giorno"); // , new ImageIcon("cut-java-image.gif"));

				DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE; //("yyyy-MM-dd")
				String dataformat = formatter.format(LocalDateTime.now());
		// itemModQuantita.setIcon(null);
				itemOggi.addActionListener((e) -> {
					
					this.resetAndDraw(prestazioneController.getPrestazioniInData(dataformat));
				});
				itemSettimana.addActionListener((e) -> {

					this.resetAndDraw(prestazioneController.getPrestazioneSettimana(dataformat));
				});
				itemMese.addActionListener((e) -> {
					this.resetAndDraw(prestazioneController.getPrestazioneMese(dataformat));
				});
				itemTutti.addActionListener((e) -> {
					this.resetAndDraw(prestazioneController.getAllPrestazioni() );
				});

				itemGiorno.addActionListener((e) -> {
					OptionSelezionaGiorno panel = new OptionSelezionaGiorno();
					int result = JOptionPane.showConfirmDialog(null, panel, "Seleziona Giorno",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						resetAndDraw(prestazioneController.getPrestazioniInData(panel.getGiorno()));
					}
				});
				itemDettagliAppuntamento.addActionListener((e) -> {
					if (this.selectedAppuntamento != -1) {
						Appuntamento appuntamento = listAppuntamenti.get(selectedAppuntamento);
						Person proprietario = new Proprietario();
						proprietario = proprietarioController.getProprietario(appuntamento.getAnimale().getCf());
						
						appuntamento.getAnimale().setProprietario(proprietario);
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						
						try {
							if(sdf.parse(appuntamento.getData()).before(sdf.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())))) {
								JOptionPane.showMessageDialog(frame, "L'appuntamento selezionato è già concluso",
										"Attenzione!", JOptionPane.ERROR_MESSAGE);
							} else {
								frame.getContentPane().add(new ModifyAppuntamento(frame, user,appuntamento));
							}
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Non hai selezionato alcun appuntamento ");
					}
				});
				itemNuovoAppuntamento.addActionListener((e) -> {
						frame.getContentPane().add(new NuovoAppuntamento(frame, user ));
				});
				menuMostra.add(itemTutti);
				menuMostra.add(itemOggi);
				menuMostra.add(itemSettimana);
				menuMostra.add(itemMese);
				menuMostra.add(itemGiorno);
				menuAggiungi.add(itemNuovoAppuntamento);
				menuAggiungi.add(itemDettagliAppuntamento);
			
				
				
		//----fine ---- MENUU


				Icon icon = IconImage.BACK.getImageIcon();
				JButton backButton = new JButton(icon);
				backButton.setBorder(BorderFactory.createEmptyBorder());
				backButton.setContentAreaFilled(false);
				backButton.setHorizontalAlignment(SwingConstants.LEFT);
				backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().add(new HomeSegretaria(frame, user));
					}
			});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);
		
	//setLayout(groupLayout);
	new GroupLayout(this);
	frame.getContentPane().setLayout(groupLayout);
	this.initCommonView(frame, user);

	}

private void resetAndDraw(List<Appuntamento> allAppuntamento) {
	this.listAppuntamenti.clear();
	this.listAppuntamenti = allAppuntamento;
		//table.clearSelection();
	model.setRowCount(0);
	this.createTable(model, listAppuntamenti);
	//scrollPane.setViewportView(table);
}

private void createTable(DefaultTableModel model, List<Appuntamento> list) {
			list.forEach(x -> {
				model.addRow(new Object[] { 
						x.getAnimale().getNome(),
					x.getData(), 
					x.getOra(), 
					x.getPrestazione(),
					x.getVeterinario().getNome(),
					x.getVeterinario().getCognome(),
				});
			});
}

private void initTable() {
	this.table = new JTable();
	table.setFont(new Font("Tahoma", Font.PLAIN, 14));
	table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
	this.model = (DefaultTableModel) table.getModel();
	model.addColumn("Animale");
	model.addColumn("Data");
	model.addColumn("Ora");
	model.addColumn("Prestazione");
	model.addColumn("Nome");
	model.addColumn("Cognome");
	//model.addColumn("Modifica");		
	table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent event) {
			selectedAppuntamento 	= table.getSelectedRow();
		}
	});
}

public void search(ActionEvent e) { // action
	List<Appuntamento> lista = new ArrayList<Appuntamento>();
//listStrumenti.clear();
	lista.addAll(searchList(searchBar.getText(), listAppuntamenti));
	this.resetAndDraw(lista);
}

private List<Appuntamento> searchList(String searchWords, List<Appuntamento> listOfAppuntamenti) {
	List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
	return listOfAppuntamenti.stream().filter(input -> {
		return searchWordsArray.stream()
				.allMatch(word -> input.getAnimale().getNome().toLowerCase().contains(word.toLowerCase()));
	}).collect(Collectors.toList());
}
}