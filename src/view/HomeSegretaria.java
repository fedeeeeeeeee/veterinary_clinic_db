package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.User;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		//GroupLayout groupLayout = new GroupLayout(this);
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
		gbl_centerPanel.columnWidths = new int[]{80, 150, 150, 20, 150, 150, 80, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 100, 20, 100, 20, 100, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JButton appuntamentiButton = new JButton("Appuntamenti");
		appuntamentiButton.setFont(new Font("Dialog", Font.PLAIN, 26));
		GridBagConstraints gbc_appuntamentiButton = new GridBagConstraints();
		gbc_appuntamentiButton.gridwidth = 3;
		gbc_appuntamentiButton.fill = GridBagConstraints.BOTH;
		gbc_appuntamentiButton.insets = new Insets(0, 0, 5, 5);
		gbc_appuntamentiButton.gridx = 2;
		gbc_appuntamentiButton.gridy = 1;
		centerPanel.add(appuntamentiButton, gbc_appuntamentiButton);
		
		appuntamentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new GestioneAppuntamento(frame, user));
			}
		});
		
		JButton registraPazienteButton = new JButton("Registra paziente");
		registraPazienteButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		GridBagConstraints gbc_registraPazienteButton = new GridBagConstraints();
		gbc_registraPazienteButton.gridwidth = 2;
		gbc_registraPazienteButton.fill = GridBagConstraints.BOTH;
		gbc_registraPazienteButton.insets = new Insets(0, 0, 5, 5);
		gbc_registraPazienteButton.gridx = 1;
		gbc_registraPazienteButton.gridy = 3;
		centerPanel.add(registraPazienteButton, gbc_registraPazienteButton);
		
		registraPazienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new RegistraPaziente(frame, user));
			}
		});
		
		JButton magazzinoButton = new JButton("Magazzino");
		magazzinoButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		GridBagConstraints gbc_magazzinoButton = new GridBagConstraints();
		gbc_magazzinoButton.gridwidth = 2;
		gbc_magazzinoButton.fill = GridBagConstraints.BOTH;
		gbc_magazzinoButton.insets = new Insets(0, 0, 5, 5);
		gbc_magazzinoButton.gridx = 4;
		gbc_magazzinoButton.gridy = 3;
		centerPanel.add(magazzinoButton, gbc_magazzinoButton);
		
			magazzinoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					  frame.getContentPane().add(new MagazzinoPage(frame, user));
					  
				}
			});
		
		JButton prestazioniButton = new JButton("Gestione Prestazioni");
		prestazioniButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		GridBagConstraints gbc_prestazioniButton = new GridBagConstraints();
		gbc_prestazioniButton.gridwidth = 2;
		gbc_prestazioniButton.fill = GridBagConstraints.BOTH;
		gbc_prestazioniButton.insets = new Insets(0, 0, 5, 5);
		gbc_prestazioniButton.gridx = 1;
		gbc_prestazioniButton.gridy = 5;
		centerPanel.add(prestazioniButton, gbc_prestazioniButton);
		
		prestazioniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new GestioneUtilizzi(frame, user));
			}
		});
		
		JButton pagamentiButton = new JButton("Riepilogo pagamenti");
		pagamentiButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		GridBagConstraints gbc_pagamentiButton = new GridBagConstraints();
		gbc_pagamentiButton.gridwidth = 2;
		gbc_pagamentiButton.fill = GridBagConstraints.BOTH;
		gbc_pagamentiButton.insets = new Insets(0, 0, 5, 5);
		gbc_pagamentiButton.gridx = 4;
		gbc_pagamentiButton.gridy = 5;
		centerPanel.add(pagamentiButton, gbc_pagamentiButton);
		
		pagamentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new RiepilogoPagamenti(frame, user));
			}
		});
		
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		
        upperPanel.setLayout(new GridLayout(0, 1, 0, 0));
	  		JLabel titleLabel = new JLabel("Home Personale Segreteria");
	  		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	  		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
	  		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
			upperPanel.add(titleLabel);
				
		//setLayout(groupLayout);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);
	}
}
