package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import model.Appuntamento;
import java.awt.GridLayout;

public class NuovoAppuntamento extends JPanel {

		private static final long serialVersionUID = -83378890947911750L;
		private JFrame frame;
		private PrestazioneController prestazioneController;
		
		public NuovoAppuntamento(final JFrame frame) {
			frame.getContentPane().removeAll();
			frame.getContentPane().validate();
			frame.getContentPane().repaint();
			this.frame = frame;
			this.prestazioneController = new PrestazioneControllerImpl();
			
			JPanel upperPanel = new JPanel();
			JPanel lowerPanel = new JPanel();
			
			JPanel panel = new JPanel();

			// per la visualizzazione
			GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
			// utile per il design
			//GroupLayout groupLayout = new GroupLayout(this);
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
							.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
						.addGap(24))
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 971, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(28, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
						.addGap(15)
						.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
						.addGap(0))
			);
			panel.setLayout(new GridLayout(4, 0, 0, 0));
			
			JLabel lblPadrone = new JLabel("Padrone");
			panel.add(lblPadrone);
			
			JLabel lblAnimale = new JLabel("Animale");
			panel.add(lblAnimale);
			
			JLabel lblNumeroPadrone = new JLabel("numero ");
			panel.add(lblNumeroPadrone);
			
			JLabel lblData = new JLabel("data");
			panel.add(lblData);
			
			JLabel lblOra = new JLabel("Ora");
			panel.add(lblOra);
			
			JLabel lblPrestazione = new JLabel("TipoPrestazione");
			panel.add(lblPrestazione);
			upperPanel.setLayout(new BorderLayout(0, 0));
			
			JLabel lblNewLabel = new JLabel("Appointment detail");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			upperPanel.add(lblNewLabel, BorderLayout.CENTER);
			
			JPanel panelUtente = new JPanel();
			upperPanel.add(panelUtente, BorderLayout.NORTH);
			panelUtente.setLayout(new BoxLayout(panelUtente, BoxLayout.X_AXIS));
			
			JLabel lblUtente = new JLabel("Utente");
			lblUtente.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblUtente.setHorizontalAlignment(SwingConstants.LEFT);
			panelUtente.add(lblUtente);
			
			JLabel lblRuolo = new JLabel("Ruolo");
			lblRuolo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblRuolo.setAlignmentX(Component.RIGHT_ALIGNMENT);
			lblRuolo.setVerticalAlignment(SwingConstants.TOP);
			lblRuolo.setHorizontalAlignment(SwingConstants.LEFT);
			panelUtente.add(lblRuolo);
			List<Appuntamento> listPrestazioni = prestazioneController.getAllPrestazioni();
			

			
			JButton button = new JButton();
		    
		    /*
		    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			        int firstIndex = e.getFirstIndex();
			        int lastIndex = e.getLastIndex();
			        System.out.println("first" + firstIndex);
				}
		    });
		    */

			
			
		

			JButton backButton = new JButton("BACK");
			backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().add(new MainPage(frame));
				}
			});
			lowerPanel.setLayout(new BorderLayout(0, 0));
			lowerPanel.add(backButton, BorderLayout.WEST);

			// importante per il design
		//setLayout(groupLayout);

			// importante per la visualizzazione ---> commentare GroupLayout groupLayout =
		new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		}
	}
