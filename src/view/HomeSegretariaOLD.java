package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.User;

public class HomeSegretariaOLD extends CommonView {
	 
	private static final long serialVersionUID = -3398122590862316754L;
	private JFrame frame;
	private User user;
	
	public HomeSegretariaOLD(final JFrame frame, User user) {

		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();

		// per la visualizzazione
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		// utile per il design
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
						.addComponent(lowerPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addGap(198)
					.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(72))
		);

		
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new MainPage(frame));
			}
		});
		lowerPanel.add(backButton, BorderLayout.WEST);
		
		JButton appuntamentiBtn = new JButton("APPUNTAMENTI");
		appuntamentiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new GestioneAppuntamento(frame, user));
			}
		});
		
		
		lowerPanel.add(appuntamentiBtn, BorderLayout.EAST);
		// importante per il design
		//setLayout(groupLayout);

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout = new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame);
	}
}
