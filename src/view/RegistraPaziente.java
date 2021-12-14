package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Appuntamento;
import model.User;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
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

import controller.PazienteController;
import controller.PazienteControllerImpl;

public class RegistraPaziente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5823886107710364208L;
	private JFrame frame;
	private User user;
	private JTable table;
	private PazienteController pazienteController;

	/**
	 * Create the panel.
	 */
	public RegistraPaziente(final JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.pazienteController = new PazienteControllerImpl();
		
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
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE)
						.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
		);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 24, 65, 70, 0, 0, 0, 37, 40, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JLabel cercaLabel = new JLabel("Cerca proprietario\n\n");
		GridBagConstraints gbc_cercaLabel = new GridBagConstraints();
		gbc_cercaLabel.fill = GridBagConstraints.VERTICAL;
		gbc_cercaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cercaLabel.gridwidth = 3;
		gbc_cercaLabel.anchor = GridBagConstraints.EAST;
		gbc_cercaLabel.gridx = 1;
		gbc_cercaLabel.gridy = 0;
		centerPanel.add(cercaLabel, gbc_cercaLabel);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridwidth = 4;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 0;
		centerPanel.add(comboBox, gbc_comboBox);
		
		List<String> listCF = pazienteController.getAllPropietariCf();
		listCF.forEach(x-> {
			comboBox.addItem(x);
		});
		
		JButton addProprietarioButton = new JButton("+");
		GridBagConstraints gbc_addProprietarioButton = new GridBagConstraints();
		gbc_addProprietarioButton.insets = new Insets(0, 0, 5, 0);
		gbc_addProprietarioButton.gridx = 8;
		gbc_addProprietarioButton.gridy = 0;
		centerPanel.add(addProprietarioButton, gbc_addProprietarioButton);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		centerPanel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model =  (DefaultTableModel) table.getModel();
		model.addColumn("Animale");
		model.addColumn("Razza");

		model.addRow(new Object[] { 
				"gatto", 
				"siamese", 
		});
		model.addRow(new Object[] { 
				"2", 
				"23", 
		});
			
				
		JButton addAnimaleButton = new JButton("Aggiungi animale");
		GridBagConstraints gbc_addAnimaleButton = new GridBagConstraints();
		gbc_addAnimaleButton.gridwidth = 5;
		gbc_addAnimaleButton.insets = new Insets(0, 0, 0, 5);
		gbc_addAnimaleButton.gridx = 3;
		gbc_addAnimaleButton.gridy = 3;
		centerPanel.add(addAnimaleButton, gbc_addAnimaleButton);
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
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		//importante per il design
		//setLayout(groupLayout);

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout = new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		//this.initCommonView(frame);
	}
}
