package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;
import javax.swing.border.EmptyBorder;

public class OptionPeriodo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JDateChooser dateChooserDa;
	private JDateChooser dateChooserA;
	private SimpleDateFormat df;

	public OptionPeriodo() {
		setLayout(new BorderLayout(0, 0));
		df = new SimpleDateFormat("yyyy-MM-dd");
		JLabel lblTitle = new JLabel("Seleziona un periodo:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(50, 0, 50, 0));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Da");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		 dateChooserDa = new JDateChooser();
		 dateChooserDa.setDateFormatString("yyyy-MM-dd");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 0;
		panel.add(dateChooserDa, gbc_dateChooser);
		
		JLabel lblNewLabel_1 = new JLabel("a");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		dateChooserA= new JDateChooser();
		dateChooserA.setDateFormatString("yyyy-MM-dd");
		GridBagConstraints gbc_dateChooser_1 = new GridBagConstraints();
		gbc_dateChooser_1.fill = GridBagConstraints.BOTH;
		gbc_dateChooser_1.gridx = 2;
		gbc_dateChooser_1.gridy = 1;
		panel.add(dateChooserA, gbc_dateChooser_1);

	}

	public String getDataInizio() {
		return df.format(dateChooserDa.getDate());
	}
	public String getDataFine() {
		return df.format(dateChooserA.getDate());
	}
	
	
	
}
