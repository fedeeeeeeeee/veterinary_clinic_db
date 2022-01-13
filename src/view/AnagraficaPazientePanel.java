package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class AnagraficaPazientePanel extends JPanel {


	public AnagraficaPazientePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		
		JPanel upperPanel = new JPanel();
		add(upperPanel, BorderLayout.NORTH);
		
		//JLabel lblNewLabel = new JLabel("Anagrafica");
		//lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//upperPanel.add(lblNewLabel);

		
	}

}
