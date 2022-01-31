package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.EmptyBorder;
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
		table.getTableHeader().setForeground(Color.decode("#56728f"));
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Strumento");
		model.addColumn("Descrizione");
		model.addColumn("Quantità richiesta");
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
		JPanel middlePanel = new JPanel();
		JPanel lowerPanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
					.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
		);
		
		middlePanel.setLayout(new BorderLayout(0, 0));
		JPanel upperMiddlePanel = new JPanel();
		upperMiddlePanel.setBorder(new EmptyBorder(0, 10, 25, 10));
		middlePanel.add(upperMiddlePanel, BorderLayout.NORTH);
		GridBagLayout gbl_upperMiddlePanel = new GridBagLayout();
		gbl_upperMiddlePanel.columnWidths = new int[]{156, 176, 30, 0, 0};
		gbl_upperMiddlePanel.rowHeights = new int[]{23, 0};
		gbl_upperMiddlePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_upperMiddlePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		upperMiddlePanel.setLayout(gbl_upperMiddlePanel);
		
		JLabel cercaLabel = new JLabel("Seleziona una prestazione ");
		GridBagConstraints gbc_cercaLabel = new GridBagConstraints();
		gbc_cercaLabel.anchor = GridBagConstraints.WEST;
		gbc_cercaLabel.insets = new Insets(0, 0, 0, 5);
		gbc_cercaLabel.gridx = 0;
		gbc_cercaLabel.gridy = 0;
		upperMiddlePanel.add(cercaLabel, gbc_cercaLabel);
		cercaLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		comboBox = new JComboBox<>();
	listPrestazioni = prestazioneController.getAllTipiDiPrestazione();
	listPrestazioni.forEach(x -> {
		comboBox.addItem(x.getNomePrestazione());
	});
	comboBox.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		upperMiddlePanel.add(comboBox, gbc_comboBox);
		
		
		Icon iconAdd = IconImage.ADD.getImageIcon();
		JButton addPrestazione = new JButton(iconAdd);
		addPrestazione.setBorder(BorderFactory.createEmptyBorder());
		addPrestazione.setBorderPainted(false);
		addPrestazione.setContentAreaFilled(false);
		

		GridBagConstraints gbc_addPrestazione = new GridBagConstraints();
		gbc_addPrestazione.insets = new Insets(0, 0, 0, 5);
		gbc_addPrestazione.anchor = GridBagConstraints.NORTHWEST;
		gbc_addPrestazione.gridx = 2;
		gbc_addPrestazione.gridy = 0;
		upperMiddlePanel.add(addPrestazione, gbc_addPrestazione);	
		addPrestazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new AggiungiPrestazione(frame, user));
			}
		});
		
		JPanel lowerMiddlePanel = new JPanel();
		middlePanel.add(lowerMiddlePanel, BorderLayout.SOUTH);
		JButton addUtilizzoButton = new JButton("Assegna Strumento");
		addUtilizzoButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lowerMiddlePanel.add(addUtilizzoButton);
		
		
		JPanel centerPanel = new JPanel();
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{40, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0};
		gbl_centerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
	
		this.initTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		scrollPane.setViewportView(table);
	
		centerPanel.add(scrollPane, gbc_scrollPane);
		centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		middlePanel.add(centerPanel, BorderLayout.CENTER);
	

		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				resetAndDraw(listUtilizzi);
			}
		});
		
		
	
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
			
			
			upperPanel.setLayout(new BorderLayout(0, 0));
			JLabel titleLabel =new JLabel("<html><font color='#56728f '>Gestione Prestazioni</font></html>");
			titleLabel.setBounds(409, 58, 194, 52);
			titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel.setVerticalAlignment(SwingConstants.CENTER);
			upperPanel.add(titleLabel, BorderLayout.CENTER);
			

	 //   setLayout(groupLayout);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}
}