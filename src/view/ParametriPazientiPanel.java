package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ParametriPaziente;
import model.Patologia;
import model.Paziente;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.AnimaleController;
import controller.AnimaleControllerImpl;

public class ParametriPazientiPanel extends JPanel {
	
	private DefaultTableModel model;
	private JTable table;
	private AnimaleController pazienteController = new AnimaleControllerImpl();
	private List<ParametriPaziente> listParametri = new ArrayList<>();
	
	public ParametriPazientiPanel(Paziente paziente) {
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());
		
		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		panelRight.setLayout(new BorderLayout(0, 0));
		panelLeft.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPaneLeft = new JScrollPane();
		
		this.initTable();
		this.listParametri = pazienteController.getAllParametriPaziente(paziente);
		this.createTable(model, listParametri);
		scrollPaneLeft.setViewportView(table);
		
		panelLeft.add(scrollPaneLeft, BorderLayout.CENTER);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
		splitPane.setResizeWeight(0.5);
		middlePanel.add(splitPane, BorderLayout.CENTER);
	}

	private void resetAndDraw(List<ParametriPaziente> allPatologie) {
		this.listParametri.clear();
		this.listParametri = allPatologie;
		model.setRowCount(0);
		this.createTable(model, listParametri);
	}

	private void createTable(DefaultTableModel model, List<ParametriPaziente> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { 
					x.getData(),
					x.getAltezza(),
					x.getPeso()
			});
		});
	}

	private void initTable() {
		this.table = new JTable();
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Data");
		model.addColumn("Peso");
		model.addColumn("Altezza");
	}

}
