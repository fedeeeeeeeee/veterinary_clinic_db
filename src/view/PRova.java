package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PRova extends JPanel {
	private JFrame frame;

	public PRova(JFrame frame) {		
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
     
		JPanel centerPanel = new JPanel();
		//GroupLayout groupLayout = new GroupLayout(this);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 906, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
					.addContainerGap())
		);
		centerPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panelSx = new JPanel();
		centerPanel.add(panelSx, "2, 2, 1, 3, fill, fill");
		panelSx.setMinimumSize(new Dimension(20,6));
		
		JPanel anagraficaPanel = new JPanel();
		anagraficaPanel.setBackground(Color.DARK_GRAY);
		centerPanel.add(anagraficaPanel, "4, 2, 3, 1, fill, fill");
		
		JPanel panelDx = new JPanel();
		centerPanel.add(panelDx, "8, 2, 1, 3, fill, fill");
		
		JPanel datiAccessoPanel = new JPanel();
		datiAccessoPanel.setBackground(Color.YELLOW);
		centerPanel.add(datiAccessoPanel, "4, 4, fill, fill");
		
		JPanel ruoloPanel = new JPanel();
		ruoloPanel.setBackground(Color.GREEN);
		centerPanel.add(ruoloPanel, "6, 4, fill, fill");
		
	    
		// importante per il design
		//setLayout(groupLayout);

		// importante per la visualizzazione ---> commentare GroupLayout groupLayout = new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
	}

}
