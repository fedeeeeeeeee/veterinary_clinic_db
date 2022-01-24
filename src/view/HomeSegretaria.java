package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.User;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class HomeSegretaria extends CommonView {

	private static final long serialVersionUID = -8240597628451997179L;
	private JFrame frame;
	
	public HomeSegretaria(final JFrame frame, User user) {

		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		
		JPanel upperPanel = new JPanel();
		
		JPanel centerPanel = new JPanel();
		
		JPanel lowerPanel = new JPanel();
		
		
		// per la visualizzazione
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		// utile per il design
		// GroupLayout groupLayout = new GroupLayout(this);
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 				.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
		 				.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
		 				.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)))
		 );
		 groupLayout.setVerticalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
		 			.addGap(4))
		 );
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{62, 133, 238, 105, 236, 53, 262, 0};
		gbl_centerPanel.rowHeights = new int[]{46, 0, 0, 0, -17, -9, -9, 0, 0, 32, 0, 62, 61, 58, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JButton registraPazienteButton = new JButton("Registra paziente");
		registraPazienteButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GridBagConstraints gbc_registraPazienteButton = new GridBagConstraints();
		gbc_registraPazienteButton.fill = GridBagConstraints.BOTH;
		gbc_registraPazienteButton.gridheight = 4;
		gbc_registraPazienteButton.insets = new Insets(0, 0, 5, 5);
		gbc_registraPazienteButton.gridx = 2;
		gbc_registraPazienteButton.gridy = 3;
		centerPanel.add(registraPazienteButton, gbc_registraPazienteButton);
		
		registraPazienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new RegistraPaziente(frame, user));
			}
		});
		
		JButton magazzinoButton = new JButton("Magazzino");
		magazzinoButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GridBagConstraints gbc_magazzinoButton = new GridBagConstraints();
		gbc_magazzinoButton.fill = GridBagConstraints.BOTH;
		gbc_magazzinoButton.gridheight = 4;
		gbc_magazzinoButton.insets = new Insets(0, 0, 5, 5);
		gbc_magazzinoButton.gridx = 4;
		gbc_magazzinoButton.gridy = 3;
		centerPanel.add(magazzinoButton, gbc_magazzinoButton);
		
			magazzinoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					  frame.getContentPane().add(new MagazzinoPage(frame, user));
					  
				}
			});
		
		JButton appuntamentiButton = new JButton("Appuntamenti");
		appuntamentiButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GridBagConstraints gbc_appuntamentiButton = new GridBagConstraints();
		gbc_appuntamentiButton.fill = GridBagConstraints.BOTH;
		gbc_appuntamentiButton.gridheight = 3;
		gbc_appuntamentiButton.insets = new Insets(0, 0, 5, 5);
		gbc_appuntamentiButton.gridx = 2;
		gbc_appuntamentiButton.gridy = 8;
		centerPanel.add(appuntamentiButton, gbc_appuntamentiButton);
		
		appuntamentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new GestioneAppuntamento(frame, user));
			}
		});
		
		JButton pagamentiButton = new JButton("Riepilogo pagamenti");
		pagamentiButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GridBagConstraints gbc_pagamentiButton = new GridBagConstraints();
		gbc_pagamentiButton.fill = GridBagConstraints.BOTH;
		gbc_pagamentiButton.gridheight = 3;
		gbc_pagamentiButton.insets = new Insets(0, 0, 5, 5);
		gbc_pagamentiButton.gridx = 4;
		gbc_pagamentiButton.gridy = 8;
		centerPanel.add(pagamentiButton, gbc_pagamentiButton);
		
		pagamentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new RiepilogoPagamenti(frame, user));
			}
		});
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		
		
				
		// importante per il design
		//setLayout(groupLayout);

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout = new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);
	}
}
