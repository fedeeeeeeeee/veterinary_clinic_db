package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.FarmaciController;
import controller.FarmaciControllerImpl;
import model.Animale;
import model.FarmacoInPrestazione;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Font;

public class FarmaciDiPazientePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2466329667594691989L;
	private Animale animale;
	private FarmaciController controller;
	private List<FarmacoInPrestazione> listToDraw = new ArrayList<>();
	private JTable table;
	private DefaultTableModel model;

	private void initTable () {
		this.table = new JTable(); 
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		this.model =  (DefaultTableModel) table.getModel();
		model.addColumn("Nome Farmaco");
		model.addColumn("Tipo Farmaco");
		model.addColumn("Inidicazioni");
		model.addColumn("Data inizio assunzione");
		model.addColumn("Data fine assunzione");
	}
	
	private void createTable(DefaultTableModel model, List<FarmacoInPrestazione> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { 
					x.getFarmaco().getNomeFarmaco(),
					x.getFarmaco().getTipoFarmaco(),
					x.getIndicazioni(),
					x.getDataInizioSomministrazione(),
					x.getDataFineSomministrazione(),

			});
		});
	}
	
	public FarmaciDiPazientePanel(Animale animale) {
		this.animale = animale;
		this.controller = new FarmaciControllerImpl();
	
		JPanel centerPanel = new JPanel();

		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{40, 53, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		centerPanel.add(scrollPane, gbc_scrollPane);

		this.initTable();	
		this.listToDraw = this.controller.getFarmaciDiAnimale(animale.getCodAnimale());		
	    if(listToDraw.size() > 0) {
			this.createTable(model, listToDraw);
	    }
	    
	    setLayout(new BorderLayout(0, 0));
	    GridBagConstraints gbc_table = new GridBagConstraints();
	    gbc_table.insets = new Insets(0, 0, 5, 5);
	    gbc_table.gridy = 1;
	    gbc_table.gridwidth = 3;
	    gbc_table.gridx = 1;
	    
		scrollPane.setViewportView(table);
	    this.add(centerPanel);
	}

}
