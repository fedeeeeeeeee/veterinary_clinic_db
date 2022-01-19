package view;

import java.util.List;

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
import model.Paziente;

public class PatologiePazientiPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private AnimaleController pazienteController;
	private List<Paziente> listPazienti;
	

	public PatologiePazientiPanel(Paziente paziente) {
		setLayout(new BorderLayout(0, 0));
		JPanel upperPanel = new JPanel();
		add(upperPanel, BorderLayout.NORTH);
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		
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
		this.initTable();
		this.listPazienti = pazienteController.getAllPatologiePaziente(paziente);
		this.createTable(model, listPazienti);
		scrollPane.setViewportView(table);
		
	}
	private void resetAndDraw(List<Paziente> allAnimali) {
		this.listPazienti.clear();
		this.listPazienti = allAnimali;
		//table.clearSelection();
		model.setRowCount(0);
		this.createTable(model, listPazienti);
	}

	private void createTable(DefaultTableModel model, List<Paziente> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { 
					/*
					x.getAnimale().getRazza(), 
					x.getAnimale().getProprietario().getNome() + " "+ x.getAnimale().getProprietario().getCognome(),*/
				 // voglaimo aggiungere Ricoverato si e no ? 
					});
		});
	}

	private void initTable() {
		this.table = new JTable();
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Patologia");
		model.addColumn("Tipo");
		model.addColumn("descrizione");
		model.addColumn("dataInizio");
		model.addColumn("dataFine");
		
	}
	

}
