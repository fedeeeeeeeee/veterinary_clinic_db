package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import org.w3c.dom.events.MouseEvent;

import model.Posto;
import model.Ricovero;
import model.RicoveroImpl;
import model.User;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.RicoveroController;
import controller.RicoveroControllerImpl;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.awt.FlowLayout;

public class GestioneRicoveri extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private RicoveroController ricoveroController;
	private List<Ricovero> listRicoveri = new ArrayList<>() ;
	private int numeroPosti;
	private ListIterator<Ricovero> iteratorRicoveri;
	private List<Posto> listPostiLiberi;
	
	public GestioneRicoveri(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
this.ricoveroController = new RicoveroControllerImpl();
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel arrowPanel = new JPanel();
		// JScrollPane scrollPane = new JScrollPane(centerPanel);
		 
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	      //GroupLayout groupLayout = new GroupLayout(this);
	        groupLayout.setHorizontalGroup(
	        	groupLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
	        				.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 985,  Short.MAX_VALUE)
	        				.addComponent(arrowPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
	        				.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
	        			.addContainerGap())
	        );
	        groupLayout.setVerticalGroup(
	        	groupLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(arrowPanel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap())
	        );
	        
	        JButton btnFrecciaSX = new JButton("<");
	        arrowPanel.add(btnFrecciaSX);
	        
	     
	        GridBagLayout gbl_centerPanel = new GridBagLayout();
	        gbl_centerPanel.columnWidths = new int[]{0, 0, 0, 0};
	        gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0};
	        gbl_centerPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
	        gbl_centerPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
	        centerPanel.setLayout(gbl_centerPanel);
	        
	        JPanel cardRicovero = new JPanel();
	        GridBagConstraints gbc_cardRicovero = new GridBagConstraints();
	        gbc_cardRicovero.insets = new Insets(0, 0, 5, 5);
	        gbc_cardRicovero.fill = GridBagConstraints.BOTH;
	        gbc_cardRicovero.gridx = 1;
	        gbc_cardRicovero.gridy = 1;
	        centerPanel.add(cardRicovero, gbc_cardRicovero);
	        
	      
	        
	       // this.numeroPosti = this.ricoveroController.getNumeroPosti();
	    this.listRicoveri =   this.ricoveroController.getRicoveriAttivi();
	    this.listPostiLiberi=   this.ricoveroController.getPostiLiberi();
	     
	      int numeroCardVuote = this.listPostiLiberi.size() -  this.listRicoveri.size();
	      this.listPostiLiberi.forEach(posto ->{
	    	  Ricovero ricovero = new RicoveroImpl();
	    	  ricovero.setPosto(posto);
	    	  this.listRicoveri.add(ricovero);
	      });
	      
	      this.iteratorRicoveri =  listRicoveri.listIterator();
	     cardRicovero.add(new CardRicovero(this.iteratorRicoveri.next()));
System.out.println(this.listPostiLiberi.size());
System.out.println(this.listRicoveri.size());
	    
	     JButton btnFrecciaDx = new JButton(">");
	        arrowPanel.add(btnFrecciaDx);
	        
	        btnFrecciaDx.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {      		
	        		if(iteratorRicoveri.hasNext()){ // 9 
		        			Ricovero elem = iteratorRicoveri.next(); // 10
		        			if(elem.getDataInizio()== null) {
		        				cardRicovero.removeAll();
				        		cardRicovero.revalidate();
				        		cardRicovero.repaint();
		        				cardRicovero.add(new CardRicoveroVuota(elem));
		        			} else {
			        			cardRicovero.removeAll();
				        		cardRicovero.revalidate();
				        		cardRicovero.repaint();
				        		cardRicovero.add(new CardRicovero(elem));     			
		        			}
	        		}
	        		
	        	}
	        });
	        btnFrecciaSX.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if(iteratorRicoveri.hasPrevious()){
	        			Ricovero elem = iteratorRicoveri.previous();
	        			if(elem.getDataInizio()== null) {
	        				cardRicovero.removeAll();
			        		cardRicovero.revalidate();
			        		cardRicovero.repaint();
	        				cardRicovero.add(new CardRicoveroVuota(elem));
	        			} else {
		        			cardRicovero.removeAll();
			        		cardRicovero.revalidate();
			        		cardRicovero.repaint();
			        		cardRicovero.add(new CardRicovero(elem));     			
	        			}
	        		}
	        	}
	        });
			/*LOWER PANEL */
			JButton sendButton = new JButton("BACK");
			sendButton.setHorizontalAlignment(SwingConstants.LEFT);
			sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			sendButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 frame.getContentPane().add(new HomeVeterinario(frame, user));
				}
			});
			lowerPanel.setLayout(new BorderLayout(0, 0));
			lowerPanel.add(sendButton, BorderLayout.WEST);
			
	        upperPanel.setLayout(new GridLayout(0, 1, 0, 0));
		  		JLabel titleLabel = new JLabel("Ricoveri");
		  		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		  		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		  		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
				upperPanel.add(titleLabel);
				
				//setLayout(groupLayout);
				new GroupLayout(this);
				frame.getContentPane().setLayout(groupLayout);
	}
}
