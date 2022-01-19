package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import controller.ProprietarioController;
import controller.ProprietarioControllerImpl;
import model.Appuntamento;
import model.User;

public class ConclusionePrestazione  extends CommonView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private JTextField searchBar;
	private JTable table;	
	private JScrollPane scrollPane;
	private PrestazioneController prestazioneController;
	private ProprietarioController proprietarioController;
	private List<Appuntamento> listAppuntamenti;
	private DefaultTableModel model;
	private Appuntamento appuntamento;
	
	public ConclusionePrestazione(final JFrame frame, User user, Appuntamento appuntamento) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.appuntamento = appuntamento;
		this.proprietarioController = new ProprietarioControllerImpl();
		this.prestazioneController = new PrestazioneControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();
	
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		//GroupLayout groupLayout = new GroupLayout(this);
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
		
		
		
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel = new JLabel("Completamento");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		/*------fine-------- table */

		JButton backButton = new JButton("BACK");
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
//	new GroupLayout(this);
	frame.getContentPane().setLayout(groupLayout);
	this.initCommonView(frame, user);

	
	}

}
