package view;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Appuntamento;
import model.Pagamento;
import model.User;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.PagamentiController;
import controller.PagamentiControllerImpl;
import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;

import java.awt.GridBagConstraints;

public class RiepilogoPagamenti extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private List<Pagamento> listPagamenti;
	private DefaultTableModel model;
 	private PagamentiController pagamentiController;
	private JTable table;	
	private JScrollPane scrollPane;
	
	private void resetAndDraw(List<Pagamento> list) {
		this.listPagamenti.clear();
		this.listPagamenti = list;
			//table.clearSelection();
		model.setRowCount(0);
		this.createTable(model, listPagamenti);
		//scrollPane.setViewportView(table);
	}

		private void createTable(DefaultTableModel model, List<Pagamento> listPagamenti) {
			listPagamenti.forEach(x -> {
				model.addRow(new Object[] { 
						x.getAnimale().getNome(), 
						x.getAnimale().getSpecie(),
						x.getAnimale().getCf(),
						x.getDataPrestazione(),
						x.getNomePrestazione(),
						x.getCosto() + "€"
				});
			});
		}

	private void initTable() {
		this.table = new JTable();
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Animale");
		model.addColumn("Specie");
		model.addColumn("Proprietario");
		model.addColumn("Data");
		model.addColumn("Nome Prestazione");
		model.addColumn("Costo"); 
	}
	
	public RiepilogoPagamenti(final JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.pagamentiController = new PagamentiControllerImpl();
		
		JPanel upperPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel lowerPanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(24)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE).addGap(44)
						.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE).addGap(84)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE).addGap(0)));
		
		middlePanel.setLayout(new BorderLayout(0, 0));
		JPanel upperMiddlePanel = new JPanel();
		upperMiddlePanel.setBorder(new EmptyBorder(0, 10, 25, 10));
		middlePanel.add(upperMiddlePanel, BorderLayout.NORTH);
		
		upperMiddlePanel.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("900px"), },
				new RowSpec[] { RowSpec.decode("25px"), }));
		
		JMenuBar menuBar = new JMenuBar();
		upperMiddlePanel.add(menuBar, "1, 1, fill, top");
		JMenu menuMostra = new JMenu("Mostra");
		JMenu menuAggiungi = new JMenu("Azioni");
		menuBar.add(menuMostra);
		menuBar.add(menuAggiungi);
		        
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		middlePanel.add(centerPanel, BorderLayout.CENTER);
		
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel = new JLabel("Pagamenti");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		/*------TABLE */
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
		
		this.initTable();
		listPagamenti = pagamentiController.getAllPagamenti();
		this.createTable(model, listPagamenti);
		scrollPane.setViewportView(table);
		
		/*------fine-------- table */
		// -----inizio ----- MENUU--- listener
		JMenu subMenuSeleziona = new JMenu("Periodo");
		menuMostra.add(subMenuSeleziona);
			JMenuItem itemOggi = new JMenuItem("Oggi"); // , new ImageIcon("cut-java-image.gif"));
			JMenuItem itemMese = new JMenuItem("Mensili");
			JMenuItem itemAnno = new JMenuItem("Annuali");
			JMenuItem itemTutti = new JMenuItem("Tutti");
			DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE; //("yyyy-MM-dd")
			String dataformat = formatter.format(LocalDateTime.now());
			
			itemOggi.addActionListener((e) -> {
				this.resetAndDraw(pagamentiController.getPagamentiOggi());
			});
			itemMese.addActionListener((e) -> {
				this.resetAndDraw(pagamentiController.getPagamentiMensili());
			});
			itemAnno.addActionListener((e) -> {
				this.resetAndDraw(pagamentiController.getPagamentiAnnuali());
			});
			
			itemTutti.addActionListener((e) -> {
				this.resetAndDraw(pagamentiController.getAllPagamenti());
			});
			
			menuMostra.add(itemTutti);
			menuMostra.add(itemOggi);
			menuMostra.add(itemAnno);
			menuMostra.add(itemMese);
			menuMostra.add(subMenuSeleziona);
			//----fine ---- MENUU
			
		JButton backButton = new JButton("BACK");
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new HomeSegretaria(frame,user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);
				
		//setLayout(groupLayout);
		frame.getContentPane().setLayout(groupLayout);
		//this.initCommonView(frame);
	}
}
