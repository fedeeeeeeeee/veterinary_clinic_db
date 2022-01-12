package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Posto;
import model.Ricovero;

public class CardRicoveroVuota extends JPanel {

	private static final long serialVersionUID = 1L;

	public CardRicoveroVuota(Ricovero ricovero ) {
		setLayout(new BorderLayout(0, 0));
		// crea interfaccia e classe posto per numero e descrizione(dimensione gabbia)
		JPanel upperPanel = new JPanel();
		add(upperPanel, BorderLayout.NORTH);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
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
		
		JButton btnInfo = new JButton("add");
		
		JPanel lowerPanel = new JPanel();
		add(lowerPanel, BorderLayout.SOUTH);
		
	
		lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		btnInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lowerPanel.add(btnInfo);
		
         
	}
}