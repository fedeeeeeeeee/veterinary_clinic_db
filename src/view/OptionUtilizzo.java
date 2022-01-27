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

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import controller.StrumentiController;
import controller.StrumentiControllerImpl;
import model.Strumento;
import model.StrumentoImpl;
import model.Utilizzo;

import javax.swing.JSpinner;

public class OptionUtilizzo extends JPanel {

	private static final long serialVersionUID = 7548382548132424662L;
	private Strumento selectedStrumento = new StrumentoImpl();
	private StrumentiController controller = new StrumentiControllerImpl();
	private JSpinner spinnerQuantita;
	private JFormattedTextField qt;
	
	public OptionUtilizzo(int codPrestazioneSelezionata, List<Utilizzo> listUtilizziGiaPresenti) {
		setLayout(new BorderLayout(0, 0));
		JLabel lblTitle = new JLabel("Aggiungi Strumento ");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(50, 0, 50, 0));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_2 = new JLabel("Aggiungere uno strumento per la prestazione slezionata");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 0;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("Strumento");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JComboBox<String> comboBoxStrumento = new JComboBox<>();
		GridBagConstraints gbc_comboBoxStrumento = new GridBagConstraints();
		gbc_comboBoxStrumento.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxStrumento.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStrumento.gridx = 3;
		gbc_comboBoxStrumento.gridy = 2;
		panel.add(comboBoxStrumento, gbc_comboBoxStrumento);

		List<Strumento> list = controller.getAllStrumenti();

		list.forEach(u -> {
			if(!listUtilizziGiaPresenti.stream().map(x->x.getStrumento().getNome()).collect(Collectors.toList()).contains(u.getNome())) {
				comboBoxStrumento.addItem(u.getNome());
			}
		});

		comboBoxStrumento.setSelectedIndex(-1);
		
		JLabel lblNewLabel = new JLabel("Quantità");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		spinnerQuantita = new JSpinner();
		GridBagConstraints gbc_spinnerQuantita = new GridBagConstraints();
		gbc_spinnerQuantita.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerQuantita.gridx = 3;
		gbc_spinnerQuantita.gridy = 3;
		panel.add(spinnerQuantita, gbc_spinnerQuantita);
		
		SpinnerModel model = new SpinnerNumberModel(00, 00, 50, 1);  
		spinnerQuantita.setModel(model);
		qt = ((JSpinner.NumberEditor) spinnerQuantita.getEditor()).getTextField();
		((NumberFormatter) qt.getFormatter()).setAllowsInvalid(false);
		
		comboBoxStrumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedStrumento = list.get(comboBoxStrumento.getSelectedIndex());
			}
		});
	}

	public Strumento getSelectedStrumento() {
		return this.selectedStrumento;
	}

	public int getQt() {
		return Integer.valueOf(qt.getText());
	}
}
