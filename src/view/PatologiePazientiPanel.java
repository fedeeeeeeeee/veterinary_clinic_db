package view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
	JCheckBoxMenuItem itemGuarito;
	JMenuItem itemTutte;
	
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
		this.itemGuarito = new JCheckBoxMenuItem("Guarito");
		this.itemTutte = new JMenuItem("Tutte");
		itemGuarito.addActionListener((e) -> {
	    		   if(itemGuarito.isSelected()) {
	    			   listPatologie = pazienteController.getAllPatologiePaziente(paziente);
	    			   List<Patologia> list = this.listPatologie.stream().collect(Collectors.toList());
	    		    	  List<Patologia> filtrato =  list.stream().filter(patologia -> !patologia.getDataFine().isBlank()).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }else {
	    			   listPatologie = pazienteController.getAllPatologiePaziente(paziente);
	    			   List<Patologia> list = this.listPatologie.stream().collect(Collectors.toList());
	    		    	  List<Patologia> filtrato =  list.stream().filter(patologia ->patologia.getDataFine().isBlank()).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);

	    		   }
	       });
		itemTutte.addActionListener((e) -> {
 			   listPatologie = pazienteController.getAllPatologiePaziente(paziente);
 			  model.setRowCount(0);
 			 this.createTable(model, listPatologie);	
	      	 scrollPane.setViewportView(table);
 			   itemGuarito.setSelected(false);
    });
		menuMostra.add(itemTutte);
		menuMostra.add(itemGuarito);
		
		
		
		
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
			model.addRow(new Object[] { x.getNomePatologia(), x.getDataInizio(), x.getDataFine(),
					x.getTipoPatologia() });
		});
	}

	private void initTable() {
		this.table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Patologia");
		model.addColumn("Data Inizio");
		model.addColumn("Data Fine");
		model.addColumn("Tipo");

	}
}
