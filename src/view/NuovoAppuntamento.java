package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import model.User;

public class NuovoAppuntamento extends JPanel {
	private JFrame frame;
	private User user;	
	private PrestazioneController prestazioneController;

	/**
	 * Create the panel.
	 */
	public NuovoAppuntamento() {
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
		
		
		setLayout(groupLayout);
		//new GroupLayout(this);
		//frame.getContentPane().setLayout(groupLayout);
		//this.initCommonView(frame);
	
	}

}
