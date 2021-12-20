package view;
/* completo*/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.StrumentiController;
import controller.StrumentiControllerImpl;
import model.Appuntamento;
import model.Strumento;
import model.User;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class StrumentiPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private JTextField fieldRicerca;
	private JTable table;
	private StrumentiController strumentiController;
	private List<Strumento> listStrumenti;
	
	private DefaultTableModel model;
	/**
	 * Create the panel.
	 */
	public StrumentiPage(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.strumentiController = new StrumentiControllerImpl();

		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        
      GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
      //GroupLayout groupLayout = new GroupLayout(this);
       groupLayout.setHorizontalGroup(
       	groupLayout.createParallelGroup(Alignment.LEADING)
       		.addGroup(groupLayout.createSequentialGroup()
       			.addContainerGap()
       			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
       				.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
       				.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
       				.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
       			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
       groupLayout.setVerticalGroup(
       	groupLayout.createParallelGroup(Alignment.LEADING)
       		.addGroup(groupLayout.createSequentialGroup()
       			.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
       			.addPreferredGap(ComponentPlacement.RELATED)
       			.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
       			.addPreferredGap(ComponentPlacement.RELATED)
       			.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
       			.addContainerGap())
       );
       //------MENU BAR------
       middlePanel.setLayout(new BorderLayout(0, 0));
       
       JPanel upperMiddlePanel = new JPanel();
       upperMiddlePanel.setBorder(new EmptyBorder(0, 10, 25, 10));
       middlePanel.add(upperMiddlePanel, BorderLayout.NORTH);
       upperMiddlePanel.setLayout(new FormLayout(new ColumnSpec[] {
       		ColumnSpec.decode("900px"),},
       	new RowSpec[] {
       		RowSpec.decode("25px"),}));
    
       JMenuBar menuBar = new JMenuBar();
      //frame.setJMenuBar(menuBar);
       upperMiddlePanel.add(menuBar, "1, 1, fill, top");
       JMenu menuMostra = new JMenu("Mostra");
       //editMostra.setMnemonic("S");
      // JMenu editScaduti = new JMenu("Scaduti");
       //JMenu editInScadenza = new JMenu("In Scadenza");
       fieldRicerca = new JTextField();
       fieldRicerca.setText("Ricerca Strumento");
       JButton buttonVai = new JButton("Vai");
       
       buttonVai.setBackground(Color.LIGHT_GRAY);
       buttonVai.setForeground(Color.BLACK);
       //menuBar.add(editScaduti);
      // menuBar.add(editInScadenza);
       menuBar.add(menuMostra);
       menuBar.add(fieldRicerca);
       menuBar.add(buttonVai);

       JButton orderAsc = new JButton (IconImage.ARROW.getImageIcon() );
       orderAsc.setBackground(Color.LIGHT_GRAY);
       orderAsc.setForeground(Color.BLACK);
       menuBar.add(orderAsc);

       
       
       JPanel centerPanel = new JPanel();
       centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
       middlePanel.add(centerPanel, BorderLayout.CENTER);
       
       
		upperPanel.setLayout(null);
		JLabel titleLabel = new JLabel("Magazzino");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel);
		
		
		/*-------------- TABLE */
	
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
		
	

		
		JButton button = new JButton();
		this.initTable();
	    table.getColumn("Aggiungi").setCellRenderer(new ButtonRenderer("Aggiungi"));
	    table.getColumn("Aggiungi").setCellEditor(new ButtonEditor(new JCheckBox(), button, "Aggiungi"));
	    
	    this.listStrumenti = strumentiController.getAllStrumenti();
	    this.createTable(model, listStrumenti);
	    /*
		listStrumenti.forEach(x -> {
			model.addRow(new Object[] { 
					x.getCodStrumento(),
					x.getNome(),
					x.getDescrizione(),
					x.getDataScadenza(),
					x.getQuantità(),
			});
		});
		*/
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				        public void valueChanged(ListSelectionEvent event) {
				        	ListSelectionModel lsm = (ListSelectionModel)event.getSource();
					        int firstIndex = event.getFirstIndex();
					        listStrumenti.get(firstIndex); // tipo Appuntamento
					        String nome =   listStrumenti.get(firstIndex).getNome();
					        JOptionPane.showMessageDialog(null, "Quanti " + nome + "vuoi aggiungere"  + "?");
				        }
				    });
			}
		});
		
		
		
		//table.setModel(model);
		scrollPane.setViewportView(table);
		
		/*------fine-------- table */
		// -----inizio ----- MENUU--- listener
	  
	       JMenuItem itemScaduti = new JMenuItem("Scaduti"); //, new ImageIcon("cut-java-image.gif"));
	       JMenuItem itemInScadenza = new JMenuItem("In Scadenza");
	       JMenuItem itemInEsaurimento = new JMenuItem("In Esaurimento");
	       itemScaduti.addActionListener((e) -> {
	    	   this.listStrumenti.clear();
	    	   this.listStrumenti = strumentiController.getStrumentiScaduti();
	    	   System.out.println(this.listStrumenti);
	    	   table.clearSelection();
	    	   this.initTable();
	    	   model.setRowCount(0);
	      	   this.createTable(model, listStrumenti);
	         });
	       itemInScadenza.addActionListener((e) -> {
	    	   this.listStrumenti.clear();
	    	   this.listStrumenti = strumentiController.getStrumentiInScadenza();    	   
	    	   System.out.println(this.listStrumenti);
	    	   table.clearSelection();
	    	   this.initTable();
	    	   model.setRowCount(0);
	      	   this.createTable(model, listStrumenti);
	       });
	       itemScaduti.addActionListener((e) -> {
	    	   this.listStrumenti.clear();
	    	   this.listStrumenti = strumentiController.getStrumentiScaduti();
	    	   System.out.println(this.listStrumenti);
	    	   table.clearSelection();
	    	   this.initTable();
	    	   model.setRowCount(0);
	      	   this.createTable(model, listStrumenti);
	         });
	       itemInEsaurimento.addItemListener((e) -> {
	    	   this.listStrumenti.clear();
	    	   this.listStrumenti = strumentiController.getStrumentiInEsaurimento();    	   
	    	   System.out.println(this.listStrumenti);
	    	   table.clearSelection();
	    	   this.initTable();
	    	   model.setRowCount(0);
	      	   this.createTable(model, listStrumenti);
	       });
	       menuMostra.add(itemScaduti);
	       menuMostra.add(itemInScadenza);
	       menuMostra.addSeparator();
	       menuMostra.add(itemInEsaurimento);
	       
	       //----fine ---- MENUU
		/*LOWER PANEL */
		JButton sendButton = new JButton("BACK");
		sendButton.setHorizontalAlignment(SwingConstants.LEFT);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new MagazzinoPage(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(sendButton, BorderLayout.WEST);
		
		//setLayout(groupLayout);
		new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
}
	private void createTable(DefaultTableModel model, List<Strumento> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { 
					x.getCodStrumento(),
					x.getNome(),
					x.getDescrizione(),
					x.getDataScadenza(),
					x.getQuantità(),
			});
		});
	}
	
	private void initTable () {
		this.table = new JTable(); 
		this.model =  (DefaultTableModel) table.getModel();
		model.addColumn("Codice");
		model.addColumn("Nome");
		model.addColumn("Descrizione");
		model.addColumn("Scadenza");
		model.addColumn("Quantità");
		model.addColumn("Aggiungi");
	}
}
