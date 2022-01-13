package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.User;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeVeterinario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;

	public HomeVeterinario(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;

		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	        //GroupLayout groupLayout = new GroupLayout(this);
	        groupLayout.setHorizontalGroup(
	        	groupLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 990, Short.MAX_VALUE)
	        				.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
	        			.addContainerGap())
	        );
	        groupLayout.setVerticalGroup(
	        	groupLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(groupLayout.createSequentialGroup()
	        			.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap())
	        );
	        GridBagLayout gbl_centerPanel = new GridBagLayout();
	        gbl_centerPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
	        gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
	        gbl_centerPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	        gbl_centerPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	        centerPanel.setLayout(gbl_centerPanel);
	        
	        JButton btnPazienti = new JButton("Pazienti");
	        btnPazienti.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getContentPane().add(new VisualizzaDatiPazienti(frame, user));
	        	}
	        });
	        GridBagConstraints gbc_btnPazienti = new GridBagConstraints();
	        gbc_btnPazienti.insets = new Insets(0, 0, 5, 5);
	        gbc_btnPazienti.gridx = 1;
	        gbc_btnPazienti.gridy = 1;
	        centerPanel.add(btnPazienti, gbc_btnPazienti);
	        
	        JButton btnPrestazione = new JButton("Gestione Prestazioni");
	        btnPrestazione.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getContentPane().add(new GestionePrestazione(frame, user));
	        	}
	        });
	        GridBagConstraints gbc_btnPrestazione = new GridBagConstraints();
	        gbc_btnPrestazione.insets = new Insets(0, 0, 5, 5);
	        gbc_btnPrestazione.gridx = 3;
	        gbc_btnPrestazione.gridy = 1;
	        centerPanel.add(btnPrestazione, gbc_btnPrestazione);
	        
	        JButton btnRicoveri = new JButton("Ricoveri");
	        btnRicoveri.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		frame.getContentPane().add(new GestioneRicoveri(frame, user));
	        	}
	        });
	        GridBagConstraints gbc_btnRicoveri = new GridBagConstraints();
	        gbc_btnRicoveri.insets = new Insets(0, 0, 5, 5);
	        gbc_btnRicoveri.gridx = 2;
	        gbc_btnRicoveri.gridy = 3;
	        centerPanel.add(btnRicoveri, gbc_btnRicoveri);
	          
	          upperPanel.setLayout(new GridLayout(0, 1, 0, 0));
	  		JLabel titleLabel = new JLabel("Home Personale Sanitario");
	  		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	  		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
	  		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
			upperPanel.add(titleLabel);
			
			lowerPanel.setLayout(new BorderLayout(0, 0));
			//setLayout(groupLayout);
		new GroupLayout(this);
			frame.getContentPane().setLayout(groupLayout);
	}
}
