package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import model.Animale;
import model.Appuntamento;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class PrestazioniDiAnimalePanel extends JPanel {

	private static final long serialVersionUID = -212868936873515141L;
	private Animale animale;
	private List<Appuntamento> listToDraw = new ArrayList<>();
	private JTable table;
	private DefaultTableModel model;
	private PrestazioneController controller;
	private int selectedAppuntamento = -1;
	private JTextArea textAreaReferto;
	
	private void initTable () {
		this.table = new JTable(); 
		this.model =  (DefaultTableModel) table.getModel();
		model.addColumn("Data");
		model.addColumn("Nome Prestazione");
		model.addColumn("Tipo Prestazione");
		model.addColumn("Farmaci prescritti");
		model.addColumn("Patologie Diagnosticate");
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				selectedAppuntamento = table.getSelectedRow();
				if (selectedAppuntamento != -1) {
				    textAreaReferto.setText(listToDraw.get(selectedAppuntamento).getDescrizioneReferto());
				}
			}
		});
	}
	
	private void createTable(DefaultTableModel model, List<Appuntamento> list) {		
		list.forEach(x -> {
			model.addRow(new Object[] { 
				x.getData(),
				x.getPrestazione(),
				x.getTipoPrestazione(),
				String.join(", ", x.getListFarmaci()),
				String.join(", ", x.getListPatologie()),
			});
		});
		
	}
	
	public PrestazioniDiAnimalePanel(Animale animale) {
		this.animale = animale;
		this.controller = new PrestazioneControllerImpl();
	
		JPanel centerPanel = new JPanel();

		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0, 271, 21, 0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{40, 53, 0, 0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		centerPanel.add(scrollPane, gbc_scrollPane);

		this.initTable();	
		this.listToDraw = this.controller.getPrestazioniByAnimale(animale.getCodAnimale());		
		if (listToDraw.size() > 0) {
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
	    
	    JLabel titleRefertoLabel = new JLabel("Referto:");
	    GridBagConstraints gbc_titleRefertoLabel = new GridBagConstraints();
	    gbc_titleRefertoLabel.gridwidth = 2;
	    gbc_titleRefertoLabel.insets = new Insets(0, 0, 5, 5);
	    gbc_titleRefertoLabel.gridx = 2;
	    gbc_titleRefertoLabel.gridy = 4;
	    centerPanel.add(titleRefertoLabel, gbc_titleRefertoLabel);
	    
	    textAreaReferto = new JTextArea();
	    GridBagConstraints gbc_textAreaReferto = new GridBagConstraints();
	    gbc_textAreaReferto.insets = new Insets(0, 0, 5, 5);
	    gbc_textAreaReferto.fill = GridBagConstraints.BOTH;
	    gbc_textAreaReferto.gridx = 3;
	    gbc_textAreaReferto.gridy = 5;
	    centerPanel.add(textAreaReferto, gbc_textAreaReferto);
	    textAreaReferto.setEditable(false);
	}

}
