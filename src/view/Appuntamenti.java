package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JList;

public class Appuntamenti extends JPanel {

	private static final long serialVersionUID = -83378890947911750L;
	private JFrame frame;
	private JScrollPane scroll;
	private PrestazioneController prestazioneController;

	public Appuntamenti(final JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.prestazioneController = new PrestazioneControllerImpl();
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();

		// per la visualizzazione
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		// utile per il design
		// GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
						.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(24)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE).addGap(44)
						.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE).addGap(84)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE).addGap(0)));
		centerPanel.setLayout(new BorderLayout(0, 0));

		JList<String> list = new JList<String>();
		centerPanel.add(list, BorderLayout.CENTER);
		
		list.setListData(this.prestazioneController.getAllPrestazioni().toArray(new String[0]));
		

		scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		centerPanel.add(scroll);

		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new MainPage(frame));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);

		// importante per il design
		// setLayout(groupLayout);

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout =
		// new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
	}
}
