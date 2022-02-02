package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Image;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import controller.FarmaciController;
import controller.FarmaciControllerImpl;
import model.Farmaco;
import model.User;
import utility.JPanelImage;

import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FarmaciPage extends CommonView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private JTextField searchBar;
	private JTable table;
	private JScrollPane scrollPane;
	private FarmaciController farmaciController;
	private List<Farmaco> listFarmaci;
	private DefaultTableModel model;

	
	public FarmaciPage(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.farmaciController = new FarmaciControllerImpl();

		JPanel upperPanel = new JPanelImage("pillole.png");
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();

        
      GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
      //GroupLayout groupLayout = new GroupLayout(this);
       groupLayout.setHorizontalGroup(
       	groupLayout.createParallelGroup(Alignment.LEADING)
       		.addGroup(groupLayout.createSequentialGroup()
       			.addContainerGap()
       			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
       				.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
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
       			.addContainerGap()));
      
       //------MENU BAR------
       middlePanel.setLayout(new BorderLayout(0, 0));
       JPanel upperMiddlePanel = new JPanel();
       upperMiddlePanel.setBorder(new EmptyBorder(0, 10, 25, 10));
       middlePanel.add(upperMiddlePanel, BorderLayout.NORTH);
       
       upperMiddlePanel.setLayout(new FormLayout(new ColumnSpec[] {
       		ColumnSpec.decode("950px"),},
       	new RowSpec[] {
       		RowSpec.decode("25px"),}));
    
       JMenuBar menuBar = new JMenuBar();
       upperMiddlePanel.add(menuBar, "1, 1, fill, top");
       JMenu menuMostra = new JMenu("Mostra");
       JMenu menuSeleziona = new JMenu("Seleziona");
       JMenu menuAggiungi = new JMenu("Aggiungi");
       
       searchBar = new JTextField();
       searchBar.setFont(new Font("Tahoma", Font.ITALIC, 13));
       searchBar.setText("Ricerca Farmaco");
       searchBar.addFocusListener((FocusListener) new FocusListener() {

		@Override
		public void focusGained(FocusEvent e) {
			if(searchBar.getText().equals("Ricerca Farmaco") ) {
			searchBar.setText("");
			}
		}
		@Override
		public void focusLost(FocusEvent e) {			
			if(searchBar.getText().equals("") ) {
				searchBar.setText("Ricerca Farmaco");
				}
		}
    	   
       });
       searchBar.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    			  searchBar.setText("");
    	   }
       });
      
		Icon iconSearch = IconImage.SEARCH.getImageIcon();
		JButton buttonVai = new JButton(iconSearch);
		buttonVai.setBorderPainted(false);
		buttonVai.setContentAreaFilled(false);
       buttonVai.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   search(e);
    	   }
       });
    	   
		
       menuBar.add(menuMostra);
       menuBar.add(menuSeleziona);
       menuBar.add(menuAggiungi);
       menuBar.add(searchBar);
       menuBar.add(buttonVai);

     
       
       JPanel centerPanel = new JPanel();
       centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
       middlePanel.add(centerPanel, BorderLayout.CENTER);
       
       
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel =new JLabel("<html><font color='#56728f '>Farmaci</font></html>");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		upperPanel.add(titleLabel, BorderLayout.CENTER);
		
		
		/*-------------- TABLE */
	
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		centerPanel.add(scrollPane, gbc_scrollPane);
		

		this.initTable();
	    this.listFarmaci = farmaciController.getAllFarmaci();
	    this.createTable(model, listFarmaci);
		scrollPane.setViewportView(table);
		
		/*------fine-------- table */
		// -----inizio ----- MENUU--- listener
	  
	       JMenuItem itemScaduti = new JMenuItem("Scaduti"); //, new ImageIcon("cut-java-image.gif"));
	       JMenuItem itemInScadenza = new JMenuItem("In Scadenza");
	       JMenuItem itemInEsaurimento = new JMenuItem("In Esaurimento");
	       JCheckBoxMenuItem itemFarmaci = new JCheckBoxMenuItem("Farmaci");
	       JMenuItem itemAddFarmaco = new JMenuItem("Aggiungi Farmaco");
	       JMenuItem itemAddLotto = new JMenuItem("Aggiungi Lotto");
	       itemFarmaci.setSelected(true);
	       JCheckBoxMenuItem itemVaccini = new JCheckBoxMenuItem("Vaccini");
	       itemVaccini.setSelected(true);
	       JMenuItem all = new JMenuItem("Tutti");
	       JMenuItem acquistati = new JMenuItem("Acquistati");
	       JMenuItem maiAcquistati = new JMenuItem("Non Acquistati");
	    

	       itemScaduti.addActionListener((e) -> {
	    	   if((itemFarmaci.isSelected() && itemVaccini.isSelected()) || ( !itemFarmaci.isSelected() && !itemVaccini.isSelected())) {
	    		   this.resetAndDraw(farmaciController.getFarmaciScaduti());
	    	   } else {
	    		   if(itemFarmaci.isSelected()) {
	    			   List<Farmaco> list = farmaciController.getFarmaciScaduti();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Farmaco".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }else {
	    			   List<Farmaco> list = farmaciController.getFarmaciScaduti();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Vaccino".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }
	    	   }
	         });
	       
	       itemInScadenza.addActionListener((e) -> {
	    	   if((itemFarmaci.isSelected() && itemVaccini.isSelected()) || ( !itemFarmaci.isSelected() && !itemVaccini.isSelected())) {
	    		   this.resetAndDraw(farmaciController.getFarmaciInScadenza());
	    	   } else {
	    		   if(itemFarmaci.isSelected()) {
	    			   List<Farmaco> list = farmaciController.getFarmaciInScadenza();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Farmaco".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }else {
	    			   List<Farmaco> list = farmaciController.getFarmaciInScadenza();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Vaccino".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }
	    	   } 
	       });
	       
	       itemInEsaurimento.addActionListener((e) -> {
	    	   if((itemFarmaci.isSelected() && itemVaccini.isSelected()) || ( !itemFarmaci.isSelected() && !itemVaccini.isSelected())) {
	    		   this.resetAndDraw(farmaciController.getFarmaciInEsaurimento());
	    	   } else {
	    		   if(itemFarmaci.isSelected()) {
	    			   List<Farmaco> list = farmaciController.getFarmaciInEsaurimento();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Farmaco".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }else {
	    			   List<Farmaco> list = farmaciController.getFarmaciInEsaurimento();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Vaccino".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }
	    	   } 
	       });
	       
	      all.addActionListener((e) -> {
	    	  if((itemFarmaci.isSelected() && itemVaccini.isSelected()) || ( !itemFarmaci.isSelected() && !itemVaccini.isSelected())) {
	    		   this.resetAndDraw(farmaciController.getAllFarmaci());
	    	   } else {
	    		   if(itemFarmaci.isSelected()) {
	    			   List<Farmaco> list = farmaciController.getAllFarmaci();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Farmaco".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }else {
	    			   List<Farmaco> list = farmaciController.getAllFarmaci();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Vaccino".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }
	    	   } 
	       });
	      acquistati.addActionListener((e) -> {
	    	  if((itemFarmaci.isSelected() && itemVaccini.isSelected()) || ( !itemFarmaci.isSelected() && !itemVaccini.isSelected())) {
	    		   this.resetAndDraw(farmaciController.getFarmaciAcquistati());
	    	   } else {
	    		   if(itemFarmaci.isSelected()) {
	    			   List<Farmaco> list = farmaciController.getFarmaciAcquistati();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Farmaco".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }else {
	    			   List<Farmaco> list = farmaciController.getFarmaciAcquistati();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Vaccino".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }
	    	   } 
	       });
	      maiAcquistati.addActionListener((e) -> {
	    	  if((itemFarmaci.isSelected() && itemVaccini.isSelected()) || ( !itemFarmaci.isSelected() && !itemVaccini.isSelected())) {
	    		   this.resetAndDraw(farmaciController.getFarmaciMaiAcquistati());
	    	   } else {
	    		   if(itemFarmaci.isSelected()) {
	    			   List<Farmaco> list = farmaciController.getFarmaciMaiAcquistati();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Farmaco".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }else {
	    			   List<Farmaco> list = farmaciController.getFarmaciMaiAcquistati();
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Vaccino".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  this.resetAndDraw(filtrato);
	    		   }
	    	   } 
	       });
	      itemFarmaci.addActionListener((e) -> { // prova a togliere
	    	  if((itemFarmaci.isSelected() && itemVaccini.isSelected()) || ( !itemFarmaci.isSelected() && !itemVaccini.isSelected())) {
	    		  List<Farmaco> list = this.listFarmaci.stream().collect(Collectors.toList());
	    		   this.resetAndDraw(list);
	    	   } else {
	    		   if(itemFarmaci.isSelected()) {
	    			  
	    			   List<Farmaco> list = this.listFarmaci;
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Farmaco".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  model.setRowCount(0); // non ho usato la funziona per mantere traccia della richiesta del menu show 
	    		      	   this.createTable(model, filtrato);	
	    		      	 scrollPane.setViewportView(table);
	    		   }else {
	    			 List<Farmaco> list = this.listFarmaci.stream().collect(Collectors.toList());
	    			   //List<Farmaco> list = this.listFarmaci;
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Vaccino".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  model.setRowCount(0); // non ho usato la funziona per mantere traccia della richiesta del menu show 
	    		      	   this.createTable(model, filtrato);	
	    		      	 scrollPane.setViewportView(table);
	    		   }
	    	   } 
	       });
	      itemVaccini.addActionListener((e) -> {
	    	  if((itemFarmaci.isSelected() && itemVaccini.isSelected()) || ( !itemFarmaci.isSelected() && !itemVaccini.isSelected())) {
	    		  List<Farmaco> list = this.listFarmaci.stream().collect(Collectors.toList());
	    		   this.resetAndDraw(list);
	    	   } else {
	    		   if(itemFarmaci.isSelected()) {
	    			   List<Farmaco> list = this.listFarmaci.stream().collect(Collectors.toList());
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Farmaco".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	  model.setRowCount(0); // non ho usato la funziona per mantere traccia della richiesta del menu show 
	    		      	   this.createTable(model, filtrato);	
	    		      	 scrollPane.setViewportView(table);
	    		   }else {
	    			   List<Farmaco> list = this.listFarmaci.stream().collect(Collectors.toList());
	    		    	  List<Farmaco> filtrato =  list.stream().filter(farmaco -> "Vaccino".equals(farmaco.getTipoFarmaco())).collect(Collectors.toList());
	    		    	   model.setRowCount(0); // non ho usato la funziona per mantere traccia della richiesta del menu show 
	    		      	   this.createTable(model, filtrato);	
	    		      	 scrollPane.setViewportView(table);
	    		   }
	    	   } 
	       });
	      menuMostra.add(all);
	      menuMostra.add(acquistati);
	      menuMostra.add(maiAcquistati);
	      menuMostra.addSeparator();
	       menuMostra.add(itemScaduti);
	       menuMostra.add(itemInScadenza);
	       menuMostra.addSeparator();
	       menuMostra.add(itemInEsaurimento);
	       menuSeleziona.add(itemFarmaci);
	       menuSeleziona.add(itemVaccini);
	       menuAggiungi.add(itemAddFarmaco);
	       menuAggiungi.add(itemAddLotto);
	       itemAddFarmaco.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// new Farmaco
					frame.getContentPane().add(new NuovoFarmacoPage(frame, user));
				}
			});
	       
	       itemAddLotto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().add(new AggiungiLotto(frame, user));
				}
			});
	       
	       JPanel panelAdd = new JPanel();
	       middlePanel.add(panelAdd, BorderLayout.SOUTH);
	       
	       //----fine ---- MENUU
		/*LOWER PANEL */
	       Icon icon = IconImage.BACK.getImageIcon();
			JButton backButton = new JButton(icon);
			backButton.setBorder(BorderFactory.createEmptyBorder());
			backButton.setContentAreaFilled(false);
			backButton.setHorizontalAlignment(SwingConstants.LEFT);
			backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new MagazzinoPage(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);
		
		//setLayout(groupLayout);
		new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

}
	
	
	
	
	private void resetAndDraw(List<Farmaco> allFarmaci) {
 	   this.listFarmaci.clear();
 	   this.listFarmaci = allFarmaci;
 	   //table.clearSelection();
 	   model.setRowCount(0);
   	   this.createTable(model, listFarmaci);
   	 //scrollPane.setViewportView(table);
	}




	private void createTable(DefaultTableModel model, List<Farmaco> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { 
					x.getCodLotto(),
					x.getNomeFarmaco(),
					x.getDescrizione(),
					x.getDataScadenza(),
					x.getDisponibilita(),
					x.getTipoFarmaco(),
			});
		});
	}
	
	

	private void initTable () {
		this.table = new JTable(); 
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		table.getTableHeader().setForeground(Color.decode("#56728f"));
		this.model =  (DefaultTableModel) table.getModel();
		model.addColumn("CodLotto");
		model.addColumn("Farmaco");
		model.addColumn("Descrizione");
		model.addColumn("Scadenza");
		model.addColumn("Disponibilità");
		model.addColumn("Tipo");
	}

	public void  search(ActionEvent e) { //action
		List<Farmaco> lista = new ArrayList<Farmaco>();
		//listStrumenti.clear();
		lista.addAll(searchList(searchBar.getText(), listFarmaci));
		this.resetAndDraw(lista);
	}




	private List<Farmaco> searchList(String searchWords, List<Farmaco> listOfFarmaco) {
		List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" ") );
		return listOfFarmaco.stream().filter(input ->{ 
			return searchWordsArray.stream().allMatch(word -> input.getNomeFarmaco().toLowerCase().contains(word.toLowerCase()));
		}).collect(Collectors.toList());
	}
}
