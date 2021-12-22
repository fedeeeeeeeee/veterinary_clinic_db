package view;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.User;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import java.awt.GridBagConstraints;

public class RiepilogoPagamenti extends JPanel {


	private JFrame frame;
	private User user;
	private JTable tableTutti;
	private JTable tableMensili;
	private JTable tableAnnuali;
	private JTable tableOggi;
	private JTable tablePeriodo;
	
	public RiepilogoPagamenti(final JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		
		JPanel upperPanel = new JPanel();
		
		JPanel centerPanel = new JPanel();
		
		JPanel lowerPanel = new JPanel();

		// per la visualizzazione
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		// utile per il design
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(upperPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
								.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
							.addGap(1)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(4))
		);
		upperPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Riepilogo pagamenti");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(lblNewLabel, BorderLayout.CENTER);
		
			
			
			 JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		        tabbedPane.setBounds(centerPanel.getBounds().x, centerPanel.getBounds().y, centerPanel.getBounds().width, centerPanel.getBounds().height);
		        frame.getContentPane().add(tabbedPane);
		        
		        JPanel allPagamentiTab = new JPanel();
		        tabbedPane.addTab("Tutti", null, allPagamentiTab, "Tutti");
		        allPagamentiTab.setLayout(new GridLayout(1, 0, 0, 0));
		        
		        JScrollPane scrollPane = new JScrollPane();
		        allPagamentiTab.add(scrollPane);
		        
		        tableTutti = new JTable();
		        scrollPane.setViewportView(tableTutti);
		        
		        JPanel mensiliPanel = new JPanel();
		        tabbedPane.addTab("Mensili", null, mensiliPanel, null);
		        mensiliPanel.setLayout(new GridLayout(1, 0, 0, 0));
		        
		        JScrollPane scrollPaneMensili = new JScrollPane();
		        mensiliPanel.add(scrollPaneMensili);
		        
		        tableMensili = new JTable();
		        scrollPaneMensili.setViewportView(tableMensili);
		        
		        JPanel annualiTab = new JPanel();
		        tabbedPane.addTab("Annuali", null, annualiTab, "Annuali");
		        annualiTab.setLayout(new GridLayout(1, 0, 0, 0));
		        
		        JScrollPane scrollPaneAnnuali = new JScrollPane();
		        annualiTab.add(scrollPaneAnnuali);
		        
		        tableAnnuali = new JTable();
		        scrollPaneAnnuali.setViewportView(tableAnnuali);
		    	        
		        JPanel oggiTab = new JPanel();
		        tabbedPane.addTab("Oggi", null, oggiTab, "Oggi");

		        oggiTab.setLayout(new GridLayout(1, 0, 0, 0));
		        
		        JScrollPane scrollPaneOggi = new JScrollPane();
		        oggiTab.add(scrollPaneOggi);
		        
		        tableOggi = new JTable();
		        scrollPaneOggi.setViewportView(tableOggi);
		        
		        JPanel periodoTab = new JPanel();
		        tabbedPane.addTab("Periodo", null, periodoTab, "Periodo");
		        periodoTab.setLayout(new BorderLayout(0, 0));
		        
		        JPanel panelSu = new JPanel();
		        periodoTab.add(panelSu, BorderLayout.NORTH);
		        panelSu.setLayout(new GridLayout(1, 0, 0, 0));
		        
		        JDateChooser dateChooser = new JDateChooser();
		        panelSu.add(dateChooser);
		        
		        JDateChooser dateChooser_1 = new JDateChooser();
		        panelSu.add(dateChooser_1);
		        
		        JPanel panelGiu = new JPanel();
		        periodoTab.add(panelGiu, BorderLayout.CENTER);
		        panelGiu.setLayout(new GridLayout(1, 0, 0, 0));
		        
		        JScrollPane scrollPanePeriodo = new JScrollPane();
		        panelGiu.add(scrollPanePeriodo);
		        
		        tablePeriodo = new JTable();
		        scrollPanePeriodo.setViewportView(tablePeriodo);
		        
		        centerPanel.add(tabbedPane);

			
		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new MainPage(frame));
			}
		});
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		lowerPanel.add(backButton);
		
				
		// importante per il design
		//setLayout(groupLayout);

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout = new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		//this.initCommonView(frame);
	}
}
