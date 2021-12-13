package view;

import javax.swing.JFrame;

import model.User;

public class RiepilogoPagamenti extends CommonView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5434266332244629674L;
	private JFrame frame;
	private User user;

	/**
	 * Create the panel.
	 */
	public RiepilogoPagamenti(final JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
	}

}
