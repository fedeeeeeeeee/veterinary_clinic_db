package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.User;
import utility.Pair;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.AnimaleController;
import controller.AnimaleControllerImpl;
import controller.ProprietarioController;
import controller.ProprietarioControllerImpl;

public class RegistraPaziente extends CommonView {

	private static final long serialVersionUID = -5823886107710364208L;
	private JFrame frame;
	private User user;
	private JTable table;
	private AnimaleController animaleController;
	private ProprietarioController proprietarioController;


	public RegistraPaziente(final JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.animaleController = new AnimaleControllerImpl();
		this.proprietarioController = new ProprietarioControllerImpl();
		
		JPanel upperPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel lowerPanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING,false)
						.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE).addGap(44)
					.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE).addGap(30)
					.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(12)));
	
		middlePanel.setLayout(new BorderLayout(0, 0));
		JPanel upperMiddlePanel = new JPanel();
		upperMiddlePanel.setBorder(new EmptyBorder(0, 10, 25, 10));
		middlePanel.add(upperMiddlePanel, BorderLayout.NORTH);
	
		JPanel lowerMiddlePanel = new JPanel();
		middlePanel.add(lowerMiddlePanel, BorderLayout.SOUTH);
		
		JButton addAnimaleButton = new JButton("Aggiungi animale");
		lowerMiddlePanel.add(addAnimaleButton);
		JComboBox<String> comboBox = new JComboBox<>();
		addAnimaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() != -1) {
					frame.getContentPane().add(new AggiungiAnimale(frame, user, comboBox.getSelectedItem().toString()));
				} else {
					JOptionPane.showMessageDialog(frame, "Seleziona un proprietario, oppure aggiungine uno nuovo per registrare un paziente.", 
							"Attenzione!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		GridBagLayout gbl_upperMiddlePanel = new GridBagLayout();
		gbl_upperMiddlePanel.columnWidths = new int[]{156, 176, 30, 41, 0, 0};
		gbl_upperMiddlePanel.rowHeights = new int[]{23, 0};
		gbl_upperMiddlePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_upperMiddlePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		upperMiddlePanel.setLayout(gbl_upperMiddlePanel);
		
		JLabel cercaLabel = new JLabel("Cerca proprietario ");
		GridBagConstraints gbc_cercaLabel = new GridBagConstraints();
		gbc_cercaLabel.anchor = GridBagConstraints.WEST;
		gbc_cercaLabel.insets = new Insets(0, 0, 0, 5);
		gbc_cercaLabel.gridx = 0;
		gbc_cercaLabel.gridy = 0;
		upperMiddlePanel.add(cercaLabel, gbc_cercaLabel);
		cercaLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		upperMiddlePanel.add(comboBox, gbc_comboBox);
		
		List<String> listCF = proprietarioController.getAllProprietariCf();
		listCF.forEach(x-> {
			comboBox.addItem(x);
		});
		Icon iconAdd = IconImage.ADD.getImageIcon();
		JButton addProprietarioButton = new JButton(iconAdd);
		addProprietarioButton.setBorder(BorderFactory.createEmptyBorder());
		addProprietarioButton.setBorderPainted(false);
		addProprietarioButton.setContentAreaFilled(false);

		GridBagConstraints gbc_addProprietarioButton = new GridBagConstraints();
		gbc_addProprietarioButton.insets = new Insets(0, 0, 0, 5);
		gbc_addProprietarioButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_addProprietarioButton.gridx = 2;
		gbc_addProprietarioButton.gridy = 0;
		upperMiddlePanel.add(addProprietarioButton, gbc_addProprietarioButton);
		
		addProprietarioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		  frame.getContentPane().add(new AggiungiProprietario(frame, user));
			}
		});
		
		// table middle panel
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		DefaultTableModel model =  (DefaultTableModel) table.getModel();
		model.addColumn("Animale");
		model.addColumn("Razza");

		comboBox.setSelectedIndex(-1);
				comboBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						model.setRowCount(0);
						List<Pair<String, String>> list = animaleController.getNomeAndRazzaByProprietario(comboBox.getSelectedItem().toString());
						list.forEach(x-> {
							model.addRow(new Object[] { 
									x.getX(), 
									x.getY(), 
							});
						});
					}
				});
	
	
		
		JPanel centerPanel = new JPanel();
GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{40, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0};
		gbl_centerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
	
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		scrollPane.setViewportView(table);
		
		centerPanel.add(scrollPane, gbc_scrollPane);
		centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		middlePanel.add(centerPanel, BorderLayout.CENTER);
		
		
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		
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
		JLabel titleLabel = new JLabel("Registrazione Paziente");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		upperPanel.add(titleLabel, BorderLayout.CENTER);
	

	   //setLayout(groupLayout);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}
}
