package view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import controller.AnimaleController;
import controller.AnimaleControllerImpl;
import model.Farmaco;
import model.Patologia;
import model.Paziente;

public class PatologiePazientiPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private AnimaleController pazienteController;
	private List<Patologia> listPatologie;

	public PatologiePazientiPanel(Paziente paziente) {
		setLayout(new BorderLayout(0, 0));
		JPanel upperPanel = new JPanel();
		add(upperPanel, BorderLayout.NORTH);
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);

		this.listPatologie = new ArrayList<>();
		pazienteController = new AnimaleControllerImpl();

		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_centerPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		mainPanel.setLayout(gbl_centerPanel);

		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		mainPanel.add(scrollPane, gbc_scrollPane);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuMostra = new JMenu("Mostra");
		upperPanel.add(menuBar, "1, 1, fill, top");
		menuBar.add(menuMostra);
		JCheckBoxMenuItem itemPatologie = new JCheckBoxMenuItem("Patologie");
		itemPatologie.setSelected(true);
		JCheckBoxMenuItem itemAllergie = new JCheckBoxMenuItem("Allergie");
		itemAllergie.setSelected(true);
		/*
		itemPatologie.addActionListener((e) -> { // prova a togliere
		    	  if((itemPatologie.isSelected() && itemAllergie.isSelected()) || ( !itemPatologie.isSelected() && !itemAllergie.isSelected())) {
		    		  List<Patologia> list = this.itemPatologie.stream().collect(Collectors.toList());
		    		   this.resetAndDraw(list);
		    	   } else {
		    		   if(itemPatologie.isSelected()) {
		    			  
		    			   List<Patologia> list = this.itemPatologie;
		    		    	  List<Patologia> filtrato =  list.stream().filter(farmaco -> "patologia".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
		    		    	  model.setRowCount(0); // non ho usato la funziona per mantere traccia della richiesta del menu show 
		    		      	   this.createTable(model, filtrato);	
		    		      	 scrollPane.setViewportView(table);
		    		   }else {
		    			 List<Patologia> list = this.itemPatologie.stream().collect(Collectors.toList());
		    			   //List<Farmaco> list = this.listFarmaci;
		    		    	  List<Patologia> filtrato =  list.stream().filter(farmaco -> "allergia".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
		    		    	  model.setRowCount(0); // non ho usato la funziona per mantere traccia della richiesta del menu show 
		    		      	   this.createTable(model, filtrato);	
		    		      	 scrollPane.setViewportView(table);
		    		   }
		    	   } 
		       });
		itemAllergie.addActionListener((e) -> {
		    	  if((itemPatologie.isSelected() && itemAllergie.isSelected()) || ( !itemPatologie.isSelected() && !itemAllergie.isSelected())) {
		    		  List<Patologia> list = this.itemPatologie.stream().collect(Collectors.toList());
		    		   this.resetAndDraw(list);
		    	   } else {
		    		   if(itemPatologie.isSelected()) {
		    			   List<Patologia> list = this.itemPatologie.stream().collect(Collectors.toList());
		    		    	  List<Patologia> filtrato =  list.stream().filter(farmaco -> "patologia".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
		    		    	  model.setRowCount(0); // non ho usato la funziona per mantere traccia della richiesta del menu show 
		    		      	   this.createTable(model, filtrato);	
		    		      	 scrollPane.setViewportView(table);
		    		   }else {
		    			   List<Patologia> list = this.itemPatologie.stream().collect(Collectors.toList());
		    		    	  List<Patologia> filtrato =  list.stream().filter(farmaco -> "allergia".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
		    		    	   model.setRowCount(0); // non ho usato la funziona per mantere traccia della richiesta del menu show 
		    		      	   this.createTable(model, filtrato);	
		    		      	 scrollPane.setViewportView(table);
		    		   }
		    	   } 
		       });
		       */
		menuMostra.add(itemPatologie);
		menuMostra.add(itemAllergie);

		
		
		
		this.initTable();
		this.listPatologie = pazienteController.getAllPatologiePaziente(paziente);
		this.createTable(model, listPatologie);
		scrollPane.setViewportView(table);

	}

	private void resetAndDraw(List<Patologia> allPatologie) {
		this.listPatologie.clear();
		this.listPatologie = allPatologie;
		model.setRowCount(0);
		this.createTable(model, listPatologie);
	}

	private void createTable(DefaultTableModel model, List<Patologia> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { x.getNomePatologia(), x.getDescrizione(), x.getDataInizio(), x.getDataFine(),
					x.getTipoPatologia() });
		});
	}

	private void initTable() {
		this.table = new JTable();
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Patologia");
		model.addColumn("descrizione");
		model.addColumn("dataInizio");
		model.addColumn("dataFine");
		model.addColumn("Tipo");

	}

}
