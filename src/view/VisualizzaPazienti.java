package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import controller.AnimaleController;
import controller.AnimaleControllerImpl;
import model.Paziente;
import model.User;

public class VisualizzaPazienti extends CommonView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private JTextField searchBar;
	private JTable table;
	private JScrollPane scrollPane;
	private AnimaleController pazienteController;
	private List<Paziente> listPazienti;
	private int selectedPaziente;
	private DefaultTableModel model;
	/**
	 * Create the panel.
	 */
	public VisualizzaPazienti(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.pazienteController = new AnimaleControllerImpl();
		this.selectedPaziente = -1;
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING,false)
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
								.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
								.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE))
						.addGap(24)));
						//.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE).addGap(25)
						.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
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
//frame.setJMenuBar(menuBar);
		
		upperMiddlePanel.add(menuBar, "1, 1, fill, top");
		JMenuItem menuMostra = new JMenuItem("Tutti");
		menuMostra.setFont(new Font("Tahoma", Font.BOLD, 14));

		searchBar = new JTextField();
		searchBar.setText("Ricerca Animale");
		searchBar.setFont(new Font("Tahoma", Font.ITALIC, 13));
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
				//if(searchBar.getText().equals("Ricerca Strumento")){
				searchBar.setText("");
			}
		});
		
		JButton buttonVai = new JButton("Vai");
		buttonVai.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonVai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search(e);
			}
		});
		
		//buttonVai.setBackground(Color.LIGHT_GRAY);
		//buttonVai.setForeground(Color.BLACK);
		menuBar.add(menuMostra);
		//menuBar.add(menuAzioni);
		menuBar.add(searchBar);
		menuBar.add(buttonVai);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		middlePanel.add(centerPanel, BorderLayout.CENTER);
		
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel = new JLabel("Visualizza Pazienti");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		upperPanel.add(titleLabel, BorderLayout.CENTER);
		
		/*-------------- TABLE */
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 0, 0 };
		gbl_centerPanel.rowHeights = new int[] { 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		centerPanel.setLayout(gbl_centerPanel);
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		centerPanel.add(scrollPane, gbc_scrollPane);
		this.initTable();
		this.listPazienti = pazienteController.getAllPazienti();
		this.createTable(model, listPazienti);
		scrollPane.setViewportView(table);
		

		menuMostra.addActionListener((e) -> {
			searchBar.setText("Ricerca Animale");
			resetAndDraw(pazienteController.getAllPazienti());
		});
			

//----fine ---- MENUU
		/* LOWER PANEL */
		Icon icon = IconImage.BACK.getImageIcon();
		JButton sendButton = new JButton(icon);
		sendButton.setBorder(BorderFactory.createEmptyBorder());
		sendButton.setContentAreaFilled(false);
		sendButton.setHorizontalAlignment(SwingConstants.LEFT);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new HomeVeterinario(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(sendButton, BorderLayout.WEST);
		
//	setLayout(groupLayout);
		//new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
	this.initCommonView(frame, user);

	}

	
	
	
	
	
	private void resetAndDraw(List<Paziente> allAnimali) {
		this.listPazienti.clear();
		this.listPazienti = allAnimali;
		//table.clearSelection();
		model.setRowCount(0);
		this.createTable(model, listPazienti);
	}

	private void createTable(DefaultTableModel model, List<Paziente> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { 
					x.getAnimale().getNome(), 
					x.getAnimale().getRazza(), 
					x.getAnimale().getProprietario().getNome() + " "+ x.getAnimale().getProprietario().getCognome(),
				 // voglaimo aggiungere Ricoverato si e no ? 
					});
		});
	}

	private void initTable() {
		this.table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Animale");
		model.addColumn("Razza");
		model.addColumn("Proprietario");
	//	model.addColumn("Ricoverato");
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				selectedPaziente = table.getSelectedRow();
				if (selectedPaziente != -1) {
					Paziente paziente = listPazienti.get(selectedPaziente);
					//Person proprietario = paziente.getAnimale().getProprietario();					
					frame.getContentPane().add(new VisualizzaDatiPaziente(frame, user,paziente ));
				} else {
					JOptionPane.showMessageDialog(null, "Non hai selezionato alcun appuntamento ");
				}
			}
		});
	}
	
	public void search(ActionEvent e) {
		List<Paziente> lista = new ArrayList<Paziente>();
		lista.addAll(searchList(searchBar.getText(), listPazienti));
		this.resetAndDraw(lista);
	}

	private List<Paziente> searchList(String searchWords, List<Paziente> listOfStrumento) {
		List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
		return listOfStrumento.stream().filter(input -> {
			return searchWordsArray.stream()
					.allMatch(word -> input.getAnimale().getNome().toLowerCase().contains(word.toLowerCase()));
		}).collect(Collectors.toList());
	}
}
