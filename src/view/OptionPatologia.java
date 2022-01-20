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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import model.Patologia;
import model.PatologiaImpl;

import javax.swing.JComboBox;

public class OptionPatologia extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2403586412054919423L;
	private SimpleDateFormat df;
	private PrestazioneController controller = new PrestazioneControllerImpl();
	private Patologia selectedPatologia = new PatologiaImpl();
	
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
		
		JComboBox<String> comboBoxPatologia = new JComboBox<>();
		GridBagConstraints gbc_comboBoxPatologia = new GridBagConstraints();
		gbc_comboBoxPatologia.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPatologia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPatologia.gridx = 3;
		gbc_comboBoxPatologia.gridy = 1;
		panel.add(comboBoxPatologia, gbc_comboBoxPatologia);
		
		List<Patologia> list = controller.getAllPatologie();
		list.forEach(u-> {
			comboBoxPatologia.addItem(u.getNomePatologia());
		});
		
		comboBoxPatologia.setSelectedIndex(-1);
		comboBoxPatologia.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	selectedPatologia = list.get(comboBoxPatologia.getSelectedIndex());
		    	selectedPatologia.setDataInizio(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		    }
		});
	}
	
	public Patologia getSelectedPatologia() {
		return this.selectedPatologia;
		
	}
	
}
