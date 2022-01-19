package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class OptionPatologia extends JPanel {
	private SimpleDateFormat df;
	
	
	public OptionPatologia() {
		setLayout(new BorderLayout(0, 0));
		df = new SimpleDateFormat("yyyy-MM-dd");
		JLabel lblTitle = new JLabel("Inserisci una patologia");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(50, 0, 50, 0));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		 
		 JLabel lblNome = new JLabel("Nome Patologia");
		 GridBagConstraints gbc_lblNome = new GridBagConstraints();
		 gbc_lblNome.anchor = GridBagConstraints.NORTHWEST;
		 gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		 gbc_lblNome.gridx = 1;
		 gbc_lblNome.gridy = 1;
		 panel.add(lblNome, gbc_lblNome);
		
		JComboBox comboBoxPatologia = new JComboBox();
		GridBagConstraints gbc_comboBoxPatologia = new GridBagConstraints();
		gbc_comboBoxPatologia.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPatologia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPatologia.gridx = 3;
		gbc_comboBoxPatologia.gridy = 1;
		panel.add(comboBoxPatologia, gbc_comboBoxPatologia);
		

	}
	
}
