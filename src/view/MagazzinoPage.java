package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.User;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MagazzinoPage extends CommonView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	/**
	 * Create the panel.
	 */
	public MagazzinoPage(JFrame frame, User user) {
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
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 985, GroupLayout.PREFERRED_SIZE)
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 985, GroupLayout.PREFERRED_SIZE)
								.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 985, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 219, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{106, 50, 75, 50, 0};
		gbl_centerPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
				JButton btnFarmaci = new JButton("Farmaci");
				GridBagConstraints gbc_btnFarmaci = new GridBagConstraints();
				gbc_btnFarmaci.fill = GridBagConstraints.BOTH;
				gbc_btnFarmaci.insets = new Insets(0, 0, 5, 5);
				gbc_btnFarmaci.gridx = 1;
				gbc_btnFarmaci.gridy = 1;
				centerPanel.add(btnFarmaci, gbc_btnFarmaci);
				btnFarmaci.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().add(new FarmaciPage(frame, user));
					}
				});
				btnFarmaci.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
				JButton btnStrumenti = new JButton("Strumenti");
				GridBagConstraints gbc_btnStrumenti = new GridBagConstraints();
				gbc_btnStrumenti.insets = new Insets(0, 0, 0, 5);
				gbc_btnStrumenti.fill = GridBagConstraints.BOTH;
				gbc_btnStrumenti.gridx = 1;
				gbc_btnStrumenti.gridy = 3;
				centerPanel.add(btnStrumenti, gbc_btnStrumenti);
				btnStrumenti.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().add(new StrumentiPage(frame, user));
					}
				});
				btnStrumenti.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel =new JLabel("<html><font color='#56728f '>Magazzino</font></html>");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		
		/* LOWER PANEL */
		Icon icon = IconImage.BACK.getImageIcon();
		JButton backButton = new JButton(icon);
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new HomeSegretaria(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);

		//setLayout(groupLayout);
		// new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}

}
