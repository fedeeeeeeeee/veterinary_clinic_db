package view;

import javax.swing.JPanel;
import model.Ricovero;
import model.User;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class CardRicovero extends JPanel {

private Ricovero ricovero; 

	public CardRicovero(JFrame frame, User user, Ricovero ricovero) {
		this.ricovero = ricovero;
		setLayout(new BorderLayout(0, 0));
		
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
		gbl_middlePanel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		middlePanel.setLayout(gbl_middlePanel);
		
		JLabel lblModNome = new JLabel("Nome");
		lblModNome.setText(ricovero.getAnimale().getNome());
		GridBagConstraints gbc_lblModNome = new GridBagConstraints();
		gbc_lblModNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblModNome.gridx = 2;
		gbc_lblModNome.gridy = 2;
		middlePanel.add(lblModNome, gbc_lblModNome);
		
		JLabel lblInzioRicovero = new JLabel("Inizio Ricovero");
		lblInzioRicovero.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		middlePanel.add(lblInzioRicovero, gbc_lblNewLabel_2);
		
		JLabel lblFineRicovero = new JLabel("Fine Ricovero");
		lblFineRicovero.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 4;
		middlePanel.add(lblFineRicovero, gbc_lblNewLabel_3);
		
		JLabel lblModDataInizio = new JLabel("data");
		lblModDataInizio.setText(ricovero.getDataInizio());
		GridBagConstraints gbc_lblModDataInizio = new GridBagConstraints();
		gbc_lblModDataInizio.insets = new Insets(0, 0, 5, 5);
		gbc_lblModDataInizio.gridx = 1;
		gbc_lblModDataInizio.gridy = 5;
		middlePanel.add(lblModDataInizio, gbc_lblModDataInizio);
		
	String dataInizio = ricovero.getDataInizio(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dataInizio));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		c.add(Calendar.DATE, ricovero.getDurataInGiorni());
		String dataFine = sdf.format(c.getTime()); 
		
		JLabel lblModDataFine = new JLabel("New label");
		lblModDataFine.setText(dataFine);
		GridBagConstraints gbc_lblModDataFine = new GridBagConstraints();
		gbc_lblModDataFine.anchor = GridBagConstraints.WEST;
		gbc_lblModDataFine.insets = new Insets(0, 0, 5, 5);
		gbc_lblModDataFine.gridx = 3;
		gbc_lblModDataFine.gridy = 5;
		middlePanel.add(lblModDataFine, gbc_lblModDataFine);
		
		JLabel lblNewLabel_6 = new JLabel("Motivo");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 7;
		middlePanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblModMotivo = new JLabel("New label");
		lblModMotivo.setText(ricovero.getMotivoRicovero());
		GridBagConstraints gbc_lblModMotivo = new GridBagConstraints();
		gbc_lblModMotivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblModMotivo.gridwidth = 2;
		gbc_lblModMotivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblModMotivo.gridx = 2;
		gbc_lblModMotivo.gridy = 7;
		middlePanel.add(lblModMotivo, gbc_lblModMotivo);
		
		JPanel lowerPanel = new JPanel();
		add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnInfo = new JButton("Visualizza Informazioni");
		btnInfo.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ricovero.getPosto().setDescrizione(dimensione);
				 frame.getContentPane().add(new InfoRicovero(frame, user, ricovero));
			}
		});
		btnInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lowerPanel.add(btnInfo);
		
		JButton btnNewButton = new JButton("Effettua Rilascio");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ricovero.getPosto().setDescrizione(dimensione);
				 frame.getContentPane().add(new RilascioRicovero(frame, user, ricovero));
			}
		});
		btnNewButton.setForeground(new Color(139, 0, 0));
		lowerPanel.add(btnNewButton);
         
	}
}
