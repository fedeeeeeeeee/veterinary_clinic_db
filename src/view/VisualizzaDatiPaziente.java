package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Paziente;
import model.User;
import javax.swing.JTabbedPane;

public class VisualizzaDatiPaziente extends CommonView {

	private static final long serialVersionUID = -6966708581951158547L;
	private JFrame frame;
	private User user;
	private Paziente paziente;

	public VisualizzaDatiPaziente(JFrame frame, User user, Paziente paziente) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.paziente = paziente;
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING,false)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING,false)
								.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 975, Short.MAX_VALUE)
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 975, Short.MAX_VALUE)
								.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 975, Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		centerPanel.add(tabbedPane, BorderLayout.CENTER);
		 
		JPanel panelAnagrafica = new AnagraficaPazientePanel(paziente);
		tabbedPane.addTab("Anagrafica", null, panelAnagrafica,
		                  "Visualizza anagrafica paziente");

		JPanel panelParametri = new ParametriPazientiPanel(paziente);
		tabbedPane.addTab("Parametri", null, panelParametri,
		                  "Visualizza altezza e peso");
		
		JPanel panelProprietario = new AnagraficaProprietarioPanel(this.paziente.getAnimale().getProprietario());
		tabbedPane.addTab("Proprietario", null, panelProprietario,
		                  "Visualizza anagrafica proprietario");
		
		JPanel panelFarmaci = new FarmaciDiPazientePanel(this.paziente.getAnimale());
		tabbedPane.addTab("Farmaci", null, panelFarmaci,
		                  "Visualizza farmaci prescritti");
		
		JPanel panelPatologie = new PatologiePazientiPanel(paziente);
		tabbedPane.addTab("Patologie", null, panelPatologie,
		                  "Visualizza patologie diagnosticate");
		
		JPanel panelPrestazioni = new PrestazioniDiAnimalePanel(this.paziente.getAnimale());
		tabbedPane.addTab("Prestazioni", null, panelPrestazioni,
		                  "Visualizza prestazioni del paziente");
			
		/* LOWER PANEL */
		Icon icon = IconImage.BACK.getImageIcon();
		JButton sendButton = new JButton(icon);
		sendButton.setBorder(BorderFactory.createEmptyBorder());
		sendButton.setContentAreaFilled(false);
		sendButton.setHorizontalAlignment(SwingConstants.LEFT);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new VisualizzaPazienti(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(sendButton, BorderLayout.WEST);
		
		upperPanel.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel titleLabel = new JLabel("Cartelle Pazienti");
		titleLabel.setText(paziente.getAnimale().getNome()); // TODO
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel);

		//setLayout(groupLayout);
		new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}
}
