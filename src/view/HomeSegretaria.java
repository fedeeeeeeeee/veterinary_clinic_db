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

public class HomeSegretaria extends CommonView {

	private static final long serialVersionUID = -8240597628451997179L;
	private JFrame frame;
	private User user;
	
	public HomeSegretaria(final JFrame frame, User user) {

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
		// GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(4))
		);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JButton registraPazienteButton = new JButton("Registra paziente");
		GridBagConstraints gbc_registraPazienteButton = new GridBagConstraints();
		gbc_registraPazienteButton.fill = GridBagConstraints.BOTH;
		gbc_registraPazienteButton.gridwidth = 2;
		gbc_registraPazienteButton.insets = new Insets(0, 0, 5, 5);
		gbc_registraPazienteButton.gridx = 2;
		gbc_registraPazienteButton.gridy = 1;
		centerPanel.add(registraPazienteButton, gbc_registraPazienteButton);
		
		registraPazienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new RegistraPaziente(frame, user));
			}
		});
		
		JButton appuntamentiButton = new JButton("Appuntamenti");
		GridBagConstraints gbc_appuntamentiButton = new GridBagConstraints();
		gbc_appuntamentiButton.gridwidth = 2;
		gbc_appuntamentiButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_appuntamentiButton.insets = new Insets(0, 0, 5, 0);
		gbc_appuntamentiButton.gridx = 5;
		gbc_appuntamentiButton.gridy = 1;
		centerPanel.add(appuntamentiButton, gbc_appuntamentiButton);
		
		appuntamentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new GestioneAppuntamento(frame, user));
			}
		});
		
		JButton magazzinoButton = new JButton("Magazzino");
		GridBagConstraints gbc_magazzinoButton = new GridBagConstraints();
		gbc_magazzinoButton.fill = GridBagConstraints.BOTH;
		gbc_magazzinoButton.gridwidth = 2;
		gbc_magazzinoButton.insets = new Insets(0, 0, 0, 5);
		gbc_magazzinoButton.gridx = 2;
		gbc_magazzinoButton.gridy = 3;
		centerPanel.add(magazzinoButton, gbc_magazzinoButton);
	
		magazzinoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new MagazzinoPage(frame, user));
				  
			}
		});
		
		JButton pagamentiButton = new JButton("Riepilogo pagamenti");
		GridBagConstraints gbc_pagamentiButton = new GridBagConstraints();
		gbc_pagamentiButton.gridwidth = 2;
		gbc_pagamentiButton.gridx = 5;
		gbc_pagamentiButton.gridy = 3;
		centerPanel.add(pagamentiButton, gbc_pagamentiButton);
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		
		pagamentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new RiepilogoPagamenti(frame, user));
			}
		});
		
		
		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new MainPage(frame));
			}
		});
		lowerPanel.add(backButton);
		
				
		// importante per il design
		//setLayout(groupLayout);

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout = new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame);
	}
}
