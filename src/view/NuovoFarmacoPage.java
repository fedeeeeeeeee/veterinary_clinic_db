package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

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
import model.Animale;
import model.Farmaco;
import model.FarmacoImpl;
import model.LottoImpl;
import model.User;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.TextArea;
import javax.swing.JTextArea;

public class NuovoFarmacoPage extends JPanel {
	private JFrame frame;
	private User user;
	private FarmaciController farmaciController;
	private JTextField textNomeFarmaco;
	private JTextField textFieldCopertura;
	
	public NuovoFarmacoPage(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.farmaciController = new FarmaciControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	    //GroupLayout groupLayout = new GroupLayout(this);
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
	       gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 200, 0, 0};
	       gbl_panel.rowHeights = new int[]{0, 0, 10, 0, 0, 0, 10, 0, 0, 0};
	       gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
	       gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
	       gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
	       gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
	       gbc_lblNewLabel_2.gridx = 7;
	       gbc_lblNewLabel_2.gridy = 1;
	       panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
	       
	       JScrollPane scrollPane = new JScrollPane();
	       scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	       GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	       gbc_scrollPane.gridheight = 7;
	       gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
	       gbc_scrollPane.fill = GridBagConstraints.BOTH;
	       gbc_scrollPane.gridx = 8;
	       gbc_scrollPane.gridy = 1;
	       panel.add(scrollPane, gbc_scrollPane);
	       
	       JTextArea textArea = new JTextArea();
	       textArea.setLineWrap(true);
	       textArea.setWrapStyleWord(true);
	       scrollPane.setViewportView(textArea);
	       
	       JLabel lblNewLabel_3 = new JLabel("Tipo Farmaco");
	       GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
	       gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
	       gbc_lblNewLabel_3.gridx = 1;
	       gbc_lblNewLabel_3.gridy = 6;
	       panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
	     
	       
	       JComboBox<String> comboBoxTipoFarmaco = new JComboBox<>();
	       GridBagConstraints gbc_comboBoxTipoFarmaco = new GridBagConstraints();
	       gbc_comboBoxTipoFarmaco.gridwidth = 3;
	       gbc_comboBoxTipoFarmaco.insets = new Insets(0, 0, 5, 5);
	       gbc_comboBoxTipoFarmaco.fill = GridBagConstraints.BOTH;
	       gbc_comboBoxTipoFarmaco.gridx = 2;
	       gbc_comboBoxTipoFarmaco.gridy = 6;
	       panel.add(comboBoxTipoFarmaco, gbc_comboBoxTipoFarmaco);
	       List<String> tipoFarmaci = farmaciController.getAllTypeFarmaco();
	      	for (String tipo : tipoFarmaci ) {
	      		comboBoxTipoFarmaco.addItem(tipo);
	      	}
	      
	      	
	       JLabel lblCopertura = new JLabel("Copertura");
	       lblCopertura.setVisible(false);
	       GridBagConstraints gbc_lblCopertura = new GridBagConstraints();
	       gbc_lblCopertura.anchor = GridBagConstraints.EAST;
	       gbc_lblCopertura.insets = new Insets(0, 0, 5, 5);
	       gbc_lblCopertura.gridx = 1;
	       gbc_lblCopertura.gridy = 7;
	       panel.add(lblCopertura, gbc_lblCopertura);
	       
	       textFieldCopertura = new JTextField();
	       textFieldCopertura.setVisible(false);
	       GridBagConstraints gbc_textFieldCopertura = new GridBagConstraints();
	       gbc_textFieldCopertura.gridwidth = 2;
	       gbc_textFieldCopertura.insets = new Insets(0, 0, 5, 5);
	       gbc_textFieldCopertura.fill = GridBagConstraints.BOTH;
	       gbc_textFieldCopertura.gridx = 2;
	       gbc_textFieldCopertura.gridy = 7;
	       panel.add(textFieldCopertura, gbc_textFieldCopertura);
	       textFieldCopertura.setColumns(10);
	       

	       
	       JButton btnInfo= new JButton("");
	       btnInfo.setVisible(false);
	       btnInfo.setBorderPainted(false);
	       btnInfo.setBorder(null);
	       btnInfo.setMargin(new Insets(0, 0, 0, 0));
	       btnInfo.setContentAreaFilled(false);
	       btnInfo.setIcon(IconImage.INFO.getImageIcon());
	       btnInfo.setToolTipText("es 3 anni");
	       GridBagConstraints gbc_btnInfo = new GridBagConstraints();
	       gbc_btnInfo.fill = GridBagConstraints.VERTICAL;
	       gbc_btnInfo.insets = new Insets(0, 0, 5, 5);
	       gbc_btnInfo.gridx = 4;
	       gbc_btnInfo.gridy = 7;
	       panel.add(btnInfo, gbc_btnInfo);
	       
	       comboBoxTipoFarmaco.addActionListener(new ActionListener() {
	  				public void actionPerformed(ActionEvent e) {
	  					if(comboBoxTipoFarmaco.getSelectedItem().equals("Vaccino") ) {
	  						 lblCopertura.setVisible(true);
	  						    textFieldCopertura.setVisible(true);
	  						    btnInfo.setVisible(true);
	  					} else {
	  						 lblCopertura.setVisible(false);
	  						    textFieldCopertura.setVisible(false);
	  						    btnInfo.setVisible(false);
	  					}
	  					 
	  				}
	  			});
	  	       
	       JPanel panelSave = new JPanel();
	       middlePanel.add(panelSave, BorderLayout.SOUTH);
	       
	       JButton btnSave = new JButton("Save");
	       
	       btnSave.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent e) {
       			if(tipoFarmaci.get(comboBoxTipoFarmaco.getSelectedIndex()).equals("Vaccino")) {
       				if(!textFieldCopertura.getText().equals("") &&  !textNomeFarmaco.getText().equals("") && !textArea.getText().equals("")){
       					Farmaco farmaco = new FarmacoImpl();
           				farmaco.setCodFarmaco(0);
           				farmaco.setDescrizione(textArea.getText());
           				farmaco.setNomeFarmaco(textNomeFarmaco.getText());
           				farmaco.setTipoFarmaco(tipoFarmaci.get(comboBoxTipoFarmaco.getSelectedIndex()));
           				farmaco.setCopertura(textFieldCopertura.getText());
           				
           				farmaciController.addNewFarmaci(farmaco);
           				
           				 frame.getContentPane().add(new FarmaciPage(frame, user));
       				} else {
       					System.out.println("ERRORE - non tutti i campi per vaccino sono completi ");
       				}
       			}else {
       				if(!textNomeFarmaco.getText().equals("") && !textArea.getText().equals("")){
           					Farmaco farmaco = new FarmacoImpl();
               				farmaco.setCodFarmaco(0);
               				farmaco.setDescrizione(textArea.getText());
               				farmaco.setNomeFarmaco(textNomeFarmaco.getText());
               				farmaco.setTipoFarmaco(tipoFarmaci.get(comboBoxTipoFarmaco.getSelectedIndex()));

               				farmaciController.addNewFarmaci(farmaco);
               				
               				 frame.getContentPane().add(new FarmaciPage(frame, user));
       				}else {
       					System.out.println("ERRORE - non tutti i campi per farmaco sono completi ");
       				}
       			}
       			
       		}
	    	   });
	       panelSave.add(btnSave);
	       /* UPPER PANEL AND TITLE */
			upperPanel.setLayout(new BorderLayout(0, 0));
			JLabel titleLabel = new JLabel("Nuovo Farmaco");
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
			//new GroupLayout(this);
			frame.getContentPane().setLayout(groupLayout);
			// this.initCommonView(frame);
	}
}
