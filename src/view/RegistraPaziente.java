package view;
import javax.swing.JFrame;

import model.User;

public class RegistraPaziente extends CommonView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5823886107710364208L;
	private JFrame frame;
	private User user;

	/**
	 * Create the panel.
	 */
	public RegistraPaziente(final JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
	}

}
