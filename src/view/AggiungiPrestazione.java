package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import model.User;
import utility.ValidityFieldsChecker;

public class AggiungiPrestazione extends JPanel {

	private static final long serialVersionUID = -4383883306628103728L;
	private JFrame frame;
	private JTextField textFieldPeso;
	private JTextField textFieldAltezza;
	private User user;
	private ValidityFieldsChecker check;
	private String selectedCf;
	private JTextField textFieldPrezzo;
	private PrestazioneController prestazioneController;

	public AggiungiPrestazione(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.check = new ValidityFieldsChecker();
		this.prestazioneController = new PrestazioneControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		
		 GroupLayout groupLayout = new GroupLayout(frame.getContentPane());           
		// utile per il design
        //  GroupLayout groupLayout = new GroupLayout(this);
         groupLayout.setHorizontalGroup(
         	groupLayout.createParallelGroup(Alignment.LEADING)
         		.addGroup(groupLayout.createSequentialGroup()
         			.addContainerGap()
         			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
         				.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         				.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
         				.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE))
         			.addContainerGap())
         );
         groupLayout.setVerticalGroup(
         	groupLayout.createParallelGroup(Alignment.LEADING)
         		.addGroup(groupLayout.createSequentialGroup()
         			.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
         			.addPreferredGap(ComponentPlacement.RELATED)
         			.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         			.addPreferredGap(ComponentPlacement.RELATED)
         			.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
         );
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		centerPanel.setLayout(null);
		
		JPanel animalePanel = new JPanel();
		animalePanel.setBounds(0, 0, 982, 448);
		centerPanel.add(animalePanel);
		GridBagLayout gbl_animalePanel = new GridBagLayout();
		gbl_animalePanel.columnWidths = new int[]{216, 158, 256, 97, 42, 187, 0};
		gbl_animalePanel.rowHeights = new int[]{66, 0, 16, 22, -49, 13, 16, 0, 58, 0};
		gbl_animalePanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_animalePanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		animalePanel.setLayout(gbl_animalePanel);
		
		JLabel labelNomePrestazione = new JLabel("Nome prestazione");
		labelNomePrestazione.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		labelNomePrestazione.setLabelFor(labelNomePrestazione);
		labelNomePrestazione.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_labelNomePrestazione = new GridBagConstraints();
		gbc_labelNomePrestazione.anchor = GridBagConstraints.EAST;
		gbc_labelNomePrestazione.insets = new Insets(0, 0, 5, 5);
		gbc_labelNomePrestazione.gridx = 1;
		gbc_labelNomePrestazione.gridy = 1;
		animalePanel.add(labelNomePrestazione, gbc_labelNomePrestazione);
		
		textFieldAltezza = new JTextField();
		textFieldAltezza.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldAltezza = new GridBagConstraints();
		gbc_textFieldAltezza.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAltezza.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAltezza.gridx = 2;
		gbc_textFieldAltezza.gridy = 1;
		animalePanel.add(textFieldAltezza, gbc_textFieldAltezza);
		textFieldAltezza.setColumns(10);
		
		JLabel labelTipo = new JLabel("Tipo");
		labelTipo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		labelTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_labelTipo = new GridBagConstraints();
		gbc_labelTipo.anchor = GridBagConstraints.EAST;
		gbc_labelTipo.insets = new Insets(0, 0, 5, 5);
		gbc_labelTipo.gridx = 1;
		gbc_labelTipo.gridy = 2;
		animalePanel.add(labelTipo, gbc_labelTipo);
		
		JComboBox<String> comboBoxTipo = new JComboBox<>();
		comboBoxTipo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_comboBoxTipo = new GridBagConstraints();
		gbc_comboBoxTipo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipo.gridx = 2;
		gbc_comboBoxTipo.gridy = 2;
		animalePanel.add(comboBoxTipo, gbc_comboBoxTipo);
		labelTipo.setLabelFor(comboBoxTipo);

		List<String> listTipi = prestazioneController.getAllTipiDiPrestazione().stream().map(p->p.getTipoPrestazione()).collect(Collectors.toList());
		listTipi.forEach(x -> {
			if(((DefaultComboBoxModel<String>)comboBoxTipo.getModel()).getIndexOf(x) == -1) {
				comboBoxTipo.addItem(x);
			}
		});
		comboBoxTipo.setSelectedIndex(-1);
		
		JButton addSpecieButton = new JButton("+");
		addSpecieButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_addSpecieButton = new GridBagConstraints();
		gbc_addSpecieButton.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_addSpecieButton.insets = new Insets(0, 0, 5, 5);
		gbc_addSpecieButton.gridx = 3;
		gbc_addSpecieButton.gridy = 2;
		animalePanel.add(addSpecieButton, gbc_addSpecieButton);
		
		JLabel labelDurata = new JLabel("Durata media");
		labelDurata.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_labelDurata = new GridBagConstraints();
		gbc_labelDurata.anchor = GridBagConstraints.EAST;
		gbc_labelDurata.insets = new Insets(0, 0, 5, 5);
		gbc_labelDurata.gridx = 1;
		gbc_labelDurata.gridy = 3;
		animalePanel.add(labelDurata, gbc_labelDurata);
		
		textFieldPeso = new JTextField();
		textFieldPeso.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldPeso = new GridBagConstraints();
		gbc_textFieldPeso.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPeso.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPeso.gridx = 2;
		gbc_textFieldPeso.gridy = 3;
		animalePanel.add(textFieldPeso, gbc_textFieldPeso);
		textFieldPeso.setColumns(10);
	    
		JLabel labelPrezzo = new JLabel("Prezzo");
		labelPrezzo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_labelPrezzo = new GridBagConstraints();
		gbc_labelPrezzo.anchor = GridBagConstraints.EAST;
		gbc_labelPrezzo.insets = new Insets(0, 0, 5, 5);
		gbc_labelPrezzo.gridx = 1;
		gbc_labelPrezzo.gridy = 4;
		animalePanel.add(labelPrezzo, gbc_labelPrezzo);
		
		textFieldPrezzo = new JTextField();
		GridBagConstraints gbc_textFieldPrezzo = new GridBagConstraints();
		gbc_textFieldPrezzo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrezzo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrezzo.gridx = 2;
		gbc_textFieldPrezzo.gridy = 4;
		animalePanel.add(textFieldPrezzo, gbc_textFieldPrezzo);
		textFieldPrezzo.setColumns(10);
		

		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 7;
		animalePanel.add(btnNewButton, gbc_btnNewButton);
		
		
		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new GestioneUtilizzi(frame, user));
			}
		});
		lowerPanel.add(backButton);
		upperPanel.setLayout(new BorderLayout(0, 0));
	
		
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel = new JLabel("Nuova Prestazione");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		upperPanel.add(titleLabel, BorderLayout.CENTER);
		
		// importante per il design
	    // setLayout(groupLayout);

		// importante per la visualizzazione 
		//this.initCommonView(frame, user);
	   frame.getContentPane().setLayout(groupLayout);
	}
}
