package view;

import java.awt.BorderLayout;
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
import model.Paziente;
import model.Person;
import model.ScaricaRefertoPrestazione;
import model.User;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class GestionePrestazione extends CommonView {

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

	public GestionePrestazione(final JFrame frame, User user) {
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
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
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
						searchBar.setText("");
					}
				});
				
				JButton buttonVai = new JButton("Vai");
				buttonVai.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						search(e);
					}
				});
				
				menuBar.add(searchBar);
				menuBar.add(buttonVai);

				
				JPanel centerPanel = new JPanel();
				centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
				middlePanel.add(centerPanel, BorderLayout.CENTER);
				
				upperPanel.setLayout(new BorderLayout(0, 0));
				JLabel titleLabel =new JLabel("<html><font color='#56728f '>Prestazioni giornaliere</font></html>");
				titleLabel.setBounds(409, 58, 194, 52);
				titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
				titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
				upperPanel.add(titleLabel, BorderLayout.CENTER);
				upperPanel.add(titleLabel);
		

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
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		centerPanel.add(scrollPane, gbc_scrollPane);
		
		this.initTable();
		this.listAppuntamenti = this.prestazioneController.getAllPrestazioniByPersonale(user.getCF());
		this.createTable(model, listAppuntamenti);
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				int rowIndex = table.getSelectedRow();
				
				if (prestazioneController.checkIfHasReferto(listAppuntamenti.get(rowIndex))) {
					if (JOptionPane.showConfirmDialog(null, "La prestazione è già conclusa, vuoi scaricare il referto?", "Scarica Referto",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						listAppuntamenti.get(rowIndex).setDescrizioneReferto(prestazioneController.getReferto(listAppuntamenti.get(rowIndex)));
						new ScaricaRefertoPrestazione(frame,listAppuntamenti.get(rowIndex));
					}
				} else {
					Person proprietario = proprietarioController
							.getProprietario(listAppuntamenti.get(rowIndex).getAnimale().getCf());
					listAppuntamenti.get(rowIndex).getAnimale().setProprietario(proprietario);
					frame.getContentPane().add(new ConclusionePrestazione(frame, user, listAppuntamenti.get(rowIndex)));
				}
					
				
			}
		});
		scrollPane.setViewportView(table);

		/*------fine-------- table */

		Icon icon = IconImage.BACK.getImageIcon();
		JButton backButton = new JButton(icon);
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new HomeVeterinario(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);
		
	//setLayout(groupLayout);
//new GroupLayout(this);
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
				});
			});
}

private void initTable() {
	this.table = new JTable();
	table.getTableHeader().setForeground(Color.decode("#56728f"));
	table.setFont(new Font("Tahoma", Font.PLAIN, 14));
	table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
	this.model = (DefaultTableModel) table.getModel();
	model.addColumn("Animale");
	model.addColumn("Data");
	model.addColumn("Ora");
	model.addColumn("Prestazione");	

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