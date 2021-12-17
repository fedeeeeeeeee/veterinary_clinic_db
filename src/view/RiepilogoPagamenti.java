package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.User;
import java.awt.GridBagLayout;

public class RiepilogoPagamenti extends JPanel {

	private static final long serialVersionUID = -5434266332244629674L;
	private JFrame frame;
	private User user;

	public RiepilogoPagamenti(final JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
	}

}
