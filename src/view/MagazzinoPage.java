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
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.User;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;

public class MagazzinoPage extends JPanel {

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

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        /*
		   int w = frame.getSize().width;
           int h = frame.getSize().height;
           int x = (dim.width-w)/2;
           int y = (dim.height-h)/2;
           */
           
          GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        // GroupLayout groupLayout = new GroupLayout(this);
          groupLayout.setHorizontalGroup(
          	groupLayout.createParallelGroup(Alignment.LEADING)
          		.addGroup(groupLayout.createSequentialGroup()
          			.addContainerGap()
          			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
          				.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
          				.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
          				.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
          			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
          centerPanel.setLayout(null);
          
          JButton btnFarmaci = new JButton("Farmaci");
          btnFarmaci.setBounds(368, 106, 219, 50);
          centerPanel.add(btnFarmaci);
          btnFarmaci.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		 frame.getContentPane().add(new FarmaciPage(frame, user));
            	}
            });
          btnFarmaci.setFont(new Font("Tahoma", Font.PLAIN, 20));
          
          JButton btnStrumenti = new JButton("Strumenti");
          btnStrumenti.setBounds(368, 231, 219, 50);
          centerPanel.add(btnStrumenti);
          btnStrumenti.addActionListener(new ActionListener() {
          	public void actionPerformed(ActionEvent e) {
          		 frame.getContentPane().add(new StrumentiPage(frame, user));
          	}
          });
          btnStrumenti.setFont(new Font("Tahoma", Font.PLAIN, 20));
         
		upperPanel.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel titleLabel = new JLabel("Magazzino");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		/*btnStrumenti.addActionListener(new ActionListener() {
	          	public void actionPerformed(ActionEvent e) {
	          		 frame.getContentPane().add(new StrumentiPage(frame, user));
	          	}
	          });*/
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel);
		
		/*LOWER PANEL */
		JButton sendButton = new JButton("BACK");
		sendButton.setHorizontalAlignment(SwingConstants.LEFT);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 frame.getContentPane().add(new HomeSegretaria(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(sendButton, BorderLayout.WEST);
		
		//setLayout(groupLayout);
	new GroupLayout(this);
	frame.getContentPane().setLayout(groupLayout);
	}

}
