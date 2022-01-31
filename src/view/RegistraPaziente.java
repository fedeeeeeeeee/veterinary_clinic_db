package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.User;
import utility.Pair;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JLabel cercaLabel = new JLabel("Cerca proprietario ");
		cercaLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_cercaLabel = new GridBagConstraints();
		gbc_cercaLabel.fill = GridBagConstraints.VERTICAL;
		gbc_cercaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cercaLabel.gridwidth = 3;
		gbc_cercaLabel.anchor = GridBagConstraints.EAST;
		gbc_cercaLabel.gridx = 1;
		gbc_cercaLabel.gridy = 0;
		centerPanel.add(cercaLabel, gbc_cercaLabel);
		
		JComboBox<String> comboBox = new JComboBox<>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 0;
		centerPanel.add(comboBox, gbc_comboBox);
	
		List<String> listCF = proprietarioController.getAllProprietariCf();
		listCF.forEach(x-> {
			comboBox.addItem(x);
		});
		comboBox.setSelectedIndex(-1);
		JButton addProprietarioButton = new JButton("+");
		GridBagConstraints gbc_addProprietarioButton = new GridBagConstraints();
		gbc_addProprietarioButton.anchor = GridBagConstraints.WEST;
		gbc_addProprietarioButton.insets = new Insets(0, 0, 5, 5);
		gbc_addProprietarioButton.gridx = 7;
		gbc_addProprietarioButton.gridy = 0;
		centerPanel.add(addProprietarioButton, gbc_addProprietarioButton);
		
		addProprietarioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		  frame.getContentPane().add(new AggiungiProprietario(frame, user));
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
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setViewportView(table);
		DefaultTableModel model =  (DefaultTableModel) table.getModel();
		model.addColumn("Animale");
		model.addColumn("Razza");

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
		
		JButton addAnimaleButton = new JButton("Aggiungi animale");
		GridBagConstraints gbc_addAnimaleButton = new GridBagConstraints();
		gbc_addAnimaleButton.insets = new Insets(0, 0, 0, 5);
		gbc_addAnimaleButton.gridx = 7;
		gbc_addAnimaleButton.gridy = 3;
		centerPanel.add(addAnimaleButton, gbc_addAnimaleButton);
		
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
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		
		JButton backButton = new JButton("BACK");
		lowerPanel.add(backButton);
		upperPanel.setLayout(new BorderLayout(0, 0));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new HomeSegretaria(frame, user));
			}
		});
		
		JLabel titleLabel = new JLabel("Registrazione Paziente");
	/*	titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(titleLabel, BorderLayout.CENTER);*/
		
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
