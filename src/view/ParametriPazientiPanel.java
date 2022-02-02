package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.ParametriPaziente;
import model.Paziente;
import utility.Pair;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import controller.AnimaleController;
import controller.AnimaleControllerImpl;

public class ParametriPazientiPanel extends JPanel {

	private static final long serialVersionUID = -63173461344650150L;
	private DefaultTableModel model;
	private JTable table;
	private AnimaleController pazienteController = new AnimaleControllerImpl();
	private List<ParametriPaziente> listParametri = new ArrayList<>();
	private Paziente paziente;
	
	public ParametriPazientiPanel(Paziente paziente) {
		
		this.paziente =paziente;
		setLayout(new BorderLayout(0, 0));
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout());
		
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

		XYDataset ds = createDataset();
		JFreeChart chart = createChart(ds);
		ChartPanel cp = new ChartPanel(chart);
		chart.getPlot().setBackgroundPaint(new  Color(161, 192, 200, 40)  );
		panelRight.add(cp);		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
		splitPane.setResizeWeight(0.5);
		mainPanel.add(splitPane, BorderLayout.CENTER);
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
		table.getTableHeader().setForeground(Color.decode("#56728f"));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Data");
		model.addColumn("Peso");
		model.addColumn("Altezza");
	}
	 
	private XYDataset createDataset() {
		List<ParametriPaziente> listParametri = pazienteController.getAllParametriPaziente(paziente);
		List<Pair<Float, Float>> pesiAltezze = new ArrayList<>();
		listParametri.forEach(parametro-> {
			pesiAltezze.add(new Pair<>(parametro.getPeso(), parametro.getAltezza()));
		});
		 var series = new XYSeries("peso-altezza");
		 for(Pair<Float, Float> pair : pesiAltezze) {
			 series.add(pair.getX(), pair.getY());
		 }
		  var dataset = new XYSeriesCollection();
	        dataset.addSeries(series);
	        
	        return dataset;
	}
	   private JFreeChart createChart(XYDataset dataset) {

	        JFreeChart chart = ChartFactory.createXYLineChart(
	                "Evoluzione",
	                "Peso",
	                "Altezza",
	                dataset,
	                PlotOrientation.VERTICAL,
	                true,
	                true,
	                false
	        );

	        XYPlot plot = chart.getXYPlot();

	        var renderer = new XYLineAndShapeRenderer();
	        renderer.setSeriesPaint(0, new Color(86, 114, 143));
	      //  renderer.setSeriesStroke(0, new BasicStroke(2.0f));

	        plot.setRenderer(renderer);
	        plot.setBackgroundPaint(Color.white);

	        plot.setRangeGridlinesVisible(true);
	        plot.setRangeGridlinePaint(Color.BLACK);

	        plot.setDomainGridlinesVisible(true);
	        plot.setDomainGridlinePaint(Color.BLACK);

	        chart.getLegend().setFrame(BlockBorder.NONE);

	        chart.setTitle(new TextTitle("Evoluzione",
	                        new Font("Serif", java.awt.Font.BOLD, 18)
	                )
	        );

	        return chart;
	    }
	
}
