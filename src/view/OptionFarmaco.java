package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import controller.FarmaciController;
import controller.FarmaciControllerImpl;
import model.Animale;
import model.Farmaco;
import model.FarmacoImpl;
import model.Patologia;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;


public class OptionFarmaco extends JPanel {

	private static final long serialVersionUID = 7548382548132424662L;
	private JDateChooser dateChooserDa;
	private SimpleDateFormat df;
	private JTextArea textAreaIndicazioni;
	private Farmaco selectedFarmaco = new FarmacoImpl();
	private FarmaciController controller = new FarmaciControllerImpl();
	private JCheckBox checkBoxSomministrato;

	public OptionFarmaco(Animale animale) {
		setLayout(new BorderLayout(0, 0));
		df = new SimpleDateFormat("yyyy-MM-dd");
		JLabel lblTitle = new JLabel("Inserisci un farmaco");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(50, 0, 50, 0));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_2 = new JLabel("Il paziente sta assumendo i seguenti farmaci:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 0;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JTextArea textAreaFarmaciAssunti = new JTextArea();
		textAreaFarmaciAssunti.setEditable(false);
		GridBagConstraints gbc_textAreaFarmaciAssunti = new GridBagConstraints();
		gbc_textAreaFarmaciAssunti.gridwidth = 3;
		gbc_textAreaFarmaciAssunti.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaFarmaciAssunti.fill = GridBagConstraints.BOTH;
		gbc_textAreaFarmaciAssunti.gridx = 1;
		gbc_textAreaFarmaciAssunti.gridy = 1;
		panel.add(textAreaFarmaciAssunti, gbc_textAreaFarmaciAssunti);
		
		List<String> farmaciAssunti = controller.getFarmaciAssuntiByAnimale(animale.getCodAnimale());
		StringBuilder sb = new StringBuilder();
		farmaciAssunti.forEach(f-> {
			sb.append(f + ",");
		});
		sb.deleteCharAt(sb.length()-1);
		textAreaFarmaciAssunti.setText(sb.toString());
				
		JLabel lblNewLabel_1 = new JLabel("Nome farmaco");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JComboBox<String> comboBoxFarmaco = new JComboBox<>();
		GridBagConstraints gbc_comboBoxFarmaco = new GridBagConstraints();
		gbc_comboBoxFarmaco.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFarmaco.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFarmaco.gridx = 3;
		gbc_comboBoxFarmaco.gridy = 2;
		panel.add(comboBoxFarmaco, gbc_comboBoxFarmaco);
		
		JLabel lblNewLabel = new JLabel("Data fine somministrazione");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		 dateChooserDa = new JDateChooser();
		 dateChooserDa.setDateFormatString("yyyy-MM-dd");
		 GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		 gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		 gbc_dateChooser.fill = GridBagConstraints.BOTH;
		 gbc_dateChooser.gridx = 3;
		 gbc_dateChooser.gridy = 3;
		 panel.add(dateChooserDa, gbc_dateChooser);
		
		JLabel lblTitolo = new JLabel("Indicazioni");
		GridBagConstraints gbc_lblTitolo = new GridBagConstraints();
		gbc_lblTitolo.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTitolo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitolo.gridx = 1;
		gbc_lblTitolo.gridy = 4;
		panel.add(lblTitolo, gbc_lblTitolo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 4;
		panel.add(scrollPane, gbc_scrollPane);
		
		this.textAreaIndicazioni = new JTextArea();
		textAreaIndicazioni.setLineWrap(true);
		textAreaIndicazioni.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaIndicazioni);

		this.checkBoxSomministrato = new JCheckBox("Somministrazione durante la seduta");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.gridwidth = 3;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 7;
		panel.add(checkBoxSomministrato, gbc_chckbxNewCheckBox);
		
		List<Farmaco> list = controller.getAllDistinctFarmaci();
		list.forEach(u-> {
			comboBoxFarmaco.addItem(u.getNomeFarmaco());
		});
		
		comboBoxFarmaco.setSelectedIndex(-1);
		comboBoxFarmaco.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	selectedFarmaco = list.get(comboBoxFarmaco.getSelectedIndex());
		    	if(list.get(comboBoxFarmaco.getSelectedIndex()).getDisponibilita() == 0) {
		    		checkBoxSomministrato.setEnabled(false);
		    		checkBoxSomministrato.setSelected(false);
		    	} else {
		    		checkBoxSomministrato.setEnabled(true);
		    	}
		    }
		});

	}
	
	public Farmaco getSelectedFarmaco() {
		selectedFarmaco.setIndicazioni(textAreaIndicazioni.getText());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(dateChooserDa.getDate());
		selectedFarmaco.setDataInizioSomministrazione(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		selectedFarmaco.setDataFineSomministrazione(date);
		selectedFarmaco.setSomministrato(checkBoxSomministrato.isSelected());
		return this.selectedFarmaco;
	}
}
