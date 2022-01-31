package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import model.Posto;
import model.Ricovero;
import model.RicoveroImpl;
import model.User;
import controller.RicoveroController;
import controller.RicoveroControllerImpl;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestioneRicoveri extends CommonView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private RicoveroController ricoveroController;
	private List<Ricovero> listRicoveri = new ArrayList<>() ;
	private List<Posto> listPostiLiberi;
	private int indice = 0;

	public GestioneRicoveri(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.ricoveroController = new RicoveroControllerImpl();

		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel arrowPanel = new JPanel();
		// JScrollPane scrollPane = new JScrollPane(centerPanel);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		// GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
								.addComponent(arrowPanel, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)
								.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(arrowPanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		JButton btnFrecciaSX = new JButton("<");
		arrowPanel.add(btnFrecciaSX);

		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 400, 0 };
		gbl_centerPanel.rowHeights = new int[] { 0, 0, 200, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		centerPanel.setLayout(gbl_centerPanel);

		JPanel panelDisponibilita = new JPanel();
		GridBagConstraints gbc_panelDisponibilita = new GridBagConstraints();
		gbc_panelDisponibilita.insets = new Insets(0, 0, 5, 0);
		gbc_panelDisponibilita.fill = GridBagConstraints.BOTH;
		gbc_panelDisponibilita.gridx = 0;
		gbc_panelDisponibilita.gridy = 0;
		centerPanel.add(panelDisponibilita, gbc_panelDisponibilita);

		JLabel lblPostiLiberi = new JLabel("Posti liberi");
		panelDisponibilita.add(lblPostiLiberi);

		JPanel cardRicovero = new JPanel();
		GridBagConstraints gbc_cardRicovero = new GridBagConstraints();
		gbc_cardRicovero.insets = new Insets(0, 0, 5, 0);
		gbc_cardRicovero.fill = GridBagConstraints.VERTICAL;
		gbc_cardRicovero.gridx = 0;
		gbc_cardRicovero.gridy = 2;
		centerPanel.add(cardRicovero, gbc_cardRicovero);

		this.listRicoveri = this.ricoveroController.getRicoveriAttivi();
		
		int numRicoveriAttivi = this.listRicoveri.size();
		this.listPostiLiberi = this.ricoveroController.getPostiLiberi();
		
		List<Posto> filteredPostiLiberi = this.listPostiLiberi.stream()
				.filter(p-> !this.listRicoveri.stream()
											  .map(r-> r.getPosto().getNumero())
											  .collect(Collectors.toList()).contains(p.getNumero()))
				.collect(Collectors.toList());
		
		filteredPostiLiberi.forEach(posto -> {
			Ricovero ricovero = new RicoveroImpl();
			ricovero.setPosto(posto);
			this.listRicoveri.add(ricovero);
		});

		JLabel lblModPostiLiberi = new JLabel("New label");
		lblModPostiLiberi.setText(String.valueOf(filteredPostiLiberi.size()));
		lblModPostiLiberi.setForeground(new Color(0, 128, 0));
		panelDisponibilita.add(lblModPostiLiberi);
		this.indice = 0;

		if (numRicoveriAttivi > 0) {
			cardRicovero.add(new CardRicovero(frame, user, this.listRicoveri.get(indice)));
		} else {
			cardRicovero.add(new CardRicoveroVuota(frame, user, this.listRicoveri.get(indice)));
		}

		JLabel lblPage = new JLabel();
		lblPage.setText("1 / 10");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		centerPanel.add(lblPage, gbc_lblNewLabel);

		JButton btnFrecciaDx = new JButton(">");
		arrowPanel.add(btnFrecciaDx);

		btnFrecciaDx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (indice + 1 < listRicoveri.size()) {
					indice++;
					Ricovero elem = listRicoveri.get(indice);
					lblPage.setText((indice + 1) + "/" + listRicoveri.size());
					if (elem.getDataInizio() == null) {
						cardRicovero.removeAll();
						cardRicovero.revalidate();
						cardRicovero.repaint();
						cardRicovero.add(new CardRicoveroVuota(frame, user, elem));
					} else {
						cardRicovero.removeAll();
						cardRicovero.revalidate();
						cardRicovero.repaint();
						cardRicovero.add(new CardRicovero(frame, user, elem));
					}
				}
			}
		});
		btnFrecciaSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (indice - 1 >= 0) {
					indice--;
					lblPage.setText((indice + 1) + "/" + listRicoveri.size());
					Ricovero elem = listRicoveri.get(indice);
					if (elem.getDataInizio() == null) {
						cardRicovero.removeAll();
						cardRicovero.revalidate();
						cardRicovero.repaint();
						cardRicovero.add(new CardRicoveroVuota(frame, user, elem));
					} else {
						cardRicovero.removeAll();
						cardRicovero.revalidate();
						cardRicovero.repaint();
						cardRicovero.add(new CardRicovero(frame, user, elem));
					}
				}
			}
		});
		/* LOWER PANEL */
		JButton sendButton = new JButton("BACK");
		sendButton.setHorizontalAlignment(SwingConstants.LEFT);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new HomeVeterinario(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(sendButton, BorderLayout.WEST);

		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel = new JLabel("Ricoveri");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		// setLayout(groupLayout);
		new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}
}
