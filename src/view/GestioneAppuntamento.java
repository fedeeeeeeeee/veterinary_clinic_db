package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import model.Appuntamento;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class GestioneAppuntamento extends JPanel {

	private static final long serialVersionUID = -83378890947911750L;
	private JFrame frame;
	private PrestazioneController prestazioneController;
	private JTable table;

	public GestioneAppuntamento(final JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.prestazioneController = new PrestazioneControllerImpl();
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();

		// per la visualizzazione
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		// utile per il design
		// GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(24)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE).addGap(44)
						.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE).addGap(84)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE).addGap(0)));
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		centerPanel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		List<Appuntamento> listPrestazioni = prestazioneController.getAllPrestazioni();
		DefaultTableModel model = new DefaultTableModel();
		
		listPrestazioni.forEach(x->{
			model.addRow(new Object[] {
					x.getNomeAnimale(),
					x.getData(),
					x.getOra(),
					x.getPrestazione(),
					x.getNomeVeterinario(),
					x.getCognomeVeterinario()
					
			}
					);
		});
	
		/*
	
		table.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Animale", "Data", "Ora", "Prestazione", "Nome", "Cognome"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		*/
		scrollPane.setViewportView(table);

		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new MainPage(frame));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);

		// importante per il design
		//setLayout(groupLayout);

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout =
	 new GroupLayout(this);
	frame.getContentPane().setLayout(groupLayout);
	}
}
