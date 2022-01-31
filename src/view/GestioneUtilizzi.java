package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import controller.StrumentiController;
import controller.StrumentiControllerImpl;
import model.Appuntamento;
import model.Farmaco;
import model.Prestazione;
import model.User;
import model.Utilizzo;
import model.UtilizzoImpl;

public class GestioneUtilizzi extends CommonView {
	
	private static final long serialVersionUID = 402015365261962386L;
	private Frame frame;
	private User user;
	private JTable table;
	private PrestazioneController prestazioneController;
	private StrumentiController strumentoController;
	private List<Utilizzo> listUtilizzi = new ArrayList<>();
	private DefaultTableModel model;
	private List<Prestazione> listPrestazioni = new ArrayList<>();
	private JComboBox<String> comboBox;
	
	private void initTable() {
		this.table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Strumento");
		model.addColumn("Descrizione");
		model.addColumn("Quantità richiesta in prestazione");
		model.addColumn("Disponibilità");
	}
	
	
	private void resetAndDraw(List<Utilizzo> allUtilizziDiPrestazione) {
		this.listUtilizzi.clear();
		this.listUtilizzi = prestazioneController.getAllUtilizziDiPrestazione(listPrestazioni.get(comboBox.getSelectedIndex()));
		model.setRowCount(0);
		this.createTable(model, listUtilizzi);
		// scrollPane.setViewportView(table);
	}
	
	private void createTable(DefaultTableModel model, List<Utilizzo> list) {
		list.forEach(x-> {
			model.addRow(new Object[] { 
				x.getStrumento().getNome(),
				x.getStrumento().getDescrizione(),
				x.getQuantita(),
				x.getStrumento().getQuantita()
			});
		});
	}
	
	public GestioneUtilizzi(JFrame frame, User user) {

		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.prestazioneController = new PrestazioneControllerImpl();
		this.strumentoController = new StrumentiControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel lowerPanel = new JPanel();

		// per la visualizzazione
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		// utile per il design
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
		);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 24, 65, 70, 0, 0, 148, 0, 0, 133, 148, 40, 0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);

		
		JLabel cercaLabel = new JLabel("Seleziona una prestazione ");
		cercaLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_cercaLabel = new GridBagConstraints();
		gbc_cercaLabel.fill = GridBagConstraints.VERTICAL;
		gbc_cercaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cercaLabel.anchor = GridBagConstraints.EAST;
		gbc_cercaLabel.gridx = 6;
		gbc_cercaLabel.gridy = 0;
		centerPanel.add(cercaLabel, gbc_cercaLabel);
		
		comboBox = new JComboBox<>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 7;
		gbc_comboBox.gridy = 0;
		centerPanel.add(comboBox, gbc_comboBox);
				
		listPrestazioni = prestazioneController.getAllTipiDiPrestazione();
		listPrestazioni.forEach(x -> {
			comboBox.addItem(x.getNomePrestazione());
		});
		comboBox.setSelectedIndex(-1);
		
		JButton addPrestazione = new JButton("+");
		GridBagConstraints gbc_addPrestazione = new GridBagConstraints();
		gbc_addPrestazione.anchor = GridBagConstraints.WEST;
		gbc_addPrestazione.insets = new Insets(0, 0, 5, 5);
		gbc_addPrestazione.gridx = 8;
		gbc_addPrestazione.gridy = 0;
		centerPanel.add(addPrestazione, gbc_addPrestazione);
		
		addPrestazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new AggiungiPrestazione(frame, user));
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		centerPanel.add(scrollPane, gbc_scrollPane);
	
		this.initTable();
		scrollPane.setViewportView(table);

		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				resetAndDraw(listUtilizzi);
			}
		});
		
		JButton addUtilizzoButton = new JButton("Assegna Strumento");
		GridBagConstraints gbc_addAnimaleButton = new GridBagConstraints();
		gbc_addAnimaleButton.insets = new Insets(0, 0, 5, 5);
		gbc_addAnimaleButton.gridx = 7;
		gbc_addAnimaleButton.gridy = 3;
		centerPanel.add(addUtilizzoButton, gbc_addAnimaleButton);
	
		addUtilizzoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() != -1) {
					OptionUtilizzo option = new OptionUtilizzo(listPrestazioni.get(comboBox.getSelectedIndex()).getCodPrestazione(), listUtilizzi);
					int result = JOptionPane.showConfirmDialog(null,
							option,
							"Strumento",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE);
							if (result == JOptionPane.OK_OPTION) {
								Utilizzo utilizzo = new UtilizzoImpl();
								utilizzo.setPrestazione(listPrestazioni.get(comboBox.getSelectedIndex()));
								utilizzo.setStrumento(option.getSelectedStrumento());
								utilizzo.setQuantita(option.getQt());
								strumentoController.addUtilizzo(utilizzo);
								resetAndDraw(listUtilizzi);
							}
				} else {
					JOptionPane.showMessageDialog(frame, "Seleziona una prestazione, oppure inseriscine una nuova per assegnarle gli strumenti", 
							"Attenzione!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
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
			
		JLabel titleLabel = new JLabel("Gestione Prestazioni");

		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		//importante per il design
	    //setLayout(groupLayout);

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout = new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}
}