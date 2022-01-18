package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import controller.FarmaciController;
import controller.FarmaciControllerImpl;
import model.User;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;

public class NuovoStrumentoPage extends CommonView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	//private FarmaciController farmaciController;
	private JTextField textNomeFarmaco;
	private JTextField textField;
	
	public NuovoStrumentoPage(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		//this.farmaciController = new FarmaciControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	// GroupLayout groupLayout = new GroupLayout(this);
	       groupLayout.setHorizontalGroup(
	       	groupLayout.createParallelGroup(Alignment.LEADING)
	       		.addGroup(groupLayout.createSequentialGroup()
	       			.addContainerGap()
	       			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	       				.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
	       				.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
	       				.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE))
	       			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	       );
	       groupLayout.setVerticalGroup(
	       	groupLayout.createParallelGroup(Alignment.LEADING)
	       		.addGroup(groupLayout.createSequentialGroup()
	       			.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
	       			.addPreferredGap(ComponentPlacement.RELATED)
	       			.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
	       			.addPreferredGap(ComponentPlacement.RELATED)
	       			.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
	       			.addContainerGap()));
	       middlePanel.setLayout(new BorderLayout(0, 0));
	       
	      
	       
	       JPanel panelForm = new JPanel();
	       middlePanel.add(panelForm, BorderLayout.CENTER);
	       panelForm.setLayout(new MigLayout("", "[grow][474px,grow,fill][grow]", "[grow][201px][grow]"));
	       
	       JPanel panel = new JPanel();
	      panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	       Border border = panel.getBorder();
	       Border margin = new EmptyBorder(20,10,20,10);
	       panel.setBorder(new CompoundBorder(border, margin));
	       panelForm.add(panel, "cell 1 1,alignx center,aligny top");
	       GridBagLayout gbl_panel = new GridBagLayout();
	       gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, -40, 0, 52, 0, 0, 0, 0, 0};
	       gbl_panel.rowHeights = new int[]{0, 0, 10, 0, 10, 0, 0, 10, 0, 0, 0};
	       gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
	       gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 5.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	       panel.setLayout(gbl_panel);
	       
	       JLabel lblNewLabel_1 = new JLabel("Nome Farmaco");
	       GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	       gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	       gbc_lblNewLabel_1.gridx = 1;
	       gbc_lblNewLabel_1.gridy = 1;
	       panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
	       
	       textNomeFarmaco = new JTextField();
	       GridBagConstraints gbc_textNomeFarmaco = new GridBagConstraints();
	       gbc_textNomeFarmaco.gridwidth = 3;
	       gbc_textNomeFarmaco.anchor = GridBagConstraints.NORTH;
	       gbc_textNomeFarmaco.insets = new Insets(0, 0, 5, 5);
	       gbc_textNomeFarmaco.fill = GridBagConstraints.HORIZONTAL;
	       gbc_textNomeFarmaco.gridx = 2;
	       gbc_textNomeFarmaco.gridy = 1;
	       panel.add(textNomeFarmaco, gbc_textNomeFarmaco);
	       textNomeFarmaco.setColumns(10);
	       
	       JLabel lblNewLabel_2 = new JLabel("Descrizione");
	       GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
	       gbc_lblNewLabel_2.gridwidth = 2;
	       gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
	       gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
	       gbc_lblNewLabel_2.gridx = 5;
	       gbc_lblNewLabel_2.gridy = 1;
	       panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
	       
	       /*
	       JLabel lblNewLabel_3 = new JLabel("Tipo Farmaco");
	       GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
	       gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
	       gbc_lblNewLabel_3.gridx = 1;
	       gbc_lblNewLabel_3.gridy = 3;
	       panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
	       
	         
	         JComboBox<String> comboBoxTipoFarmaco = new JComboBox<>();
	         GridBagConstraints gbc_comboBoxTipoFarmaco = new GridBagConstraints();
	         gbc_comboBoxTipoFarmaco.gridwidth = 3;
	         gbc_comboBoxTipoFarmaco.insets = new Insets(0, 0, 5, 5);
	         gbc_comboBoxTipoFarmaco.fill = GridBagConstraints.BOTH;
	         gbc_comboBoxTipoFarmaco.gridx = 2;
	         gbc_comboBoxTipoFarmaco.gridy = 3;
	         panel.add(comboBoxTipoFarmaco, gbc_comboBoxTipoFarmaco);
	         
	 */
	       
	       JScrollPane scrollPane = new JScrollPane();
	       scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	       GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	       gbc_scrollPane.gridheight = 7;
	       gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
	       gbc_scrollPane.fill = GridBagConstraints.BOTH;
	       gbc_scrollPane.gridx = 7;
	       gbc_scrollPane.gridy = 1;
	       panel.add(scrollPane, gbc_scrollPane);
	       
	       JTextArea textArea = new JTextArea();
	       scrollPane.setViewportView(textArea);
	       textArea.setLineWrap(true);
	       textArea.setWrapStyleWord(true);
	       
	       JLabel lblDataScadenza = new JLabel("Data Scadenza");
	       lblDataScadenza.setHorizontalAlignment(SwingConstants.LEFT);
	       GridBagConstraints gbc_lblDataScadenza = new GridBagConstraints();
	       gbc_lblDataScadenza.insets = new Insets(0, 0, 5, 5);
	       gbc_lblDataScadenza.gridx = 1;
	       gbc_lblDataScadenza.gridy = 3;
	       panel.add(lblDataScadenza, gbc_lblDataScadenza);
	       
	       JDateChooser dateChooser = new JDateChooser();
	       GridBagConstraints gbc_dateChooser = new GridBagConstraints();
	       gbc_dateChooser.gridwidth = 3;
	       gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
	       gbc_dateChooser.fill = GridBagConstraints.BOTH;
	       gbc_dateChooser.gridx = 2;
	       gbc_dateChooser.gridy = 3;
	       panel.add(dateChooser, gbc_dateChooser);
	       
	       JLabel lblNewLabel = new JLabel("Disponibilità");
	       GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	       gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	       gbc_lblNewLabel.gridx = 1;
	       gbc_lblNewLabel.gridy = 7;
	       panel.add(lblNewLabel, gbc_lblNewLabel);
	       
	       JSpinner spinner = new JSpinner();
	       GridBagConstraints gbc_spinner = new GridBagConstraints();
	       gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
	       gbc_spinner.insets = new Insets(0, 0, 5, 5);
	       gbc_spinner.gridx = 2;
	       gbc_spinner.gridy = 7;
	       panel.add(spinner, gbc_spinner);
	       
	       JLabel lblNewLabel_4 = new JLabel("Prezzo");
	       GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
	       gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
	       gbc_lblNewLabel_4.gridx = 4;
	       gbc_lblNewLabel_4.gridy = 7;
	       panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
	       
	       textField = new JTextField();
	       GridBagConstraints gbc_textField = new GridBagConstraints();
	       gbc_textField.insets = new Insets(0, 0, 5, 5);
	       gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	       gbc_textField.gridx = 5;
	       gbc_textField.gridy = 7;
	       panel.add(textField, gbc_textField);
	       textField.setColumns(10);

	       JPanel panelSave = new JPanel();
	       middlePanel.add(panelSave, BorderLayout.SOUTH);
	       
	       JButton btnSave = new JButton("Save");
	       
	       btnSave.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent e) {
//TODO
	    	   }
	    	   });
	       panelSave.add(btnSave);
	       
	       /* UPPER PANEL AND TITLE */
			upperPanel.setLayout(new BorderLayout(0, 0));
			JLabel titleLabel = new JLabel("Nuovo Strumento");
			titleLabel.setBounds(409, 58, 194, 52);
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
			upperPanel.add(titleLabel, BorderLayout.CENTER);

			/* LOWER PANEL */
			JButton sendButton = new JButton("BACK");
			sendButton.setHorizontalAlignment(SwingConstants.LEFT);
			sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			sendButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().add(new MagazzinoPage(frame, user));
				}
			});
			lowerPanel.setLayout(new BorderLayout(0, 0));
			lowerPanel.add(sendButton, BorderLayout.WEST);
			
		    //setLayout(groupLayout);
			new GroupLayout(this);
			frame.getContentPane().setLayout(groupLayout);
			this.initCommonView(frame, user);

	}
}
