package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AnimaleController;
import controller.AnimaleControllerImpl;
import model.Animale;
import model.AnimaleImpl;
import model.User;
import utility.ReadTextToHashMap;
import utility.ValidityFieldsChecker;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;

public class AggiungiAnimale extends JPanel {

	private static final long serialVersionUID = -2292831111277932599L;
	private static Map<String, List<String>> map;
	private JFrame frame;
	private JTextField textFieldProprietario;
	private JTextField textFieldNome;
	private JTextField textFieldPeso;
	private JTextField textFieldAltezza;
	private User user;
	private AnimaleController controller;
	private Animale animale;
	private ValidityFieldsChecker check;
	private String selectedCf;

	public AggiungiAnimale(JFrame frame, User user, String selectedCf) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.selectedCf = selectedCf;
		this.check = new ValidityFieldsChecker();
		this.controller = new AnimaleControllerImpl();
		map = ReadTextToHashMap.hashMapFromTextFile();
		
		JPanel upperPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());           
		// utile per il design
        // GroupLayout groupLayout = new GroupLayout(this);		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
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
		animalePanel.setBounds(0, 0, 465, 261);
		animalePanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Animale", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		centerPanel.add(animalePanel);
		GridBagLayout gbl_animalePanel = new GridBagLayout();
		gbl_animalePanel.columnWidths = new int[]{143, 170, 40, 0, 0};
		gbl_animalePanel.rowHeights = new int[]{0, 16, 22, 130, 13, 16, 23, 16, 0};
		gbl_animalePanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_animalePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		animalePanel.setLayout(gbl_animalePanel);
		
		JLabel labelProprietario = new JLabel("Proprietario");
		GridBagConstraints gbc_labelProprietario = new GridBagConstraints();
		gbc_labelProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_labelProprietario.anchor = GridBagConstraints.EAST;
		gbc_labelProprietario.gridx = 0;
		gbc_labelProprietario.gridy = 0;
		animalePanel.add(labelProprietario, gbc_labelProprietario);
		
		textFieldProprietario = new JTextField(selectedCf);
		textFieldProprietario.setEditable(false);
		labelProprietario.setLabelFor(textFieldProprietario);
		GridBagConstraints gbc_textFieldProprietario = new GridBagConstraints();
		gbc_textFieldProprietario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldProprietario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldProprietario.gridx = 1;
		gbc_textFieldProprietario.gridy = 0;
		animalePanel.add(textFieldProprietario, gbc_textFieldProprietario);
		textFieldProprietario.setColumns(10);
		
		JLabel labelNome = new JLabel("Nome animale");
		GridBagConstraints gbc_labelNome = new GridBagConstraints();
		gbc_labelNome.anchor = GridBagConstraints.EAST;
		gbc_labelNome.insets = new Insets(0, 0, 5, 5);
		gbc_labelNome.gridx = 0;
		gbc_labelNome.gridy = 1;
		animalePanel.add(labelNome, gbc_labelNome);
		
		textFieldNome = new JTextField();
		labelNome.setLabelFor(textFieldNome);
		GridBagConstraints gbc_textFieldNome = new GridBagConstraints();
		gbc_textFieldNome.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNome.gridx = 1;
		gbc_textFieldNome.gridy = 1;
		animalePanel.add(textFieldNome, gbc_textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel labelGenere = new JLabel("Genere");
		GridBagConstraints gbc_labelGenere = new GridBagConstraints();
		gbc_labelGenere.anchor = GridBagConstraints.EAST;
		gbc_labelGenere.insets = new Insets(0, 0, 5, 5);
		gbc_labelGenere.gridx = 0;
		gbc_labelGenere.gridy = 2;
		animalePanel.add(labelGenere, gbc_labelGenere);
		
		JRadioButton maleButton = new JRadioButton("M");
		GridBagConstraints gbc_maleButton = new GridBagConstraints();
		gbc_maleButton.insets = new Insets(0, 0, 5, 5);
		gbc_maleButton.gridx = 1;
		gbc_maleButton.gridy = 2;
		animalePanel.add(maleButton, gbc_maleButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(maleButton);
		
		JRadioButton femaleButton = new JRadioButton("F");
		GridBagConstraints gbc_femaleButton = new GridBagConstraints();
		gbc_femaleButton.anchor = GridBagConstraints.WEST;
		gbc_femaleButton.insets = new Insets(0, 0, 5, 5);
		gbc_femaleButton.gridx = 2;
		gbc_femaleButton.gridy = 2;
		animalePanel.add(femaleButton, gbc_femaleButton);
		group.add(femaleButton);
	    
		JLabel labelDataDiNascita = new JLabel("Data di nascita");
		GridBagConstraints gbc_labelDataDiNascita = new GridBagConstraints();
		gbc_labelDataDiNascita.anchor = GridBagConstraints.EAST;
		gbc_labelDataDiNascita.fill = GridBagConstraints.VERTICAL;
		gbc_labelDataDiNascita.insets = new Insets(0, 0, 5, 5);
		gbc_labelDataDiNascita.gridx = 0;
		gbc_labelDataDiNascita.gridy = 3;
		animalePanel.add(labelDataDiNascita, gbc_labelDataDiNascita);
		
		JDateChooser dateChooser = new JDateChooser();
		labelDataDiNascita.setLabelFor(dateChooser);
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 3;
		animalePanel.add(dateChooser, gbc_dateChooser);
		
		JLabel labelPeso = new JLabel("Peso");
		GridBagConstraints gbc_labelPeso = new GridBagConstraints();
		gbc_labelPeso.anchor = GridBagConstraints.EAST;
		gbc_labelPeso.insets = new Insets(0, 0, 5, 5);
		gbc_labelPeso.gridx = 0;
		gbc_labelPeso.gridy = 4;
		animalePanel.add(labelPeso, gbc_labelPeso);
		
		textFieldPeso = new JTextField();
		labelPeso.setLabelFor(textFieldPeso);
		GridBagConstraints gbc_textFieldPeso = new GridBagConstraints();
		gbc_textFieldPeso.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPeso.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPeso.gridx = 1;
		gbc_textFieldPeso.gridy = 4;
		animalePanel.add(textFieldPeso, gbc_textFieldPeso);
		textFieldPeso.setColumns(10);
		
		JLabel labelAltezza = new JLabel("Altezza");
		labelAltezza.setLabelFor(labelAltezza);
		labelAltezza.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_labelAltezza = new GridBagConstraints();
		gbc_labelAltezza.anchor = GridBagConstraints.EAST;
		gbc_labelAltezza.insets = new Insets(0, 0, 5, 5);
		gbc_labelAltezza.gridx = 0;
		gbc_labelAltezza.gridy = 5;
		animalePanel.add(labelAltezza, gbc_labelAltezza);
		
		textFieldAltezza = new JTextField();
		GridBagConstraints gbc_textFieldAltezza = new GridBagConstraints();
		gbc_textFieldAltezza.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAltezza.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAltezza.gridx = 1;
		gbc_textFieldAltezza.gridy = 5;
		animalePanel.add(textFieldAltezza, gbc_textFieldAltezza);
		textFieldAltezza.setColumns(10);
		
		JLabel labelSpecie = new JLabel("Specie");
		labelSpecie.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_labelSpecie = new GridBagConstraints();
		gbc_labelSpecie.anchor = GridBagConstraints.EAST;
		gbc_labelSpecie.insets = new Insets(0, 0, 5, 5);
		gbc_labelSpecie.gridx = 0;
		gbc_labelSpecie.gridy = 6;
		animalePanel.add(labelSpecie, gbc_labelSpecie);
		
		JComboBox<String> comboBoxSpecie = new JComboBox<>();
		GridBagConstraints gbc_comboBoxSpecie = new GridBagConstraints();
		gbc_comboBoxSpecie.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSpecie.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSpecie.gridx = 1;
		gbc_comboBoxSpecie.gridy = 6;
		animalePanel.add(comboBoxSpecie, gbc_comboBoxSpecie);
		comboBoxSpecie.setSelectedIndex(-1);		
		labelSpecie.setLabelFor(comboBoxSpecie);
		
		JButton addSpecieButton = new JButton("+");
		GridBagConstraints gbc_addSpecieButton = new GridBagConstraints();
		gbc_addSpecieButton.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_addSpecieButton.insets = new Insets(0, 0, 5, 5);
		gbc_addSpecieButton.gridx = 2;
		gbc_addSpecieButton.gridy = 6;
		animalePanel.add(addSpecieButton, gbc_addSpecieButton);
		addSpecieButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String result = (String)JOptionPane.showInputDialog(
			               frame,
			               "Inserisci nuova specie", 
			               "Nuova specie",            
			               JOptionPane.PLAIN_MESSAGE,
			               null,            
			               null, 
			               "");
				map.put(result, new ArrayList<>());
				ReadTextToHashMap.writeMapToFile(map);
				//ReadTextToHashMap.addKeyOrValueToFile(Optional.of(result), Optional.empty());
				comboBoxSpecie.addItem(result);
			}
		});
		
		JButton resetSpecieButton = new JButton("RESET");
		GridBagConstraints gbc_resetSpecieButton = new GridBagConstraints();
		gbc_resetSpecieButton.insets = new Insets(0, 0, 5, 0);
		gbc_resetSpecieButton.gridx = 3;
		gbc_resetSpecieButton.gridy = 6;
		animalePanel.add(resetSpecieButton, gbc_resetSpecieButton);
		
		JLabel labelRazza = new JLabel("Razza");
		GridBagConstraints gbc_labelRazza = new GridBagConstraints();
		gbc_labelRazza.anchor = GridBagConstraints.EAST;
		gbc_labelRazza.insets = new Insets(0, 0, 0, 5);
		gbc_labelRazza.gridx = 0;
		gbc_labelRazza.gridy = 7;
		animalePanel.add(labelRazza, gbc_labelRazza);
		
		JComboBox<String> comboBoxRazza = new JComboBox<>();
		GridBagConstraints gbc_comboBoxRazza = new GridBagConstraints();
		gbc_comboBoxRazza.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxRazza.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRazza.gridx = 1;
		gbc_comboBoxRazza.gridy = 7;
		animalePanel.add(comboBoxRazza, gbc_comboBoxRazza);
		comboBoxRazza.setEnabled(false);
		labelRazza.setLabelFor(comboBoxRazza);
		
		JButton addRazzaButton = new JButton("+");
		GridBagConstraints gbc_addRazzaButton = new GridBagConstraints();
		gbc_addRazzaButton.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_addRazzaButton.insets = new Insets(0, 0, 0, 5);
		gbc_addRazzaButton.gridx = 2;
		gbc_addRazzaButton.gridy = 7;
		animalePanel.add(addRazzaButton, gbc_addRazzaButton);
		addRazzaButton.setEnabled(false);
	 
		addRazzaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				
				JComboBox<String> specie = new JComboBox<>();
				JTextField razza = new JTextField();
				specie.addItem(comboBoxSpecie.getSelectedItem().toString());
				specie.setSelectedItem(comboBoxSpecie.getSelectedItem().toString());
				//map.keySet().forEach(k-> specie.addItem(k));
				Object[] message = { 
						"Specie", specie, 
						"Inserisci razza", razza, 
						};
				int option = JOptionPane.showConfirmDialog(frame, message, "Nuova razza", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {

					String key = specie.getSelectedItem().toString();
					String value = razza.getText();
					if(map.containsKey(key)) {
						map.get(key).add(value);
						ReadTextToHashMap.writeMapToFile(map);
						if (((DefaultComboBoxModel<String>) comboBoxRazza.getModel()).getIndexOf(value) == -1) {
							comboBoxRazza.addItem(value);
						}
						comboBoxRazza.setSelectedIndex(-1);
					}
				}

			}
		});
		
		JButton resetRazzaButton = new JButton("RESET");
		GridBagConstraints gbc_resetRazzaButton = new GridBagConstraints();
		gbc_resetRazzaButton.gridx = 3;
		gbc_resetRazzaButton.gridy = 7;
		animalePanel.add(resetRazzaButton, gbc_resetRazzaButton);
		resetRazzaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxRazza.setSelectedIndex(-1);
			}
			
		});

		
		resetSpecieButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxSpecie.setEnabled(true);
				comboBoxSpecie.setSelectedIndex(-1);
				comboBoxRazza.setEnabled(false);
				addRazzaButton.setEnabled(false);
				comboBoxRazza.removeAllItems();
			}
			
		});
		
		map.keySet().forEach(k-> comboBoxSpecie.addItem(k));
		comboBoxSpecie.setSelectedIndex(-1);
		comboBoxSpecie.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {	
				if(comboBoxRazza.isEnabled()) {
					comboBoxRazza.removeAllItems();
				}
								
				comboBoxRazza.setEnabled(true);
				addRazzaButton.setEnabled(true);
				if(comboBoxSpecie.getSelectedIndex() != -1) {
						map.entrySet().stream()	
							  .filter(entry-> entry.getKey().equals(comboBoxSpecie.getSelectedItem().toString()))
							  .map(x-> x.getValue())
							  .flatMap(x-> x.stream())
							  .forEach(s-> {
								  comboBoxRazza.addItem(s);
				});
				}
				comboBoxRazza.setSelectedIndex(-1);
			}
		});
		
		comboBoxRazza.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {	
				
			}
		});
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check.checkCF(textFieldProprietario.getText()) 
						&& check.checkNotEmpty(textFieldNome.getText())
						&& check.checkNotEmpty(textFieldAltezza.getText())
						&& check.checkNotEmpty(textFieldPeso.getText())
						&& check.checkFloatFieldsValue(textFieldPeso.getText())
						&& check.checkFloatFieldsValue(textFieldAltezza.getText())
						&& group.getSelection() != null
						&& dateChooser.getDate() != null 
						&& comboBoxRazza.getSelectedItem() != null
						&& comboBoxSpecie.getSelectedItem() != null
						) {
					animale = new AnimaleImpl();
					animale.setCf(textFieldProprietario.getText());
					animale.setNome(textFieldNome.getText());
					animale.setGenere(group.getElements().nextElement().getText());

					dateChooser.setDateFormatString("yyyy-MM-dd");
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date = format.format(dateChooser.getDate());
					animale.setDataDiNascita(date);

					animale.setPeso(Float.parseFloat(textFieldPeso.getText()));
					animale.setAltezza(Float.parseFloat(textFieldAltezza.getText()));
					animale.setRazza(comboBoxRazza.getSelectedItem().toString());
					animale.setSpecie(comboBoxSpecie.getSelectedItem().toString());		
					controller.addNewAnimale(animale);
					System.out.println("tutto ok");
					frame.getContentPane().add(new RegistraPaziente(frame, user));
				} else {
					// errore di compilazione della form
					System.out.println("errore");
				}
			}
		});
		btnNewButton.setBounds(183, 267, 117, 24);
		centerPanel.add(btnNewButton);
		
		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new RegistraPaziente(frame, user));
			}
		});
		lowerPanel.add(backButton);
		upperPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel titleLabel = new JLabel("Aggiungi animale");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(titleLabel, BorderLayout.CENTER);
		
		// importante per il design
		// setLayout(groupLayout);

		// importante per la visualizzazione 
		frame.getContentPane().setLayout(groupLayout);
	}
}
