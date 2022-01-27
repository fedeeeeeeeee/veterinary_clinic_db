package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerModel;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import model.Prestazione;
import model.PrestazioneImpl;
import model.User;
import utility.ReadTextToHashMap;
import utility.ValidityFieldsChecker;
import com.github.lgooddatepicker.components.TimePicker;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.toedter.components.JSpinField;
import javax.swing.JSpinner;

public class AggiungiPrestazione extends CommonView {

	private static final long serialVersionUID = -4383883306628103728L;
	private JFrame frame;
	private JTextField textFieldNome;
	private User user;
	private ValidityFieldsChecker checker;
	private String selectedCf;
	private JTextField textFieldPrezzo;
	private PrestazioneController prestazioneController;

	public AggiungiPrestazione(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.checker = new ValidityFieldsChecker();
		this.prestazioneController = new PrestazioneControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel lowerPanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		// utile per il design
		// GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)));
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		centerPanel.setLayout(null);
		
		JPanel animalePanel = new JPanel();
		animalePanel.setBounds(0, 0, 982, 448);
		centerPanel.add(animalePanel);
		GridBagLayout gbl_animalePanel = new GridBagLayout();
		gbl_animalePanel.columnWidths = new int[]{216, 158, 57, 43, 44, -108, 0, 0, 50, 97, 42, 187, 0};
		gbl_animalePanel.rowHeights = new int[]{66, 0, 16, 22, -49, 13, 42, 0, 0, 58, 0};
		gbl_animalePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_animalePanel.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		animalePanel.setLayout(gbl_animalePanel);
		
		JLabel labelTipo = new JLabel("Tipo");
		labelTipo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		labelTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_labelTipo = new GridBagConstraints();
		gbc_labelTipo.anchor = GridBagConstraints.WEST;
		gbc_labelTipo.insets = new Insets(0, 0, 5, 5);
		gbc_labelTipo.gridx = 1;
		gbc_labelTipo.gridy = 1;
		animalePanel.add(labelTipo, gbc_labelTipo);
		
		JComboBox<String> comboBoxTipo = new JComboBox<>();
		comboBoxTipo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_comboBoxTipo = new GridBagConstraints();
		gbc_comboBoxTipo.gridwidth = 7;
		gbc_comboBoxTipo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipo.gridx = 2;
		gbc_comboBoxTipo.gridy = 1;
		animalePanel.add(comboBoxTipo, gbc_comboBoxTipo);
		labelTipo.setLabelFor(comboBoxTipo);

		JButton addTipoButton = new JButton("+");
		addTipoButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_addSpecieButton = new GridBagConstraints();
		gbc_addSpecieButton.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_addSpecieButton.insets = new Insets(0, 0, 5, 5);
		gbc_addSpecieButton.gridx = 9;
		gbc_addSpecieButton.gridy = 1;
		animalePanel.add(addTipoButton, gbc_addSpecieButton);
		addTipoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String result = (String) JOptionPane.showInputDialog(frame, "Inserisci nuovo tipo di prestazione",
						"Nuovo Tipo di Prestazione", JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (result != null) {
					comboBoxTipo.addItem(result);
				}

			}
		});
		
		
		JLabel labelNomePrestazione = new JLabel("Nome prestazione");
		labelNomePrestazione.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		labelNomePrestazione.setLabelFor(labelNomePrestazione);
		labelNomePrestazione.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_labelNomePrestazione = new GridBagConstraints();
		gbc_labelNomePrestazione.anchor = GridBagConstraints.WEST;
		gbc_labelNomePrestazione.insets = new Insets(0, 0, 5, 5);
		gbc_labelNomePrestazione.gridx = 1;
		gbc_labelNomePrestazione.gridy = 2;
		animalePanel.add(labelNomePrestazione, gbc_labelNomePrestazione);

		List<String> listTipi = prestazioneController.getAllTipiDiPrestazione().stream().map(p->p.getTipoPrestazione()).collect(Collectors.toList());
		listTipi.forEach(x -> {
			if(((DefaultComboBoxModel<String>)comboBoxTipo.getModel()).getIndexOf(x) == -1) {
				comboBoxTipo.addItem(x);
			}
		});
		comboBoxTipo.setSelectedIndex(-1);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldNome = new GridBagConstraints();
		gbc_textFieldNome.gridwidth = 7;
		gbc_textFieldNome.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNome.gridx = 2;
		gbc_textFieldNome.gridy = 2;
		animalePanel.add(textFieldNome, gbc_textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel labelDurata = new JLabel("Durata media");
		labelDurata.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_labelDurata = new GridBagConstraints();
		gbc_labelDurata.anchor = GridBagConstraints.WEST;
		gbc_labelDurata.insets = new Insets(0, 0, 5, 5);
		gbc_labelDurata.gridx = 1;
		gbc_labelDurata.gridy = 3;
		animalePanel.add(labelDurata, gbc_labelDurata);
		
		JLabel lblNewLabel = new JLabel("ore");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		animalePanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JSpinner spinnerOre = new JSpinner();
		GridBagConstraints gbc_spinnerOre = new GridBagConstraints();
		gbc_spinnerOre.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerOre.gridwidth = 2;
		gbc_spinnerOre.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerOre.gridx = 3;
		gbc_spinnerOre.gridy = 3;
		animalePanel.add(spinnerOre, gbc_spinnerOre);
		SpinnerModel model = new SpinnerNumberModel(00, 00, 8, 1);  
		spinnerOre.setModel(model);
		JFormattedTextField txt2 = ((JSpinner.NumberEditor) spinnerOre.getEditor()).getTextField();
		((NumberFormatter) txt2.getFormatter()).setAllowsInvalid(false);
		//spinnerOre.setEditor(new JSpinner.NumberEditor(spinnerOre, "HH"));

		JLabel lblMinuti = new JLabel("minuti");
		GridBagConstraints gbc_lblMinuti = new GridBagConstraints();
		gbc_lblMinuti.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinuti.gridx = 6;
		gbc_lblMinuti.gridy = 3;
		animalePanel.add(lblMinuti, gbc_lblMinuti);
		
		JSpinner spinnerMinuti = new JSpinner();
		GridBagConstraints gbc_spinnerMinuti = new GridBagConstraints();
		gbc_spinnerMinuti.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerMinuti.gridwidth = 2;
		gbc_spinnerMinuti.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerMinuti.gridx = 7;
		gbc_spinnerMinuti.gridy = 3;
		animalePanel.add(spinnerMinuti, gbc_spinnerMinuti);
		SpinnerModel modelMin = new SpinnerNumberModel(00, 00, 59, 1);
		spinnerMinuti.setModel(modelMin);
		JFormattedTextField txt3 = ((JSpinner.NumberEditor) spinnerMinuti.getEditor()).getTextField();
		((NumberFormatter) txt3.getFormatter()).setAllowsInvalid(false);
		//spinnerMinuti.setEditor(new JSpinner.NumberEditor(spinnerMinuti, "mm"));


		JLabel labelPrezzo = new JLabel("Prezzo");
		labelPrezzo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GridBagConstraints gbc_labelPrezzo = new GridBagConstraints();
		gbc_labelPrezzo.anchor = GridBagConstraints.WEST;
		gbc_labelPrezzo.insets = new Insets(0, 0, 5, 5);
		gbc_labelPrezzo.gridx = 1;
		gbc_labelPrezzo.gridy = 4;
		animalePanel.add(labelPrezzo, gbc_labelPrezzo);
		
		textFieldPrezzo = new JTextField();
		GridBagConstraints gbc_textFieldPrezzo = new GridBagConstraints();
		gbc_textFieldPrezzo.gridwidth = 7;
		gbc_textFieldPrezzo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrezzo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrezzo.gridx = 2;
		gbc_textFieldPrezzo.gridy = 4;
		animalePanel.add(textFieldPrezzo, gbc_textFieldPrezzo);
		textFieldPrezzo.setColumns(10);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		GridBagConstraints gbc_lblDescrizione = new GridBagConstraints();
		gbc_lblDescrizione.anchor = GridBagConstraints.WEST;
		gbc_lblDescrizione.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrizione.gridx = 1;
		gbc_lblDescrizione.gridy = 5;
		animalePanel.add(lblDescrizione, gbc_lblDescrizione);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 5;
		animalePanel.add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JButton btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 7;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 8;
		animalePanel.add(btnSave, gbc_btnNewButton);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checker.checkNotEmpty(textFieldPrezzo.getText())
					&& checker.checkFloatFieldsValue(textFieldPrezzo.getText())
						&& checker.checkNotEmpty(textFieldNome.getText())
							&& checker.checkNotEmpty(txt3.toString())
								&& checker.checkNotEmpty(txt2.toString())
								&& comboBoxTipo.getSelectedIndex() != -1
									&& checker.checkNotEmpty(textArea.getText())) {
					Prestazione prestazione = new PrestazioneImpl();
					prestazione.setDescrizione(textArea.getText());
					
					StringBuilder sb = new StringBuilder();
					sb.append("0");
					sb.append(txt2.getText() + ":");
					if(txt3.getText().length() == 1) {
						sb.append("0" + txt3.getText() + ":00");
					} else {
						sb.append(txt3.getText() + ":00");
					}
					
					prestazione.setDurataMedia(sb.toString());
					prestazione.setNomePrestazione(textFieldNome.getText());
					prestazione.setPrezzo(Float.valueOf(textFieldPrezzo.getText()));
					prestazione.setTipoPrestazione(comboBoxTipo.getSelectedItem().toString());
					prestazioneController.addPrestazione(prestazione);
					frame.getContentPane().add(new GestioneUtilizzi(frame, user));
				}
				
			}
		});
		
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
		this.initCommonView(frame, user);
	   frame.getContentPane().setLayout(groupLayout);
	}
}
