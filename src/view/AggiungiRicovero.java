package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import controller.RicoveroController;
import controller.RicoveroControllerImpl;
import model.Appuntamento;
import model.ParametriPaziente;
import model.Person;
import model.Ricovero;
import model.User;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AggiungiRicovero extends CommonView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private RicoveroController ricoveroController;
	private DefaultTableModel model;
	private JTable table;
	private List<Appuntamento> listAppuntamenti;
	private PrestazioneController prestazioneController = new PrestazioneControllerImpl();
	
	public AggiungiRicovero(JFrame frame, User user, Ricovero ricovero) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.ricoveroController = new RicoveroControllerImpl();
		this.prestazioneController = new PrestazioneControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel savePanel = new JPanel();
		
	GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	     //GroupLayout groupLayout = new GroupLayout(this);
	        groupLayout.setHorizontalGroup(
	        	groupLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
	        				.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 985,  Short.MAX_VALUE)
	        				.addComponent(savePanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
	        				.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
	        			.addContainerGap())
	        );
	        groupLayout.setVerticalGroup(
	        	groupLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(savePanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap())
	        );
	        GridBagLayout gbl_centerPanel = new GridBagLayout();
	        gbl_centerPanel.columnWidths = new int[]{0, 0};
	        gbl_centerPanel.rowHeights = new int[]{0, 0, 0};
	        gbl_centerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	        gbl_centerPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	        centerPanel.setLayout(gbl_centerPanel);
	        
	        JLabel lblNewLabel = new JLabel("Seleziona l'animale da ricoverare e completa la form");
	        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
	        gbc_lblNewLabel.gridx = 0;
	        gbc_lblNewLabel.gridy = 0;
	        centerPanel.add(lblNewLabel, gbc_lblNewLabel);
	        
	        JPanel panelToSplit = new JPanel();
	        panelToSplit.setLayout(new BorderLayout());
	        GridBagConstraints gbc_panelToSplit = new GridBagConstraints();
	        gbc_panelToSplit.fill = GridBagConstraints.BOTH;
	        gbc_panelToSplit.gridx = 0;
	        gbc_panelToSplit.gridy = 1;
	        centerPanel.add(panelToSplit, gbc_panelToSplit);
	        
	        JPanel panelLeft = new JPanel();
			JPanel panelRight = new JPanel();
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
	        panelToSplit.add(splitPane);

			JScrollPane scrollPaneLeft = new JScrollPane();
			panelLeft.add(scrollPaneLeft, BorderLayout.CENTER);
			
			this.initTable();
			this.listAppuntamenti = this.prestazioneController.getAllPrestazioniByPersonale(user.getCF());
			this.createTable(model, listAppuntamenti);
			this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent event) {
					   int rowIndex = table.getSelectedRow();
					   // TODO completamento campi
					  // Person proprietario = proprietarioController.getProprietario(listAppuntamenti.get(rowIndex).getAnimale().getCf());
					//   listAppuntamenti.get(rowIndex).getAnimale().setProprietario(proprietario);
				}
			});
			scrollPaneLeft.setViewportView(table);
			
			
	        JButton btnSave = new JButton("Salva");
	        savePanel.add(btnSave);
	        
	    	/*LOWER PANEL */
			JButton sendButton = new JButton("BACK");
			sendButton.setHorizontalAlignment(SwingConstants.LEFT);
			sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			sendButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 frame.getContentPane().add(new HomeVeterinario(frame, user));
				}
			});
			lowerPanel.setLayout(new BorderLayout(0, 0));
			lowerPanel.add(sendButton, BorderLayout.WEST);
	        upperPanel.setLayout(new GridLayout(0, 1, 0, 0));
	  		JLabel titleLabel = new JLabel("Nuovo Ricovero");
	  		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	  		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
	  		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
			upperPanel.add(titleLabel);
			
	    	//setLayout(groupLayout);
			//new GroupLayout(this);
			frame.getContentPane().setLayout(groupLayout);
			this.initCommonView(frame, user);
	}

	private void createTable(DefaultTableModel model, List<Appuntamento> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { 
					x.getAnimale().getNome(),
					x.getAnimale().getSpecie(),
					x.getAnimale().getAltezza(),
					x.getPrestazione()
			});
		});
	}

	private void initTable() {
		this.table = new JTable();
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Nome");
		model.addColumn("Specie");
		model.addColumn("Altezza");
		model.addColumn("Prestazione");
	}
	 
}
