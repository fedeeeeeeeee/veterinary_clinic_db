package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.User;

public class GestionePrestazione extends JPanel {

	private JFrame frame;
	private User user;

	public GestionePrestazione(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;

		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		// GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 990, Short.MAX_VALUE)
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
								.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_centerPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		centerPanel.setLayout(gbl_centerPanel);

		/* LOWER PANEL */
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
		JLabel titleLabel = new JLabel("Cartelle Pazienti");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel);

		lowerPanel.setLayout(new BorderLayout(0, 0));
		// setLayout(groupLayout);
		new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);

	}
}
