package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import model.Ricovero;
import model.User;

public class CardRicoveroVuota extends JPanel {

	private static final long serialVersionUID = 1L;

	public CardRicoveroVuota(JFrame frame, User user, Ricovero ricovero ) {
		setLayout(new BorderLayout(0, 0));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		Border margin = new EmptyBorder(0,15,60,15);
		setBorder(new CompoundBorder(border, margin));

		JPanel upperPanel = new JPanel();
		add(upperPanel, BorderLayout.NORTH);
		
		JLabel lblModPostoEDim = new JLabel();
		String dimensione = ricovero.getPosto().getDescrizione().substring(18);
		lblModPostoEDim.setText("Posto Numero: " + ricovero.getPosto().getNumero() + " Taglia:  " +  dimensione);
		
		upperPanel.add(lblModPostoEDim);
		
		JPanel middlePanel = new JPanel();
		add(middlePanel, BorderLayout.CENTER);
		GridBagLayout gbl_middlePanel = new GridBagLayout();
		gbl_middlePanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_middlePanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_middlePanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_middlePanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		middlePanel.setLayout(gbl_middlePanel);
		JLabel lblVuoto = new JLabel("Libero");
		lblVuoto.setForeground(new Color(34, 139, 34));
		GridBagConstraints gbc_lblVuoto = new GridBagConstraints();
		gbc_lblVuoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblVuoto.gridx = 2;
		gbc_lblVuoto.gridy = 2;
		middlePanel.add(lblVuoto, gbc_lblVuoto);
		JPanel lowerPanel = new JPanel();
		add(lowerPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_lowerPanel = new GridBagLayout();
		gbl_lowerPanel.columnWidths = new int[]{280, 0};
		gbl_lowerPanel.rowHeights = new int[]{23, 0};
		gbl_lowerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_lowerPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		lowerPanel.setLayout(gbl_lowerPanel);
		
		JButton btnAdd = new JButton("add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ricovero.getPosto().setDescrizione(dimensione);
				frame.getContentPane().add(new AggiungiRicovero(frame, user, ricovero));
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.NORTH;
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		lowerPanel.add(btnAdd, gbc_btnAdd);
		
         
	}
}