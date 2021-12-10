package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import model.Appuntamento;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Component;
import com.toedter.calendar.JDateChooser;

public class GestioneAppuntamento extends CommonView {

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
		//GroupLayout groupLayout = new GroupLayout(this);
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
		upperPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Appointment");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel menu = new JPanel();
		upperPanel.add(menu, BorderLayout.SOUTH);
		
		JButton todayButton = new JButton("Today");
		todayButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		todayButton.setHorizontalAlignment(SwingConstants.LEFT);
		todayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("All");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		menu.add(btnNewButton_1);
		menu.add(todayButton);
		
		JButton btnNewButton = new JButton("This week");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		menu.add(btnNewButton);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setSize(20, 20);
		BorderLayout borderLayout = (BorderLayout) dateChooser.getLayout();
		menu.add(dateChooser);
		
		JPanel panelUtente = new JPanel();
		upperPanel.add(panelUtente, BorderLayout.NORTH);
		panelUtente.setLayout(new BoxLayout(panelUtente, BoxLayout.X_AXIS));
		
		JLabel lblUtente = new JLabel("Utente");
		lblUtente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUtente.setHorizontalAlignment(SwingConstants.LEFT);
		panelUtente.add(lblUtente);
		
		JLabel lblRuolo = new JLabel("Ruolo");
		lblRuolo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRuolo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblRuolo.setVerticalAlignment(SwingConstants.TOP);
		lblRuolo.setHorizontalAlignment(SwingConstants.LEFT);
		panelUtente.add(lblRuolo);
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
		DefaultTableModel model =  (DefaultTableModel) table.getModel();
		
		
		model.addColumn("Animale");
		model.addColumn("Data");
		model.addColumn("Ora");
		model.addColumn("Prestazione");
		model.addColumn("Nome");
		model.addColumn("Cognome");
		model.addColumn("Modifica");

		
		JButton button = new JButton();
		//button.addActionListener(showOrder);
	    table.getColumn("Modifica").setCellRenderer(new ButtonRenderer());
	    table.getColumn("Modifica").setCellEditor(new ButtonEditor(new JCheckBox(), button));
	    
	    /*
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        int firstIndex = e.getFirstIndex();
		        int lastIndex = e.getLastIndex();
		        System.out.println("first" + firstIndex);
			}
	    });
	    */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				        public void valueChanged(ListSelectionEvent event) {
				        	ListSelectionModel lsm = (ListSelectionModel)event.getSource();
					        int firstIndex = event.getFirstIndex();
					      
				            //System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
					        //System.out.println("index" + firstIndex);
					        frame.getContentPane().add(new NuovoAppuntamento(frame));
					        listPrestazioni.get(firstIndex); // tipo Appuntamento
					       // JOptionPane.showMessageDialog(null, "Do you want to modify  line" + firstIndex + "?");
					      //  System.out.println("index" + firstIndex);
				        }
				    });
				
			}
		});
		
		
		listPrestazioni.forEach(x -> {
			model.addRow(new Object[] { 
					x.getNomeAnimale(), 
					x.getData(), 
					x.getOra(), 
					x.getPrestazione(),
					x.getNomeVeterinario(), 
					x.getCognomeVeterinario(),
			});
		});
		
		//table.setModel(model);
		scrollPane.setViewportView(table);

		JButton backButton = new JButton("BACK");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
	this.initCommonView(frame);
	}
}
