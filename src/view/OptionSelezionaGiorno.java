package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;
import javax.swing.border.EmptyBorder;

public class OptionSelezionaGiorno extends JPanel {

	private static final long serialVersionUID = 1L;
	private JDateChooser dateChooserDa;
	private SimpleDateFormat df;

	public OptionSelezionaGiorno() {
		setLayout(new BorderLayout(0, 0));
		df = new SimpleDateFormat("yyyy-MM-dd");
		JLabel lblTitle = new JLabel("Visualizza appuntamenti in data:");
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

		
		 dateChooserDa = new JDateChooser();
		 dateChooserDa.setDateFormatString("yyyy-MM-dd");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 0;
		panel.add(dateChooserDa, gbc_dateChooser);
	}

	public String getGiorno() {
		return df.format(dateChooserDa.getDate());
	}	
	
}
