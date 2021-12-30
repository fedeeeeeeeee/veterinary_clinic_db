package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import model.User;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class NuovoAppuntamento extends JPanel {
	private JFrame frame;
	private User user;	
	private PrestazioneController prestazioneController;

	/**
	 * Create the panel.
	 */
	public NuovoAppuntamento(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.prestazioneController = new PrestazioneControllerImpl();
		
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		
Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(24)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE).addGap(44)
						.addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE).addGap(84)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE).addGap(0)));
		middlePanel.setLayout(new BorderLayout());
		
		JPanel scrollPanelLeft = new JPanel();
		JPanel scrollPanelRight = new JPanel();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrollPanelLeft, scrollPanelRight);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);
        middlePanel.add(splitPane, BorderLayout.CENTER);
       
        
        /*UPPER PANEL AND TITLE */
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel = new JLabel("Nuovo Appuntamento");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel, BorderLayout.CENTER);
	   
			/*LOWER PANEL */
			JButton sendButton = new JButton("BACK");
			sendButton.setHorizontalAlignment(SwingConstants.LEFT);
			sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			sendButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().add(new MagazzinoPage(frame, user));
				}
			});
			lowerPanel.setLayout(new BorderLayout(0, 0));
			lowerPanel.add(sendButton, BorderLayout.WEST);
			
		setLayout(groupLayout);
		//new GroupLayout(this);
		//frame.getContentPane().setLayout(groupLayout);
		//this.initCommonView(frame);
	}
}
